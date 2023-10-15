import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ListinoProdotti {
    public List<Prodotto> prodotti;

    public ListinoProdotti() throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        this.prodotti = new ArrayList<>();

        importDatiXML();
    }

    public Prodotto getProdotto(int idProdotto) {
        for (Prodotto prodotto : prodotti) {
            if (prodotto.id == idProdotto)
                return prodotto;
        }
        
        return null;
    }

    public Document getDocument() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("Listino Prodotti.xml"); 

        return document;
    }

    public void saveDocument(Document document) throws TransformerConfigurationException, TransformerException {
        // Codice per salvare il documento in un file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

        // Specifica il percorso del file XML in cui desideri salvare i dati
        File file = new File("Listino Prodotti.xml");
        StreamResult result = new StreamResult(file);
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
    }

      public void importDatiXML() throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        File file = new File("Listino Prodotti.xml");

        if (!file.exists()) {
            // Se il file non esiste, crealo
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            saveDocument(document);
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("Listino Prodotti.xml"));

        NodeList prodottoNodes = document.getElementsByTagName("prodotto");

        for (int i = 0; i < prodottoNodes.getLength(); i++) {
            Element prodottoElement = (Element) prodottoNodes.item(i);
            Prodotto prodotto = new Prodotto();
            prodotti.add(prodotto.deserializeFromElement(prodottoElement));
        }
    }

    public void salvaDatiXML() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element elementListino = document.createElement("listinoProdotti");
        document.appendChild(elementListino);

        for (int i = 0; i < prodotti.size(); i++)
            elementListino.appendChild(prodotti.get(i).serializeToElement());
        
        saveDocument(document);
    }
}