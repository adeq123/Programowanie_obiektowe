package crossword.browse;

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

import crossword.board.Crossword;
import crossword.dictionary.CwEntry;
import crossword.dictionary.CwEntry.Direction;
import crossword.exceptions.toLargeCrossowrdToCreatePdfFileException;

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
			File f = new File(file.getAbsoluteFile() + "/" + Long.toString(getUniqueID()));
			FileWriter fileWriter = new FileWriter(f);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			if (crossword.getStrategy().getClass().getSimpleName().equals("EasyStrategy"))
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
			f.setWritable(false);
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
	 * @throws toLargeCrossowrdToCreatePdfFileException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public File createPdf(File direct, Crossword cw) throws toLargeCrossowrdToCreatePdfFileException, DocumentException, IOException {

		if (cw.getBoard().getHeight() > 26 || cw.getBoard().getWidth() > 17)
			throw new toLargeCrossowrdToCreatePdfFileException("The dimensions of crossword are to large to create A4 pdf file");

		File file = new File(direct.getAbsolutePath() + "/" + Long.toString(new Date().getTime()) + ".pdf");
		Document document = new Document(PageSize.A4);
		document.addTitle("Crossword example");
		document.addAuthor("Krzysztof Spytkowski");
		document.addSubject("Crossword");
		document.addCreator("Crossword's creator");
		BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
		Font font = new Font(baseFont, 16, Font.NORMAL);
		PdfWriter pdfWriter = null;
		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));

		document.open();
		PdfContentByte cb = pdfWriter.getDirectContent();
		Graphics2D g2 = new PdfGraphics2D(cb, PageSize.A4.getWidth(), PageSize.A4.getHeight());

		Paragraph p = new Paragraph();
		CwEntry temp;
		Iterator<CwEntry> it = cw.getROEntryIter();
		int k = 0;
		if (cw.getStrategy().getClass().getSimpleName().equals("EasyStrategy") == true) {
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
			k++;
		}
		for (int i = 0; i < cw.getBoard().getHeight(); i++) {
			p.add(new Chunk("\n", font).setLineHeight(30));
			for (int j = 0; j < cw.getBoard().getWidth(); j++) {
				if (cw.getBoard().getCell(i, j).getContent() != null) {
					g2.setColor(Color.BLACK);
					g2.drawRect(40 + j * 30, i * 30 + 30, 30, 30);
				}
			}
		}
		p.add(new Chunk("\n", font).setLineHeight(30));
		int i = 1, j = 0;
		if (cw.getStrategy().getClass().getSimpleName().equals("EasyStrategy") == true) {
			j++;
		}
		for (; j < cw.getEntries().size(); i++, j++) {
			p.add(new Chunk(i + ". " + cw.getEntries().get(j).getClue() + "\n\n", font).setLineHeight(10));
		}
		g2.dispose();
		document.add(p);
		if (document != null)
			document.close();
		return file;
	}

}