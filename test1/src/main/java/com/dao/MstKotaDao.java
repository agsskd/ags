package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.MstKota;

public interface MstKotaDao extends JpaRepository<MstKota, String> {

	@Query("select a from MstKota a")
	public List<Object[]> getAll();
	
	@Query("select a,b.namaProvinsi from MstKota a, MstProvinsi b "
			+ "where a.kodeProvinsi=b.kodeProvinsi")
	public List<Object[]> getAllLengkap();
	
	@Query("select a from MstKota a where a.kodeKota= :kodeKota")
	public MstKota getOne(@Param("kodeKota") String kdKota);
	
	@Query("select a from MstKota a where a.kodeProvinsi= :kodeprov")
	public List<MstKota> getOneprov(@Param("kodeprov") String kdprov);
}
