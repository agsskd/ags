package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.dao.MstCustomerDao;
import com.dto.MstCustomerDto;
import com.model.MstCustomer;
import com.service.MstCustomerSvc;
import com.util.CommonConstants;

@Service
@Transactional
public class MstCustomerSvcImpl implements MstCustomerSvc {
	
	@Autowired
	private MstCustomerDao mstCustomerDao;
	
	@Autowired
	private MapperFacade mapperFacade;

	
	@Override
	public List<MstCustomerDto> getAllCustomer() {
		// TODO Auto-generated method stub
		List<MstCustomerDto> listMstCustomerDtos=new ArrayList<>();
		List<Object[]> obj=new ArrayList<>();
		
		obj = mstCustomerDao.getAll();
		listMstCustomerDtos = mapperFacade.mapAsList(obj, MstCustomerDto.class);
		
		return listMstCustomerDtos;
	}


	@Override
	public List<MstCustomerDto> getAllLengkap() {
		// TODO Auto-generated method stub
		List<MstCustomerDto> listMstCustomerDtos=new ArrayList<>();
		List<Object[]> listObjects=new ArrayList<>();
		
		listObjects = mstCustomerDao.getAllLengkap();
		for(Object[] obj: listObjects){
			MstCustomer mstCustomer=new MstCustomer();
			
			mstCustomer = (MstCustomer) obj[0];
			
			MstCustomerDto mstCustomerDto=new MstCustomerDto();
			mstCustomerDto = mapperFacade.map(mstCustomer, MstCustomerDto.class);
			mstCustomerDto.setNamaKota((String) obj[1]);
			
			listMstCustomerDtos.add(mstCustomerDto);
		}
		
		return listMstCustomerDtos;
	}


	@Override
	public MstCustomerDto getOne(String kdCustomer) {
		// TODO Auto-generated method stub
		MstCustomerDto mstCustomerDto=new MstCustomerDto();
		MstCustomer mstCustomer = new MstCustomer();
		mstCustomer=mstCustomerDao.getOne(kdCustomer);
		mstCustomerDto = mapperFacade.map(mstCustomer, MstCustomerDto.class);
		
		return mstCustomerDto;
	}


	@Override
	public int saveUpdate(MstCustomerDto mstCustomerDto) {
		// TODO Auto-generated method stub
		MstCustomer b=mstCustomerDao.getOne(mstCustomerDto.getKodeCustomer());
		if(!(b==null)){
			try{
				MstCustomer mstCustomer=mapperFacade.map(mstCustomerDto, MstCustomer.class);
				mstCustomerDao.save(mstCustomer);			
				return 2;
			}catch(Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}			
		}else{
			try{
				MstCustomer mstCustomer= mapperFacade.map(mstCustomerDto, MstCustomer.class);
				mstCustomerDao.save(mstCustomer);
				return CommonConstants.OK_REST_STATUS;
			}catch(Exception e){
				return CommonConstants.ERROR_REST_STATUS;
			}
		}
	}
	

}
