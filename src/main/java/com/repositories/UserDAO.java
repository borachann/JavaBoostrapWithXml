package com.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.entities.user;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate; // new JdbcTemplate();
	
	
	public List<user> getAllUsers(){
		return jdbcTemplate.query("SELECT * FROM USERS", new RowMapper<user>(){

			@Override
			public user mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				System.out.println("rowNum" + rowNum);
				user User = new user();
				User.setId(rs.getInt("id"));
				User.setEmail(rs.getString("email"));
				return User;
			}
			
		});
	}
	
	public Boolean deleteUser(){
	int i =	jdbcTemplate.update("delete from users where id =?" , '1');
	if (i>0)
		return true;
	return false;
	}
	
	public Boolean addNewUser(user User){
		int i = jdbcTemplate.update("Insert into users(email) values(?)", User.getEmail());
		if(i>0)
			return true;
		else
			return false;
	}
}
