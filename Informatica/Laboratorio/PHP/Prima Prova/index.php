<h1>HELLO WORLD!</h1>
<?php
    echo "Ciao mondo.<br>";

    $v1 = 10;
    $v2 = 20;
    $risultato = $v1 + $v2;

    echo "<b>".$risultato."</b>";

    if ($risultato %2 == 0) {
        echo "Pari";
    } else {
        echo "Dispari";
    } echo "<br>";

    $contatore = 0;

    echo "<ul>";
        while ($contatore < 10) {
            echo "<li>".$contatore."</li>";
            $contatore++;
        }
    echo "</ul>";

    $contatore = 0;

    echo "<ol>";
        do {
            echo "<li>".$contatore."</li>";
            $contatore++;
        } while ($contatore < 10);
    echo "</ol>";

    echo "<table><tr>";
        for ($i = 0; $i < 5; $i++) {
            echo "<li>".$contatore."</li>";
            $contatore++;
        }
    echo "</tr></table>";

    $vettore = array();
    $vettore[1] = "Pippo";
    $vettore[2] = "Pluto";
    $vettore[0] = 123.5;

    print_r($vettore);
?>