package net.jaredible.reporter.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import net.jaredible.reporter.model.Assignment;
import net.jaredible.reporter.model.AssignmentType;
import net.jaredible.reporter.model.Question;

public class PDUtils {

	public static String generate(String path, AssignmentType type, Assignment assignment, Map<Integer, Question> questions) {
		String result = null;

		try {
			result = test(new File(path, type.getFolder()).getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String test(String path) throws IOException {
		String result = null;

		PDDocument doc = null;
		try {
			doc = new PDDocument();
			PDPage page = new PDPage();
			doc.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(doc, page);

			PDFont pdfFont = PDType1Font.HELVETICA;
			float fontSize = 25;
			float leading = 1.5f * fontSize;

			PDRectangle mediabox = page.getMediaBox();
			float margin = 72;
			float width = mediabox.getWidth() - 2 * margin;
			float startX = mediabox.getLowerLeftX() + margin;
			float startY = mediabox.getUpperRightY() - margin;

			String text = "I am trying to create a PDF file with a lot of text contents in the document. I am using PDFBox";
			List<String> lines = new ArrayList<String>();
			int lastSpace = -1;
			while (text.length() > 0) {
				int spaceIndex = text.indexOf(' ', lastSpace + 1);
				if (spaceIndex < 0)
					spaceIndex = text.length();
				String subString = text.substring(0, spaceIndex);
				float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
				System.out.printf("'%s' - %f of %f\n", subString, size, width);
				if (size > width) {
					if (lastSpace < 0)
						lastSpace = spaceIndex;
					subString = text.substring(0, lastSpace);
					lines.add(subString);
					text = text.substring(lastSpace).trim();
					System.out.printf("'%s' is line\n", subString);
					lastSpace = -1;
				} else if (spaceIndex == text.length()) {
					lines.add(text);
					System.out.printf("'%s' is line\n", text);
					text = "";
				} else {
					lastSpace = spaceIndex;
				}
			}

			contentStream.beginText();
			contentStream.setFont(pdfFont, fontSize);
			contentStream.newLineAtOffset(startX, startY);
			for (String line : lines) {
				contentStream.showText(line);
				contentStream.newLineAtOffset(0, -leading);
			}
			contentStream.endText();
			contentStream.close();

			File f = new File(path, "test.pdf");
			doc.save(f);
			result = f.getPath();
		} finally {
			if (doc != null) {
				doc.close();
			}
		}

		return result;
	}

}

// pdfbox line break
// https://stackoverflow.com/questions/19635275/how-to-generate-multiple-lines-in-pdf-using-apache-pdfbox
