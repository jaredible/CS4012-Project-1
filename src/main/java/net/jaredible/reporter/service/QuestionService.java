package net.jaredible.reporter.service;

import java.util.List;
import java.util.Properties;

import net.jaredible.reporter.model.Question;

public interface QuestionService {

	public List<Question> getQuestions();

	public Question getQuestion(int id);

	public void saveQuestion(Question question);

	public void deleteQuestion(int id);

	public List<String> getLinks(Properties props);

}
