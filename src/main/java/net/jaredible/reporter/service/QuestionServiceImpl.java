package net.jaredible.reporter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jaredible.reporter.dao.QuestionDAO;
import net.jaredible.reporter.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	public List<Question> getQuestions() {
		return questionDAO.getQuestions();
	}

	@Override
	public Question getQuestion(int id) {
		return questionDAO.getQuestion(id);
	}

	@Override
	public void saveQuestion(Question question) {
		questionDAO.saveQuestion(question);
	}

	@Override
	public void deleteQuestion(int id) {
		questionDAO.deleteQuestion(id);
	}

}
