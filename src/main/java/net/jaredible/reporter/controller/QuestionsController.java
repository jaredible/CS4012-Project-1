package net.jaredible.reporter.controller;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	public String uploadDataFile(Model model, @RequestParam("file") MultipartFile file) {
		questionService.processProperties(getTest());
		model.addAttribute("message", "Upload successful!");
		return "success";
	}

	private Properties getTest() {
		Properties props = new Properties();

		props.setProperty("new", "What is 2+2?");
		props.setProperty("assign:2", "Is 3+3=6?");
		props.setProperty("test:1", "Is |-1|=1?");
		props.setProperty("assign:2.DB", "2");
		props.setProperty("test:2.DB", "3");

		return props;
	}

}

// https://www.tutorialspoint.com/spring/spring_mvc_form_handling_example.htm
