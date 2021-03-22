package com.dao;


import java.util.List;

import com.models.User;

public interface IUser {
//	@Select("select * from user where id = #{id}")
	public User	getUserByID(int id);
	//pulic User getUserList
	
	public List<User> getUserList();	
	
	public void insertUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
}
