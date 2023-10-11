public class App {
    public static void main(String[] args) throws Exception {
        Archivio archivio = new Archivio("Archivio.txt");

        // Visualizza i dati dei dipendenti
        archivio.visualizzaDipendenti();

        // Visualizza i dati degli uffici
        archivio.visualizzaUffici();

        // Aggiungi un dipendente
        Dipendente nuovoDipendente = new Dipendente(8, "20/04/1990", "01/2021", 4, "Brambilla", "Marco", "Como - Via Napoleone 16");
        boolean successoDipendente = archivio.aggiungiDipendente(nuovoDipendente);

        // Aggiungi un ufficio
        Ufficio nuovoUfficio = new Ufficio(4, "C1", 6, 4, "Ufficio marketing", "Piano 2");
        boolean successoUfficio = archivio.aggiungiUfficio(nuovoUfficio);

        if (successoDipendente) 
            System.out.println("Dipendente aggiunto con successo.");
        else 
            System.out.println("Impossibile aggiungere il dipendente. Potrebbe esistere già un dipendente con lo stesso ID.");
        

        if (successoUfficio) 
            System.out.println("Ufficio aggiunto con successo.");
        else 
            System.out.println("Impossibile aggiungere l'ufficio. Potrebbe esistere già un ufficio con lo stesso ID.");
    }
}
