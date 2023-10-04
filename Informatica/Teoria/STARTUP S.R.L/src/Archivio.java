import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivio {
    public ArrayList<Ufficio> uffici;
    public ArrayList<Dipendente> dipendenti;
    File[] fileArchivio;

    public Archivio() throws IOException {
        this.uffici = new ArrayList<>();
        this.dipendenti = new ArrayList<>();
        
        String path = "Archivio/";
        File folder = new File(path);
        this.fileArchivio = folder.listFiles();

        caricaDaFile();
    }

    public void caricaDaFile() throws IOException {
        for (int i = 0; i < this.fileArchivio.length; i++) {
            if (this.fileArchivio[i].isFile()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileArchivio[i]));

                String line;
                int count = 0;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] parametri = line.split(";");
                    if (count == 0) 
                        uffici.add(new Ufficio(parametri[1], Integer.parseInt(parametri[2]), parametri[3], Integer.parseInt(parametri[4]), parametri[5]));
                    else 
                        dipendenti.add(new Dipendente(parametri[1], parametri[2], parametri[3], parametri[4], parametri[5], Integer.parseInt(parametri[6]), uffici.get(i)));
                    count++;
                }
                bufferedReader.close();
            }
        }
    }

    public void inserisciUfficio(String nome, Integer piano, String siglaLocale, Integer numeroPostazioni, String responsabile) throws IOException {
        for (int i = 0; i < uffici.size(); i++) {
            if (uffici.get(i).siglaLocale.equals(siglaLocale))
                return;
        }
    
        Ufficio ufficio = new Ufficio(nome, piano, siglaLocale, numeroPostazioni, responsabile);
        uffici.add(ufficio);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ufficio.file, true));
    
        bufferedWriter.append(ufficio.getInformazioni());
        bufferedWriter.newLine();
    
        bufferedWriter.close();
    
        System.out.println("Ufficio inserito con successo.");
    }

    public void inserisciDipendente(String nome, String cognome, String residenza, String dataNascita, String dataAssunzione, Integer oreSettimanali, String siglaLocaleUfficio) throws IOException {
        for (int i = 0; i < dipendenti.size(); i++) {
            if (dipendenti.get(i).nome.equals(nome) && dipendenti.get(i).cognome.equals(cognome))
                return;
        }
    
        Dipendente dipendente = null;
    
        for (int i = 0; i < uffici.size(); i++) {
            if (uffici.get(i).siglaLocale.equals(siglaLocaleUfficio)) {
                dipendente = new Dipendente(nome, cognome, residenza, dataNascita, dataAssunzione, oreSettimanali, uffici.get(i));
                break;
            } else if (i == uffici.size() - 1) {
                System.out.println("Error: Ufficio inesistente.");
                return;
            }
        }
    
        dipendenti.add(dipendente);
        dipendente.ufficioAppartenente.dipendenti.add(dipendente);
    
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dipendente.ufficioAppartenente.file, true));
    
        bufferedWriter.append(dipendente.getInformazioni());
        bufferedWriter.newLine();
    
        bufferedWriter.close();
    
        System.out.println("Dipendente inserito con successo.");
    }

    public void visualizza() {
        for (int i = 0; i < uffici.size(); i++) {
            System.out.println(uffici.get(i).getInformazioni());

            for (int j = 0; j < uffici.get(i).dipendenti.size(); j++) 
                System.out.println(uffici.get(i).dipendenti.get(j).getInformazioni());
        }
    }
}
