class Partita {
    constructor() {
        this.stato = "non iniziata"
        // determina se la partita è in esecuzione
        this.esecuzione = false;
    }

    // cambia lo sttao della partita
    cambiaStato() {
        if (this.stato == "terminata")
            return;

        // se non è iniziata, inizia la partita
        if (this.stato == "non iniziata") {
            this.stato = "iniziata";
            // manda in esecuzione la partita
            this.esecuzione = true;
            document.getElementById("bottoneStato").value = "PAUSE";
            // non permrtte più la modifica del numero di celle
            document.getElementById("numeroCelle").style.display = "none";
            return;
        }

        // se è in esecuzione
        if (this.esecuzione == true) {
            // la mette in pausa
            document.getElementById("bottoneStato").value = "PLAY";
        } 
        // se è in pausa
        else if (this.esecuzione == false) {
            // riprende la partita
            document.getElementById("bottoneStato").value = "PAUSE";
        }

        this.esecuzione = !this.esecuzione;
    }

    termina() {
        this.stato = "terminata";
        this.esecuzione = false;
        document.getElementById("bottoneStato").value = "FINISH";
    }
}