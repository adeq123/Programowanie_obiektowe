package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import board.Crossword;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator {

	public File createPdf(File direct, Crossword cw) throws DocumentException,
			IOException {
		File file = new File(direct.getAbsolutePath() + "/"
				+ Long.toString(new Date().getTime()) + ".pdf");
		Document document = new Document(PageSize.A4);
		document.addTitle("Crossword example");
		document.addAuthor("Krzysztof Spytkowski");
		document.addSubject("Crossword");
		document.addKeywords("crossword");
		document.addCreator("My program using iText");
		Paragraph p = new Paragraph();
		PdfWriter pdfWriter = null;
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,
				BaseFont.CP1250, BaseFont.EMBEDDED);
		Font f = new Font(bf, 16, Font.NORMAL);
		pdfWriter = PdfWriter.getInstance(document,
				new FileOutputStream(file.getAbsoluteFile()));
		document.open();
		PdfContentByte cb = pdfWriter.getDirectContent();
		for (int i = 0; i < cw.getBoard().getHeight(); i++) {
			p.add(new Chunk((i + 1) + "\n", f).setLineHeight(30));
			for (int j = 0; j < cw.getBoard().getWidth(); j++) {
				if (cw.getBoard().getCell(i, j).content != null) {
					cb.saveState();
					cb.setColorStroke(GrayColor.BLACK);
					cb.setColorFill(GrayColor.WHITE);
					cb.rectangle(60 + j * 30, -45 + 840 - 30 * i - 30, 30, 30); // A4
																				// height
																				// =
																				// 840f
					cb.fillStroke();
					cb.restoreState();
				}
			}
		}
		p.add(new Chunk("\n", f).setLineHeight(30));
		for (int i = 1; i <= cw.getBoard().getHeight(); i++) {
			p.add(new Chunk(i + ". " + cw.getEntries().get(i).getClue()
					+ "\n\n", f).setLineHeight(15));
		}
		document.add(p);
		document.close();
		if (pdfWriter != null)
			pdfWriter.close();
		if (document != null)
			document.close();
		return file;
	}
}
