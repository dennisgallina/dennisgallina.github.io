public class App {
    public static void main(String[] args) throws Exception {
        Archivio archivio = new Archivio();

        archivio.visualizza();

        archivio.inserisciUfficio("Ufficio 1 Sistemi", 0, "U1S", 8, "Dennis Gallina");
        archivio.inserisciDipendente("Dennis", "Gallina", "Meda", "28/12/2004", "27/09/2023", 40, "U1S");

        archivio.inserisciUfficio("Ufficio 2 Sistemi", 0, "U2S", 8, "Andrea Rega");
        archivio.inserisciDipendente("Andrea", "Rega", "Barlassina", "20/08/2004", "29/09/2023", 40, "U2S");

        archivio.inserisciDipendente("Andrea", "Disanti", "Mariano Comense", "15/01/2004", "28/09/2004", 40, "U1S");
    }
}
