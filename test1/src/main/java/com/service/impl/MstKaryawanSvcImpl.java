package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MstKaryawanDao;
import com.dto.MstKaryawanDto;
import com.model.MstKaryawan;
import com.service.MstKaryawanSvc;
import com.util.CommonConstants;

@Service
@Transactional
public class MstKaryawanSvcImpl implements MstKaryawanSvc {
	
	@Autowired
	private MstKaryawanDao mstKaryawanDao;
	
	@Autowired
	private MapperFacade mapperFacade;

	@Override
	public List<MstKaryawanDto> getAllKaryawan() {
		// TODO Auto-generated method stub
		List<MstKaryawanDto> listMstKaryawanDtos=new ArrayList<>();
		List<Object[]> listObjects=new ArrayList<>();
		
		listObjects = mstKaryawanDao.getAll();
		listMstKaryawanDtos=mapperFacade.mapAsList(listObjects, MstKaryawanDto.class);
		
		return listMstKaryawanDtos;
	}

	@Override
	public MstKaryawanDto getOneKaryawan(String kdKaryawan) {
		// TODO Auto-generated method stub
		MstKaryawanDto mstKaryawanDto=new MstKaryawanDto();
		MstKaryawan mstKaryawan=new MstKaryawan();
		
		mstKaryawan=mstKaryawanDao.getOne(kdKaryawan);
		
		mstKaryawanDto= mapperFacade.map(mstKaryawan, MstKaryawanDto.class);
		
		//System.out.println("aa"+mstKaryawanDto.getKodeKaryawan());
		return mstKaryawanDto;
		
	}

	@Override
	public int saveUpdate(MstKaryawanDto mstKaryawanDto) {
		// TODO Auto-generated method stub
		MstKaryawan cekKdKaryawan=mstKaryawanDao.getOne(mstKaryawanDto.getKodeKaryawan());
		MstKaryawan mstKaryawan=mapperFacade.map(mstKaryawanDto, MstKaryawan.class);
		if(cekKdKaryawan==null){
			try{
				mstKaryawanDao.save(mstKaryawan);
				return CommonConstants.OK_REST_STATUS;
			}catch(Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}
			
		}else if(!(cekKdKaryawan==null)){
			
			try{
				mstKaryawanDao.save(mstKaryawan);
				return 2;
			}catch(Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}
		}else{
			return 3;
		}
	}

	@Override
	public Map<String, Object> getLogin(MstKaryawanDto mstKaryawanDto) {
		MstKaryawan isi = mstKaryawanDao.getUser(mstKaryawanDto.getUsername(), mstKaryawanDto.getPassword());
		
		
		Map<String, Object> map = new HashMap<>();
		
		if (isi != null){
			MstKaryawanDto isiDto = new MstKaryawanDto();
			isiDto.setKodeKaryawan(isi.getKodeKaryawan());
			isiDto.setNamaKaryawan(isi.getNamaKaryawan());
			
			map.put("contentData", isiDto);
			
		}else{
			map.put("contentData", null);
		}
		
		return map;
	}

}
