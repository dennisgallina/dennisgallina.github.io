using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Client
{
    public class Prodotto
    {
        public int id;
        public String nome;
        public int prezzoAlChilo;
        public int quantità;

        public Prodotto()
        {
        }

        public Prodotto(int id, String nome, int prezzoAlChilo, int quantità)
        {
            this.id = id;
            this.nome = nome;
            this.prezzoAlChilo = prezzoAlChilo;
            this.quantità = quantità;
        }

        public Prodotto deserializeXML(XmlElement elementProdotto)
        {
            this.id = Convert.ToInt32(elementProdotto.GetElementsByTagName("id").Item(0).InnerText);
            this.nome = elementProdotto.GetElementsByTagName("nome").Item(0).InnerText;
            this.prezzoAlChilo = Convert.ToInt32(elementProdotto.GetElementsByTagName("prezzoAlChilo").Item(0).InnerText);
            this.quantità = Convert.ToInt32(elementProdotto.GetElementsByTagName("quantita").Item(0).InnerText);

            return this;
        }
    }
}
