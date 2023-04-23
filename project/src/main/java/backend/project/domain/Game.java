package backend.project.domain;

import jakarta.persistence.*;

//THIS CLASS IS FOR THE GAME OBJECT

@Entity
public class Game {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String title, studio;
	
	@ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reviewid")
    private Review review;
	
	
public Game() {}
	
	public Game(String title, String studio, Category category) {
		super();
		this.title = title;
		this.studio = studio;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null) {
			return "Game [id=" + id + ", title=" + title + ", studio=" + studio
			 + ", category=" + this.getCategory() + "]";
		}
		return "Game [id=" + id + ", title=" + title + ", studio=" + studio
				+ "]";
	}
	
}
