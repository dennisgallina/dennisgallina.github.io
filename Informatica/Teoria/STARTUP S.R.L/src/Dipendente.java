public class Dipendente {
    public String id;
    public Integer oreSettimanali;
    public String dataNascita;
    public String dataAssunzione;
    public String nome;
    public String cognome;
    public String residenza;
    public Ufficio ufficioAppartenente;

    public Dipendente() {
        this.id = "";
        this.nome = "";
        this.cognome = "";
        this.residenza = "";
        this.dataNascita = "";
        this.dataAssunzione = "";
        this.oreSettimanali = 0;
        this.ufficioAppartenente = new Ufficio();
    }

    public Dipendente(String nome, String cognome, String residenza, String dataNascita, 
    String dataAssunzione, Integer oreSettimanali, Ufficio ufficioAppartenente) {
        this.nome = nome;
        this.cognome = cognome;
        this.residenza = residenza;
        this.dataNascita = dataNascita;
        this.dataAssunzione = dataAssunzione;
        this.oreSettimanali = oreSettimanali;
        this.ufficioAppartenente = ufficioAppartenente;
        this.id = this.nome + this.cognome + this.ufficioAppartenente.siglaLocale;
    }

    public String getInformazioni() {
        return this.id + ";" + this.nome + ";" + this.cognome + ";" + this.residenza + ";" + this.dataNascita 
            + ";" + this.dataAssunzione + ";" + this.oreSettimanali + ";" + this.ufficioAppartenente.siglaLocale;
    }    
}
