class SlotMachine {
    constructor() {
        this.credito = 100;
        this.spin1 = 0;
        this.spin2 = 0;
        this.spin3 = 0;
        this.spin4 = 0;
    }

    spin() {
        this.credito -= document.getElementById("puntata").value;

        // genera le spin
        this.spin1 = Math.floor(Math. random() * 3 + 1);
        this.spin2 = Math.floor(Math. random() * 3 + 1);
        this.spin3 = Math.floor(Math. random() * 3 + 1);
        this.spin4 = Math.floor(Math. random() * 3 + 1);
    
        // assegna le immagini ai numeri usciti
        this.assegnaImmagine(this.spin1, "immagine1");
        this.assegnaImmagine(this.spin2, "immagine2");
        this.assegnaImmagine(this.spin3, "immagine3");
        this.assegnaImmagine(this.spin4, "immagine4");

        // assegna il credito se vince
        this.assegnaVincita();

        document.getElementById("credito").innerHTML = "Credito " + this.credito;
    }
    
    assegnaImmagine(spin, immagine) {
        if (spin == 1) {
            document.getElementById(immagine).src = "images/ciliegia.PNG";
        } else if (spin == 2) {
            document.getElementById(immagine).src = "images/diamante.PNG";
        } else if (spin == 3) {
            document.getElementById(immagine).src = "images/bar.PNG";
        }
    }
    
    assegnaVincita() {
        if (this.spin1 == this.spin2 && this.spin2 == this.spin3 && this.spin3 == this.spin4) {
            this.credito += document.getElementById("puntata").value * 10;
        } else if ((this.spin1 == this.spin2 && this.spin2 == this.spin3) || (this.spin2 == this.spin3 && this.spin3 == this.spin4)) {
            this.credito += document.getElementById("puntata").value * 5;
        }
    }

    reset() {
        this.credito = 100;
        this.spin1 = 0;
        this.spin2 = 0;
        this.spin3 = 0;
        this.spin4 = 0;

        document.getElementById("immagine1").src = "images/ciliegia.PNG";
        document.getElementById("immagine2").src = "images/diamante.PNG";
        document.getElementById("immagine3").src = "images/bar.PNG";
        document.getElementById("immagine4").src = "images/ciliegia.PNG";

        document.getElementById("credito").innerHTML = "Credito " + this.credito;
    }
}