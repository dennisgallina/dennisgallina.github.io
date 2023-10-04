import java.io.File;
import java.util.ArrayList;

public class Ufficio {
    public String id;
    public Integer piano;
    public Integer numeroPostazioni;
    public String siglaLocale;
    public String nome;
    public String responsabile;
    public ArrayList<Dipendente> dipendenti;
    public File file;

    public Ufficio() {
        this.id = "";
        this.nome = "";
        this.piano = 0;
        this.siglaLocale = "";
        this.numeroPostazioni = 0;
        this.responsabile = "";
        this.dipendenti = new ArrayList<>();
        this.file = null;
    }

    public Ufficio(String nome, Integer piano, String siglaLocale, Integer numeroPostazioni, String responsabile) {
        this.nome = nome;
        this.piano = piano;
        this.siglaLocale = siglaLocale;
        this.numeroPostazioni = numeroPostazioni;
        this.responsabile = responsabile;
        this.id = this.nome + " " + this.piano + " " + this.siglaLocale;
        this.dipendenti = new ArrayList<>();
        this.file = new File("Archivio\\" + this.id + ".csv");
    } 

    public String getInformazioni() {
        return this.id + ";" + this.nome + ";" + this.piano + ";" + this.siglaLocale + ";" + this.numeroPostazioni + ";" + this.responsabile;
    }
}
