package backend.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backend.project.domain.GameRepository;
import backend.project.domain.Review;
import backend.project.domain.ReviewRepository;
import backend.project.domain.Category;
import backend.project.domain.CategoryRepository;
import backend.project.domain.Game;

@Controller
public class ProjectController {

	@Autowired
	private GameRepository grepository;

	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private ReviewRepository rrepository;

	@GetMapping("/gamelist")
	public String gamelist(Model model) {
		model.addAttribute("games", grepository.findAll());

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		System.out.println("USERNAME: " + username);
		model.addAttribute("name", username);

		return "gamelist";
	}

	@GetMapping("/home")
	public String home(Model model) {

		return "home";
	}

	@RequestMapping(value = "/addreview")
	public String addReview(Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("game", new Game());
		model.addAttribute("category", crepository.findAll());

		return "addreview";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveReview(@ModelAttribute("review") Review review, Model model) {
		Game game = review.getGame();
	    game.setTitle(review.getGame().getTitle());
	    game.setStudio(review.getGame().getStudio());
	    
	    
	    game.setCategory(crepository.findByName(game.getCategory().getName()));
	    rrepository.save(review);
	    
		return "redirect:gamelist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGame(@PathVariable("id") Long gameId, Model model) {
		grepository.deleteById(gameId);

		return "redirect:../gamelist";
	}

	@RequestMapping(value = "/edit/{id}")
	public String showModStu(@PathVariable("id") Long gameId, String rating, Model model) {
		model.addAttribute("game", grepository.findById(gameId));
		model.addAttribute("review", rrepository.findByRating(rating));
		model.addAttribute("category", crepository.findAll());

		return "editgame";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

}
