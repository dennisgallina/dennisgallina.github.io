import java.util.ArrayList;
import java.util.List;

public class MyCarte {
    List<Carta> carte;

    public MyCarte() {
        this.carte = new ArrayList<>();
    }

    public MyCarte(Carta[] carte) {
        this.carte = new ArrayList<>();

        for (int i = 0; i < carte.length; i++) 
            push(carte[i]);
    }

    public void push(Carta carta) {
        // cerca che non sia doppia
        if (cerca(carta))
            return;
        
        carte.add(carta);
    }

    public Carta pop(int posizione) {
        Carta carta = carte.get(posizione);
        carte.remove(posizione);

        return carta;
    }

    // rimuove la prima carta in cima
    public Carta top() {
        Carta carta = carte.get(carte.size() - 1);
        carte.remove(carte.size() - 1);

        return carta;
    }

    // data una carta controlla non sia già presente nel mazzo
    public boolean cerca(Carta carta) {
        for (int i = 0; i < carte.size(); i++) {
            if (carta.numero == carte.get(i).numero && carta.seme == carte.get(i).seme && carta.colore == carte.get(i).colore) {
                // dato che è stato richiesto di mettere jolly uguali, se trova i jolly doppi non li conta
                if (carta.seme == "Jolly") 
                    break;
                return true;
            } 
        } return false;
    }

    // inserisce la carta data una posizione
    public void inserisci(int numero, String seme, String colore, int posizione) {
        carte.add(posizione - 1, new Carta(numero, seme, colore));
    }

    // divide il mazzo per poi mescolarlo
    public void dividi(int numero) {
        // divide il mazzo in due mazzi diversi mettendole nello stesso ordine con cui è stato diviso
        // mazzo 1
        MyCarte mazzoX = new MyCarte();
        for (int i = numero - 1; i > -1; i--)  {
            mazzoX.push(pop(i));
        }
        //mazzo 2
        MyCarte mazzoY = new MyCarte();
        for (int i = carte.size() - 1; i > -1; i--) 
            mazzoY.push(pop(i));
           
        // se i due mazzi sono alti uguali
        if (mazzoX.getSize() == mazzoY.getSize()) {
            for (int i = 0; i < mazzoX.getSize(); i++) {
                // prende le carte dai mazzi in modo alternato
                push(mazzoX.top());
                push(mazzoY.top());
            }
        } 
        // se il primo mazzo è più alto del secondo
        else if (mazzoX.getSize() > mazzoY.getSize()) {
            for (int i = 0; i < mazzoY.getSize(); i++) {
                // prende le carte dai mazzi in modo alternato
                push(mazzoX.top());
                push(mazzoY.top());
            }
            // quando il mazzo più piccolo è terminato, continua con l'altro
            for (int i = 0; i < mazzoX.getSize(); i++) 
                push(mazzoX.top());
        }
        // se il secondo mazzo è più alto del primo
        else if (mazzoY.getSize() > mazzoX.getSize()) {
            for (int i = 0, j = mazzoX.getSize(); i < j; i++) {
                // prende le carte dai mazzi in modo alternato
                push(mazzoY.top());
                push(mazzoX.top());
            }
            // quando il mazzo più piccolo è terminato, continua con l'altro
            for (int i = 0, j = mazzoY.getSize(); i < j; i++) 
                push(mazzoY.top());
        }
    }

    // divide i mazzi in due per poi sovrapporli nel modo inverso
    public void sovrapponi(int numero) {
        // divide il mazzo in due mazzi diversi mettendole nello stesso ordine con cui è stato diviso
        MyCarte mazzoX = new MyCarte();
        for (int i = numero - 1; i > -1 ; i--) 
            mazzoX.push(pop(i));
        
        MyCarte mazzoY = new MyCarte();
        for (int i = carte.size() - 1; i > -1; i--) 
            mazzoY.push(pop(i));
    
        // sovrappone i mazzi
        for (int i = mazzoY.getSize() - 1; i > -1; i--) 
            push(mazzoY.pop(i));
        
        for (int i = mazzoX.getSize() - 1; i > -1; i--)  
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
