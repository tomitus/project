package backend.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import backend.project.domain.Category;
import backend.project.domain.CategoryRepository;
import backend.project.domain.Game;
import backend.project.domain.GameRepository;
import backend.project.domain.UserRepository;
import backend.project.domain.User;



@SpringBootApplication
public class ProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	
	
	@Bean
	public CommandLineRunner bookDemo(GameRepository grepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("example data");
			crepository.save(new Category("MMO"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Thriller"));
			crepository.save(new Category("Puzzle"));
			crepository.save(new Category("Action"));
			crepository.save(new Category("Adventure"));
			crepository.save(new Category("Simulation"));
			crepository.save(new Category("Sports"));
			crepository.save(new Category("Racing"));
			crepository.save(new Category("RPG"));
			//available categories are hardcoded here 
			
			grepository.save(new Game("Portal", "Valve", crepository.findByName("Puzzle")));
			grepository.save(new Game("World of warcraft", "Blizzard Entertainment", crepository.findByName("MMO")));
			grepository.save(new Game("Halo 3", "Bungie", crepository.findByName("Action")));
			grepository.save(new Game("CS:GO", "Valve", crepository.findByName("Action")));
			grepository.save(new Game("Animal Crossing", "Nintendo", crepository.findByName("Simulation")));
			grepository.save(new Game("Total war: WARHAMMER II", "CREATIVE ASSEMBLY", crepository.findByName("Simulation")));
			grepository.save(new Game("Valheim", "Iron Gate AB", crepository.findByName("Adventure")));
			grepository.save(new Game("Far Cry  5", "Ubisoft", crepository.findByName("Action")));
			grepository.save(new Game("Raft", "Redbeet Interactive", crepository.findByName("Adventure")));
			grepository.save(new Game("Deep Rock Galactic", "Ghost Ship games", crepository.findByName("Action")));
			grepository.save(new Game("XCOM 2", "Firaxis Games", crepository.findByName("Action")));
			grepository.save(new Game("Satisfactory", "Coffee Stain Studios", crepository.findByName("Simulation")));
			grepository.save(new Game("Stardew valley", "ConcernedApe", crepository.findByName("Adventure")));
			grepository.save(new Game("Sekiro: Shadows Die Twice", "From Software", crepository.findByName("Action")));
			//here are some games premade so the user doesn't need to input them all
			
			
			User user1 = new User("Tomi", "$2a$10$gP.I3mvNRZnSqbcQ6LzHm.LpsryxqhSYxyUhjLZ/l18V.KgXpA1ae", "USER");
			User user2 = new User("admin", "$2a$10$YYj56XNu0YI4dc/X4wYiOeHcXG.yUU9gcg9PfFHBzF.vv4Oc9S2vO", "ADMIN");
			//2 premade users
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all games");
			for (Game game : grepository.findAll()) {
				log.info(game.toString());
			}
		};
		}
}
