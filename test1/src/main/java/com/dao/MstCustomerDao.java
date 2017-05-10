package com.dao;

import java.util.List;




import javax.swing.text.MaskFormatter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.repository.query.Param;

import com.model.MstCustomer;


public interface MstCustomerDao extends JpaRepository<MstCustomer, String> {
	
	@Query("select a from MstCustomer a")
	public List<Object[]> getAll();
	
	@Query("select a,b.namaKota from MstCustomer a, MstKota b where a.kodeKota = b.kodeKota")
	public List<Object[]> getAllLengkap();
	
	@Query("select a from MstCustomer a where a.kodeCustomer = :kodeCustomer")
	public MstCustomer getOne(@Param("kodeCustomer") String kdCustomer);
	
//	@Query("SELECT a FROM MstCustomer a WHERE u.namaCustomer LIKE CONCAT('%',:namaCustomer,'%')")
//	List<String> finByNamaCustomer(@Param("namaCustomer") String namaCustomer);

			//public MstCustomer finByNamaCustomer(String namaCustomer);


}
