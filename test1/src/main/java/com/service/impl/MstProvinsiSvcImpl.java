package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MstProvinsiDao;
import com.dto.MstProvinsiDto;
import com.model.MstProvinsi;
import com.service.MstProvinsiSvc;
import com.util.CommonConstants;

@Service
@Transactional
public class MstProvinsiSvcImpl implements MstProvinsiSvc {
	
	@Autowired
	private MstProvinsiDao mstProvinsiDao;
	
	@Autowired
	private MapperFacade mapperFacade;

	@Override
	public List<MstProvinsiDto> getAllProvinsi() {
		// TODO Auto-generated method stub
		List<MstProvinsiDto> listProvinsiDtos=new ArrayList<>();
		List<Object[]> listObjects=new ArrayList<>();
		
		listObjects = mstProvinsiDao.getAll();
		listProvinsiDtos = mapperFacade.mapAsList(listObjects, MstProvinsiDto.class);
		
		return listProvinsiDtos;
	}

	@Override
	public MstProvinsiDto getOneProvinsi(String kdProvinsi) {
		// TODO Auto-generated method stub
		MstProvinsiDto mstProvinsiDto=new MstProvinsiDto();
		MstProvinsi mstProvinsi=new MstProvinsi();
		
		mstProvinsi = mstProvinsiDao.getOne(kdProvinsi);
		mstProvinsiDto=mapperFacade.map(mstProvinsi, MstProvinsiDto.class);
		
		return mstProvinsiDto;
	}

	@Override
	public int saveUpdate(MstProvinsiDto mstProvinsiDto) {
		// TODO Auto-generated method stub
		MstProvinsi cekKodeProvinsi=mstProvinsiDao.getOne(mstProvinsiDto.getKodeProvinsi());
		MstProvinsi mstProvinsi=mapperFacade.map(mstProvinsiDto, MstProvinsi.class);
		
		if(cekKodeProvinsi==null){
			try{
				mstProvinsiDao.save(mstProvinsi);
				return CommonConstants.OK_REST_STATUS;
			}catch(Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}
		}else if(!(cekKodeProvinsi==null)){			
			try{
				mstProvinsiDao.save(mstProvinsi);
				return 2;
			}catch(Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}
		}else{
			return 3;
		}
		
	}

}
