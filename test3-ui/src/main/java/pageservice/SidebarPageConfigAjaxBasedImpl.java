package pageservice;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SidebarPageConfigAjaxBasedImpl implements SidebarPageConfig{
	
	HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	public SidebarPageConfigAjaxBasedImpl(){		
		
		pageMap.put("fn1",new SidebarPage("Customer","Customer","","/master/customer/customer.zul"));
		pageMap.put("fn2",new SidebarPage("employee","Employee","","/master/karyawan/karyawan.zul"));

	}
	
	
	public List<SidebarPage> getPages(){
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
	public SidebarPage getPage(String name){
		return pageMap.get(name);
	}
	
}
