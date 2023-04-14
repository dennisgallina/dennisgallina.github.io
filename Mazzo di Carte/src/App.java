import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        MyFile file = new MyFile("Carte.csv");

        // importo tutto nel vettore
        Carta[] carte = new Carta[file.contaCarte()];
        file.importaCSV(carte);

        // controllo se ci sono nel file carte mancanti o doppie
        System.out.println("Nel mazzo ci sono " + file.controlloCarteMancanti(carte) + " carte mancanti");
        System.out.println("Nel mazzo ci sono " + file.controlloCarteDoppie(carte) + " carte doppie");

        // crea il mazzo vero e proprio
        MyCarte mazzo = new MyCarte(carte);
        // mette tutti i jolly
        inserisciJolly(mazzo);

        // mischia il mazzo
        System.out.print("Inserire un numero per mischiare il mazzo: ");
        mazzo.dividi(scanner.nextInt());

        // sovrappone il mazzo dividendolo in due
        System.out.print("Inserire un numero per sovrapporre il mazzo: ");
        mazzo.sovrapponi(scanner.nextInt());

        // creazione dei giocatori, e controlla che i giocatori non siano troppi per le carte a disposizione
        int nGiocatori = 0;
        do {
            System.out.print("Inserire il numero di giocatori: ");
            nGiocatori = scanner.nextInt();
        } while (nGiocatori > mazzo.getSize() / 13);

        // distribuisce le carte ai giocatori
        Giocatore[] giocatori = new Giocatore[nGiocatori];
        for (int i = 0; i < giocatori.length; i++) 
            giocatori[i] = new Giocatore("Giocatore " + (i + 1));
        distribuisciCarte(mazzo, giocatori);

        // visualizza tutti i mazzi
        visualizzaMazzi(mazzo, giocatori);

        // esporta in un fie diverso ogni mazzo
        esportaMazzi(mazzo, giocatori);

        scanner.close();
    }

    public static void inserisciJolly(MyCarte mazzo) {
        mazzo.inserisci(0, "Jolly", "Blu", 3);
        mazzo.inserisci(0, "Jolly", "Blu", 22);
        mazzo.inserisci(0, "Jolly", "Rosso", 60);
        mazzo.inserisci(0, "Jolly", "Rosso", 77);
    }

    public static void distribuisciCarte(MyCarte mazzo, Giocatore[] giocatori) {
        for (int i = 0; i < giocatori.length; i++)
            giocatori[i].distribuisciCarte(mazzo);
    }

    public static void visualizzaMazzi(MyCarte mazzo, Giocatore[] giocatori) {
        System.out.println("Carte rimanenti");
        mazzo.visualizza();

        for (int i = 0; i < giocatori.length; i++) {
            System.out.println("Giocatore " + (i + 1));
            giocatori[i].mazzo.visualizza();
        }
    }

    public static void esportaMazzi(MyCarte mazzo, Giocatore[] giocatori) throws IOException {
        MyFile carteRimanenti = new MyFile("Mazzo rimanente.csv");
        carteRimanenti.esportaCSV(mazzo);

        for (int i = 0; i < giocatori.length; i++) {
            MyFile mazzoGiocatore = new MyFile(giocatori[i].nome + ".csv");
            mazzoGiocatore.esportaCSV(giocatori[i].mazzo);
        }
    }
}