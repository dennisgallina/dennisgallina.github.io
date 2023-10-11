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

    public GestioneDipendenti() throws IOException {
        file = new File("Dipendenti.csv");
        dipendenti = new HashMap<>();

        operazioniPreliminari(); // Operazioni alla creazione di una gestione
    }

    private void operazioniPreliminari() throws IOException {
        if (!file.exists())
            file.createNewFile();

        caricaDatiDaCSV(); // Importa i dati dal CSV
    }

    public void caricaDatiDaCSV() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String linea;
        while((linea = bufferedReader.readLine()) != null) {
            String datiSeparati[] = linea.split(";");
            aggiungi(new Dipendente(Integer.parseInt(datiSeparati[0]), datiSeparati[1], datiSeparati[2], Integer.parseInt(datiSeparati[3]), datiSeparati[4], datiSeparati[5], datiSeparati[6]));
        } 
        
        bufferedReader.close();
    }

    public boolean aggiungi(Dipendente dipendente) throws IOException {
        if (verificaSeEsiste(dipendente.id))
            return false;
        
        dipendenti.put(dipendente.id, dipendente);

        aggiungiSuFileCSV(dipendente);

        return true;
    }

    // Verifica l'esistenza di un dipendente
    public boolean verificaSeEsiste(int id) {
        if (dipendenti.containsKey(id))
            return true;
        else
            return false;
    }

    public boolean aggiungiSuFileCSV(Dipendente dipendente) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        writer.write(dipendente.ToString()); 
        writer.newLine();
        writer.close();
        
        return true;
    }

    // Riporta i dati in una stringa col formato Serialize PHP
    public String ToSerializePHP() {
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
            datiToSerializePHP.append(dipendente.getValue().ToSerializePHP());
        }

        // Termina la serializzazione dell'array
        datiToSerializePHP.append("}");

        return datiToSerializePHP.toString();
    }

    public void visualizzaDipendenti() {
        for (Dipendente dipendente : dipendenti.values()) {
            System.out.println(dipendente.toString());
        }
    }
}