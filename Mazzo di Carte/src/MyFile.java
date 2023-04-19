import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyFile {
    File file;

    public MyFile(String nomeFile) {
        this.file = new File(nomeFile);
    }

    public void importaCSV(Carta[] carte) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        int i = 0;

        while ((linea = br.readLine()) != null) {
            String[] valori = linea.split(",");
            Carta carta = new Carta(Integer.parseInt(valori[0]), valori[1], valori[2]);
            carte[i] = carta;
            i++;
        }

        br.close();
    }

    public void esportaCSV(MyCarte mazzo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < mazzo.length(); i++) {
            Carta carta = mazzo.carta(i);

            // se è l'ultimo elemento non crea la linea successiva
            if (i == mazzo.length() - 1)
                bw.write(carta.numero + "," + carta.seme + "," + carta.colore);
            else {
                bw.write(carta.numero + "," + carta.seme + "," + carta.colore);
                bw.newLine();
            }
        }

        bw.close();
    }

    public int contaCarte() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        int carteNelFile = 0;

        while ((linea = br.readLine()) != null) 
            carteNelFile++;

        br.close();
        return carteNelFile;
    }

    public int controlloCarteMancanti(Carta[] carte) throws IOException {
        int carteNelFile = contaCarte();
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] valori = linea.split(",");

            Carta carta = new Carta(Integer.parseInt(valori[0]), valori[1], valori[2]);
            for (int i = 0; i < carte.length; i++) {
                // ogni volta che trova la carta nel file la toglie
                if (carta.ugualeA(carte[i]) == true) 
                    carteNelFile--;
            }
        }

        br.close();
        // ritorna il numero di carte che non sono state rimosse dal contatore, quindi quelle non presenti nel file
        return carteNelFile;
    }

    public int controlloCarteDoppie(Carta[] carte) throws IOException {
        int carteDoppie = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] valori = linea.split(",");

            Carta carta = new Carta(Integer.parseInt(valori[0]), valori[1], valori[2]);
            for (int i = 0; i < carte.length; i++) {
                if (carta.ugualeA(carte[i])) {
                    for (int j = i; j < carte.length; i++, j++) {
                        if (carta.ugualeA(carte[i])) {
                            carteDoppie++;
                            break;
                        }
                    }
                } 
            } 
        }

        br.close();
        return carteDoppie;
    }
}
