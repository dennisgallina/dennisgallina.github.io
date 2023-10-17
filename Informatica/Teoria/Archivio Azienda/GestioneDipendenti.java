import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class GestioneDipendenti {
    File file; // File CSV
    HashMap<Integer, Dipendente> dipendenti; // Gestore locale

    public GestioneDipendenti(String fileName) throws IOException {
        file = new File(fileName);
        dipendenti = new HashMap<>();

        operazioniPreliminari(); 
    }

    private void operazioniPreliminari() throws IOException {
        if (!file.exists()) // Se il File non esiste
            file.createNewFile();

        caricaDaCSV(); // Importa i dati dal File CSV
    }

    // Importa i dati dal File CSV
    private void caricaDaCSV() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String linea;
        while((linea = bufferedReader.readLine()) != null) {
            String datiSeparati[] = linea.split(";");
           aggiungiLocalmente(new Dipendente(Integer.parseInt(datiSeparati[0]), datiSeparati[1], datiSeparati[2], Integer.parseInt(datiSeparati[3]), datiSeparati[4], datiSeparati[5], datiSeparati[6]));
        } 
        
        bufferedReader.close();
    }

    // Aggiunge un dipendente e lo salva su CSV
    public boolean aggiungi(Dipendente dipendenteDaAggiungere) throws IOException {
        if (!aggiungiLocalmente(dipendenteDaAggiungere)) // Se non riesce ad aggiungerlo
            return false;

        salvaSuCSV(dipendenteDaAggiungere);

        return true;
    }

    // Aggiunge un dipendente localmente
    private boolean aggiungiLocalmente(Dipendente dipendenteDaAggiungere) {
        if (verificaSeEsiste(dipendenteDaAggiungere.id)) // Se già esiste
            return false;
        
        dipendenti.put(dipendenteDaAggiungere.id, dipendenteDaAggiungere);

        return true;
    }

    // Salva un dipendente su CSV
    private void salvaSuCSV(Dipendente dipendenteDaAggiungere) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        writer.write(dipendenteDaAggiungere.toCSV());
        writer.newLine();
        writer.close();
    }

    // Verifica l'esistenza di un dipendente
    public boolean verificaSeEsiste(int id) {
        if (dipendenti.containsKey(id)) // Se trova un dipendente con quell'ID
            return true;
        else
            return false;
    }

    public void visualizzaDipendenti() {
        for (Dipendente dipendente : dipendenti.values()) {
            System.out.println(dipendente.toString());
        }
    }

    public HashMap<Integer, Dipendente> getDipendentiDaUfficio(int idUfficio) {
        HashMap<Integer, Dipendente> dipendentiUfficioRichiesto = new HashMap<>();

        for (Dipendente dipendente : dipendenti.values()) {
            if (dipendente.idUfficio == idUfficio)
                dipendentiUfficioRichiesto.put(dipendente.id, dipendente);
        }
        return dipendentiUfficioRichiesto;
    }

    // Riporta i dati in una stringa col formato Serialize PHP
    public String toSerializePHP() {
         StringBuilder datiToSerializePHP = new StringBuilder();

        // Inizia la serializzazione dell'array
        datiToSerializePHP.append("a:");
        datiToSerializePHP.append(dipendenti.size());
        datiToSerializePHP.append(":{");

        for (Map.Entry<Integer, Dipendente> dipendente : dipendenti.entrySet()) {
            // Serializza la chiave dell'array (ID del dipendente)
            datiToSerializePHP.append("i:");
            datiToSerializePHP.append(dipendente.getKey());
            datiToSerializePHP.append(";");

            // Utilizza il metodo ToSerializePHP() del Dipendente per serializzare l'oggetto
            datiToSerializePHP.append(dipendente.getValue().toSerializePHP());
        }

        // Termina la serializzazione dell'array
        datiToSerializePHP.append("}");

        return datiToSerializePHP.toString();
    }
}