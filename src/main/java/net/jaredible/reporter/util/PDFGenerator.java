package net.jaredible.reporter.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import net.jaredible.reporter.model.Assignment;
import net.jaredible.reporter.model.Question;

public class PDFGenerator {

	public String generate(String path, String directory, String filename, Assignment assignment, Map<Integer, Question> questions) throws IOException {
		String result = null;

		PDDocument document = null;
		try {
			document = new PDDocument();
			PDPage page = new PDPage();

			document.addPage(page);

			PDPageContentStream stream = new PDPageContentStream(document, page);
			PDFont font = PDType1Font.HELVETICA;
			float fontSize = 12;
			float leading = 1.5f * fontSize;

			PDRectangle box = page.getMediaBox();
			float margin = 72;
			float width = box.getWidth() - 2 * margin;
			float startX = box.getLowerLeftX() + margin;
			float startY = box.getUpperRightY() - margin;

			stream.beginText();
			stream.setFont(font, fontSize);
			stream.newLineAtOffset(startX, startY);

			stream.showText(assignment.getTitle());
			stream.newLineAtOffset(0, -leading);
			stream.showText("Due: " + DateUtils.prettify(assignment.getDueDate()));
			stream.newLineAtOffset(0, -leading);

			stream.newLineAtOffset(0, -leading);

			for (Entry<Integer, Question> question : questions.entrySet()) {
				String text = question.getKey() + ". " + question.getValue().getContent();
				List<String> lines = getLines(text, font, width, fontSize);
				for (String line : lines) {
					stream.showText(line);
					stream.newLineAtOffset(0, -leading);
				}
				stream.newLineAtOffset(0, -leading);
			}

			stream.endText();
			stream.close();

			File file = new File(path + "/" + directory, filename + ".pdf");
			document.save(file);
			result = "/assignments/" + directory + "/" + filename + ".pdf";
		} finally {
			if (document != null) {
				document.close();
			}
		}

		return result;
	}

	private static List<String> getLines(String text, PDFont font, float width, float fontSize) throws IOException {
		List<String> lines = new ArrayList<String>();

		int lastSpace = -1;
		while (text.length() > 0) {
			int spaceIndex = text.indexOf(' ', lastSpace + 1);

			if (spaceIndex < 0) {
				spaceIndex = text.length();
			}

			String subString = text.substring(0, spaceIndex);
			float size = fontSize * font.getStringWidth(subString) / 1000;

			if (size > width) {
				if (lastSpace < 0) {
					lastSpace = spaceIndex;
				}

				subString = text.substring(0, lastSpace);
				lines.add(subString);
				text = text.substring(lastSpace).trim();
				lastSpace = -1;
			} else if (spaceIndex == text.length()) {
				lines.add(text);
				text = "";
			} else {
				lastSpace = spaceIndex;
			}
		}

		return lines;
	}

}

// pdfbox line break
// https://stackoverflow.com/questions/19635275/how-to-generate-multiple-lines-in-pdf-using-apache-pdfbox
