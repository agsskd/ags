package com.service;

import java.util.List;
import java.util.Map;

import com.dto.MstKaryawanDto;

public interface MstKaryawanSvc {
	public List<MstKaryawanDto> getAllKaryawan();
	
	public MstKaryawanDto getOneKaryawan(String kdKaryawan);
	
	public int saveUpdate(MstKaryawanDto mstKaryawanDto);
	
	public Map<String,Object> getLogin(MstKaryawanDto mstKaryawanDto);

}
