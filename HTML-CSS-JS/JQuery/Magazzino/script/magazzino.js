class Magazzino {
    constructor() {
        this.prodotti = [];
    }

    aggiungi(prodotto) {
        for (let i = 0; i < this.prodotti.length; i++) {
            if (prodotto.seriale == this.prodotti[i].seriale) {
                this.prodotti[i].quantità += prodotto.quantità;
                return;
            }
        }

        this.prodotti[nEle] = prodotto;
    }

    elimina() {
    }

    visualizza() {
        for (let i = 0; i < this.prodotti.length; i++) {
            this.prodotti[i].visualizza(i);
        }
    }

    visualizza(valori) {
        alert(valori[0]);
    }
}