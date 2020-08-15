package net.emt.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "testResult")
public class TestResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="category_id")
    private Category category;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="trainer")
    private Trainer trainer;
	
	@Column(name="score")
	private Long score;
	
	@Column(name="all_questions")
	private Long allQuestions;
	
	@Column(name="student_name")
	private String studentName;
	
	@Column(name="student_surname")
	private String studentSurname;
	
	@Column(name="course_date")
	private Date courseDate;

	
	public TestResult() {
		super();
	}


	public TestResult(Long score, Long allQuestions, String studentName,
			String studentSurname, Date courseDate) {
		super();
		this.score = score;
		this.allQuestions = allQuestions;
		this.studentName = studentName;
		this.studentSurname = studentSurname;
		this.courseDate = courseDate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Trainer getTrainer() {
		return trainer;
	}


	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}


	public Long getScore() {
		return score;
	}


	public void setScore(Long score) {
		this.score = score;
	}


	public Long getAllQuestions() {
		return allQuestions;
	}


	public void setAllQuestions(Long allQuestions) {
		this.allQuestions = allQuestions;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getStudentSurname() {
		return studentSurname;
	}


	public void setStudentSurname(String studentSurname) {
		this.studentSurname = studentSurname;
	}


	public Date getCourseDate() {
		return courseDate;
	}


	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}	
	
}
