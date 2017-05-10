package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import response.RestResponse;

import com.dto.MstKaryawanDto;
import com.service.MstKaryawanSvc;
import com.util.CommonConstants;

@RestController
@RequestMapping("/karyawan")
public class MstKaryawanCtl {
	
	@Autowired
	private MstKaryawanSvc mstKaryawanSvc;
	
	@RequestMapping(value="/all" , method=RequestMethod.GET)
	public RestResponse getAllKaryawan(){
		RestResponse restResponse=new RestResponse();
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstKaryawanSvc.getAllKaryawan());
		//restResponse.setTotalRecords(mstKaryawanSvc.getAllKaryawan().size());
		
		return restResponse;
	}
	
	@RequestMapping(value="/one/{kodeKaryawan}" , method=RequestMethod.GET)
	public RestResponse getOneKaryawan(@PathVariable("kodeKaryawan") String kdKaryawan){
		RestResponse restResponse=new RestResponse();
		MstKaryawanDto mstKaryawanDto = new MstKaryawanDto();
		
		mstKaryawanDto = mstKaryawanSvc.getOneKaryawan(kdKaryawan);
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setContents(mstKaryawanDto);
		
		return restResponse;
	}
	
	@RequestMapping (value="/saveupdate",method=RequestMethod.POST)
	public RestResponse saveupdate(@RequestBody MstKaryawanDto mstKaryawanDto){
		RestResponse restResponse=new RestResponse();
		int i=mstKaryawanSvc.saveUpdate(mstKaryawanDto);
		if(i==0){
			restResponse.setMessage("Data telah berhasil disimpan");
		}else if(i==2){
			restResponse.setMessage("Data telah berhasil diupdate");
		}else{
			restResponse.setMessage("Datat Gagl disimpan");
		}
		
		return restResponse;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RestResponse findUser(@RequestBody MstKaryawanDto mstKaryawanDto) {
		RestResponse restResponse = new RestResponse();
		
		Map<String, Object> map = mstKaryawanSvc.getLogin(mstKaryawanDto);
		restResponse.setStatus(CommonConstants.OK_REST_STATUS);
		restResponse.setMessage("Data OK");
		
		restResponse.setContents(map.get("contentData"));
		
		return restResponse;
	}
	
}
