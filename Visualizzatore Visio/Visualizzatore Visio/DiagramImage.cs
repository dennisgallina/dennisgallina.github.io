using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Visualizzatore_Visio
{
    public class DiagramImage
    {
        // Nome del file dell'immagine del diagramma
        public String fileName { get; set; }
        // Percorso del file dell'immagine del diagramma
        public String filePath { get; set; }

        public DiagramImage()
        {
            this.fileName = "";
            this.filePath = "";
        }

        public DiagramImage(String fileName, String filePath)
        {
            this.fileName = fileName;
            this.filePath = filePath;
        }
    }

}
