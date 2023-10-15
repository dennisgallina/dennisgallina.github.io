using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Client
{
    public class Cassiere
    {
        public int id; // Identificativo
        public bool ordineInCorso; // Stabilisce se ha un ordine attivo
        public Scontrino scontrino; // Scontrino dell'ordine in gestione
        public String nome; // Nome del cassiere

        public Cassiere()
        {
        }

        public Cassiere(int id, Scontrino scontrino)
        {
            this.id = id;
            this.scontrino = new Scontrino();
        }

        public Cassiere deserializeXML(XmlElement elementCassiere) 
        {
            this.id = Convert.ToInt32(elementCassiere.GetElementsByTagName("id").Item(0).InnerText);
            this.scontrino = new Scontrino();
            this.scontrino = scontrino.deserializeXML((XmlElement)elementCassiere.GetElementsByTagName("scontrino").Item(0));

            return this;
        }
    }
}
