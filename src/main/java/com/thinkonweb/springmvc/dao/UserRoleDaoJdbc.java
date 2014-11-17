package com.thinkonweb.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.thinkonweb.springmvc.domain.UserRole;

@Repository
public class UserRoleDaoJdbc implements UserRoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<UserRole> userRoleMapper = 
		new RowMapper<UserRole>() {
				public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserRole userRole = new UserRole();
				userRole.setId(rs.getInt("id"));
				userRole.setUserId(rs.getString("user_id"));
				userRole.setRole(rs.getString("role"));
				return userRole;
			}
		};


	@Override
	public void add(UserRole userRole) {
		this.jdbcTemplate.update(
				"insert into user_roles(user_id, role) " +
				"values(?,?)", 
					userRole.getUserId(), userRole.getRole());
	}

	@Override
	public void update(UserRole userRole) {
		this.jdbcTemplate.update(
				"update user_roles set role = ? where user_id = ? ", userRole.getRole(), userRole.getUserId());		
	}

	public UserRole get(String userId) {
		return this.jdbcTemplate.queryForObject("select * from user_roles where user_id = ?", new Object[] {userId}, this.userRoleMapper);
	} 
}