import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class App {
    static SerializerToXML serializer; // Oggetto per serializzare
    static DatagramSocket socket; // Socket del Server
    static RichiestaDelClient richiestaDelClient; // Gestione della Richiesta del Client in corso
    static MessaggioAlClient rispostaAlClient; // Gestione della Risposta al Client in corso
    static Cassa cassa; // Cassa da cui ascolta il Server

    public static void main(String[] args) throws Exception {
        serializer = new SerializerToXML();
        socket = new DatagramSocket(666);
        cassa = new Cassa();

        // In attesa di richieste
        while (true) {
            if (riceviDalClient()) // Riceve il pacchetto dal Client e lo inserisce in un oggetto RichiestaClient
                gestioneRichiesta(); // Gestisce la richiesta ricevuta dal Client e si occupa di inviare al Client le risposte
        }
    }

    // Riceve un pacchetto dal Client e lo inserisce in un oggetto RichiestaClient
    private static boolean riceviDalClient() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        byte[] buff = new byte[1500];
        DatagramPacket packetDalClient = new DatagramPacket(buff, buff.length);
        socket.receive(packetDalClient); // Ricezione di un pacchetto UDP

        richiestaDelClient = new RichiestaDelClient(packetDalClient);

        return true;
    }

    // Gestisce la richiesta ricevuta dal Client e si occupa di inviare al Client le risposte
    private static void gestioneRichiesta() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        System.out.println(richiestaDelClient.ToShowConsole()); // Mostra in console la richiesta ricevuta dal client
    
        // In base al comando ricevuto
        switch(richiestaDelClient.comando) {
            case "start": // Richiede i dati necessari all'avvio della cassa
                inviaAlClient(serializer.toXML(cassa.getDocument()).getBytes());
                rispostaAlClient = new MessaggioAlClient(200, "Sincronizzazione riuscita.");
                break;
            case "open": // Richiede l'avvio di un ordine da parte di un commesso
                if (cassa.apriOrdineAlCassiere(richiestaDelClient.idCassiere)) {
                    Scontrino scontrinoCassiere = cassa.getScontrinoDelCassiere(richiestaDelClient.idCassiere);
                    inviaAlClient(serializer.toXML((scontrinoCassiere.getDocument())).getBytes());
                    rispostaAlClient = new MessaggioAlClient(200, "Apertura ordine riuscita."); // Messaggo di conferma
                } else
                    rispostaAlClient = new MessaggioAlClient(400, "Apertura ordine non riuscita."); // Messaggo di conferma
                break;
            case "insert": // Richiede l'aggiunta di un prodotto da parte di un commesso
                if (cassa.aggiungiProdottoAlCassiere(richiestaDelClient.idCassiere, richiestaDelClient.idProdotto, richiestaDelClient.quantitàProdotto)) {
                    Scontrino scontrinoCassiere = cassa.getScontrinoDelCassiere(richiestaDelClient.idCassiere);
                    inviaAlClient(serializer.toXML((scontrinoCassiere.getDocument())).getBytes());
                    rispostaAlClient = new MessaggioAlClient(200, "Prodotto aggiunto con succcesso."); // Messaggo di conferma
                } else
                    rispostaAlClient = new MessaggioAlClient(400, "Aggiunta del prodotto non riuscita."); // Messaggo di conferma
                break;
            case "remove": // Richiede la rimozione di un prodotto da parte di un commesso
                if (cassa.rimuoviProdottoAlCassiere(richiestaDelClient.idCassiere, richiestaDelClient.idProdotto)) {
                    Scontrino scontrinoCassiere = cassa.getScontrinoDelCassiere(richiestaDelClient.idCassiere);
                    inviaAlClient(serializer.toXML((scontrinoCassiere.getDocument())).getBytes());   
                    rispostaAlClient = new MessaggioAlClient(200, "Prodotto rimosso con succcesso."); // Messaggo di conferma
                } else
                    rispostaAlClient = new MessaggioAlClient(400, "Rimozione del prodotto non riuscita."); // Messaggo di conferma
                break;
            case "close": // Richiede la chusura di un ordine da parte di un commesso
                if (cassa.chiudiOrdineAlCassiere(richiestaDelClient.idCassiere)) {
                    Scontrino scontrinoCassiere = cassa.getScontrinoDelCassiere(richiestaDelClient.idCassiere);
                    inviaAlClient(serializer.toXML((scontrinoCassiere.getDocument())).getBytes());
                    rispostaAlClient = new MessaggioAlClient(200, "Chiusura ordine riuscita."); // Messaggo di conferma
                } else
                    rispostaAlClient = new MessaggioAlClient(400, "Chiusura ordine non riuscita."); // Messaggo di conferma
                break;
            default:
                rispostaAlClient = new MessaggioAlClient(500, "Comando non riconosciuto."); // Messaggo di conferma
                break;
        }

        // Invia il Messaggio di conferma
    } 

    // Invia al Client un pacchetto
    public static void inviaAlClient(byte[] datiBytes) throws IOException {
        DatagramPacket rispostaAlClient = new DatagramPacket(datiBytes, datiBytes.length);
        rispostaAlClient.setAddress(richiestaDelClient.ipClient);
        rispostaAlClient.setPort(richiestaDelClient.portaClient);

        socket.send(rispostaAlClient); // Invia un pacchetto UDP
    }
}