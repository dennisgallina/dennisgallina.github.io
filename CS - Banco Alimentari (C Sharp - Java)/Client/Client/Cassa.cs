using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

namespace Client
{
    public class Cassa
    {
        public int id;
        public List<Cassiere> cassieri;
        public ListinoProdotti listinoProdotti;

        public void deserializeXML(String stringaXML)
        {
            XmlDocument document = new XmlDocument();
            document.LoadXml(stringaXML);
            XmlNode nodeListino = document.GetElementsByTagName("cassieri").Item(0);
            XmlNodeList nodesListProdotto = document.GetElementsByTagName("cassiere");

            for (int i = 0; i < nodesListProdotto.Count; i++)
            {
                Cassiere cassiere = new Cassiere();
                this.cassieri.Add(cassiere.deserializeXML((XmlElement)nodesListProdotto.Item(i)));
            }

            this.listinoProdotti = listinoProdotti.deserializeXML((XmlElement)document.GetElementsByTagName("listinoProdotti").Item(0));
        }
    }

}
