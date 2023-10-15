using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Xml.Serialization;

namespace Client
{
    /// <summary>
    /// Logica di interazione per FinestraCassiere.xaml
    /// </summary>
    public partial class FinestraCassiere : Window
    {
        ComunicazioneServer comunicazioneServer;

        Cassa cassa;
        Cassiere cassiere;

        public FinestraCassiere(Cassa cassa, Cassiere cassiere)
        {
            InitializeComponent();

            this.cassa = cassa;
            this.cassiere = cassiere;
            this.comunicazioneServer = new ComunicazioneServer();

            this.comunicazioneServer.inviaRichiesta("open;" + cassiere.id);
            this.cassiere.scontrino.deserializeXML(comunicazioneServer.ricevi());

            aggiornaGrafica();
        }

        private void aggiornaGrafica()
        {
            if (cassiere.scontrino.carrello.Count() == 0)
                lBoxCarrello.Items.Add("Carrello vuoto.");
            else
                for (int i = 0; i < cassiere.scontrino.carrello.Count(); i++)
                    lBoxCarrello.Items[i] = (cassiere.scontrino.carrello[i].nome + " " + cassiere.scontrino.carrello[i].quantità + " - " + cassiere.scontrino.carrello[i].id);

            lblTotale.Content = cassiere.scontrino.totale + " €";

            cBoxListino.Items[-1] = ("Seleziona il prodotto da inserire.");

            for (int i = 0; i < cassa.listinoProdotti.prodotti.Count(); i++)
                cBoxListino.Items[i] = (cassa.listinoProdotti.prodotti[i].nome + " " + cassa.listinoProdotti.prodotti[i].prezzoAlChilo + " - " + cassa.listinoProdotti.prodotti[i].id);

            if (lBoxCarrello.SelectedIndex == -1)
                btnRimuovi.Visibility = Visibility.Collapsed;
            else
                btnRimuovi.Visibility= Visibility.Visible;  
        }

        private void btnAggiungi_Click(object sender, RoutedEventArgs e)
        {
            if (cBoxListino.SelectedIndex == 0 || txtQuantità.Text.Equals(null))
            {
                MessageBox.Show("Dati mancanti.");
                return;
            }

            comunicazioneServer.inviaRichiesta("insert;" + cassiere.id + ";" + cassa.listinoProdotti.prodotti[cBoxListino.SelectedIndex].id + ";" + txtQuantità.Text);
            cassiere.scontrino.deserializeXML(comunicazioneServer.ricevi());

            aggiornaGrafica();
        }

        private void btnRimuovi_Click(object sender, RoutedEventArgs e)
        {
            if (cBoxListino.SelectedIndex == 0 || txtQuantità.Text.Equals(null))
            {
                MessageBox.Show("Nessun elemento seleionato.");
                return;
            }

            comunicazioneServer.inviaRichiesta("remove;" + cassiere.id + ";" + cassa.listinoProdotti.prodotti[lBoxCarrello.SelectedIndex].id);
            cassiere.scontrino.deserializeXML(comunicazioneServer.ricevi());

            aggiornaGrafica();
        }

        private void btnTermina_Click(object sender, RoutedEventArgs e)
        {
            comunicazioneServer.inviaRichiesta("close;" + cassiere.id);
            cassiere.scontrino.deserializeXML(comunicazioneServer.ricevi());

            MessageBox.Show("Ordine terminato.");

            MainWindow main = new MainWindow();
            main.Show();
            this.Close();
        }
    }
}
