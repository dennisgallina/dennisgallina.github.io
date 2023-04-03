class ToDo {
    constructor(oggetto, data) {
        this.oggetto = oggetto;
        this.stato = 'incompletata';
        this.data = data;
    }

    modifica(oggetto, data) {
        this.oggetto = oggetto;
        this.data = data;
    }

    seScaduta() {
        const oggi = new Date();

        let differenzaMillisecondi = Math.abs(this.data - oggi);
        let differenzaGiorni = Math.ceil(differenzaMillisecondi / (1000 * 60 * 60 * 24));
    }

    getData() {
        return (this.data.getDate() + "-" + (this.data.getMonth() + 1) + "-" + this.data.getFullYear());
    }
}