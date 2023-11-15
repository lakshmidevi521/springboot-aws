package springboot.mysql.aws.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.mysql.aws.example.entity.User;
import springboot.mysql.aws.example.exception.ResourceNotFoundException;
import springboot.mysql.aws.example.repo.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	//get all users
	@GetMapping()
	public List<User> getAllUsers()
	{
		return this.userRepo.findAll();
	}
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id")Long userId)
	{
		return this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id"+userId));
	}
	
	// create user
	@PostMapping()
	public User createUser(@RequestBody User user)
	{
		return this.userRepo.save(user);
	}
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable Long id)
	{
	  User existing=this.userRepo.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("user not found with id"+id));
	  existing.setFirstname(user.getFirstname());
	  existing.setLastname(user.getLastname());
	  existing.setEmail(user.getEmail());
	  return this.userRepo.save(existing);  
	}
	//delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id)
	{
		User existingUser=this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id"+id));
		
		this.userRepo.delete(existingUser);
		
		return ResponseEntity.ok().build();
	}

}
