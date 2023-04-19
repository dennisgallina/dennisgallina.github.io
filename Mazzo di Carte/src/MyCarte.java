public class MyCarte {
    Carta head;

    MyCarte() {
        this.head = null;
    }

    MyCarte(Carta[] carte) {
        this.head = null;

        for (int i = 0; i < carte.length; i++) 
            push(carte[i]);
    }

    public boolean cerca(Carta cartaDaCercare) {
        // controlla che la lista non sia vuota
        if (head == null) 
            return false;

        Carta carta = head;

        // se la carta è un jolly non la controllo perché ci sono le doppie
        if (cartaDaCercare.seme == "Jolly") 
            return false;
        
        while (carta != null) {
            if (carta.numero == cartaDaCercare.numero && carta.seme == cartaDaCercare.seme && carta.colore == cartaDaCercare.colore) 
                return true;
            carta = carta.next;
        } return false;
    }

    public void push(int numero, String seme, String colore) {
        Carta cartaDaInserire = new Carta(numero, seme, colore);

        // verifica che non sia già presente nel mazzo
        if (cerca(cartaDaInserire))
            return;

        // controlla che la lista non sia vuota
        if (head == null) {
            head = cartaDaInserire;
            return;
        }

        Carta carta = head;

        while (carta.next != null)
            carta = carta.next;
        
        cartaDaInserire.prev = carta;
        carta.next = cartaDaInserire;
    }

    public void push(Carta cartaDaInserire) {
        // controlla che non sia già presente nel mazzo
        if (cerca(cartaDaInserire))
            return;

        cartaDaInserire.next = null;

        // controlla che la lista non sia vuota
        if (head == null) {
            head = cartaDaInserire;
            return;
        }

        Carta carta = head;

        while (carta.next != null)
            carta = carta.next;
        
        cartaDaInserire.prev = carta;
        carta.next = cartaDaInserire;
    }

    // inserisce la carta data una posizione
    public void inserisci(int numero, String seme, String colore, int posizione) {
        // controlla che la posizione non sia fuori lista
        if (posizione > length() - 1)
            return;

        // controlla che la lista non sia vuota
        if (head == null) 
            return;

        Carta cartaDaInserire = new Carta(numero, seme, colore);

        // controlla che non sia già presente nel mazzo
        if (cerca(cartaDaInserire))
            return;

        Carta carta = carta(posizione);

        cartaDaInserire.next = carta;
        cartaDaInserire.prev = carta.prev;
        carta.prev.next = cartaDaInserire; 
        carta.prev = cartaDaInserire;

        return;
    }
    
    public Carta top() {
        // controlla che la lista non sia vuota
        if (head == null) 
            return null;

        // salva la carta prima di rimuoverla così da fare il return
        Carta carta = carta(length() - 1);

        // la rimuove
        rimuovi(length() - 1);

        return carta;
    }

    public Carta pop(int posizione) {
        // controlla che la posizione non sia fuori lista
        if (posizione > length() - 1)
            return null;

        // controlla che la lista non sia vuota
        if (head == null) 
            return null;

        // salva la carta prima di rimuoverla così da fare il return
        Carta carta = carta(posizione);

        // la rimuove
        rimuovi(posizione);

        return carta;
    }

    public void rimuovi(int posizione) {
        // controlla che la posizione non sia fuori lista
        if (posizione > length() - 1)
            return;

        // controlla che la lista non sia vuota
        if (head == null) 
            return;
        
        // se è alla prima posizione
        if (posizione == 0) {
            // ed è l'unico elemento nella lista
            if (head.next == null)
                head = null;
            // e non è l'unico elemento nella lista
            else {
                head = head.next;
                head = head.next;
                head.prev = null;
            }
            return;
        }

        Carta carta = carta(posizione);

        // se è l'ultima posizione
        if (posizione == length() - 1) {
            carta.prev.next = null;
            carta = null;
            return;
        }

        carta.prev.next = carta.next;
        carta.next.prev = carta.prev;
        carta = null;

        return;
    }

    // divide il mazzo per poi mescolarlo
    public void dividi(int posizione) {
        // controlla che la posizione non sia fuori lista
        if (posizione > length() - 1)
            return;

        // divide il mazzo in due mazzi diversi mettendole nello stesso ordine con cui è stato diviso
        // mazzo 1
        MyCarte mazzoX = new MyCarte();
        for (int i = posizione; i > -1; i--) 
            mazzoX.push(pop(i));
        
        //mazzo 2
        MyCarte mazzoY = new MyCarte();
        for (int i = length() - 1; i > -1; i--) 
            mazzoY.push(pop(i));
           
        // se i due mazzi sono alti uguali
        if (mazzoX.length() == mazzoY.length()) {
            for (int i = 0; i < mazzoX.length(); i++) {
                // prende le carte dai mazzi in modo alternato
                push(mazzoX.top());
                push(mazzoY.top());
            }
        } 
        // se il primo mazzo è più alto del secondo
        else if (mazzoX.length() > mazzoY.length()) {
            for (int i = 0; i < mazzoY.length(); i++) {
                // prende le carte dai mazzi in modo alternato
                push(mazzoX.top());
                push(mazzoY.top());
            }
            // quando il mazzo più piccolo è terminato, continua con l'altro
            for (int i = 0; i < mazzoX.length(); i++) 
                push(mazzoX.top());
        }
        // se il secondo mazzo è più alto del primo
        else if (mazzoY.length() > mazzoX.length()) {
            for (int i = 0, j = mazzoX.length(); i < j; i++) {
                // prende le carte dai mazzi in modo alternato
                push(mazzoY.top());
                push(mazzoX.top());
            }
            // quando il mazzo più piccolo è terminato, continua con l'altro
            for (int i = 0, j = mazzoY.length(); i < j; i++) 
                push(mazzoY.top());
        }
    }

    // divide i mazzi in due per poi sovrapporli nel modo inverso
    public void sovrapponi(int posizione) {
        // controlla che la posizione non sia fuori lista
        if (posizione > length() - 1)
            return;

        // divide il mazzo in due mazzi diversi mettendole nello stesso ordine con cui è stato diviso
        MyCarte mazzoX = new MyCarte();
        for (int i = posizione; i > -1 ; i--) 
            mazzoX.push(pop(i));
        
        MyCarte mazzoY = new MyCarte();
        for (int i = length() - 1; i > -1; i--) 
            mazzoY.push(pop(i));
    
        // sovrappone i mazzi
        for (int i = mazzoY.length() - 1; i > -1; i--) 
            push(mazzoY.pop(i));
        
        for (int i = mazzoX.length() - 1; i > -1; i--)  
            push(mazzoX.pop(i));
    }

    public void visualizza() {
        Carta carta = head;

        while (carta != null) {
            carta.visualizza();
            carta = carta.next;
        }
    }
    
    public Carta carta(int posizione) {
        Carta carta = head;

        for (int i = 0; carta != null; i++) {
            if (posizione == i) 
                return carta;

            carta = carta.next;
        } return null;
    } 

    public Carta nextCarta(Carta carta) {
        if (carta == null)
            return null;
        else
            return carta.next;
    }

    public Carta prevCarta(Carta carta) {
        if (carta == null)
            return null;
        else
            return carta.prev;
    }

    public int length() {
        Carta carta = head;

        int i = 0;

        while(carta != null) {
            i++;
            carta = carta.next;
        }
        
        return i;
    }
}