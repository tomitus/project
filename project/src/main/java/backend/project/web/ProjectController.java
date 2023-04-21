package backend.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import backend.project.domain.GameRepository;
import backend.project.domain.Review;
import backend.project.domain.ReviewRepository;
import backend.project.domain.User;
import backend.project.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
	
	@Autowired
	private UserRepository urepository;

	@GetMapping("/gamelist")
	public String gamelist(Model model) {
		model.addAttribute("reviews", rrepository.findAll());

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		System.out.println("USERNAME: " + username);
		model.addAttribute("name", username);

		return "gamelist";
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("games", grepository.findAll());
		
		return "home";
	}

	@RequestMapping(value = "/addreview/{id}")
	public String addReview(@PathVariable("id") Long gameId, Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("game", grepository.findById(gameId));
		model.addAttribute("category", crepository.findAll());

		return "addreview";
	}
	
	@RequestMapping(value = "/addreview")
	public String addReviewNew(Model model) {
		
	    
		model.addAttribute("review", new Review());
		model.addAttribute("game", grepository.findAll());

		return "addreview";
	}
	
	@RequestMapping(value = "/addgame")
	public String addGame(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("category", crepository.findAll());

		return "addgame";
	}
	

	@RequestMapping(value = "/savegame", method = RequestMethod.POST)
	public String saveGame(Game game) {
		grepository.save(game);
		
		return "redirect:gamelist";
	}
	
	@RequestMapping(value = "/savereview", method = RequestMethod.POST)
	public String saveReview(@ModelAttribute("review")Review review, Authentication authentication) {
		User currUser = urepository.findByUsername(authentication.getName());
		review.setUser(currUser);
		rrepository.save(review);
		
		return "redirect:gamelist";
	}
	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteReview(@PathVariable("id") Long reviewId, Model model) {
		rrepository.deleteById(reviewId);

		return "redirect:../gamelist";
	}

	@RequestMapping(value = "/editgame/{id}")
	public String showModStu(@PathVariable("id") Long gameId, Model model) {
		model.addAttribute("game", grepository.findById(gameId));
		model.addAttribute("category", crepository.findAll());

		return "editgame";
	}

	@GetMapping("/login")
	public String login(Model model) {
		
		return "login";
	}
	
}
