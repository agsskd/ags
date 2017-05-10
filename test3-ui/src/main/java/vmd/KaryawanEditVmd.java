package vmd;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import pagevmd.NavigationVmd;
import util.JsonUtil;
import util.RestResponse;


import com.dto.MstKaryawanDto;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class KaryawanEditVmd extends NavigationVmd{
	
	private MstKaryawanDto mstKaryawan = new MstKaryawanDto();
	
	private static Logger logger = LoggerFactory.getLogger(BaseVmd.class);
	
	private final String WS_URL = "http://localhost:8083/test1";
	
//	@WireVariable
//	private MstBarangSvc mstBarangSv;
//	
//	@WireVariable
//	private MstSupplierSvc mstSupplierSvc;
	
	@Init
	public void load(){
		
		mstKaryawan = (MstKaryawanDto) Sessions.getCurrent().getAttribute("obj");
			
		RestResponse restResponse = new RestResponse();
		BaseVmd bs = new BaseVmd();
	}
	
	@Command("back")
	@NotifyChange({"includeSrc","p"})
	public void back(){
		
		Sessions.getCurrent().setAttribute("obj", mstKaryawan);
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		inc.setSrc("/master/karyawan/karyawan.zul");
	}
	
	@Command("save")
	public void save(){
		
		if (mstKaryawan.getKodeKaryawan() !=null){
			
			String uriSave = WS_URL + "/karyawan/saveupdate";
			RestResponse restResponse = new RestResponse();
			BaseVmd bs = new BaseVmd();
			
			restResponse = bs.callWs(uriSave,mstKaryawan,HttpMethod.POST);
			Clients.showNotification(restResponse.getMessage(), Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
			
			Sessions.getCurrent().setAttribute("obj", mstKaryawan);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawan.zul");
		
		}
	}

	public MstKaryawanDto getMstKaryawan() {
		return mstKaryawan;
	}

	public void setMstKaryawan(MstKaryawanDto mstKaryawan) {
		this.mstKaryawan = mstKaryawan;
	}
}
