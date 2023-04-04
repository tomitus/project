package backend.project.web;

import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.access.prepost.PreAuthorize;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.core.context.SecurityContextHolder;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.ResponseBody;

import backend.project.domain.GameRepository;
import backend.project.domain.CategoryRepository;
import backend.project.domain.Game;

@Controller
public class ProjectController {
	
	@Autowired
	private GameRepository grepository;
	
	@Autowired
	private CategoryRepository crepository;
				
		@GetMapping("/gamelist")
		public String gamelist(Model model) {
			model.addAttribute("games", grepository.findAll());
			
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername();
			System.out.println("USERNAME: " + username);
	    	model.addAttribute("name", username);

			return "gamelist";
		}
		
		@RequestMapping(value="/games", method = RequestMethod.GET)
	    public @ResponseBody List<Game> gameListRest() {	
	        return (List<Game>) grepository.findAll();
	    }
		
		@RequestMapping(value="/game/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Game> findGameRest(@PathVariable("id") Long gameId) {
		return grepository.findById(gameId);
		}

		@RequestMapping(value = "/add")
		public String addGame(Model model) {
			model.addAttribute("game", new Game());
			model.addAttribute("category", crepository.findAll());

			return "addgame";
		}

		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Game game) {
			grepository.save(game);

			return "redirect:gamelist";
		}

		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('ADMIN')")
		public String deleteGame(@PathVariable("id") Long gameId, Model model) {
			grepository.deleteById(gameId);

			return "redirect:../gamelist";
		}

		@RequestMapping(value = "/edit/{id}")
		public String showModStu(@PathVariable("id") Long gameId, Model model) {
			model.addAttribute("game", grepository.findById(gameId));
			model.addAttribute("category", crepository.findAll());

			return "editgame";
		}
		
		@RequestMapping(value="/login")
		public String login() {
			return "login";
		}

	
}
