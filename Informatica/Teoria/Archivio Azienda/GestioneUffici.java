import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestioneUffici {
    File file;
    HashMap<Integer, Ufficio> uffici;

    public GestioneUffici() throws IOException {
        file = new File("Uffici.csv");
        uffici = new HashMap<>();

        operazioniPreliminari();
    }

    private void operazioniPreliminari() throws IOException {
        if (!file.exists())
            file.createNewFile();

        caricaDatiDaCSV();
    }

    public void caricaDatiDaCSV() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String linea;
        while((linea = bufferedReader.readLine()) != null) {
            String datiSeparati[] = linea.split(";");
            aggiungi(new Ufficio(Integer.parseInt(datiSeparati[0]), datiSeparati[1], Integer.parseInt(datiSeparati[2]), Integer.parseInt(datiSeparati[3]), datiSeparati[4], datiSeparati[5]));
        } 
        
        bufferedReader.close();
    }

    public boolean verificaSeEsiste(int id) {
        if (uffici.containsKey(id))
            return true;
        else
            return false;
    }

    public boolean aggiungi(Ufficio ufficio) throws IOException {
        if (verificaSeEsiste(ufficio.id))
            return false;
        
        uffici.put(ufficio.id, ufficio);

        salvaSuFileCSV(ufficio);
        
        return true;
    }

    public boolean salvaSuFileCSV(Ufficio ufficio) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        writer.write(ufficio.ToString()); 
        writer.newLine();
        writer.close();
        
        return true;
    }

    public String ToSerializePHP() {
         StringBuilder datiToSerializePHP = new StringBuilder();

        // Inizia la serializzazione dell'array
        datiToSerializePHP.append("a:");
        datiToSerializePHP.append(uffici.size());
        datiToSerializePHP.append(":{");

        for (Map.Entry<Integer, Ufficio> ufficio : uffici.entrySet()) {
            // Serializza la chiave dell'array (ID del dipendente)
            datiToSerializePHP.append("i:");
            datiToSerializePHP.append(ufficio.getKey());
            datiToSerializePHP.append(";");

            // Utilizza il metodo ToSerializePHP() del Dipendente per serializzare l'oggetto
            datiToSerializePHP.append(ufficio.getValue().ToSerializePHP());
        }

        // Termina la serializzazione dell'array
        datiToSerializePHP.append("}");

        return datiToSerializePHP.toString();
    }

    public void visualizzaUffici() {
        for (Ufficio ufficio : uffici.values()) {
            System.out.println(ufficio.toString());
        }
    }
}
