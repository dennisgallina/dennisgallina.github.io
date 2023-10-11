import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Archivio {
    File file;
    GestioneDipendenti gestioneDipendenti;
    GestioneUffici gestioneUffici;

    public Archivio(String nomeFileArchivio) throws IOException {
        file = new File(nomeFileArchivio);
        gestioneDipendenti = new GestioneDipendenti();
        gestioneUffici = new GestioneUffici();

        operazioniPreliminari();
    }

    private void operazioniPreliminari() throws IOException {
        if (!file.exists())
            file.createNewFile();

        sincronizzaDati();
    }

    public void sincronizzaDati() throws IOException {
        sincronizzaDipendenti();
        sincronizzaUffici();
    }

    private void sincronizzaDipendenti() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(gestioneDipendenti.ToSerializePHP());
        writer.close();
    }

    private void sincronizzaUffici() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(gestioneUffici.ToSerializePHP());
        writer.close();
    }

    // Metodo per aggiungere un dipendente
    public boolean aggiungiDipendente(Dipendente dipendente) throws IOException {
        if (!gestioneDipendenti.aggiungi(dipendente)) 
            return false;

        sincronizzaDati();
        return true;
    }

    // Metodo per aggiungere un ufficio
    public boolean aggiungiUfficio(Ufficio ufficio) throws IOException {
        if (!gestioneUffici.aggiungi(ufficio)) 
            return false;

        sincronizzaDati();
        return true;
    }

    // Metodo per visualizzare i dati dei dipendenti
    public void visualizzaDipendenti() {
        gestioneDipendenti.visualizzaDipendenti();
    }

    // Metodo per visualizzare i dati degli uffici
    public void visualizzaUffici() {
        gestioneUffici.visualizzaUffici();
    }
}
