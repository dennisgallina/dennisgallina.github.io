class Lista {
    constructor() {
        this.toDo = [];
    }

    aggiungiToDo(elemento) {
        this.toDo.push(elemento);
    }

    modificaToDo(posizione, oggetto, data) {
        this.toDo[posizione].modifica(oggetto, data);
    }

    rimuovi(posizione) {
        this.toDo.splice(posizione, 1);
    }

    rimuoviTutte() {
        this.toDo.splice(0, this.toDo.length);
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