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

public class StoricoScontrini {
    public List<Scontrino> scontrini;

    public StoricoScontrini() throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        scontrini = new ArrayList<>();

        importDatiXML();
    }

    public void aggiungi(Scontrino scontrino) {
        scontrini.add(scontrino);
    }

    public Document getDocument() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("Storico Scontrini.xml"); // Nota la modifica nel percorso del file

        return document;
    }

    public void saveDocument(Document document) throws TransformerConfigurationException, TransformerException {
        // Codice per salvare il documento in un file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

        // Specifica il percorso del file XML in cui desideri salvare i dati
        File file = new File("Storico Scontrini.xml"); // Nota la modifica nel percorso del file
        StreamResult result = new StreamResult(file);
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
    }

    public void importDatiXML() throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        File file = new File("Storico Scontrini.xml"); // Nota la modifica nel percorso del file

        if (!file.exists()) {
            // Se il file non esiste, crealo
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            saveDocument(document);
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("Storico Scontrini.xml")); // Nota la modifica nel percorso del file

        NodeList scontrinoNodes = document.getElementsByTagName("scontrino");

        for (int i = 0; i < scontrinoNodes.getLength(); i++) {
            Element scontrinoElement = (Element) scontrinoNodes.item(i);
            Scontrino scontrino = new Scontrino();
            scontrini.add(scontrino.deserializeFromElement(scontrinoElement));
        }
    }

    public void salvaDatiXML() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element elementStorico = document.createElement("storicoScontrini");
        document.appendChild(elementStorico);

        for (int i = 0; i < scontrini.size(); i++)
            elementStorico.appendChild(scontrini.get(i).serializeToElement());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

        // Specifica il percorso del file XML in cui desideri salvare i dati
        File file = new File("Storico Scontrini.xml"); // Nota la modifica nel percorso del file
        StreamResult result = new StreamResult(file);
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
    }
}
