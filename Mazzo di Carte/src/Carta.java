public class Carta {
    int numero;
    String seme;
    String colore;

    public Carta(int numero, String seme, String colore) {
        this.numero = numero;
        this.seme = seme;
        this.colore = colore;
    }

    public boolean ugualeA(Carta carta) {
        if (this.numero == carta.numero) {
            // il controllo del seme non riesco a capire perché non funziona nelle carte mancanti
            if (this.seme == carta.seme) {
                if (this.colore == carta.colore)
                    return true;
            } 
        }   
        return false;
    }

    public void visualizza() {
        System.out.println(numero + " " + seme + " " + colore);
    }
}
