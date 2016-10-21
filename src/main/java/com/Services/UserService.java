package com.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.entities.user;
@Service
public class UserService implements UserServiceInf{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<user> listUsers() {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(user.class);
		
		@SuppressWarnings("unchecked")
		List<user> products = (List<user>)criteria.list();
		// TODO Auto-generated method stub
		return products;
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate; // new JdbcTemplate();



	@Override
	public List<user> listUser() {
		return jdbcTemplate.query("SELECT * FROM users", new EventMapper());
	}
	class EventMapper implements RowMapper<user>{		
		public user mapRow(ResultSet rs, int row) throws SQLException {
			
			System.out.println(1);
			user User = new user();
			User.setId(rs.getInt(1));
			User.setEmail(rs.getString(2));
			return User;
		}
	}

}
