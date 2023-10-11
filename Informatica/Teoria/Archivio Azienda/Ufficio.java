class Ufficio {
    public int id;
    public String codice;
    public int numeroDiPostazioni; 
    public int idResponsabile;
    public String nome;
    public String piano;
    
    public Ufficio(int id, String codice, int numeroDiPostazioni, int idResponsabile, String nome, String piano) {
        this.id = id;
        this.codice = codice;
        this.numeroDiPostazioni = numeroDiPostazioni;
        this.idResponsabile = idResponsabile;
        this.nome = nome;
        this.piano = piano;
    }

    public String ToString() {
        return "" + id + ";" + codice + ";" + numeroDiPostazioni + ";" + idResponsabile + ";" + nome + ";" + piano;
    }

    public String ToSerializePHP() {
        StringBuilder datiToSerializePHP = new StringBuilder();
    
        // Inizia la serializzazione dell'oggetto Ufficio
        datiToSerializePHP.append("O:7:\"Ufficio\":6:{");
    
        // Aggiungi i campi dell'Ufficio qui...
        appendSerializedString(datiToSerializePHP, "codice", this.codice);
        appendSerializedString(datiToSerializePHP, "nome", this.nome);
        appendSerializedString(datiToSerializePHP, "piano", this.piano);

        appendSerializedInteger(datiToSerializePHP, "id", this.id);
        appendSerializedInteger(datiToSerializePHP, "numeroDiPostazioni", this.numeroDiPostazioni);
        appendSerializedInteger(datiToSerializePHP, "idResponsabile", this.idResponsabile);
    
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