<?php
    function leggiDatiDaFile($file, &$dizionario) {
        // Leggi i dati dal file e dividi i dati in coppie chiave,messaggio
        $dati = explode(';', file_get_contents($file));

        // Itera su ogni coppia di dati
        foreach ($dati as $coppia) {
            // Dividi la coppia in chiave e messaggio
            list($chiave, $messaggio) = explode(',', $coppia);

            // Aggiungi la chiave e il messaggio al dizionario
            $dizionario[$chiave] = $messaggio;
        }
    }

    function gestisciRichiesta($file, &$dizionario) {
        // Ottieni i parametri dall'URL
        if ($_GET['comando'] == "set") {
            // Se il comando e' "set", aggiungi la nuova chiave e valore al dizionario e salva su file
            $dizionario[$_GET['key']] = $_GET['valore'];
            salvaDatiSuFile($file, $_GET['key'], $_GET['valore']);
            
            return "200:Dati salvati con successo.";
        } else if ($_GET['comando'] == "get") {
            // Verifica se la chiave esiste nell'array
            if (isset($dizionario[$_GET['key']])) {
                // Se la chiave esiste, restituisci il valore corrispondente
                return "200:".$dizionario[$_GET['key']];
            } else {
                // Se la chiave non esiste, restituisci un messaggio di errore
                return "Error 404:Chiave non trovata.";
            }
        } else {
            // Se il comando non e' riconosciuto, restituisci un messaggio di errore
            return "Error 501:Comando non riconosciuto.";
        }
    }

    function salvaDatiSuFile($file, $key, $valore) {
        // Salva la nuova coppia chiave,valore nel file
        file_put_contents($file, $key.",".$valore.";", FILE_APPEND);
    }

    // Definisci il nome del file e il dizionario
    $file = 'dizionario.txt';
    $dizionario = array();

    // Leggi i dati dal file e carica il dizionario
    leggiDatiDaFile($file, $dizionario);

    echo gestisciRichiesta($file, $dizionario);
?>
