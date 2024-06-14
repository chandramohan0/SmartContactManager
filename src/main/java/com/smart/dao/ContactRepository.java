package com.smart.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.Contact;

import jakarta.transaction.Transactional;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	//Pagination
	
	@Query("from Contact as c where c.user.id = :userId")
	public  Page<Contact> findContactByUser(@Param("userId") int userId, Pageable pePageable);
	
	@Modifying
	@Transactional
	@Query("delete from Contact c where c.cId = :id")
    public void deleteContactById(@Param("id") Integer id); 
}

