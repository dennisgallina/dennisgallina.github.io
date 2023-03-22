class Calcolatrice {
    constructor() {
      this.numero = 0;
      this.parziale = 0;
      this.segno = 0;
      this.risultato = 0;
    }

    inserisci(valore) {
        if (segno == 0)
            this.parziale += valore;
        else
            this.numero += valore;
    }

    operazione(valore) {
        this.segno = valore;
    }

    calcola() {
        if (segno == "+") 
            risultato = parziale + numero;
        else if ( segno == "-") 
            risultato = parziale - numero;
        else if ( segno == "x") 
            risultato = parziale * numero;
        else if ( segno == ":") 
            risultato = parziale / numero;
        return risultato;
    }

    reset() {
        this.numero = 0;
        this.parziale = 0;
        this.segno = 0;
        this.risultato = 0;
    }
}