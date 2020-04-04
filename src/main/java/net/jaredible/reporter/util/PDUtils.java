package net.jaredible.reporter.util;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import net.jaredible.reporter.entity.Question;

public class PDUtils {

	public static String generateDocument(List<Question> questions) {
		PDDocument doc = new PDDocument();
		return null;
	}

}

// pdfbox line break
// https://stackoverflow.com/questions/19635275/how-to-generate-multiple-lines-in-pdf-using-apache-pdfbox
