package net.jaredible.reporter.service;

import java.util.List;
import java.util.Properties;

import net.jaredible.reporter.entity.Question;

public interface QuestionService {

	public List<Question> getQuestions();

	public List<String> process(Properties[] props);

}
