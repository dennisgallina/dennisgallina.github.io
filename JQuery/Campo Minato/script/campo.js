class Campo {
    constructor(partita, righe, colonne) {
        this.partita = partita;
        this.righe = righe;
        this.colonne = colonne;
        this.caselle = [];
        // numero di bombe presenti nel campo
        this.numeroBombe = this.righe * 2;
        // numero di bandiere posizionabili dal giocatore
        this.numeroBandiere = this.numeroBombe;
        this.esplosioneIniziale = false;

        this.genera();
    }

    cambiaNumeroCelle(righe, colonne) {
        this.righe = righe;
        this.colonne = colonne;
        this.caselle = [];
        this.numeroBombe = this.righe * 2;
        this.numeroBandiere = this.numeroBombe;

        this.genera();
        this.inserisciCelle();
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
        this.visualizza();
    }

    // assegna le bombe nel campo
    assegnaBombe() {
        // per ogni bomba
        for (let i = 0; i < this.numeroBombe; i++) {
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
            // per ogni elemento della riga
            for (let colonna = 0; colonna < this.colonne; colonna++) {
                let bombeAdiacenti = 0;

                // controllo cella superiore
                if (riga - 1 > -1) {
                    if (this.caselle[riga - 1][colonna].contenuto == "bomba")
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
                // controllo angolo alto sx
                if (riga - 1 > -1 && colonna - 1 > -1) {
                    if (this.caselle[riga - 1][colonna - 1].contenuto == "bomba")
                        bombeAdiacenti++;
                }

                this.caselle[riga][colonna].bombeAdiacenti = bombeAdiacenti;
            }
        }
    }

    scopriCaselleBombe() {
        // per ogni riga
        for (let riga = 0; riga < this.righe; riga++) {
            // per ogni elemento della riga
            for (let colonna = 0; colonna < this.colonne; colonna++) {
                if (this.caselle[riga][colonna].contenuto == "bomba") {
                    $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').addClass("casellaBomba");
                    $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').removeClass("casellaCoperta");
                }
            }
        }
    }

    scopriCaselleAdiacenti(riga, colonna) {
        let trovataBomba = false;

        // controllo cella superiore
        if (riga - 1 > -1) {
            if (this.caselle[riga - 1][colonna].contenuto != "bomba")
                this.scopriCasella(riga - 1, colonna);
            else
                trovataBomba = true;
        }
        // controllo angolo alto dx
        if (riga - 1 > -1 && colonna + 1 < this.colonne) {
            if (this.caselle[riga - 1][colonna + 1].contenuto != "bomba")
                this.scopriCasella(riga - 1, colonna + 1);
            else
                trovataBomba = true;
        }
        // controllo cella a destra
        if (colonna + 1 < this.colonne) {
            if (this.caselle[riga][colonna + 1].contenuto != "bomba")
                this.scopriCasella(riga, colonna + 1);
            else
                trovataBomba = true;
        }
        // controllo angolo basso dx
        if (riga + 1 < this.righe && colonna + 1 < this.colonne) {
            if (this.caselle[riga + 1][colonna + 1].contenuto != "bomba")
                this.scopriCasella(riga + 1, colonna + 1);
            else
                trovataBomba = true;
        }
        // controllo cella inferiore
        if (riga + 1 < this.righe) {
            if (this.caselle[riga + 1][colonna].contenuto != "bomba")
                this.scopriCasella(riga + 1, colonna);
            else
                trovataBomba = true;
        }
        // controllo angolo basso sx
        if (riga + 1 < this.righe && colonna - 1 > - 1) {
            if (this.caselle[riga + 1][colonna - 1].contenuto != "bomba")
                this.scopriCasella(riga + 1, colonna - 1);
            else
                trovataBomba = true;
        }
        // controllo cella a sinistra
        if (colonna - 1 > - 1) {
            if (this.caselle[riga][colonna - 1].contenuto != "bomba")
                this.scopriCasella(riga, colonna - 1);
            else
                trovataBomba = true;
        }
        // controllo angolo alto sx
        if (riga - 1 > -1 && colonna - 1 > -1) {
            if (this.caselle[riga - 1][colonna - 1].contenuto != "bomba")
                this.scopriCasella(riga - 1, colonna -1);
            else
                trovataBomba = true;
        }

        return trovataBomba;
    }

    esplosione(riga, colonna) {
        // controlla partendo dalla cella superiore
        for (let i = riga - 1; i > -1; i--) {
            if (this.caselle[i][colonna].contenuto == "bomba") 
                break;
            
            this.scopriCasella(i, colonna);
            
            if (this.scopriCaselleAdiacenti(i, colonna) == true) 
                break;
        }
        // controlla partendo dall'angolo alto dx
        for (let i = riga - 1, j = colonna + 1; i > -1 && j < this.colonne; i--, j++) {
            if (this.caselle[i][j].contenuto == "bomba") 
                break;
            
            this.scopriCasella(i, j); 
            
            if (this.scopriCaselleAdiacenti(i, j) == true) 
                break;
        }
        // controlla partendo dalla cella a destra
        for (let j = colonna + 1;j < this.colonne; j++) {
            if (this.caselle[riga][j].contenuto == "bomba") 
                break;
            
            this.scopriCasella(riga, j);

            if (this.scopriCaselleAdiacenti(riga, j) == true) 
                break;
        }
        // controlla partendo dall'angolo basso dx
        for (let i = riga + 1, j = colonna + 1; i < this.righe && j < this.colonne; i++, j++) {
            if (this.caselle[i][j].contenuto == "bomba") 
                break;
            
            this.scopriCasella(i, j);

            if (this.scopriCaselleAdiacenti(i, j) == true) 
                break;
        }
        // controlla partendo dalla cella inferiore
        for (let i = riga + 1; i < this.righe; i++) {
            if (this.caselle[i][colonna].contenuto == "bomba") 
                break;
            
            this.scopriCasella(i, colonna);

            if (this.scopriCaselleAdiacenti(i, colonna) == true) 
                break;
        }
        // controlla partendo dall'angolo basso sx
        for (let i = riga + 1, j = colonna - 1; i < this.righe && j > - 1; i++, j--) {
            if (this.caselle[i][j].contenuto == "bomba") 
                break;

            this.scopriCasella(i, j);
            
            if (this.scopriCaselleAdiacenti(i, j) == true) 
                break;
        }
        // controllo partendo dalla cella a sinistra
        for (let j = colonna - 1; j > -1; j--) {
            if (this.caselle[riga][j].contenuto == "bomba") 
                break;

            this.scopriCasella(riga, j);
            
            if (this.scopriCaselleAdiacenti(riga, j) == true) 
                break;
        }
        // controlla partendo dall'angolo alto sx
        for (let i = riga - 1, j = colonna - 1; i > -1 && j > - 1; i--, j--) {
            if (this.caselle[i][j].contenuto == "bomba") 
                break;

            this.scopriCasella(i, j);
            
            if (this.scopriCaselleAdiacenti(i, j) == true) 
                break;
        }
    }

    scopriCasella(riga, colonna) {
        if (this.caselle[riga][colonna].stato == "scoperta")
            return;

        if (this.caselle[riga][colonna].stato == "coperta") {
            this.caselle[riga][colonna].stato = "scoperta";

            if (this.caselle[riga][colonna].contenuto == "bomba") {
                $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').addClass("casellaBomba");
                $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').removeClass("casellaCoperta");
                this.scopriCaselleBombe();
                this.partita.termina();
                return;
            } else {
                $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').addClass("casellaScoperta");
                $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').removeClass("casellaCoperta");
            }
        }

        if (this.esplosioneIniziale == false) {
            this.esplosioneIniziale = true;
            this.esplosione(riga, colonna);
        }
    }

    assegnaBandiera(riga, colonna) {
        if (this.numeroBandiere == 0) 
            return;

        if (this.caselle[riga][colonna].stato == "coperta") {
            this.caselle[riga][colonna].stato = "bandiera";
            $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').addClass("casellaBandiera");
            $('.casellaCoperta[data-riga=' + riga+ '][data-colonna=' + colonna + ']').removeClass("casellaCoperta");
            this.numeroBandiere--;
        }

        document.getElementById("bandiereRimaste").innerHTML = this.numeroBandiere;
    }

    visualizza() {
        document.getElementById("griglia").style.width = (30 * this.righe) + "px";
        document.getElementById("griglia").style.height = (30 * this.righe) + "px";

        $("#griglia").empty();
        document.getElementById("griglia").style.gridTemplateColumns = " auto";

        // per ogni riga
        for (let riga = 0; riga < this.righe; riga++) {
            // per ogni elemento della riga
            for (let colonna = 0; colonna < this.colonne; colonna++) {
                // visualizza casella
                if (this.caselle[riga][colonna].bombeAdiacenti == 0 || this.caselle[riga][colonna].contenuto == "bomba")
                    $("#griglia").append("<div class='casellaCoperta' data-riga='" + riga + "' data-colonna='" + colonna + "'></div>");
                else
                    $("#griglia").append("<div class='casellaCoperta' data-riga='" + riga + "' data-colonna='" + colonna + "'>" + this.caselle[riga][colonna].bombeAdiacenti + "</div>");
            }
            // va a capo a fine riga
            $("#griglia").append("<br>");
            document.getElementById("griglia").style.gridTemplateColumns += " auto";
        }
    }
}