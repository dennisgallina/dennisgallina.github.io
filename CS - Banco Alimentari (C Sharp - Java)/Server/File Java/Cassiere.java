import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Cassiere {
    public int id; // Identificativo
    public boolean ordineInCorso; // Stabilisce se ha un ordine attivo
    public Scontrino scontrino; // Scontrino dell'ordine in gestione

    public Cassiere() {
    }

    public Cassiere(int id) {
        this.id = id;
        this.ordineInCorso = false;
        this.scontrino = new Scontrino();
    }

    public Element serializeToElement() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element elementCassiere = document.createElement("cassiere");

        Element elementId = document.createElement("id");
        elementId.appendChild(document.createTextNode(String.valueOf(id)));
		elementCassiere.appendChild(elementId);

		elementCassiere.appendChild(scontrino.serializeToElement());

        return elementCassiere;
    }

    public Cassiere deserializeFromElement(Element element) {
        Cassiere cassiere = new Cassiere();

        cassiere.id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        cassiere.scontrino = cassiere.scontrino.deserializeFromElement((Element)element.getElementsByTagName("scontrino").item(0));
    
        return cassiere;
    }

    public boolean ordineInCorso() {
        return ordineInCorso;
    }

    // Inizia un ordine nuovo
    public boolean apriOrdine() {
        ordineInCorso = true;
        return true;
    }

    // Aggiunge il prodotto dal carrello
    public boolean aggiungiProdotto(Prodotto prodotto) {
        scontrino.carrello.add(prodotto);
        return true;
    }

    // Rimuove il prodotto dal carrello
    public boolean rimuoviProdotto(Prodotto prodotto) {
        scontrino.carrello.remove(prodotto);
        return true;
    }

    // Chiude l'ordine aperto
    public boolean chiudiOrdine() {
        ordineInCorso = false;
        return true;
    }
}