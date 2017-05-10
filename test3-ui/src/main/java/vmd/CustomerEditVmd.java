package vmd;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.zkoss.bind.BindUtils;
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

import com.dto.MstCustomerDto;
import com.dto.MstKotaDto;
import com.dto.MstProvinsiDto;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerEditVmd extends NavigationVmd{
	
	private List<MstProvinsiDto> listProvinsi = new ArrayList<>();
	private List<MstKotaDto> listKota = new ArrayList<>();
	private MstCustomerDto mstCustomer = new MstCustomerDto();
	private MstProvinsiDto mstProvinsi = new MstProvinsiDto();
	private MstKotaDto mstKota = new MstKotaDto();
	
	private static Logger logger = LoggerFactory.getLogger(BaseVmd.class);
	
	private final String WS_URL = "http://localhost:8083/test1";
	
//	@WireVariable
//	private MstBarangSvc mstBarangSv;
//	
//	@WireVariable
//	private MstSupplierSvc mstSupplierSvc;
	
	@Init
	public void load(){
		
		mstCustomer = (MstCustomerDto) Sessions.getCurrent().getAttribute("obj");
		
		String uriProvinsiAll = WS_URL + "/provinsi/all";
		String uriKotaAll = WS_URL + "/kota/all";
		
		RestResponse restResponse1 = new RestResponse();
		BaseVmd bs1 = new BaseVmd();
		
		restResponse1 = bs1.callWs(uriProvinsiAll,null,HttpMethod.GET);
		try {
			listProvinsi = JsonUtil.mapJsonToListObject(restResponse1.getContents(), MstProvinsiDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestResponse restResponse2 = new RestResponse();
		BaseVmd bs2 = new BaseVmd();
		
		restResponse2 = bs2.callWs(uriKotaAll,null,HttpMethod.GET);
		try {
			listKota = JsonUtil.mapJsonToListObject(restResponse2.getContents(), MstKotaDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Command("back")
	@NotifyChange({"includeSrc","p"})
	public void back(){
		
		Sessions.getCurrent().setAttribute("obj", mstCustomer);
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		inc.setSrc("/master/customer/customer.zul");
	}
	
	@Command("save")
	public void save(){
		
		
		if (mstKota.getKodeKota() != null){
//			Messagebox.show(mstKota.getKodeKota());
			mstCustomer.setKodeKota(mstKota.getKodeKota());
		}
		
		if (mstCustomer.getKodeCustomer() !=null){
			String uriSave = WS_URL + "/csmt/saveupdate";
			RestResponse restResponse = new RestResponse();
			BaseVmd bs = new BaseVmd();
			
			restResponse = bs.callWs(uriSave,mstCustomer,HttpMethod.POST);
			Clients.showNotification(restResponse.getMessage(), Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
			
			Sessions.getCurrent().setAttribute("obj", mstCustomer);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/customer/customer.zul");
		
		}
	}
	
	@Command("findKota")
	public void fkota(){
		
		String kdprov = mstProvinsi.getKodeProvinsi();
		
		String uriKotaAll = WS_URL + "/kota/prov/"+kdprov;
		
		RestResponse restResponse2 = new RestResponse();
		BaseVmd bs2 = new BaseVmd();
		
		restResponse2 = bs2.callWs(uriKotaAll,null,HttpMethod.GET);
		try {
			listKota = JsonUtil.mapJsonToListObject(restResponse2.getContents(), MstKotaDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BindUtils.postNotifyChange(null, null, CustomerEditVmd.this, "listKota");
		
		
	}

	public List<MstProvinsiDto> getListProvinsi() {
		return listProvinsi;
	}

	public void setListProvinsi(List<MstProvinsiDto> listProvinsi) {
		this.listProvinsi = listProvinsi;
	}

	public List<MstKotaDto> getListKota() {
		return listKota;
	}

	public void setListKota(List<MstKotaDto> listKota) {
		this.listKota = listKota;
	}

	public MstCustomerDto getMstCustomer() {
		return mstCustomer;
	}

	public void setMstCustomer(MstCustomerDto mstCustomer) {
		this.mstCustomer = mstCustomer;
	}

	public MstProvinsiDto getMstProvinsi() {
		return mstProvinsi;
	}

	public void setMstProvinsi(MstProvinsiDto mstProvinsi) {
		this.mstProvinsi = mstProvinsi;
	}

	public MstKotaDto getMstKota() {
		return mstKota;
	}

	public void setMstKota(MstKotaDto mstKota) {
		this.mstKota = mstKota;
	}

}
