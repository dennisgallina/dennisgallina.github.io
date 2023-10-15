using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Client
{
    public class ListinoProdotti
    {
        public List<Prodotto> prodotti;

        public ListinoProdotti()
        {
            this.prodotti = new List<Prodotto>();
        }

        public void deserializeXML(String stringaXML)
        {
            XmlDocument document = new XmlDocument();
            document.LoadXml(stringaXML);   
            XmlNode nodeListino = document.GetElementsByTagName("listinoProdotti").Item(0);
            XmlNodeList nodesListProdotto = document.GetElementsByTagName("prodotto");

            for (int i = 0; i < nodesListProdotto.Count; i++)
            {
                Prodotto prodotto = new Prodotto();
                this.prodotti.Add(prodotto.deserializeXML((XmlElement)nodesListProdotto.Item(i)));
            }
        }

        public ListinoProdotti deserializeXML(XmlElement elementListino)
        {
            XmlDocument document = new XmlDocument();
            XmlNode nodeListino = document.GetElementsByTagName("listinoProdotti").Item(0);
            XmlNodeList nodesListProdotto = document.GetElementsByTagName("prodotto");

            for (int i = 0; i < nodesListProdotto.Count; i++)
            {
                Prodotto prodotto = new Prodotto();
                this.prodotti.Add(prodotto.deserializeXML((XmlElement)nodesListProdotto.Item(i)));
            }

            return this;
        }
    }
}
