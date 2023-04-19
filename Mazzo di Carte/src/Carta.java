public class Carta {
    public int numero;
    public String seme;
    public String colore;
    public Carta next;
    public Carta prev;

    public Carta(int numero, String seme, String colore) {
        this.numero = numero;
        this.seme = seme;
        this.colore = colore;
        this.next = null;
        this.prev = null;
    }

    public boolean ugualeA(Carta carta) {
        if (this.numero == carta.numero) {
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