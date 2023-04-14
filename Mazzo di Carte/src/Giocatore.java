public class Giocatore {
    MyCarte mazzo;

    public Giocatore() {
        this.mazzo = new MyCarte();
    }

    public void distribuisciCarte(MyCarte mazzo) {
        for (int i = 0; i < 13; i++) 
            this.mazzo.push(mazzo.top());
    }
}
