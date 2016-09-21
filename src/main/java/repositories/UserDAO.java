package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import entities.user;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate; // new JdbcTemplate();
	
	
	public List<user> getAllUsers(){
		return jdbcTemplate.query("SELECT * FROM USER", new RowMapper<user>(){

			@Override
			public user mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				user User = new user();
				User.setId(rs.getLong("id"));
				User.setEmail(rs.getString("email"));
				return User;
			}
			
		});
	}
	public Boolean addNewUser(user User){
		int i = jdbcTemplate.update("Insert into users(email) values(?)", User.getEmail());
		if(i>0)
			return true;
		else
			return false;
	}
}
