import java.net.DatagramPacket;
import java.net.InetAddress;

public class RichiestaDelClient {
    public InetAddress ipClient; // Indirizzo IP
    public Integer portaClient; // Porta di comunicazione
    public String comando; // Azione richiesta
    public Integer idCassiere; // Cassiere che ha inviato la richiesta
    public Integer idProdotto; // Prodotto su cui vuole agire
    public Integer quantitàProdotto; // Quantità del prodotto su cui vuole agire

    public RichiestaDelClient(DatagramPacket packetDalClient) {
        // Converte in String la richiesta e la divide per i suoi parametri
        String richiestaRicevuta = new String(packetDalClient.getData(), 0, packetDalClient.getLength());
        String[] parametriRichiesta = richiestaRicevuta.split(";");

        // Memorizza le informazioni del Client
        this.ipClient = packetDalClient.getAddress();
        this.portaClient = packetDalClient.getPort();

        this.comando = parametriRichiesta[0];

        // Se la richiesta è di aprire o chiudere un ordine
        if (parametriRichiesta.length > 1)
            this.idCassiere = Integer.parseInt(parametriRichiesta[1]);
        
        // Se la richiesta è di agire su un prodotto
        if (parametriRichiesta.length > 2) 
            this.idProdotto = Integer.parseInt(parametriRichiesta[2]);
        else 
            this.idProdotto = null;
    
        // Se la richiesta è di aggiungere un prodotto
        if (parametriRichiesta.length > 3) 
            this.quantitàProdotto = Integer.parseInt(parametriRichiesta[3]);
        else
            this.quantitàProdotto = null;
    }

    public String ToShowConsole() {
        return "Richiesta ricevuta dal Client " + ipClient + " " + portaClient + ": " + 
        comando + ";" + idCassiere + ";" + idProdotto + ";" + quantitàProdotto;
    }
}
