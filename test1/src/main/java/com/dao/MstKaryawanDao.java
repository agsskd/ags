package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.model.MstKaryawan;

public interface MstKaryawanDao extends JpaRepository<MstKaryawan, String> {

	@Query("select a from MstKaryawan a")
	public List<Object[]> getAll();
	
	@Query("select a from MstKaryawan a where a.kodeKaryawan= :kodeKaryawan")
	public MstKaryawan getOne(@Param("kodeKaryawan") String kdKaryawan);
	
	@Query("select a from MstKaryawan a where "
			+ "a.username = :userName and a.password = :password ")
	public MstKaryawan getUser(@Param("userName") String userName, 
			@Param("password") String password );
}
