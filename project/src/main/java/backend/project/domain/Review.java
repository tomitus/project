package backend.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long reviewId;
	private String rating, comment;
	private double hours;
	
	@ManyToOne
    @JoinColumn(name = "userId")
    private User user;

	@ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;
	
	private String gameTitle;
	private String gameStudio;
	private Category gameCategory;
	
	public Review() {}
	
	public Review(User user, Game game,  String rating, String comment, Double hours, String gameTitle, String gameStudio, Category gameCategory) {
		super();
		this.user = user;
		this.game = game;	
		this.rating = rating;
		this.comment = comment;
		this.hours = hours;
		this.gameTitle = game.getTitle();
		this.gameStudio = game.getStudio();
		this.gameCategory = game.getCategory();
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getGameStudio() {
		return gameStudio;
	}

	public void setGameStudio(String gameStudio) {
		this.gameStudio = gameStudio;
	}

	public Category getGameCategory() {
		return gameCategory;
	}

	public void setGameCategory(Category gameCategory) {
		this.gameCategory = gameCategory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	

}
