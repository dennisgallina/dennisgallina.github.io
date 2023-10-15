using System;
using System.Net.Sockets;
using System.Net;
using System.Text;
using System.Xml.Linq;
using System.Windows;
using System.IO;
using System.Xml.Serialization;

namespace Client
{
    public class ComunicazioneServer
    {
        public XDocument datiReteServer; // Documento XML da cui prende i dati del Server
        public String ipServer; // IP del Server
        public int portaServer; // Porta del Server
        UdpClient client; // Pacchetto Client
        private IPEndPoint riceveEP;

        public ComunicazioneServer()
        {
            this.datiReteServer = XDocument.Load("dati.xml");
            this.ipServer = datiReteServer.Root.Element("server").Element("IP").Value;
            this.portaServer = int.Parse(datiReteServer.Root.Element("server").Element("Port").Value);
            this.client = new UdpClient();
            this.riceveEP = new IPEndPoint(IPAddress.Any, 0);
        }

        public void inviaRichiesta(String richiesta)
        {
            byte[] data = Encoding.ASCII.GetBytes(richiesta);
            client.Send(data, data.Length, "localhost", portaServer);
        }

        // Attende una risposta da parte del Server
        public String ricevi()
        {
            // Ricezione di un pacchetto dalla stessa porta associata durante la creazione del client UDP
            IPEndPoint riceveEP = new IPEndPoint(IPAddress.Any, 0);

            // Ricezione dei dati dal server e conversione in stringa
            byte[] rispostaBytes = client.Receive(ref riceveEP);

            String rispostaXml = Encoding.ASCII.GetString(rispostaBytes);

            return rispostaXml; // Riporta l'esito della gestione della risposta
        }
    }
}
