package net.jaredible.reporter.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import net.jaredible.reporter.entity.Question;
import net.jaredible.reporter.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionsController {

	@Autowired
	private ServletContext context;

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
	public String uploadDataFile(Model model, @RequestParam("files") MultipartFile[] files) {
		test(files);

		Properties[] props = read(files);
		List<String> urls = questionService.test(context.getContextPath(), props);
		model.addAttribute("urls", urls);
		return "success";
	}

	private Properties[] read(MultipartFile[] files) {
		List<Properties> props = new ArrayList<Properties>();

		for (MultipartFile file : files) {
			try (InputStream input = file.getInputStream()) {
				Properties prop = new Properties();
				prop.load(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private void test(MultipartFile[] files) {
		try {
			String path = context.getRealPath("/WEB-INF");
			System.out.println(path);
			File file = new File(path, "files");
			System.out.println(file.exists());
			if (!file.exists()) {
				file.mkdir();
			}
			file = new File(file, "abc.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("test1");
			bw.newLine();
			bw.write("test2");
			bw.newLine();
			bw.write("test3");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// https://www.tutorialspoint.com/spring/spring_mvc_form_handling_example.htm
