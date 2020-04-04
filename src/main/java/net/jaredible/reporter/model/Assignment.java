package net.jaredible.reporter.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "due_date")
	private Timestamp dueDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "assignment_question", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "assignment_id"))
	private Set<Question> questions = new HashSet<Question>();

	public Assignment() {
	}

	public Assignment(Integer id, String title, String author, Timestamp dueDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.dueDate = dueDate;
	}

	public Assignment(Integer id, String title, String author, Timestamp dueDate, Set<Question> questions) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.dueDate = dueDate;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
