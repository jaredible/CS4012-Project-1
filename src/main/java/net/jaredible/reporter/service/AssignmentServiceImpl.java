package net.jaredible.reporter.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.jaredible.reporter.dao.AssignmentDAO;
import net.jaredible.reporter.dao.QuestionDAO;
import net.jaredible.reporter.model.Assignment;
import net.jaredible.reporter.model.Question;
import net.jaredible.reporter.util.PDUtils;
import net.jaredible.reporter.util.PropertiesUtils;

public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	private AssignmentDAO assignmentDAO;

	@Autowired
	private QuestionDAO questionDao;

	@Override
	@Transactional
	public List<Assignment> getAssignments() {
		return assignmentDAO.getAssignments();
	}

	@Override
	@Transactional
	public Assignment getAssignment(int id) {
		return assignmentDAO.getAssignment(id);
	}

	@Override
	@Transactional
	public void saveAssignment(Assignment assignment) {
		assignmentDAO.saveAssignment(assignment);
	}

	@Override
	@Transactional
	public void deleteAssignment(int id) {
		assignmentDAO.deleteAssignment(id);
	}

	@Override
	public String process(Properties properties) {
		String title = properties.getProperty("title");
		String author = properties.getProperty("author");
		Set<Question> questions = new HashSet<Question>();

		for (Entry<Object, Object> prop : properties.entrySet()) {
			String key = String.valueOf(prop.getKey());
			String value = String.valueOf(prop.getValue());

			if (NumberUtils.isCreatable(key)) {
				if (NumberUtils.isCreatable(value)) {
					Integer questionId = Integer.parseInt(value);
					Question question = questionDao.getQuestion(questionId);
					questions.add(question);
					// references question from DB
				} else {
					// new question, so save it
				}
			}
		}

		Assignment assignment = new Assignment(null, title, author);
		assignmentDAO.saveAssignment(assignment);

		String link = PDUtils.generate(assignment);
		return link;
	}

	@Override
	public List<String> process(Properties[] properties) {
		List<String> links = new ArrayList<String>();

		for (Properties prop : properties) {
			String link = process(prop);
			if (link != null) {
				links.add(link);
			}
		}

		return links;
	}

}
