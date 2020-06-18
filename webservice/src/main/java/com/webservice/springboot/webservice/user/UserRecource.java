package com.webservice.springboot.webservice.user;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservice.springboot.webservice.service.UserDaoService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*; // create a static import make it easy to use ControllerLinkBuilder


@RestController
public class UserRecource {

	@Autowired
	private UserDaoService service;

	// GET/users
	// retrieve all users as Json format in Browser
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}

	// Get/users/{id}
	// Retrive specific user

	// It is path variable for specific user so we use @PathVariable
	@GetMapping("/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {
		User user =  service.findOneUser(id);
		if(user == null) {   // if user is not we throw an exception(custom/userdefind exception)
			throw new UserNotFoundException("This "+id+" is not available, Please try again"); 
		} 
		
		// Here We use HATEOAS. I want to add the link to this retriveUser method.
		Resource<User> resource = new Resource<User>(user);
		// Here I can add linke to resource. ControllerLinkBuilder will enable us to create link to the method
		//ControllerLinkBuilder.linkTo(controller) because I used import static ControllerLinkBuilder we can write just linkTo(controller)  
		ControllerLinkBuilder link = 
				linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(link.withRel("all-users"));
		
		return resource;
	}

	// input - details of user
	// output - CREATED and return created URI
	// Create a user
	// Post/users
	// id will assign from backend. it will generate new id as explain it in save method
	// method in DAo
	// @RequestBody: What ever I pass in body of request it will map to user
	// parameter.
	// To able to send Post request we need to use rest client like PostMan
	@PostMapping("/users")
	public ResponseEntity<Object> createNewUser(@Valid @RequestBody User user) {  // for validation we uesed @Valid if new user created check for validation(for ex, name should be 2 charectors)
	
		User savedUser = service.save(user);
		// We need to add the status of created which is 201 so we know new user created
		// How to set the URI of the created resource into response like(http://localhost:8080/users/6) so when we create, end user we get id 6 and whne we get that user we can write(http://localhost:8080/users/6)

		//use it to return created statuse(ResponseEntity) 
		// .created --> I'm able to pass in what was the location of resource which was created. 
		//How to create URI for the location of the resouce which was created? ServletUriComponentsBuilder
		// current uri is /users and I want to append the current uri to /6 the newly created user.
		// /users/{id} I replace this {id} with savedUser.getId . 
		URI location = ServletUriComponentsBuilder  // return current request uri
		.fromCurrentRequest()       //  return current request uri
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	
	}
	
	// we can return 200 status it user deleted successfully
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteUserById(id);
		if(user == null) {   
			throw new UserNotFoundException("id "+id+ " Not Available For Deletion"); 
		} 
		
		
	}
	

}
