package net.jaredible.reporter.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.jaredible.reporter.dao.QuestionDAO;
import net.jaredible.reporter.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private ServletContext context;

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	@Transactional
	public List<Question> getQuestions() {
		return questionDAO.getQuestions();
	}

	@Override
	@Transactional
	public Question getQuestion(int id) {
		return questionDAO.getQuestion(id);
	}

	@Override
	@Transactional
	public void saveQuestion(Question question) {
		questionDAO.saveQuestion(question);
	}

	@Override
	@Transactional
	public void deleteQuestion(int id) {
		questionDAO.deleteQuestion(id);
	}

	@Override
	public List<String> getLinks(Properties props) {
		List<String> result = new ArrayList<String>();

		for (Entry<Object, Object> entry : props.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
			props.remove(entry.getKey());
		}

		return result;
	}

	private void test(MultipartFile[] files) {
		try {
			String path = context.getRealPath("/files");
			System.out.println(path);
			File file = new File(path, "abc.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("test1");
			bw.newLine();
			bw.write("test2");
			bw.newLine();
			bw.write("test3");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
