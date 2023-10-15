import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Cassa {
    public int id;
    public List<Cassiere> cassieri;
    public ListinoProdotti listinoProdotti;
    public StoricoScontrini storicoScontrini;

    public Cassa() throws Exception {
        this.id = 1;
        this.cassieri = new ArrayList<>();
        this.listinoProdotti = new ListinoProdotti();
        this.storicoScontrini = new StoricoScontrini();

        importDatiXML();
    }

    public Document getDocument() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("Cassa.xml");

        return document;
    }

    public void importDatiXML() throws Exception {
        File file = new File("Cassa.xml");

        if (!file.exists()) {
            // Se il file non esiste, crealo
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            salvaDocumento(document, "Cassa.xml");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("Cassa.xml");

        NodeList cassiereNodes = document.getElementsByTagName("cassiere");

        for (int i = 0; i < cassiereNodes.getLength(); i++) {
            Element cassiereElement = (Element) cassiereNodes.item(i);

            // Estrae altre informazioni dall'XML
            int idCassiere = Integer.parseInt(cassiereElement.getElementsByTagName("id").item(0).getTextContent());

            // Aggiunge i dati alla HashMap
            Cassiere cassiere = new Cassiere(idCassiere);
            cassieri.add(cassiere);
        }
    }

    public void salvaDocumento(Document document, String filename) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

        // Specifica il percorso del file XML in cui desideri salvare i dati
        File file = new File(filename);
        StreamResult result = new StreamResult(file);
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
    }

    public void salvaDatiXML() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element elementCassieri = document.createElement("cassieri");
        document.appendChild(elementCassieri);

        for (Cassiere cassiere : cassieri) 
            elementCassieri.appendChild(cassiere.serializeToElement());

        salvaDocumento(document, "Cassa.xml");
    }

    public boolean controlliAlCassiere(Integer idCassiere) {

        // Controlla che non abbia già un ordine attivo
        if (cassieri.get(idCassiere).ordineInCorso())
            return false;
        
        return true;
    }

    public boolean apriOrdineAlCassiere(Integer idCassiere) {
        // Controlla l'esistenza del cassiere e se abbia l'ordine attivo
        if (!controlliAlCassiere(idCassiere))
            return false;

        if (!cassieri.get(idCassiere).apriOrdine()) // Apre l'ordine sul cassiere
            return false;

        return true;
    }

    public boolean aggiungiProdottoAlCassiere(Integer idCassiere, Integer idProdotto, Integer quantità) {
        // Controlla l'esistenza del cassiere e se abbia l'ordine attivo
        if (!controlliAlCassiere(idCassiere))
            return false;
        
        // Controlla che esista il prodotto
        if (listinoProdotti.getProdotto(idProdotto).equals(null))
            return false;

        // Aggiunge un prodotto al carrello dello scontrino con la sua quantità
        if (cassieri.get(idCassiere).aggiungiProdotto(listinoProdotti.getProdotto(idProdotto)))
            return false;

        return true;
    }

    public boolean rimuoviProdottoAlCassiere(Integer idCassiere, Integer idProdotto) {
        // Controlla l'esistenza del cassiere e se abbia l'ordine attivo
        if (!controlliAlCassiere(idCassiere))
            return false;
        
        // Controlla che esista il prodotto
        if (listinoProdotti.getProdotto(idProdotto).equals(null))
            return false;

        // Rimuove un prodotto al carrello dello scontrino 
        if (!cassieri.get(idCassiere).rimuoviProdotto(listinoProdotti.getProdotto(idProdotto)))
            return false;

        return true;
    }

    public boolean chiudiOrdineAlCassiere(Integer idCassiere) {
        // Controlla se abbia un ordine attivo
        if (!cassieri.get(idCassiere).ordineInCorso())
            return false;

        storicoScontrini.aggiungi(getScontrinoDelCassiere(idCassiere)); // Salva lo scontrino nello storico

        if (!cassieri.get(idCassiere).chiudiOrdine()) // Chiude l'ordine sul cassiere
            return false;
        
        return true;
    }

    public Scontrino getScontrinoDelCassiere(Integer idCassiere) {
        // Controlla l'esistenza del cassiere e se abbia l'ordine attivo
        if (!controlliAlCassiere(idCassiere))
            return null;

        return cassieri.get(idCassiere).scontrino;
    }
}
