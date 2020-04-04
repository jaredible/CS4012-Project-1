package net.jaredible.reporter.model;

public enum AssignmentType {

	HOMEWORK("homework"), TEST("tests"), QUIZ("quizzes");

	private String folder;

	private AssignmentType(String folder) {
		this.folder = folder;
	}

	public String getFolder() {
		return folder;
	}

}
