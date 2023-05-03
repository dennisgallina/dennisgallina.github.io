class Campo {
    constructor(righe, colonne) {
        this.righe = righe;
        this.colonne = colonne;
        this.caselle = [];
    }

    cambiaNumeroCelle(righe, colonne) {
        this.righe = righe;
        this.colonne = colonne;
        this.caselle = [];

        this.genera();
        this.visualizza();
    }

    genera() {
        // per ogni riga aggiunge un vettore per inserire le caselle
        for (let riga = 0; riga < this.righe; riga++) 
            this.caselle[riga] = [];

        // per ogni riga
        for (let riga = 0; riga < this.righe; riga++) {
            // per ogni elemento della riga
            for (let colonna = 0; colonna < this.colonne; colonna++) {
                // aggiunge e crea una casella
                this.caselle[riga][colonna] = new Casella();
            }
        }

        this.assegnaBombe()
        this.assegnaBombeAdiacenti();
    }

    // assegna le bombe nel campo
    assegnaBombe() {
        // genera il numero di bombe, due per ogni riga
        let numeroBombe = this.righe * 2;

        // per ogni bomba
        for (let i = 0; i < numeroBombe; i++) {
            // genera la riga in cui posizionarla
            let posRigaBomba = Math.floor(Math.random() * (this.righe - 1));
            // genera la colonna in cui posizionarla
            let posColonnaBomba = Math.floor(Math.random() * (this.colonne - 1));
            // la posiziona
            this.caselle[posRigaBomba][posColonnaBomba].contenuto = "bomba";
        }
    }

    // assegna le bombe adiacenti a ogni casella, se presenti
    assegnaBombeAdiacenti() {
        // per ogni riga
        for (let riga = 0; riga < this.righe; riga++) {
            // per ogni elemento della riga controlla attorno alle sue celle adiacenti la presenza di bombe
            for (let colonna = 0; colonna < this.colonne; colonna++) {
                let bombeAdiacenti = 0;
                // per ogni controllo verifica che non esca dal campo
                // controllo angolo alto sx
                if (riga - 1 > -1) {
                    if (this.caselle[riga - 1][colonna].contenuto == "bomba")
                        bombeAdiacenti++;
                }
                // controllo cella superiore
                if (riga - 1 > -1 && colonna + 1 < this.colonne) {
                    if (this.caselle[riga - 1][colonna + 1].contenuto == "bomba")
                        bombeAdiacenti++;
                }
                // controllo angolo alto dx
                if (riga - 1 > -1 && colonna + 1 < this.colonne) {
                    if (this.caselle[riga - 1][colonna + 1].contenuto == "bomba")
                        bombeAdiacenti++;
                }
                // controllo cella a destra
                if (colonna + 1 < this.colonne) {
                    if (this.caselle[riga][colonna + 1].contenuto == "bomba")
                        bombeAdiacenti++;
                }
                // controllo angolo basso dx
                if (riga + 1 < this.righe && colonna + 1 < this.colonne) {
                    if (this.caselle[riga + 1][colonna + 1].contenuto == "bomba")
                        bombeAdiacenti++;
                }
                // controllo cella inferiore
                if (riga + 1 < this.righe) {
                    if (this.caselle[riga + 1][colonna].contenuto == "bomba")
                        bombeAdiacenti++;
                }
                // controllo angolo basso sx
                if (riga + 1 < this.righe && colonna - 1 > - 1) {
                    if (this.caselle[riga + 1][colonna - 1].contenuto == "bomba")
                        bombeAdiacenti++;
                }
                // controllo cella a sinistra
                if (colonna - 1 > - 1) {
                    if (this.caselle[riga][colonna - 1].contenuto == "bomba")
                        bombeAdiacenti++;
                }

                this.caselle[riga][colonna].bombeAdiacenti = bombeAdiacenti;
            }
        }
    }

    visualizza() {
        $("#griglia").empty();
        document.getElementById("griglia").style.gridTemplateColumns = " auto";

        // per ogni riga
        for (let riga = 0; riga < this.righe; riga++) {
            // per ogni elemento della riga
            for (let colonna = 0; colonna < this.colonne; colonna++) {
                // visualizza casella
                $("#griglia").append("<div class='cella' id='" + riga + colonna + "'>1</div>");
            }
            // va a capo a fine riga
            $("#griglia").append("<br>");
            document.getElementById("griglia").style.gridTemplateColumns += " auto";
        }
    }
}