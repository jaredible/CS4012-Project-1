package net.jaredible.reporter.service;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.jaredible.reporter.dao.AssignmentDAO;
import net.jaredible.reporter.model.Assignment;
import net.jaredible.reporter.model.AssignmentType;
import net.jaredible.reporter.model.Question;

@Service
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	private AssignmentDAO assignmentDAO;

	@Autowired
	private QuestionService questionService;

	@Value("${generatorClass}")
	private String generatorClass;

	@Value("${generatorMethod}")
	private String generatorMethod;

	@Override
	public List<Assignment> getAssignments() {
		return assignmentDAO.getAssignments();
	}

	@Override
	public Assignment getAssignment(int id) {
		return assignmentDAO.getAssignment(id);
	}

	@Override
	public void saveAssignment(Assignment assignment) {
		assignmentDAO.saveAssignment(assignment);
	}

	@Override
	public void deleteAssignment(int id) {
		assignmentDAO.deleteAssignment(id);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String process(Properties properties, String path) {
		String type = properties.getProperty("type");
		String title = properties.getProperty("title");
		String author = properties.getProperty("author");
		String dueDate = properties.getProperty("due");
		String filename = properties.getProperty("filename");
		Map<Integer, Question> questions = new HashMap<Integer, Question>();

		for (Entry<Object, Object> prop : properties.entrySet()) {
			String key = String.valueOf(prop.getKey());
			String value = String.valueOf(prop.getValue());

			if (NumberUtils.isCreatable(key)) {
				if (NumberUtils.isCreatable(value)) {
					Integer questionId = Integer.parseInt(value);
					Question question = questionService.getQuestion(questionId);
					questions.put(Integer.valueOf(key), question);
				} else {
					Question question = new Question(null, value);
					questionService.saveQuestion(question);
					questions.put(Integer.valueOf(key), question);
				}
			}
		}

		Assignment assignment = new Assignment(null, title, author, Timestamp.valueOf(dueDate));
		assignmentDAO.saveAssignment(assignment);

		try {
			Class cls = Class.forName(generatorClass);
			Object obj = cls.getDeclaredConstructor().newInstance();

			Method method = cls.getMethod(generatorMethod, String.class, String.class, String.class, Assignment.class, Map.class);
			String link = (String) method.invoke(obj, path, AssignmentType.valueOf(type).getDirectory(), filename, assignment, questions);

			return link;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> process(Properties[] properties, String path) {
		List<String> links = new ArrayList<String>();

		for (Properties prop : properties) {
			String link = process(prop, path);
			if (link != null) {
				links.add(link);
			}
		}

		return links;
	}

}
