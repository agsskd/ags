package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import response.RestResponse;

import com.dto.MstKotaDto;
import com.service.MstKotaSvc;
import com.util.CommonConstants;

@RestController
@RequestMapping("/kota")
public class MstKotaCtl {
	
	@Autowired
	private MstKotaSvc mstKotaSvc;
	
	@RequestMapping(value="/all" , method=RequestMethod.GET)
	public RestResponse getAllKota(){
		RestResponse restResponse=new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstKotaSvc.getAllKota());
		
		return restResponse;
	}
	
	@RequestMapping(value="/lengkap",method=RequestMethod.GET)
	public RestResponse getAllLengkapKota(){
		RestResponse respRestResponse=new RestResponse();
		respRestResponse.setStatus(CommonConstants.OK_REST_STATUS);
		respRestResponse.setContents(mstKotaSvc.getAllLengkap());
		
		return respRestResponse;
	}
	
	@RequestMapping(value="/one/{kodeKota}",method=RequestMethod.GET)
	public RestResponse getOneKota(@PathVariable ("kodeKota") String kdKota){
		RestResponse restResponse=new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstKotaSvc.getOne(kdKota));
		
		return restResponse;
	}
	
	@RequestMapping(value="/prov/{kodeprov}",method=RequestMethod.GET)
	public RestResponse getOneProv(@PathVariable ("kodeprov") String kdprov){
		RestResponse restResponse=new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstKotaSvc.getOneProv(kdprov));
		
		return restResponse;
	}
	
	@RequestMapping(value="/saveupdate",method=RequestMethod.POST)
	public RestResponse saveupdate(@RequestBody MstKotaDto mstKotaDto){
		RestResponse restResponse=new RestResponse();
		int i=mstKotaSvc.saveUpdate(mstKotaDto);
		if(i==CommonConstants.OK_REST_STATUS){
			restResponse.setMessage("Data telah berhasil disimpan");			
		}else if(i==2){
			restResponse.setMessage("Data telah berhasil diupdate");
		}else{
			restResponse.setMessage("Data Gagal disimpan");
		}
		
		return restResponse;
	}
	
}
