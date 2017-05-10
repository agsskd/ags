package vmd;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import com.dto.MstKaryawanDto;
import util.RestResponse;
import util.JsonUtil;
import vmd.BaseVmd;
//import service.MstKaryawanSvc;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginVmd {
	@WireVariable
//	MstKaryawanSvc mstKaryawanSvc;
	
//	private MstKaryawanDto mstKaryawanDto;
	private String username;
	private String password;
//	private BaseVmd baseVmd;
	
	private static Logger logger = LoggerFactory.getLogger(BaseVmd.class);
	
	private final String WS_URL = "http://localhost:8083/test1";
	
	
	
	@Command("login")
	@NotifyChange({"username", "password"})
	public void login(){
		System.out.println(username+"===============================");
		System.out.println(password+"===============================");
		if(username !=null && password !=null){
			MstKaryawanDto mstKaryawanDto = new MstKaryawanDto();
			mstKaryawanDto.setUsername(username);
			mstKaryawanDto.setPassword(password);
		String uri = WS_URL + "/karyawan/login";
		System.out.println(uri+"=====================================");
		RestResponse restResponse = new RestResponse();
		BaseVmd bs = new BaseVmd();
		restResponse = bs.callWs(uri,mstKaryawanDto,HttpMethod.POST);
		System.out.println("LOGIN ======================="+ restResponse.getStatus());
		System.out.println("LOGIN ======================="+ restResponse.getContents());
		
		try {
			mstKaryawanDto = JsonUtil.mapJsonToSingleObject(restResponse.getContents(), MstKaryawanDto.class);
		} catch (Exception e) {
			System.out.println("Unable to Convert JSON!");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		
//			if(mstKaryawan.getUsername() == username && mstKaryawan.getPassword() == password && mstKaryawan !=null)
			if(mstKaryawanDto.getKodeKaryawan()!= null)
			{
				mstKaryawanDto.setUsername(username);
				mstKaryawanDto.setPassword(password);
				Sessions.getCurrent().setAttribute("user", mstKaryawanDto);
				Executions.sendRedirect("/index.zul");
			}
			else{
				Messagebox.show("Username atau Password Salah");
				setUsername(null);
				setPassword(null);
			}
		}else{
			Messagebox.show("Harap diisi terlebih dahulu");
		}
	}

//	public MstKaryawanDto getMstKaryawan() {
//		return mstKaryawanDto;
//	}
//
//	public void setMstKaryawan(MstKaryawanDto mstKaryawanDto) {
//		this.mstKaryawanDto = mstKaryawanDto;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
