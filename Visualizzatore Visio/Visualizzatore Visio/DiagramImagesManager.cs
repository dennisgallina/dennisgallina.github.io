using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.ComTypes;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Xml.Serialization;

namespace Visualizzatore_Visio
{
    public class DiagramImagesManager
    {
        public List<DiagramImage> diagramImages { get; set; }

        public DiagramImagesManager()
        {
            diagramImages = new List<DiagramImage>();
        }

        // Verifica se esiste un'immagine del diagramma con il percorso specificato
        public bool ifExist(String filePath)
        {
            for (int i = 0; i < diagramImages.Count; i++)
            {
                if (diagramImages[i].filePath == filePath)
                    return true;
            }

            // Se non viene trovata nessuna corrispondenza, restituisce false
            return false;
        }

        // Rimuove l'immagine del diagramma dal percorso specificato
        public void removeFromFilePath(String filePath)
        {
            for (int i = 0; i < diagramImages.Count; i++)
            {
                if (diagramImages[i].filePath == filePath)
                    diagramImages.RemoveAt(i);
            }
        }

        // Elimina l'immagine del diagramma nella posizione specificata
        public void delete(int posizione)
        {
            // Elimina fisicamente il file dal percorso dell'immagine del diagramma
            File.Delete(diagramImages[posizione].filePath);

            // Rimuove l'immagine del diagramma dalla lista
            diagramImages.RemoveAt(posizione);
        }

        // Verifica se il percorso dell'immagine del diagramma nella posizione specificata è cambiato
        public bool changedPath(int posizione)
        {
            // Verifica se il file esiste nel percorso specificato
            if (File.Exists(diagramImages[posizione].filePath) == true)
                return false;
            else
                return true;
        }

        // Restituisce l'oggetto DiagramImage corrispondente al percorso specificato
        public DiagramImage getDiagramImage(String filePath)
        {
            for (int i = 0; i < diagramImages.Count; i++)
            {
                if (diagramImages[i].filePath == filePath)
                    return diagramImages[i];
            }

            // Se non viene trovata nessuna corrispondenza, restituisce null
            return null;
        }

        // Carica i dati delle immagini del diagramma da un file XML
        public void caricaXML()
        {
            // Verifica se il file XML esiste
            if (File.Exists(System.AppDomain.CurrentDomain.BaseDirectory + "Diagram Images.xml") == false)
            {
                // Se il file non esiste, salva un nuovo file XML
                salvaXML();
                return;
            }

            // Deserializza il file XML per ottenere la lista delle immagini del diagramma
            XmlSerializer xmlSerializer = new XmlSerializer(typeof(List<DiagramImage>));
            StreamReader streamReader = new StreamReader("Diagram Images.xml");

            diagramImages = (List<DiagramImage>)xmlSerializer.Deserialize(streamReader);

            streamReader.Close();
        }

        // Salva i dati delle immagini del diagramma in un file XML
        public void salvaXML()
        {
            // Serializza la lista delle immagini del diagramma in un file XML
            XmlSerializer xmlSerializer = new XmlSerializer(typeof(List<DiagramImage>));
            StreamWriter streamWriter = new StreamWriter("Diagram Images.xml");

            xmlSerializer.Serialize(streamWriter, diagramImages);

            streamWriter.Close();
        }
    }
}
