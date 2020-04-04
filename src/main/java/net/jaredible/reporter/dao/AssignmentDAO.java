package net.jaredible.reporter.dao;

import java.util.List;

import net.jaredible.reporter.model.Assignment;

public interface AssignmentDAO {

	public List<Assignment> getAssignments();

	public Assignment getAssignment(int id);

	public void saveAssignment(Assignment assignment);

	public void deleteAssignment(int id);

}
