class Lista {
    constructor() {
        this.toDo = [];

        this.genera();
    }

    genera() {
        ("#body").append("<div id='lista' align='center'></div>");

        for (let i = 0; this.toDo.length; i++) {
            ("#lista").append("<div id='toDo" + i + "'>");
            ("#toDo" + i).append("<input type='checkbox' id='checkbox" + i + "' onclick='lista.cambiaStatoCheckBox(" + i + ")'>");
            ("#toDo" + i).append("<p id='descrizione" + i + "'" + this.toDo[i].descrizione + "</p>");
            ("#toDo" + i).append("<p id='scadenza" + i + "'" + this.toDo[i].scadenza + "</p>");
            ("#toDo" + i).append("<input type='button' class='elimina' value='ELIMINA' onclick='lista.elimina(" + i + ")'>");
        }
    }

    aggiungi(elemento) {
        ("#lista").append("<div id='toDo" + i + "'>");
        ("#toDo" + this.toDo.length).append("<input type='checkbox' id='checkbox" + this.toDo.length + "' onclick='lista.cambiaStatoCheckBox(" + this.toDo.length + ")'>");
        ("#toDo" + this.toDo.length).append("<p id='descrizione" + this.toDo.length + "'" + elemento.descrizione + "</p>");
        ("#toDo" + this.toDo.length).append("<p id='scadenza" + this.toDo.length + "'" + elemento.descrizione + "</p>");
        ("#toDo" + this.toDo.length).append("<input type='button' class='elimina' value='ELIMINA' onclick='lista.elimina(" + this.toDo.length + ")'>");

        this.toDo.push(elemento);
    }

    modifica(posizione, descrizione, scadenza) {
        this.toDo[posizione].descrizione = descrizione;
        this.toDo[posizione].scademza = scadenza;
    }

    rimuovi(posizione) {
        this.toDo.splice(posizione, 1);
    }

    svuota() {
        this.toDo.splice(0, this.toDo.length);
    }

    cambiaStatoCheckBox(posizione) {
        if (this.toDo[posizione].stato == "incompletata") {
            this.toDo[posizione].stato = "completata";
            ("#checkbox" + posizione).checked = true;
        }
        else if (this.toDo[posizione].stato == "completata") {
            this.toDo[posizione].stato = "incompletata";
            ("#checkbox" + posizione).checked = false;
        }
    }

    ordinaPerStato() {
        for (let i = 0; i < this.toDo.length - 1; i++) {
            for (let j = 0; j < this.toDo.length - 1; j++) {
                if (this.toDo[j].stato == 'completata') {
                    let temp = this.toDo[j];
				    this.toDo[j] = this.toDo[j + 1];
				    this.toDo[j + 1] = temp;
                }
            }
        }
    }

    ordinaPerData() {
        for (let i = 0; i < this.toDo.length - 1; i++) {
            for (let j = 0; j < this.toDo.length - 1; j++) {
                if (this.toDo[j].data > this.toDo[j + 1].data) {
                    const temp = this.toDo[j];
				    this.toDo[j] = this.toDo[j + 1];
				    this.toDo[j + 1] = temp;
                }
            }
        }
    }

    inScadenza(posizione) {
        const oggi = new Date();

        let differenzaMillisecondi = Math.abs(this.toDo[posizione].data - oggi);
        let differenzaGiorni = Math.ceil(differenzaMillisecondi / (1000 * 60 * 60 * 24));

        if (differenzaGiorni <= 3) 
            return true;
        else 
            return false;
    }

    getLength() {
        return this.toDo.length;
    }

    getOggettoToDo(posizione) {
        return this.toDo[posizione].oggetto;
    }

    getStatoToDo(posizione) {
        return this.toDo[posizione].stato;
    }

    setStatoToDo(posizione, stato) {
        this.toDo[posizione].stato = stato;
    }

    getDataToDo(posizione) {
        return this.toDo[posizione].getData();
    }
}