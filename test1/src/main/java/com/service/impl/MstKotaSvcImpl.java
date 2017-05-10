package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MstKotaDao;
import com.dto.MstKotaDto;
import com.model.MstKota;
import com.service.MstKotaSvc;
import com.util.CommonConstants;

@Service
@Transactional
public class MstKotaSvcImpl implements MstKotaSvc {
	
	@Autowired
	private MstKotaDao mstKotaDao;
	
	@Autowired
	private MapperFacade mapperFacade;

	@Override
	public List<MstKotaDto> getAllKota() {
		// TODO Auto-generated method stub
		List<MstKotaDto> listMstKotaDtos=new ArrayList<>();	
		List<Object[]> listoObjects=new ArrayList<>();
		
		listoObjects = mstKotaDao.getAll();
		
		listMstKotaDtos = mapperFacade.mapAsList(listoObjects, MstKotaDto.class);
		
		return listMstKotaDtos;
	}

	@Override
	public List<MstKotaDto> getAllLengkap() {
		// TODO Auto-generated method stub
		List<MstKotaDto> listMstKotaDtos=new ArrayList<>();
		List<Object[]> listObjects=new ArrayList<>();
		
		listObjects = mstKotaDao.getAllLengkap();
		for(Object[] obj:listObjects){
			MstKota mstKota=new MstKota();
			MstKotaDto mstKotaDto=new MstKotaDto();
			
			mstKota = (MstKota) obj[0];
			
			mstKotaDto = mapperFacade.map(mstKota, MstKotaDto.class);
			mstKotaDto.setNamaProvinsi((String) obj[1]);
			
			listMstKotaDtos.add(mstKotaDto);
		}
		
		return listMstKotaDtos;
	}

	@Override
	public MstKotaDto getOne(String kdKota) {
		// TODO Auto-generated method stub
		MstKotaDto mstKotaDto=new MstKotaDto();
		MstKota mstKota =new MstKota();
		
		mstKota=mstKotaDao.getOne(kdKota);
		mstKotaDto = mapperFacade.map(mstKota, MstKotaDto.class);
		
		return mstKotaDto;
	}
	
	@Override
	public List<MstKotaDto> getOneProv(String kdprov) {
		// TODO Auto-generated method stub
		List<MstKotaDto> mstKotaDto=new ArrayList<>();
		List<MstKota> mstKota =new ArrayList<>();
		
		mstKota=mstKotaDao.getOneprov(kdprov);
		mstKotaDto = mapperFacade.mapAsList(mstKota, MstKotaDto.class);
		
		return mstKotaDto;
	}

	@Override
	public int saveUpdate(MstKotaDto mstKotaDto) {
		// TODO Auto-generated method stub
		//String cekkdKota=mstKotaDto.getKodeKota();
		MstKota cekkdKota=mstKotaDao.getOne(mstKotaDto.getKodeKota());
		MstKota mstKota=mapperFacade.map(mstKotaDto, MstKota.class);
		if(cekkdKota==null){			
			try{
				mstKotaDao.save(mstKota);
				return CommonConstants.OK_REST_STATUS;
			}catch(Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}
		}else if(!(cekkdKota==null)){
			try{
				mstKotaDao.save(mstKota);
				return 2;
			}catch (Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}
		}else{
			return 3;
		}	
	}

}
