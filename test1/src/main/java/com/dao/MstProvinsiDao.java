package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.MstProvinsi;

public interface MstProvinsiDao extends JpaRepository<MstProvinsi, String> {

	@Query("select a from MstProvinsi a")
	public List<Object[]> getAll();
	
	@Query("select a from MstProvinsi a where a.kodeProvinsi= :kodeProvinsi")
	public MstProvinsi getOne(@Param("kodeProvinsi") String kdProvinsi);
}
