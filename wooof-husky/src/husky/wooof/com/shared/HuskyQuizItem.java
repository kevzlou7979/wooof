package husky.wooof.com.shared;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class HuskyQuizItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Long quizId;
	private String title;
	private int itemNo;
	private double point;
	private String explanation;
	
	
	public HuskyQuizItem() {}


	public HuskyQuizItem(Long quizId, String title, int itemNo, double point,
			String explanation) {
		super();
		this.quizId = quizId;
		this.title = title;
		this.itemNo = itemNo;
		this.point = point;
		this.explanation = explanation;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getQuizId() {
		return quizId;
	}


	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getItemNo() {
		return itemNo;
	}


	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}


	public double getPoint() {
		return point;
	}


	public void setPoint(double point) {
		this.point = point;
	}


	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


}
