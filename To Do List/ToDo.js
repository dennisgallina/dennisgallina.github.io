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

    getData() {
        return (this.data.getDate() + "-" + (this.data.getMonth() + 1) + "-" + this.data.getFullYear());
    }
}