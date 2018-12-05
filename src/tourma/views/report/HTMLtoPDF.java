/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.report;

import java.io.FileOutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.IOException;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class HTMLtoPDF {

    
    
    public static void exportToPDF_Landscape(FileOutputStream output, String source, String title)
    {
        com.itextpdf.text.Rectangle A4H=new Rectangle(PageSize.A4.getHeight(),PageSize.A4.getWidth());
        exportToPDF(output,source,title,A4H);
    }
            
    public static void exportToPDF(FileOutputStream output, String source, String title)
    {
        exportToPDF(output,source,title,PageSize.A4);
    }
    
    public static void exportToPDF(FileOutputStream output, String source, String title, Rectangle size) 
    {
        exportToPDF(output,source,title,size,false);
    }
    
    public static void exportToPDF(FileOutputStream output, String source, String title, Rectangle size,boolean excludeCutTable) {
        try {
            Document document = new Document(size);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, output);            
            document.open();
            document.addAuthor(Tournament.getTournament().getParams().getTournamentOrga());
            document.addCreator("TourMa");
            document.addSubject(Tournament.getTournament().getParams().getTournamentName());
            document.addCreationDate();
            document.addTitle(title);           
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            
            worker.parseXHtml(pdfWriter, document, new StringReader(source));           
            

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
}

