public class Giocatore {
    String nome;
    MyCarte mazzo;

    public Giocatore(String nome) {
        this.nome = nome;
        this.mazzo = new MyCarte();
    }

    // distribuisce le carte prendendolo dall'alto
    public void distribuisciCarte(MyCarte mazzo) {
        for (int i = 0; i < 13; i++) 
            this.mazzo.push(mazzo.top());
    }
}
