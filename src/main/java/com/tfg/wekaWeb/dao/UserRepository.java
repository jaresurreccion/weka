package com.tfg.wekaWeb.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.tfg.wekaWeb.dto.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

	
	public User findByUsername (String username);
	
	public User findByPrimerApellido (String primerApellido);
	
	public User findBysegundoApellido (String segundoApellido);
	
	public User findById(int id);
	
	@Query("SELECT p FROM User p")
	public List<User> findAllUser();

	
}
