package net.jaredible.reporter.controller;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.jaredible.reporter.model.Question;
import net.jaredible.reporter.service.AssignmentService;
import net.jaredible.reporter.service.QuestionService;
import net.jaredible.reporter.util.PropertiesUtils;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	private ServletContext context;

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private QuestionService questionService;

	@GetMapping
	public ModelAndView viewQuestions() {
		List<Question> questions = questionService.getQuestions();
		ModelAndView mav = new ModelAndView("upload");
		mav.addObject("questions", questions);
		return mav;
	}

	@PostMapping
	public String generateAssignments(Model model, @RequestParam("files") MultipartFile[] files) {
		Properties[] props = PropertiesUtils.convert(files);
		List<String> links = assignmentService.process(props, context.getRealPath("/assignments"));
		model.addAttribute("links", links);
		return "success";
	}

}

// https://www.tutorialspoint.com/spring/spring_mvc_form_handling_example.htm
// spring mvc separation of concerns
// https://www.petrikainulainen.net/software-development/design/understanding-spring-web-application-architecture-the-classic-way/
