class ToDo {
    constructor(oggetto, data) {
        this.descrizione = oggetto;
        this.stato = 'incompletata';
        this.scadenza = data;
    }

    seScaduta() {
        const oggi = new Date();

        let differenzaMillisecondi = Math.abs(this.scadenza - oggi);
        let differenzaGiorni = Math.ceil(differenzaMillisecondi / (1000 * 60 * 60 * 24));
    }

    getData() {
        return (this.scadenza.getDate() + "-" + (this.scadenza.getMonth() + 1) + "-" + this.scadenza.getFullYear());
    }
}