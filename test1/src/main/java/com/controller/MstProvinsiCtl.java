package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.MstProvinsiDto;
import com.service.MstProvinsiSvc;
import com.util.CommonConstants;

import response.RestResponse;

@RestController
@RequestMapping("/provinsi")
public class MstProvinsiCtl {
	
	@Autowired
	private MstProvinsiSvc mstProvinsiSvc;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public RestResponse getAllProvinsi(){
		RestResponse restResponse =new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstProvinsiSvc.getAllProvinsi());
		
		return restResponse;
		
	}
	
	@RequestMapping(value="/one/{kodeProvinsi}", method=RequestMethod.GET)
	public RestResponse getOneProvinsi(@PathVariable("kodeProvinsi") String kdProvinsi){
		RestResponse restResponse=new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstProvinsiSvc.getOneProvinsi(kdProvinsi));
		
		return restResponse;
		
	}
	
	@RequestMapping(value="/saveupdate",method=RequestMethod.POST)
	public RestResponse saveupdate(@RequestBody MstProvinsiDto mstProvinsiDto){
		RestResponse restResponse=new RestResponse();
		int i=mstProvinsiSvc.saveUpdate(mstProvinsiDto);
		if(i==CommonConstants.OK_REST_STATUS){
			restResponse.setMessage("Data telah berhasil disimpan");
		}else if(i==2){
			restResponse.setMessage("Data telah berhasil diupdate");
		}else{
			restResponse.setMessage("Data Gagal disimpan/update");
		}
		
		return restResponse;
	}

}
