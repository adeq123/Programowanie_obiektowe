package browse;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import board.Crossword;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import dictionary.CwEntry;
import dictionary.CwEntry.Direction;

/**
 * 
 * @author krzysztof
 * 
 */
public class CwWriter implements Writer {

	@Override
	public void write(String path, LinkedList<Crossword> crosswordsList) throws IOException {
		File file = new File(path);
		Iterator<Crossword> it = crosswordsList.iterator();
		Crossword crossword;
		while (it.hasNext()) {
			crossword = it.next();
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile() + "/" + Long.toString(getUniqueID()));
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			if (crossword.getStrategy().toString().equals("Easy"))
				bufferedWriter.write("Easy");
			else
				bufferedWriter.write("Hard");
			bufferedWriter.newLine();
			bufferedWriter.write(Integer.toString(crossword.getBoard().getHeight()));
			bufferedWriter.newLine();
			bufferedWriter.write(Integer.toString(crossword.getBoard().getWidth()));
			bufferedWriter.newLine();
			Iterator<CwEntry> iter = crossword.getEntries().iterator();
			CwEntry c;
			while (iter.hasNext() == true) {
				c = iter.next();
				bufferedWriter.write(c.toString());
				bufferedWriter.newLine();
			}
			if (bufferedWriter != null)
				bufferedWriter.close();
		}
	}

	@Override
	public long getUniqueID() {
		return new Date().getTime();
	}

	/**
	 * Creates the PDF File with crossword
	 * 
	 * @param direct
	 *            - directory to write to
	 * @param cw
	 *            - crossword
	 * @return file with written crossword
	 * @throws DocumentException
	 * @throws IOException
	 */
	public File createPdf(File direct, Crossword cw) throws DocumentException, IOException {

		File file = new File(direct.getAbsolutePath() + "/" + Long.toString(new Date().getTime()) + ".pdf");
		Document document = new Document(PageSize.A4);
		document.addTitle("Crossword example");
		document.addAuthor("Krzysztof Spytkowski");
		document.addSubject("Crossword");
		document.addKeywords("crossword");
		document.addCreator("My program using iText");
		Paragraph p = new Paragraph();
		PdfWriter pdfWriter = null;
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
		Font f = new Font(bf, 16, Font.NORMAL);
		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
		document.open();
		PdfContentByte cb = pdfWriter.getDirectContent();

		Graphics2D g2 = new PdfGraphics2D(cb, PageSize.A4.getWidth(), PageSize.A4.getHeight());

		CwEntry temp;
		Iterator<CwEntry> it = cw.getROEntryIter();
		int k = 0;
		if (cw.getStrategy().getClass().getName().equals("board.EasytStrategy") == true) {
			it.next();
		}
		while (it.hasNext() == true) {
			temp = it.next();
			if (temp.getDir() == Direction.HORIZ) {
				if (k + 1 < 10)
					g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 30, temp.getX() * 30 + 50);
				else
					g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 22, temp.getX() * 30 + 50);
			} else {
				if (k + 1 < 10)
					g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 52, temp.getX() * 30 + 26);
				else
					g2.drawString(String.valueOf(k + 1) + " ", temp.getY() * 30 + 48, temp.getX() * 30 + 26);

			}
			// g2.drawString(String.valueOf(k + 1) + ".", 20, k*30 + 70 +
			// cw.getBoard().getHeight()*30);
			// g2.drawString(cw.getEntries().get(s).getClue(), 40, k*30 + 70 +
			// cw.getBoard().getHeight()*30);
			k++;
		}
		for (int i = 0; i < cw.getBoard().getHeight(); i++) {
			p.add(new Chunk("\n", f).setLineHeight(30));
			for (int j = 0; j < cw.getBoard().getWidth(); j++) {
				if (cw.getBoard().getCell(i, j).content != null) {
					/*
					 * cb.saveState(); cb.setColorStroke(GrayColor.BLACK);
					 * cb.setColorFill(GrayColor.WHITE); cb.rectangle(60 + j *
					 * 30, -45 + 840 - 30 * i - 30, 30, 30); // A4 // height //
					 * = // 840f cb.fillStroke(); cb.restoreState();
					 */
					g2.setColor(Color.BLACK);
					g2.drawRect(40 + j * 30, i * 30 + 30, 30, 30);
				}
			}
		}
		p.add(new Chunk("\n", f).setLineHeight(30));
		int i = 1, j = 0;
		if (cw.getStrategy().getClass().getName().equals("board.EasytStrategy") == true)
			j++;
		for (; j < cw.getEntries().size(); i++, j++) {
			p.add(new Chunk(i + ". " + cw.getEntries().get(j).getClue() + "\n\n", f).setLineHeight(10));
		}
		g2.dispose();
		document.add(p);
		document.close();
		if (pdfWriter != null)
			pdfWriter.close();
		if (document != null)
			document.close();
		return file;
	}

}
