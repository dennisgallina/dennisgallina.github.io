import java.util.ArrayList;
import java.util.List;

public class MyCarte {
    List<Carta> carte;

    public MyCarte() {
        this.carte = new ArrayList<Carta>();
    }

    public MyCarte(Carta[] carte) {
        for (int i = 0; i < carte.length; i++) 
            push(carte[i]);
    }

    public void push(Carta carta) {
        if (cerca(carta))
            return;
        
        carte.add(carta);
    }

    public Carta pop(int posizione) {
        Carta carta = carte.get(posizione);
        carte.remove(posizione);

        return carta;
    }

    public Carta top() {
        Carta carta = carte.get(carte.size());
        carte.remove(carte.size());

        return carta;
    }

    public boolean cerca(Carta carta) {
        for (int i = 0; i < carte.size(); i++) {
            if (carta.numero == carte.get(i).numero && carta.seme == carte.get(i).seme && carta.colore == carte.get(i).colore) 
                return true;
        } return false;
    }

    public void inserisci(int numero, String seme, String colore, int posizione) {
        carte.add(posizione - 1, new Carta(numero, seme, colore));
    }

    public void dividi(int numero) {
        MyCarte mazzoX = new MyCarte();
        for (int i = numero - 1; i > -1 ; i--) 
            mazzoX.push(pop(i));
        
        MyCarte mazzoY = new MyCarte();
        for (int i = 0; i > carte.size(); i++) 
            mazzoY.push(pop(i));
        
        if (mazzoX.getSize() == mazzoY.getSize()) {
            for (int i = 0; i < mazzoX.getSize(); i++) {
                push(mazzoX.top());
                push(mazzoY.top());
            }
        } else if (mazzoX.getSize() > mazzoY.getSize()) {
            for (int i = 0; i < mazzoY.getSize(); i++) {
                push(mazzoX.top());
                push(mazzoY.top());
            }

            for (int i = 0; i < mazzoX.getSize(); i++) 
                push(mazzoX.top());
        } else if (mazzoY.getSize() > mazzoX.getSize()) {
            for (int i = 0; i < mazzoX.getSize(); i++) {
                push(mazzoY.top());
                push(mazzoX.top());
            }

            for (int i = 0; i < mazzoX.getSize(); i++) 
                push(mazzoY.top());
        }
    }

    public void sovrapponi(int numero) {
        MyCarte mazzoX = new MyCarte();
        for (int i = numero - 1; i > -1 ; i--) 
            mazzoX.push(pop(i));
        
        MyCarte mazzoY = new MyCarte();
        for (int i = 0; i < carte.size(); i++) 
            mazzoY.push(pop(i));
    
        for (int i = 0; i < mazzoY.getSize(); i++) 
            push(mazzoY.pop(i));
        
        for (int i = 0; i < mazzoX.getSize(); i++) 
            push(mazzoX.pop(i));
    }

    public void visualizza() {
        for (int i = 0; i < carte.size(); i++) 
            carte.get(i).visualizza();
    }

    public int getSize() {
        return carte.size();
    }
}
