class Quiz {
    constructor() {
        this.quesiti = [];
    }

    aggiungi(domanda, risposta1, risposta2, risposta3, risposta4) {
        this.quesiti[this.quesiti.length] = new Quesito(domanda, risposta1, risposta2, risposta3, risposta4);
    }

    verificaRispostaQuesito(numeroQuesito) {
        this.quesiti[numeroQuesito].verificaRisposta();
    }

    visualizza() {
        for (let i = 0; i < this.quesiti.length; i++) {
            $("#quiz").append("<div class='quesiti' id='quesito" + i + "'></div>")
            $("#quesito" + i).append("<h4 id='domanda" + i + "'>" + this.quesiti[i].domanda + "</h2>");

            $("#quesito" + i).append("<ul id='risposteDomanda" + i + "'></ul>")
            for (let j = 0; j < this.quesiti[i].risposte.length; j++) {
                $("#risposteDomanda" + i).append("<li id='risposta" + j + "'>" + this.quesiti[i].risposte[j] + "</li>");
            }
            $("#quesito" + i).append("<input type='button' value='RISPONDI' onclick='quiz.verificaRispostaQuesito(" + i + ")'>");
        }
    }
}