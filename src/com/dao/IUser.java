package com.dao;

import org.apache.ibatis.annotations.Select;

import com.models.User;

public interface IUser {
	@Select("select * from user where id = #{id}")
	public User	getUserByID(int id);
}
