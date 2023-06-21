function calcola() {
    let bollo = 0;

    if (document.bollo.tipologia.value == "-" || document.bollo.carburante.value == "-" || document.bollo.cavalli.value == "" || document.bollo.telaio.value == "") {
        document.getElementById("calcolo").innerHTML = "Dati mancanti!";
        document.getElementById("errore").src = "images/errore.png";
        return;
    }

    let tipologia = 0
    if (document.bollo.tipologia.value == "Auto") 
        tipologia = 20;
    else if (document.bollo.tipologia.value == "Moto") 
        tipologia = 10;
    else if (document.bollo.tipologia.value == "Autobus") 
        tipologia = 50;
   
    
    let carburante = 0;
    if (document.bollo.carburante.value == "Benzina") 
        carburante = 0.4;
    else if (document.bollo.carburante.value == "Diesel") 
        carburante = 0.8;
    else if (document.bollo.carburante.value == "Ibrida") 
        carburante = 0.1;
    else if (document.bollo.carburante.value == "Elettrica") 
        carburante = 0;

    let telaio = document.bollo.telaio.value;

    let cavalli = document.bollo.cavalli.value;
    if (cavalli > 280) 
        carburante = carburante * 2;

    bollo = tipologia + (carburante * cavalli);

    document.getElementById("calcolo").innerHTML = "Veicolo " + telaio + " ha un costo del bollo di " + bollo + " euro";
}