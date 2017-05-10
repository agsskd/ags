package com.service;

import java.util.List;

import com.dto.MstProvinsiDto;

public interface MstProvinsiSvc {
	
	public List<MstProvinsiDto> getAllProvinsi();

	public MstProvinsiDto getOneProvinsi(String kdProvinsi);
	
	public int saveUpdate(MstProvinsiDto mstProvinsiDto);
}
