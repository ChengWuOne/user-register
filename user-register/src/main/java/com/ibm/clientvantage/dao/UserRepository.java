package com.ibm.clientvantage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibm.clientvantage.domain.User;

@Transactional  
public interface UserRepository extends JpaRepository<User, Integer>{
	
//	@Query("from cas_user where user_name = :user_name")
//    public List<User> findByName(@Param("user_name") String user_name);
  //  public List<User> findByNameAndPassword(String user_name,String password);
   // @Query("select * from cas_user u where u.user_name=:user_name")
    public List<User> findByName(String name);

	public List<User> findByEmail(String email);
	
	public User findByCode(String code);
   
}
