class Cliente {
    constructor() {
        this.carrello = [];
    }

    aggiungiPizza(pizza) {
        this.carrello.push(pizza);

        let bottone = $("#rigaBottone");
        bottone.before("<tr class = pizza><td class = numero>" + this.carrello.length + "</td><td class tipoPizza>" + pizza + "</td></tr>");
    }

    svuotaCarrello() {
        this.carrello.splice(0, this.carrello.length);
        $("tr").remove(".pizza");
    }
}