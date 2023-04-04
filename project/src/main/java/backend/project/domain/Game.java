package backend.project.domain;

import jakarta.persistence.*;

@Entity
public class Game {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String title, studio, publisher;
	private int publicationYear;
	
	@ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
	
public Game() {}
	
	public Game(String title, String studio, String publisher, int publicationYear, Category category) {
		super();
		this.title = title;
		this.studio = studio;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
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
			return "Game [id=" + id + ", title=" + title + ", studio=" + studio + ", publisher=" + publisher
			+ ", publicationYear=" + publicationYear + ", category=" + this.getCategory() + "]";
		}
		return "Game [id=" + id + ", title=" + title + ", studio=" + studio + ", publisher=" + publisher
				+ ", publicationYear=" + publicationYear + "]";
	}
	
}
