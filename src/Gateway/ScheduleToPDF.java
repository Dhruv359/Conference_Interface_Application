package Gateway;
//import com.itextpdf.kernel.colors.DeviceRgb;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.pdf.tagging.StandardRoles;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleToPDF {

    public void scheduleToPDF(HashMap<String, List<String>> conferenceSchedule) throws IOException {
//        try {
//            FileOutputStream fos = new FileOutputStream("phase2/src/Data/schedule.pdf");
//            PdfWriter writer = new PdfWriter(fos);
//            PdfDocument pdf = new PdfDocument(writer);
//            Document document = new Document(pdf);
//            Paragraph title = new Paragraph("Conference Schedule").setFontSize(30f);
//            document.add(title);
//            for (Map.Entry<String, List<String>> entry : conferenceSchedule.entrySet()) {
//                Paragraph roomHeader = new Paragraph(entry.getKey()).setFontSize(20f);
//                roomHeader.getAccessibilityProperties().setRole(StandardRoles.H2);
//                document.add(roomHeader);
//                for (String s : entry.getValue()) {
//                    document.add(new Paragraph(s));
//                }
//            }
//            document.close();
//        }
//        catch (Exception e) {
//            System.out.println("error");
//        }
    }

}
