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
        if (numero == carta.numero && seme == carta.seme && colore == carta.colore) 
            return true;
        return false;
    }

    public void visualizza() {
        System.out.println(numero + " " + seme + " " + colore);
    }
}
