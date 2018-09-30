package com.stevenyaussi.acesup.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stevenyaussi.acesup.models.Card;
import com.stevenyaussi.acesup.models.CardPile;
import com.stevenyaussi.acesup.models.DeckOfCards;
import com.stevenyaussi.acesup.models.User;
import com.stevenyaussi.acesup.services.AcesService;
import com.stevenyaussi.acesup.validator.UserValidator;

@Controller
public class AcesController {
    private final AcesService acesService;
    private final UserValidator userValidator;
//    static Card card;
//    static CardPile cardpile;
//    static DeckOfCards deckofcards;
//	static DeckOfCards d;
//	static  CardPile cp1;
//	static  CardPile cp2;
//	static  CardPile cp3;
//	static  CardPile cp4;
    
    
    public AcesController(AcesService acesService, UserValidator userValidator) {
        this.acesService = acesService;
        this.userValidator = userValidator;
        
    }
    
    
	@RequestMapping("/")
    public String login(@ModelAttribute("user") User user) {
        return "loginReg.jsp";
    }
    //login_reg
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
    	userValidator.validate(user, result);
    	User e = acesService.findByEmail(user.getEmail());
    	if(e != null) {
    		model.addAttribute("errorReg", "Email already exsists");
    		return "loginReg.jsp";
    	}
    	if(result.hasErrors()) {
        	return "loginReg.jsp";
        } else {
        	User u =acesService.registerUser(user);
        	session.setAttribute("userID", u.getId());
        	return "redirect:/acesup";
        }
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") User user, @RequestParam("e") String email, @RequestParam("p") String password, Model model, HttpSession session) {
        if(email.length() == 0) {
        	model.addAttribute("error", "Email required");
        	return "loginReg.jsp";
        }
        if( password.length() == 0 ) {
        	model.addAttribute("error", "Password required");
        	return "loginReg.jsp";
        }
    	boolean isAuthenticated = acesService.authenticateUser(email, password);
        if(isAuthenticated) {
	        User u = acesService.findByEmail(email);
	        session.setAttribute("userID", u.getId());
	        return "redirect:/acesup";
        } else {
        	model.addAttribute("error", "InvalidCredentials. Please try again.");
        	return "loginReg.jsp";
        }
        
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    @RequestMapping("/reset")
    public String reset(HttpSession session) {
    	session.removeAttribute("deck");
    	session.removeAttribute("currentP1");
    	session.removeAttribute("currentP2");
    	session.removeAttribute("currentP3");
    	session.removeAttribute("currentP4");
    	session.removeAttribute("currentDiscard");
    	session.removeAttribute("score");
        return "redirect:/acesup";
    }
    
    @RequestMapping("/acesup")
    public String home(HttpSession session, Model model) throws IOException{
    	
        //Check if logged in
        Long userID = (Long) session.getAttribute("userID");
        if(userID == null) {
        	return "redirect:/";
        }
        //find user
        User u = acesService.findUserById(userID);
        model.addAttribute("user", u);
        //check deck id
        DeckOfCards deckID = (DeckOfCards) session.getAttribute("deck");
        if(deckID == null) {
        	DeckOfCards d = new DeckOfCards();
         	//Shuffle Deck
         	d.shuffle();
         	session.setAttribute("deck", d);
        }
        //check CardPile 1 exists
        CardPile cp1_start = (CardPile) session.getAttribute("currentP1");
        if(cp1_start == null) {
        	CardPile cp1 = new CardPile();
        	//set cp1
        	session.setAttribute("currentP1", cp1);
        	
        }
        //check CardPile 2 exists
        CardPile cp2_start = (CardPile) session.getAttribute("currentP2");
        if(cp2_start == null) {
        	CardPile cp2 = new CardPile();
        	//set cp2
        	session.setAttribute("currentP2", cp2);
        }
        //check CardPile 3 exists
        CardPile cp3_start = (CardPile) session.getAttribute("currentP3");
        if(cp3_start == null) {
        	CardPile cp3 = new CardPile();
        	//set cp3
        	session.setAttribute("currentP3", cp3);
        }
        //check CardPile 4 exists
        CardPile cp4_start = (CardPile) session.getAttribute("currentP4");
        if(cp4_start == null) {
        	CardPile cp4 = new CardPile();
        	//set cp4
        	session.setAttribute("currentP4", cp4);
        }
      //check DiscardCardPile 4 exists
        CardPile discard_start = (CardPile) session.getAttribute("currentDiscard");
        if(discard_start == null) {
        	CardPile discard = new CardPile();
        	//set cp4
        	session.setAttribute("currentDiscard", discard);
        }
        //check DiscardCardPile 4 exists
        Integer score = (Integer) session.getAttribute("score");
        if(score == null) {
        	int scoreInt = -52;
        	//set cp4
        	session.setAttribute("score", scoreInt);
        }
	        
        return "homePage.jsp";
    }
    
//    @RequestMapping(value = "/discardCard", method = RequestMethod.GET)
//    public @ResponseBody String processAJAXRequest(@RequestParam("pile") int pile, Model model, DeckOfCards d, CardPile cp1) {
//   
//    	cp1.addToPile(d.deal());
//		model.addAttribute("cp1", cp1.getPile());
//		System.out.println(cp1.getPile());
//		System.out.println(pile);
//    	
//    	if(pile == 1) {
//		
//		} else if (pile == 2) {
//			System.out.println(pile);
//		} else if (pile == 3) {
//			System.out.println(pile);
//		} else if (pile == 4) {
//			System.out.println(pile);
//		} else {
//			System.out.println("Invalid Param");
//		}
//        String response = "homePage";
//        // Process the request
//        return response;
//        
//    }
//    

    
   
//    @RequestMapping(value="/addCourse", method=RequestMethod.POST)
//    public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, HttpSession session, Model model) {
//    	if(result.hasErrors()) {
//            return "addCourse.jsp";
//        } else {
//        	courseService.createCourse(course);
//        	return "redirect:/courses";
//        }
//    } 
//   
//    //join course
//    @RequestMapping("/courses/{id}")
//    public String courseJoin(@PathVariable("id") Long id, Model model, HttpSession session) {
//    	Long userID = (Long) session.getAttribute("userID");
//        if(userID == null) {
//        	return "redirect:/";
//        }
//    	Course c = courseService.findCourse(id);
//    	model.addAttribute("course", c);
//    	List<Attendee> a = courseService.findAllAttendees(id);
//    	Long count = courseService.countAttendees(id);
//    	model.addAttribute("attendee", a);
//    	model.addAttribute("count", count);
//        return "joinCourse.jsp";
//    }
//    
//    //joining course
//    @RequestMapping(value="/joinCourse/{id}", method=RequestMethod.GET)
//    public String joiningCourse(@PathVariable("id") Long id, HttpSession session) {
//    	Long userID = (Long) session.getAttribute("userID");
//        User u = courseService.findUserById(userID);
//        Course c = courseService.findCourse(id);
//        courseService.joiningCourse(u, c);
//        return "redirect:/courses/"+id;
//        
//    }
//    
//    //edit course
//    @RequestMapping("/courses/{id}/edit")
//    public String courseEdit(@PathVariable("id") Long id, Model model, HttpSession session) {
//    	Long userID = (Long) session.getAttribute("userID");
//        if(userID == null) {
//        	return "redirect:/";
//        }
//    	Course c = courseService.findCourse(id);
//    	model.addAttribute("course", c);
//        return "editCourse.jsp";
//    }
//    
//    @RequestMapping(value="/editCourse/{id}", method=RequestMethod.PUT)
//    public String update(@Valid @ModelAttribute("course") Course course, BindingResult result, @PathVariable("id") Long id) {
//        if (result.hasErrors()) {
//            return "editCourse.jsp";
//        } else {
//            courseService.updateCourse(course, id);
//            return "redirect:/courses";
//        }
//    }
//    //delete
//    @RequestMapping(value="/courses/{id}/delete", method=RequestMethod.GET)
//    public String deleteCourse(@PathVariable("id") Long id, HttpSession session) {
//        courseService.deleteCourse(id);
//        return "redirect:/courses";
//        
//    }
//    @RequestMapping(value="/removeFromCourse/{id}", method=RequestMethod.GET)
//    public String removeUser(@PathVariable("id") Long id, HttpSession session) {
//        courseService.removeUser(id);
//        return "redirect:/courses";
//        
//    }
    
   
}