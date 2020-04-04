package net.jaredible.reporter.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jaredible.reporter.dao.AssignmentDAO;
import net.jaredible.reporter.model.Assignment;
import net.jaredible.reporter.model.AssignmentType;
import net.jaredible.reporter.model.Question;
import net.jaredible.reporter.util.PDUtils;

@Service
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	private AssignmentDAO assignmentDAO;

	@Autowired
	private QuestionService questionService;

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
	public String process(Properties properties, String path) {
		String type = properties.getProperty("type");
		String title = properties.getProperty("title");
		String author = properties.getProperty("author");
		String dueDate = properties.getProperty("due");
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

		Assignment assignment = new Assignment(null, title, author, Timestamp.valueOf(dueDate), new HashSet<Question>(questions.values()));
		assignmentDAO.saveAssignment(assignment);

		String link = PDUtils.generate(path, AssignmentType.valueOf(type), assignment, questions);
		return link;
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
