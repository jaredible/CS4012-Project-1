package net.jaredible.reporter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.jaredible.reporter.entity.Question;
import net.jaredible.reporter.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionsController {

	@Autowired
	private QuestionService questionService;

	@GetMapping
	public ModelAndView viewQuestions() {
		List<Question> questions = questionService.getQuestions();
		ModelAndView mav = new ModelAndView("questions");
		mav.addObject("questions", questions);
		return mav;
	}

	@PostMapping
	public String uploadDataFile() {
		return "success";
	}

}

// https://www.tutorialspoint.com/spring/spring_mvc_form_handling_example.htm
