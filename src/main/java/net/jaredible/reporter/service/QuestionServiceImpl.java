package net.jaredible.reporter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jaredible.reporter.dao.QuestionDAO;
import net.jaredible.reporter.entity.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	@Transactional
	public List<Question> getQuestions() {
		return questionDAO.getQuestions();
	}

	@Override
	public List<String> test(String contextPath, Properties[] props) {
		List<String> result = new ArrayList<String>();

		if (props != null) {
		}

		return result;
	}

	private String processDataFile(Properties props) {
		StringBuilder result = new StringBuilder();

		result.append("testing"); // test

//		try {
//			PDDocument doc = PDFUtil.generateDocument("");
//			doc.save("");
//			doc.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		return result.toString();
	}

}
