import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Prodotto {
    public int id;
    public String nome;
    public int prezzoAlChilo;
    public int quantità;

    public Prodotto() {
        this.id = 0;
        this.nome = "";
        this.prezzoAlChilo = 0;
        this.quantità = 0;
    }

    public Element serializeToElement() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element elementProdotto = document.createElement("prodotto");

        Element elementId = document.createElement("id");
        elementId.appendChild(document.createTextNode(String.valueOf(id)));
		elementProdotto.appendChild(elementId);

        Element elementNome = document.createElement("nome");
        elementId.appendChild(document.createTextNode(nome));
		elementProdotto.appendChild(elementNome);

        Element elementPrezzoAlChilo = document.createElement("prezzoAlChilo");
        elementId.appendChild(document.createTextNode(String.valueOf(prezzoAlChilo)));
		elementProdotto.appendChild(elementPrezzoAlChilo);

        Element elementQuantità = document.createElement("quantità");
        elementId.appendChild(document.createTextNode(String.valueOf(quantità)));
		elementProdotto.appendChild(elementQuantità);

        return elementProdotto;
    }

    public Prodotto deserializeFromElement(Element element) {
        Prodotto prodotto = new Prodotto();

        // Estrai i dati dall'elemento XML
        prodotto.id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        prodotto.nome = element.getElementsByTagName("nome").item(0).getTextContent();
        prodotto.prezzoAlChilo = Integer.parseInt(element.getElementsByTagName("prezzoAlChilo").item(0).getTextContent());
        prodotto.quantità = Integer.parseInt(element.getElementsByTagName("quantità").item(0).getTextContent());
    
        return prodotto;
    }
}
