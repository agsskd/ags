package com.service;

import java.util.List;

import com.dto.MstKotaDto;

public interface MstKotaSvc {
	
public List<MstKotaDto> getAllKota();

public List<MstKotaDto> getAllLengkap();

public MstKotaDto getOne(String kdKota);

public List<MstKotaDto> getOneProv(String kdprov);

public int saveUpdate(MstKotaDto mstKotaDto);

}
