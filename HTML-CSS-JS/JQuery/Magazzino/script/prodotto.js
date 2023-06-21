class Prodotto {
    constructor() {
        this.seriale;
        this.nome;
        this.descrizione;
        this.categoria;
        this.quantità;
    }

    visualizza(posizione) {
        $("#visualizzazioneProdotti").append("<div id='prodotto" + posizione + "'></div>");
        let info = "Seriale: " + this.seriale + " Nome: " + this.nome + " Categoria: " + this.categoria + " Quantità: " + this.quantità;
        $("#prodotto" + posizione).append(info);
    }
}