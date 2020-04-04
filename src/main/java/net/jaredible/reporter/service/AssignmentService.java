package net.jaredible.reporter.service;

import java.util.List;
import java.util.Properties;

import net.jaredible.reporter.model.Assignment;

public interface AssignmentService {

	public List<Assignment> getAssignments();

	public Assignment getAssignment(int id);

	public void saveAssignment(Assignment assignment);

	public void deleteAssignment(int id);

	public String process(Properties properties, String path);

	public List<String> process(Properties[] properties, String path);

}
