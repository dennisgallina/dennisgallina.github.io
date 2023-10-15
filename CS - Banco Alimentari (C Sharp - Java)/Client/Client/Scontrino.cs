using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;

namespace Client
{
    public class Scontrino
    {
        public int id;
        public int idCassiere;
        public List<Prodotto> carrello;
        public int totale;

        public Scontrino()
        {
            
        }

        public Scontrino(int id, int idCassiere, List<Prodotto> carrello, int totale)
        {
            this.id = id;
            this.idCassiere = idCassiere;
            this.carrello = carrello;
            this.totale = totale;
        }

        public void deserializeXML(String stringaXML)
        {
            XmlDocument document = new XmlDocument();
            document.LoadXml(stringaXML);
            XmlNode nodeListino = document.GetElementsByTagName("scontrino").Item(0);
            XmlNodeList nodesProdotto = document.GetElementsByTagName("prodotto");

            for (int i = 0; i < nodesProdotto.Count; i++)
            {
                Prodotto prodotto = new Prodotto();
                this.carrello.Add(prodotto.deserializeXML((XmlElement)nodesProdotto.Item(i)));
            }
        }

        public Scontrino deserializeXML(XmlElement elementScontrino)
        {
            this.id = Convert.ToInt32(elementScontrino.GetElementsByTagName("id").Item(0).InnerText);
            this.idCassiere = Convert.ToInt32(elementScontrino.GetElementsByTagName("idCassiere").Item(0).InnerText);

            XmlNodeList nodesListProdotto = elementScontrino.GetElementsByTagName("prodotto");
            for (int i = 0; i < elementScontrino.GetElementsByTagName("prodotto").Count; i++)
            {
                Prodotto prodotto = new Prodotto();
                carrello.Add(prodotto.deserializeXML((XmlElement)nodesListProdotto.Item(i)));
            }

            this.totale = Convert.ToInt32(elementScontrino.GetElementsByTagName("totale").Item(0).InnerText);

            return this;
        }
    }
}
