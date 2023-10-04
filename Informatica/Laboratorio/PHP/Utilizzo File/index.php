<?php
    // Definisci il nome del file
    $file = 'Contatore.txt';

    // Controlla se il file esiste
    if (file_exists($file)) 
        // Se esiste, leggi il valore corrente del contatore
        $count = file_get_contents($file);
    else 
        // Se non esiste, inizializza il contatore a 0
        $count = 0;

    // Incrementa il valore del contatore
    $count++;

    // Salva il nuovo valore del contatore nel file
    file_put_contents($file, $count);

    // Controlla se il contatore è uguale a 1
    if ($count == 1) 
        // Se è uguale a 1, stampa un messaggio singolare
        echo "Pagina aggiornata $count volta.";
    else
        // Se è maggiore di 1, stampa un messaggio plurale
        echo "Pagina aggiornata $count volte.";
?>
