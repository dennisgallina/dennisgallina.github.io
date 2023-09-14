function operazione(segno) {
    var primoNumero=parseFloat($("#primoNumero").val());
    var secondoNumero=parseFloat($("#secondoNumero").val());
    let risultato;

    switch(segno) {
        case '+':
            risultato=primoNumero+secondoNumero;
            break;
        case '-':
            risultato=primoNumero-secondoNumero;
            break;
        case 'x':
            risultato=primoNumero*secondoNumero;
            break;
        case '/':
            risultato=primoNumero/secondoNumero;
            break;
    }

    localStorage.setItem("risultato", risultato);
    $("risultato").html(risultato);
}

function carica() {
    $("risultato").html(localStorage.getItem("risultato"));
}