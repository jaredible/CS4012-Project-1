package net.jaredible.reporter.dao;

import java.util.List;

import net.jaredible.reporter.entity.Question;

public interface QuestionDAO {

	public List<Question> getQuestions();

	public Question getQuestion(int id);

	public void saveQuestion(Question question);

	public void deleteQuestion(int id);

}
