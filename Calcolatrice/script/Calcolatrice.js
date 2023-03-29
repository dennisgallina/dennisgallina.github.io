class Calcolatrice {
    constructor() {
        this.numero = "";
        this.parziale = "";
        this.segno = "";
        this.risultato = "";
    }

    inserisci(numero) {
        this.numero += numero;
        document.getElementById('barra').value = this.numero;
    }

    operazione(segno) {
        this.segno = segno;
        this.parziale = this.numero;
        this.numero = "";

        document.getElementById('barra').value = "";
    }

    calcola() {
        if (this.segno == "+") 
            this.risultato = parseInt(this.parziale) + parseInt(this.numero);
        else if (this.segno == "-") 
            this.risultato = parseInt(this.parziale) - parseInt(this.numero);
        else if (this.segno == "*") 
            this.risultato = parseInt(this.parziale) * parseInt(this.numero);
        else if (this.segno == "/") 
            this.risultato = parseInt(this.parziale) / parseInt(this.numero);

        document.getElementById('barra').value = this.risultato;

        this.parziale = "";
        this.numero = this.risultato;
    }

    reset() {
        this.numero = "";
        this.parziale = "";
        this.segno = "";
        this.risultato = "";

        document.getElementById('barra').value = "";
    }
}