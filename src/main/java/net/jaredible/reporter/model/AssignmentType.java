package net.jaredible.reporter.model;

public enum AssignmentType {

	HOMEWORK("homework"), TEST("tests"), QUIZ("quizzes");

	private String directory;

	private AssignmentType(String directory) {
		this.directory = directory;
	}

	public String getDirectory() {
		return directory;
	}

}
