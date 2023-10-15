import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Scontrino {
    public int id;
    public int idCassiere;
    public List<Prodotto> carrello;
    public int totale;

    public Scontrino() {
        this.id = 0;
        this.idCassiere = 0;
        this.carrello = new ArrayList<>();
        this.totale = 0;
    }

    public Scontrino(int id, int idCassiere, List<Prodotto> carrello, int totale) {
        this.id = id;
        this.idCassiere = idCassiere;
        this.carrello = carrello;
        this.totale = totale;
    }  

    public Document getDocument() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        document.appendChild(serializeToElement());

        return document;
    }

    public Element serializeToElement() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element elementScontrino = document.createElement("scontrino");

        for (Prodotto prodotto : carrello) {
            Element elementoProdotto = prodotto.serializeToElement();
            elementScontrino.appendChild(elementoProdotto);
        }

        return elementScontrino;
    }

    public Scontrino deserializeFromElement(Element element) {
        Scontrino scontrino = new Scontrino();

        scontrino.id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        scontrino.idCassiere = Integer.parseInt(element.getElementsByTagName("idCassiere").item(0).getTextContent());

        NodeList prodottoNodes = element.getElementsByTagName("prodotto");
        List<Prodotto> carrello = new ArrayList<>();

        for (int i = 0; i < prodottoNodes.getLength(); i++) {
            Element prodottoElement = (Element) prodottoNodes.item(i);
            Prodotto prodotto = new Prodotto();
            carrello.add(prodotto.deserializeFromElement(prodottoElement));
        }

        scontrino.carrello = carrello;

        scontrino.totale = Integer.parseInt(element.getElementsByTagName("totale").item(0).getTextContent());

        return scontrino;
    }
}

