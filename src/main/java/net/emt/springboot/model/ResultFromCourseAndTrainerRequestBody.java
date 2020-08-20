package net.emt.springboot.model;

public class ResultFromCourseAndTrainerRequestBody {
	private Long trainerId;
	private Long courseId;
	
	public ResultFromCourseAndTrainerRequestBody(Long trainerId, Long courseId) {
		super();
		this.trainerId = trainerId;
		this.courseId = courseId;
	}
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
}
