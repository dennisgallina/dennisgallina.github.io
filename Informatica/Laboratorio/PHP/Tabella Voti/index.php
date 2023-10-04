<?php
    // Definizione dello stile CSS per la tabella
    echo "<style>
        table, th, td {
  			border: 1px solid black;  // Bordi della tabella
  			text-align: center;  // Allineamento del testo al centro
		}
        
        table {
  			border-collapse: collapse;  // Rimuove gli spazi tra le celle
		}
    </style>";

    // Definizione della classe Studente
    class Studente {
        public $nome;  // Nome dello studente
        public $voti;  // Array dei voti dello studente

        // Costruttore della classe Studente
        public function __construct($nome, $voti) {
            $this->nome = $nome;  // Assegna il nome
            $this->voti = $voti;  // Assegna i voti
        }
    }

    // Stampa l'intestazione della tabella
    echo "<h1 style='text-align:center;'>TABELLA VOTI</h1>";

    // Inizializza l'array degli studenti
    $intestazioni = array("Nome", "Italiano", "Matematica", "Storia", "Inglese", "Sistemi", "Informatica", "Tecnologie", "Gestione Progetto", "Educazione Fisica");
    $studenti = array();

    // Inizializza il generatore di numeri casuali
    srand(microtime(true)); 

    // Genera i dati per 10 studenti
    for ($i = 0; $i < 10; $i++) {
        $voti = array();  // Inizializza l'array dei voti

        // Genera 9 voti casuali per ogni studente
        for ($j = 0; $j < count($intestazioni) - 1; $j++) {
            $voti[$j] = rand(1, 10); 
        }

        // Crea un nuovo oggetto Studente e lo aggiunge all'array degli studenti
        $studenti[$i] = new Studente("Studente ".($i + 1), $voti); 
    }

    // Inizia a stampare la tabella
    echo "<table>";
        echo "<tr>";  // Inizia una nuova riga
            for ($i = 0; $i < count($intestazioni); $i++) {
                echo "<td>".$intestazioni[$i]."</td>";
            }
        echo "</tr>";  // Termina la riga
        
        // Stampa i dati di ogni studente in una nuova riga della tabella
        for ($i = 0; $i < 10; $i++) {
            echo "<tr>";  // Inizia una nuova riga
                echo "<td>{$studenti[$i]->nome}</td>";  // Stampa il nome dello studente
                
                // Stampa i voti dello studente
                for ($j = 0; $j < 9; $j++) {
                    echo "<td>{$studenti[$i]->voti[$j]}</td>"; 
                }
                
            echo "</tr>";  // Termina la riga
        }  
    echo "</table>";  // Termina la tabella
?>