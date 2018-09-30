package com.stevenyaussi.acesup.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.stevenyaussi.acesup.models.User;
import com.stevenyaussi.acesup.repositories.UserRepository;


@Service
public class AcesService {
    private final UserRepository userRepository;
    

    public AcesService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return falsea
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
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
