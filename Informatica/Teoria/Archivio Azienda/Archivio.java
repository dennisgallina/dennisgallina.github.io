import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Archivio {
    File file; // File TXT scritto in formato Serialize PHP
    GestioneUffici gestioneUffici; 

    public Archivio(String nomeFileArchivio) throws IOException {
        file = new File(nomeFileArchivio);
        gestioneUffici = new GestioneUffici("Uffici.csv");

        operazioniPreliminari(); 
    }

    private void operazioniPreliminari() throws IOException {
        if (!file.exists())
            file.createNewFile();

        salvaComeSerializePHP(); // Scrive i dati in formato Serialize PHP 
    }

    // Scrive i dati in formato Serialize PHP 
    private void salvaComeSerializePHP() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(gestioneUffici.toSerializePHP()); // Scrive i dati Serializzati
        writer.newLine();
        writer.close();
    }

    // Aggiunge un dipendente nel suo ufficio di appartenenza
    public boolean aggiungiDipendente(Dipendente dipendente) throws IOException {
        if (!gestioneUffici.aggiungiDipendente(dipendente)) // Se non riesce ad aggiungerlo
            return false;

        salvaComeSerializePHP(); // Scrive i dati in formato Serialize PHP
        return true;
    }

    // Aggiungere un ufficio
    public boolean aggiungiUfficio(Ufficio ufficio) throws IOException {
        if (!gestioneUffici.aggiungi(ufficio)) // Se non riesce ad aggiungerlo
            return false;

        salvaComeSerializePHP(); // Scrive i dati in formato Serialize PHP
        return true;
    }

    // Visualizza i dati degli uffici
    public void visualizzaUffici() {
        gestioneUffici.visualizzaUffici();
    }
}
