package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import response.RestResponse;

import com.dao.MstCustomerDao;
import com.dto.MstCustomerDto;
import com.model.MstCustomer;
import com.service.MstCustomerSvc;
import com.util.CommonConstants;

@RestController
@RequestMapping("/csmt")
public class MstCustomerCtl {
	
	@Autowired
	private MstCustomerSvc mstCustomerSvc;
	@Autowired
	private MstCustomerDao mstcustDao;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public RestResponse getAllCustomer(){
		RestResponse restResponse=new RestResponse();		
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstCustomerSvc.getAllCustomer());
		
		return restResponse;
	}
	
	@RequestMapping( value = "/lengkap" , method = RequestMethod.GET)
	public RestResponse getAllCustomerLengkap(){
		RestResponse restResponse=new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstCustomerSvc.getAllLengkap());
		
		return restResponse;
	}

	@RequestMapping(value="/one/{kodeCustomer}",method=RequestMethod.GET)
	public RestResponse getOne(@PathVariable("kodeCustomer") String kdCustomer){
		MstCustomerDto mstCustomerDto=new MstCustomerDto();
		mstCustomerDto = mstCustomerSvc.getOne(kdCustomer);
		RestResponse restResponse=new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstCustomerDto);
		
		return restResponse;
	}
	
	@RequestMapping(value="/saveupdate",method=RequestMethod.POST)
	public RestResponse saveCustomer(@RequestBody MstCustomerDto mstCustomerDto){
		RestResponse restResponse=new RestResponse();
		int i=mstCustomerSvc.saveUpdate(mstCustomerDto);
		if(i==CommonConstants.OK_REST_STATUS){
			restResponse.setMessage("Data sudah tersimpan");			
		}else if(i==2){
			restResponse.setMessage("Data sudah diupdate");
		}else{
			restResponse.setMessage("Data Gagal disimpan/update");
		}
		return restResponse;
	}
	
//	@RequestMapping(value = "/like", method = RequestMethod.GET)
//	public MstCustomer getcustomer(
//			@RequestParam("namaCustomer") String namaCustomer){
//				return mstcustDao.finByNamaCustomer(namaCustomer);
						
//			}
	
}
