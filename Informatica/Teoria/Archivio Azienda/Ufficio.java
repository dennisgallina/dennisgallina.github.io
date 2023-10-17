import java.io.IOException;
import java.util.HashMap;

class Ufficio {
    public int id;
    public String codice;
    public int numeroDiPostazioni; 
    public int idResponsabile;
    public String nome;
    public String piano;
    public HashMap<Integer, Dipendente> dipendenti;
    
    public Ufficio(int id, String codice, int numeroDiPostazioni, int idResponsabile, String nome, String piano) throws IOException {
        this.id = id;
        this.codice = codice;
        this.numeroDiPostazioni = numeroDiPostazioni;
        this.idResponsabile = idResponsabile;
        this.nome = nome;
        this.piano = piano;
        this.dipendenti = new HashMap<>();
    }

    public void visualizza() {
        System.out.println(id + " " + codice + " " + numeroDiPostazioni + " " + idResponsabile + " " + nome + " " + piano);

        for (Dipendente dipendente : dipendenti.values()) 
            dipendente.visualizza();
    }

    // Riporta i dati in una stringa col formato Serialize CSV
    public String toCSV() {
        return "" + id + ";" + codice + ";" + numeroDiPostazioni + ";" + idResponsabile + ";" + nome + ";" + piano;
    }

    // Riporta i dati in una stringa col formato Serialize PHP
    public String toSerializePHP() {
        StringBuilder datiToSerializePHP = new StringBuilder();
    
        // Inizia la serializzazione dell'oggetto Ufficio
        datiToSerializePHP.append("O:7:\"Ufficio\":6:{");
    
        // Aggiungi i campi dell'Ufficio
        appendSerializedInteger(datiToSerializePHP, "id", this.id);
        appendSerializedString(datiToSerializePHP, "codice", this.codice);
        appendSerializedInteger(datiToSerializePHP, "numeroDiPostazioni", this.numeroDiPostazioni);
        appendSerializedInteger(datiToSerializePHP, "idResponsabile", this.idResponsabile);
        appendSerializedString(datiToSerializePHP, "nome", this.nome);
        appendSerializedString(datiToSerializePHP, "piano", this.piano);
        for (Dipendente dipendente : dipendenti.values()) // Aggiunge tutti i suoi dipendenti
            datiToSerializePHP.append(dipendente.toSerializePHP());
    
        // Termina la serializzazione dell'oggetto Ufficio
        datiToSerializePHP.append("}");
    
        return datiToSerializePHP.toString();
    }
    
    private void appendSerializedString(StringBuilder datiToSerializePHP, String key, String value) {
        datiToSerializePHP.append("s:");
        datiToSerializePHP.append(key.length());
        datiToSerializePHP.append(":\"");
        datiToSerializePHP.append(key);
        datiToSerializePHP.append("\";");
        datiToSerializePHP.append("s:");
        datiToSerializePHP.append(value.length());
        datiToSerializePHP.append(":\"");
        datiToSerializePHP.append(value);
        datiToSerializePHP.append("\";");
    }
    
    private void appendSerializedInteger(StringBuilder datiToSerializePHP, String key, int value) {
        datiToSerializePHP.append("s:");
        datiToSerializePHP.append(key.length());
        datiToSerializePHP.append(":\"");
        datiToSerializePHP.append(key);
        datiToSerializePHP.append("\";");
        datiToSerializePHP.append("i:");
        datiToSerializePHP.append(value);
        datiToSerializePHP.append(";");
    }
}