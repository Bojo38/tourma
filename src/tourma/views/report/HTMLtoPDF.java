/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.report;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import tourma.data.Tournament;


class defaultFontProvider extends FontFactoryImp {

    private String _default;

    public defaultFontProvider(String def) {
        _default = def;
    }

    public Font getFont(String fontName, String encoding, boolean embedded, float size, int style, BaseColor color, boolean cached) {
        if (fontName == null || size == 0) {
            fontName = _default;
        }

        return super.getFont(fontName, encoding, embedded, size, style, color, cached);
    }
    }

/**
 *
 * @author WFMJ7631
 */
public class HTMLtoPDF {

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

