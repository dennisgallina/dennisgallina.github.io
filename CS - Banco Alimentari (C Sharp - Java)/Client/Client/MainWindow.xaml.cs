using System;
using System.Collections.Generic;
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
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Xml.Serialization;
using System.IO;

namespace Client
{
    /// <summary>
    /// Logica di interazione per MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public ComunicazioneServer comunicazioneServer;
        public Cassa cassa;

        public MainWindow()
        {
            // Inizializzazione dei componenti
            InitializeComponent();

            comunicazioneServer = new ComunicazioneServer();

            comunicazioneServer.inviaRichiesta("start");
            this.cassa = new Cassa();
            this.cassa.deserializeXML(comunicazioneServer.ricevi());
        }

        private void btnCassiereA_Click(object sender, RoutedEventArgs e)
        {
            FinestraCassiere finestraCassiere = new FinestraCassiere(cassa, cassa.cassieri[0]);
            finestraCassiere.Show();
            this.Close();
        }

        private void btnCassiereB_Click(object sender, RoutedEventArgs e)
        {
            FinestraCassiere finestraCassiere = new FinestraCassiere(cassa, cassa.cassieri[1]);
            finestraCassiere.Show();
            this.Close();
        }

        private void btnCassiereC_Click(object sender, RoutedEventArgs e)
        {
            FinestraCassiere finestraCassiere = new FinestraCassiere(cassa, cassa.cassieri[2]);
            finestraCassiere.Show();
            this.Close();
        }
    }
}
