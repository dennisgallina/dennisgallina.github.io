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

        for (int i = 0; i < mazzo.getSize(); i++) {
            Carta carta = mazzo.carte.get(i);

            if (i == mazzo.getSize() - 1)
                bw.write(carta.numero + "," + carta.seme + "," + carta.colore);
            else {
                bw.write(carta.numero + "," + carta.seme + "," + carta.colore);
                bw.newLine();
            }
        }

        bw.close();
    }

    public int controlloCarteMancanti(Carta[] carte) throws IOException {
        int carteMancanti = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] valori = linea.split(",");

            Carta carta = new Carta(Integer.parseInt(valori[0]), valori[1], valori[2]);
            for (int i = 0; i < carte.length; i++) {
                if (carta.ugualeA(carte[i])) 
                    break;

                if (i < carte.length - 1 && carta.ugualeA(carte[i]) == false)
                    carteMancanti++;
            } 
        }

        br.close();
        return carteMancanti;
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
