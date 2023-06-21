class Quesito {
    constructor(domanda, risposta1, risposta2, risposta3, risposta4, rispostaCorretta) {
        this.domanda = domanda;
        this.risposte = [risposta1, risposta2, risposta3, risposta4]; 
        this.rispostaCorretta = rispostaCorretta;
    }

    verificaRisposta(rispostaData) {
        if (rispostaData == this.risposte[this.rispostaCorretta - 1]) 
            alert("Risposta corretta!");
        else
            alert("Risposta errata!");
    }
}