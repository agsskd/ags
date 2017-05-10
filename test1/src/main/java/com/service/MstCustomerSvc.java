package com.service;

import java.util.List;

import com.dto.MstCustomerDto;

public interface MstCustomerSvc {
	public List<MstCustomerDto> getAllCustomer();
	
	public List<MstCustomerDto> getAllLengkap();
	
	public MstCustomerDto getOne(String kdCustomer);
	
	public int saveUpdate(MstCustomerDto mstCustomerDto);
}
