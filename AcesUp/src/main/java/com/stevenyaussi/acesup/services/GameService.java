package com.stevenyaussi.acesup.services;

import org.springframework.stereotype.Service;
import com.stevenyaussi.acesup.models.Game;
import com.stevenyaussi.acesup.models.User;
import com.stevenyaussi.acesup.repositories.GameRepository;

@Service
public class GameService {
    private final GameRepository gameRepository;
    

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    public Game createOutcome(Integer score, Boolean outcome, User user) {
    	Game game = new Game();
    	game.setScore(score);
    	game.setOutcome(outcome);
    	game.setUser(user);
    	return gameRepository.save(game);
	}
   

 
    
    
    
    
    
    
//    //create/edit course
//    public Course createCourse(Course course) {
//        return courseRepository.save(course);
//    }
//    public Course updateCourse(Course course, Long id) {
//    	 Optional<Course> c = courseRepository.findById(id);
//	     if(c.isPresent()) {
//	    	 Course  cor = c.get();
//	    	 cor.setName(course.getName());
//	    	 cor.setInstructor(course.getInstructor());
//	    	 cor.setCapacity(course.getCapacity());
//	    	 return courseRepository.save(cor);
//	     } else {
//	         return null;
//	     }
//    }
//    public List<Course> findCourses() {
//        return courseRepository.findAll();
//    }
//    public Course findCourse(Long id) {
//    	Optional<Course> c = courseRepository.findById(id);
//    	if(c.isPresent()) {
//            return c.get();
//    	} else {
//    	    return null;
//    	}
//    }
//    //joining course
//    public Attendee joiningCourse(User user, Course course) {
//    	Attendee a = new Attendee();
//    	a.setUser(user);
//    	a.setCourse(course);
//    	return attendeeRepository.save(a);
//    }
//    public List<Attendee> findAllAttendees(Long id) {
//    	return attendeeRepository.findAllByCourse_id(id);
//    
//    }
//    public Long countAttendees(Long id) {
//    	return attendeeRepository.countByCourse_id(id);
//    
//    }
//    //delete
//    public void deleteCourse(Long id) {
//    	courseRepository.deleteById(id);
//    }
//    public void removeUser(Long id) {
//    	attendeeRepository.deleteById(id);
//    }
}
