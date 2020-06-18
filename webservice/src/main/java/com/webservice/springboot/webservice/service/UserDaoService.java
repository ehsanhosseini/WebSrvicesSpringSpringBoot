package com.webservice.springboot.webservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.webservice.springboot.webservice.user.User;

@Component   //We need to tell it is Spring so we use componenet
public class UserDaoService {
	
	private static ArrayList<User> users = new ArrayList<User>();
	private static int usercount = 5; // current user we have 5 but when we add new user then countuser will start from 5
	
	static {
		users.add(new User(100, "Ehsan", new Date()));
		users.add(new User(101, "Majed", new Date()));
		users.add(new User(102, "Hamid", new Date()));
		users.add(new User(103, "Jimi", new Date()));
		users.add(new User(104, "Khashi", new Date()));
		
	}
	
	
	//find all users
	public List<User> findAll(){
		return users;
	}
	
	//Save user or add a new user
	//when you creat a new user Id will be calculating by backend . Primary key determin by database
	public User save(User user) {
		if(user.getId() == null) { //if user doesnt have user id(a new user) then it can start after ++5. next ont will be on 6 
			user.setId(++usercount);
		}
		users.add(user);
		return user;
	}
	
	// fine one specific user
	public User findOneUser(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	
	//for-each is syntactic sugar for using iterators (approach 2). You might need to use iterators if you need to modify collection in your loop. First approach will throw exception.
	public User deleteUserById(int id) {
		Iterator<User> itr =  users.iterator();
		while(itr.hasNext()) {
			User user = itr.next(); 
			if(user.getId() == id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
