import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GestioneUffici {
    File file; // File CSV
    HashMap<Integer, Ufficio> uffici; // Gestore locale
    GestioneDipendenti gestioneDipendenti; // Gestore dei dipendenti negli uffici

    public GestioneUffici(String fileName) throws IOException {
        file = new File(fileName);
        uffici = new HashMap<>();
        gestioneDipendenti = new GestioneDipendenti("Dipendenti.csv");

        operazioniPreliminari(); 
    }

    private void operazioniPreliminari() throws IOException {
        if (!file.exists())
            file.createNewFile();

        caricaDaCSV(); // Importa i dati dal File CSV
        caricaDipendentiNegliUffici(); // Aggiunge i dipendenti nei rispettivi uffici
    }

    // Importa i dati dal File CSV
    public void caricaDaCSV() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String linea;
        while((linea = bufferedReader.readLine()) != null) {
            String datiSeparati[] = linea.split(";");
            aggiungiLocalmente(new Ufficio(Integer.parseInt(datiSeparati[0]), datiSeparati[1], Integer.parseInt(datiSeparati[2]), Integer.parseInt(datiSeparati[3]), datiSeparati[4], datiSeparati[5]));
        } 

        bufferedReader.close();
    }

    // Aggiunge i dipendenti nei rispettivi uffici
    private void caricaDipendentiNegliUffici() {
        for (Ufficio ufficio : uffici.values()) 
            ufficio.dipendenti = gestioneDipendenti.getDipendentiDaUfficio(ufficio.id); 
    }

    // Aggiunge un ufficio e lo salva su CSV
    public boolean aggiungi(Ufficio ufficio) throws IOException {
        if (!aggiungiLocalmente(ufficio)) // Se non riesce ad aggiungerlo
            return false;

        salvaSuCSV(ufficio);

        return true;
    }

    // Aggiunge un ufficio localmente
    private boolean aggiungiLocalmente(Ufficio ufficioDaAggiungere) {
        if (verificaSeEsiste(ufficioDaAggiungere.id))
            return false;
        
        uffici.put(ufficioDaAggiungere.id, ufficioDaAggiungere);

        return true;
    }

    // Salva un ufficio su CSV
    private void salvaSuCSV(Ufficio ufficioDaAggiungere) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        writer.write(ufficioDaAggiungere.toCSV());
        writer.newLine();
        writer.close();
    }

    // Verifica l'esistenza di un ufficio
    public boolean verificaSeEsiste(int id) {
        if (uffici.containsKey(id))
            return true;
        else
            return false;
    }

    public void visualizzaUffici() {
        for (Ufficio ufficio : uffici.values()) {
            ufficio.visualizza();
            System.out.println();
        } 
    }

    public boolean aggiungiDipendente(Dipendente dipendenteDaAggiungere) throws IOException {
        if (!uffici.containsKey(dipendenteDaAggiungere.idUfficio))
            return false;

        Ufficio ufficioRichiesto = uffici.get(dipendenteDaAggiungere.idUfficio);
        
        if (ufficioRichiesto.dipendenti.containsKey(dipendenteDaAggiungere.id))
            return false;

        ufficioRichiesto.dipendenti.put(dipendenteDaAggiungere.id, dipendenteDaAggiungere);
        uffici.put(ufficioRichiesto.id, ufficioRichiesto);

        gestioneDipendenti.aggiungi(dipendenteDaAggiungere);

        return true;
    }

    // Riporta i dati in una stringa col formato Serialize PHP
    public String toSerializePHP() {
         StringBuilder datiToSerializePHP = new StringBuilder();

        // Inizia la serializzazione dell'array
        datiToSerializePHP.append("a:");
        datiToSerializePHP.append(uffici.size());
        datiToSerializePHP.append(":{");

        for (Ufficio ufficio : uffici.values()) 
            datiToSerializePHP.append(ufficio.toSerializePHP()); // Utilizza il metodo ToSerializePHP() del Dipendente per serializzare l'oggetto

        // Termina la serializzazione dell'array
        datiToSerializePHP.append("}");

        return datiToSerializePHP.toString();
    }
}
