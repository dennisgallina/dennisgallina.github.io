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
using System.Windows.Navigation;
using System.Windows.Shapes;
using Aspose;
using Aspose.Diagram;
using Microsoft.Win32;
using Aspose.Diagram.Saving;
using System.Reflection;
using System.Windows.Interop;
using System.Diagnostics.Eventing.Reader;
using System.Diagnostics;
using System.Drawing;
using System.Windows.Forms;

namespace Visualizzatore_Visio
{
    /// <summary>
    /// Logica di interazione per MainWindow.xaml
    /// </summary>
    public partial class MainWindow : System.Windows.Window
    {
        private String filePathVisio { get; set; }
        private DiagramImagesManager diagImgManag { get; set; }

        public MainWindow()
        {
            InitializeComponent();

            // Inizializzazione delle variabili
            this.filePathVisio = "";
            this.diagImgManag = new DiagramImagesManager();

            // Carica i dati delle immagini del diagramma dal file XML
            this.diagImgManag.caricaXML();

            // Aggiorna il layout dell'interfaccia utente
            updateLayout();
        }

        // Aggiorna il layout dell'interfaccia utente in base allo stato delle immagini del diagramma
        public void updateLayout()
        {
            if (btnSfoglia.Visibility == Visibility.Visible)
                btnSalva.Visibility = Visibility.Collapsed;
            else if (btnSalva.Visibility == Visibility.Visible)
                btnSfoglia.Visibility = Visibility.Collapsed;

            if (diagImgManag.diagramImages.Count() > 0)
            {
                btnVisualizza.Visibility = Visibility.Visible;
                btnElimina.Visibility = Visibility.Visible;
                cmbBoxDiagramImages.Visibility = Visibility.Visible;

                cmbBoxDiagramImages.Items.Clear();

                for (int i = 0; i < diagImgManag.diagramImages.Count(); i++)
                {
                    cmbBoxDiagramImages.Items.Insert(i, diagImgManag.diagramImages[i].filePath);

                    // Aggiunge l'indicatore "(Not found)" al percorso dell'immagine del diagramma se il percorso è cambiato
                    if (diagImgManag.changedPath(i))
                    {
                        cmbBoxDiagramImages.Items.Insert(i, diagImgManag.diagramImages[i].filePath + " (Not found)");
                    }
                }

                cmbBoxDiagramImages.SelectedIndex = diagImgManag.diagramImages.Count - 1;
            }
            else
            {
                btnVisualizza.Visibility = Visibility.Collapsed;
                btnElimina.Visibility = Visibility.Collapsed;
                cmbBoxDiagramImages.Visibility = Visibility.Collapsed;
            }
        }

        // Gestore dell'evento per il clic sul pulsante "Sfoglia"
        private void btnSfoglia_Click(object sender, RoutedEventArgs e)
        {
            lblMessage.Content = "Caricamento...";

            System.Windows.Forms.OpenFileDialog fileDialog = new System.Windows.Forms.OpenFileDialog();
            fileDialog.Title = "Seleziona un file";
            fileDialog.Filter = "File di Microsoft Visio|*.vsdx; *.vsd; *.vdx; *.vsx; *.vtx; *.vssx; *.vstx; *.vdmx; *.vssm; *.vstm";
            fileDialog.InitialDirectory = @"C:\";

            fileDialog.ShowDialog();

            // Visualizza la finestra di dialogo per selezionare il file Visio
            if (fileDialog.FileName == "")
            {
                updateLayout();
                lblMessage.Content = "";
                return;
            }

            filePathVisio = fileDialog.FileName;

            updateLayout();

            lblMessage.Content = System.IO.Path.GetFileName(filePathVisio);

            btnSfoglia.Visibility = Visibility.Collapsed;
            btnSalva.Visibility = Visibility.Visible;
        }

        // Gestore dell'evento per il clic sul pulsante "Salva"
        private void btnSalva_Click(object sender, RoutedEventArgs e)
        {
            lblMessage.Content = "Caricamento...";

            System.Windows.Forms.SaveFileDialog saveFileDialog = new System.Windows.Forms.SaveFileDialog();
            saveFileDialog.InitialDirectory = System.IO.Path.GetDirectoryName(filePathVisio);
            saveFileDialog.Filter = "Imamagine|*.jpeg; *.png";
            saveFileDialog.Title = "Salva file con nome";
            saveFileDialog.AddExtension = true;
            saveFileDialog.FileName = System.IO.Path.GetFileNameWithoutExtension(filePathVisio);
            saveFileDialog.ShowDialog();

            if (saveFileDialog.FileName == "" || saveFileDialog.FileName == System.IO.Path.GetFileNameWithoutExtension(filePathVisio))
            {
                updateLayout();
                lblMessage.Content = System.IO.Path.GetFileName(filePathVisio);
                return;
            }

            // Crea un nuovo oggetto DiagramImage per l'immagine del diagramma
            DiagramImage diagramImage = new DiagramImage(System.IO.Path.GetFileName(saveFileDialog.FileName), saveFileDialog.FileName);

            // Salva i dati delle immagini del diagramma nel file XML
            diagImgManag.salvaXML();

            // Specifica il formato del file di output
            ImageSaveOptions options = new ImageSaveOptions(SaveFileFormat.Jpeg);
            switch (saveFileDialog.FilterIndex)
            {
                case 1:
                    options = new ImageSaveOptions(SaveFileFormat.Jpeg);
                    break;
                case 2:
                    options = new ImageSaveOptions(SaveFileFormat.Png);
                    break;
            }

            // Carica il file Visio
            Diagram diagram = new Diagram(filePathVisio);

            // Salva solo una pagina, in base all'indice della pagina
            options.PageIndex = 0;

            // Salva l'immagine nella stessa directory del file Visio
            diagram.Save(diagramImage.filePath, options);

            // Aggiunge l'immagine del diagramma alla lista
            diagImgManag.diagramImages.Add(diagramImage);

            // Aggiorna il layout dell'interfaccia utente
            updateLayout();

            btnSfoglia.Visibility = Visibility.Visible;
            btnSalva.Visibility = Visibility.Collapsed;

            saveFileDialog.Dispose();
            diagram.Dispose();
            diagImgManag.salvaXML();
        }

        // Gestore dell'evento per il clic sul pulsante "Ricarica"
        private void btnRicarica_Click(object sender, RoutedEventArgs e)
        {
            filePathVisio = "";

            updateLayout();

            lblMessage.Content = "";

            btnSfoglia.Visibility = Visibility.Visible;
            btnSalva.Visibility = Visibility.Collapsed;
        }

        // Gestore dell'evento per il clic sul pulsante "Visualizza"
        private void btnVisualizza_Click(object sender, RoutedEventArgs e)
        {
            // Ottiene l'oggetto DiagramImage selezionato
            DiagramImage diaImgSelected = diagImgManag.diagramImages[cmbBoxDiagramImages.SelectedIndex];

            // Apre il file immagine con l'applicazione predefinita
            Process visualizza = new Process();
            visualizza.StartInfo.FileName = diaImgSelected.filePath;
            visualizza.Start();
        }

        // Gestore dell'evento per il clic sul pulsante "Elimina"
        private void btnElimina_Click(object sender, RoutedEventArgs e)
        {
            // Elimina l'immagine del diagramma selezionata
            diagImgManag.delete(cmbBoxDiagramImages.SelectedIndex);

            // Salva i dati delle immagini del diagramma nel file XML
            diagImgManag.salvaXML();

            // Aggiorna il layout dell'interfaccia utente
            updateLayout();
        }
    }
}