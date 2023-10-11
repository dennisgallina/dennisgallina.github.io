class Dipendente {
    public int id;
    public String dataDiNascita;
    public String dataDiAssunzione; 
    public int idUfficio;
    public String cognome;
    public String nome;
    public String residenza;

    public Dipendente(int id, String dataDiNascita, String dataDiAssunzione, int idUfficio, String cognome, String nome, String residenza) {
        this.id = id;
        this.dataDiNascita = dataDiNascita;
        this.dataDiAssunzione = dataDiAssunzione;
        this.idUfficio = idUfficio;
        this.cognome = cognome;
        this.nome = nome;
        this.residenza = residenza;
    }
    
    public String ToString() {
        return "" + id + ";" + dataDiNascita + ";" +  dataDiAssunzione + ";" + idUfficio + ";" + cognome + ";" + nome + ";" + residenza;
    }

    public String ToSerializePHP() {
    StringBuilder datiToSerializePHP = new StringBuilder();

        // Inizia la serializzazione dell'oggetto Dipendente
        datiToSerializePHP.append("O:9:\"Dipendente\":7:{");

        // Aggiungi i campi del Dipendente qui...
        // Ad esempio, per un campo stringa "nome":
        appendSerializedString(datiToSerializePHP, "nome", this.nome);
        appendSerializedString(datiToSerializePHP, "dataDiNascita", this.dataDiNascita);
        appendSerializedString(datiToSerializePHP, "dataDiAssunzione", this.dataDiAssunzione);
        appendSerializedString(datiToSerializePHP, "cognome", this.cognome);
        appendSerializedString(datiToSerializePHP, "residenza", this.residenza);

        appendSerializedInteger(datiToSerializePHP, "id", this.id);
        appendSerializedInteger(datiToSerializePHP, "idUfficio", this.idUfficio);

        // Termina la serializzazione dell'oggetto Dipendente
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