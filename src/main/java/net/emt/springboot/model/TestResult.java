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
	@JoinColumn(name="course_id")
    private Course course;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="trainer")
    private Trainer trainer;
	
	@Column(name="score")
	private Long score;
	
	@Column(name="all_questions")
	private Long allQuestions;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="student")
	private Student student;
	
	@Column(name="course_date")
	private Date courseDate;

	
	@Column(name="result")
	private Float result;

	
	public TestResult() {
		super();
	}


	public TestResult(Long score, Long allQuestions, Student student, Date courseDate, float result) {
		super();
		this.score = score;
		this.allQuestions = allQuestions;
		this.student = student;
		this.courseDate = courseDate;
		this.result = result;
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

	

	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
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


	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}


	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}


	public Date getCourseDate() {
		return courseDate;
	}


	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}


	public float getResult() {
		return result;
	}


	public void setResult(float result) {
		this.result = result;
	}	
	
	
}
