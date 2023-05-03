class Partita {
    constructor(righe) {
        // determina lo stato della partita
        this.esecuzione = false;
        // numero di bandiere posizionabili dal giocatore
        this.bandiere = righe * 2;
    }

    inizia() {
        this.esecuzione = true;
    }
}