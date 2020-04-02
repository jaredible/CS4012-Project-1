package net.jaredible.reporter.service;

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
	public void processProperties(Properties props) {
	}

}
