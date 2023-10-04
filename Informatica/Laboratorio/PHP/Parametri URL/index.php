<?php
    // Ottieni i parametri dall'URL
    $parametri = $_GET;

    // Converti l'array dei parametri in una stringa
    $parametri_stringa = var_export($parametri, true);

    // Definisci il nome del file
    $file = 'parametri.txt';

    // Scrivi i parametri nel file
    file_put_contents($file, $parametri_stringa);

    echo "Parametri scritti nel file.";
?>
