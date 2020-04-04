package net.jaredible.reporter.service;

import java.util.List;

import net.jaredible.reporter.model.Question;

public interface QuestionService {

	public List<Question> getQuestions();

	public Question getQuestion(int id);

	public void saveQuestion(Question question);

	public void deleteQuestion(int id);

}
