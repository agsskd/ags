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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

import com.dto.MstCustomerDto;

import pagevmd.NavigationVmd;
import util.JsonUtil;
import util.RestResponse;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerVmd extends NavigationVmd {

	private static Logger logger = LoggerFactory.getLogger(BaseVmd.class);

	private final String WS_URL = "http://localhost:8083/test1";

	private List<MstCustomerDto> listCustomer = new ArrayList<>();
	private MstCustomerDto mstCustomer = new MstCustomerDto();
	private boolean readonly = false;
	private String search;

	@Init
	public void load() {

		// List<MstBarangDto> mstBarangDto = new ArrayList<>();

		String uri = WS_URL + "/csmt/lengkap";
		System.out.println(uri + "=====================================");
		RestResponse restResponse = new RestResponse();
		BaseVmd bs = new BaseVmd();
		restResponse = bs.callWs(uri, null, HttpMethod.GET);

		try {
			listCustomer = JsonUtil.mapJsonToListObject(
					restResponse.getContents(), MstCustomerDto.class);
		} catch (Exception e) {
			System.out.println("Unable to Convert JSON!");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("customer ======================="
				+ restResponse.getStatus());
		System.out.println("CUSTOMER ======================="
				+ restResponse.getContents());

	}

	@Command("add")
	@NotifyChange({ "includeSrc", "p" })
	public void add() {
		MstCustomerDto mstCustomer = new MstCustomerDto();
		Sessions.getCurrent().setAttribute("obj", mstCustomer);
		Include inc = (Include) Executions.getCurrent().getDesktop()
				.getPage("index").getFellow("mainInclude");
		inc.setSrc("/master/customer/customeredit.zul");
		// SidebarPage p = new SidebarPage("Barang Edit", "Barang Edit Label",
		// "Icon Uri", "/master/barang/barangedit.zul");
		// onNavigate(p);
		// Messagebox.show(getIncludeSrc());
		// Executions.sendRedirect("/master/barang/barangedit.zul");
	}

	@Command("edit")
	@NotifyChange({ "includeSrc", "p" })
	public void edit() {
		if (mstCustomer.getKodeCustomer() == null) {
			Messagebox.show("Pilih data yang akan di edit");
		} else {
			Sessions.getCurrent().setAttribute("obj", mstCustomer);
			Include inc = (Include) Executions.getCurrent().getDesktop()
					.getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/customer/customeredit.zul");
		}
	}

	@Command("delete")
	public void delete() {
		if (mstCustomer.getKodeCustomer() == null) {
			Messagebox.show("Pilih data yang akan di delete");
		} else {

			Messagebox.show("Apakah yakin mau dihapus", "perhatian",
					new Button[] { Button.YES, Button.NO },
					Messagebox.QUESTION, Button.NO,
					new EventListener<Messagebox.ClickEvent>() {

						@Override
						public void onEvent(ClickEvent event) throws Exception {

							if (Messagebox.ON_YES.equals(event.getName())) {

								String uriSave = WS_URL + "/csmt/delete/"
										+ mstCustomer.getKodeCustomer();
								RestResponse restResponse = new RestResponse();
								BaseVmd bs = new BaseVmd();

								restResponse = bs.callWs(uriSave, null,
										HttpMethod.DELETE);
								Clients.showNotification(
										restResponse.getMessage(),
										Clients.NOTIFICATION_TYPE_INFO, null,
										null, 1500);

								Sessions.getCurrent().setAttribute("obj",
										mstCustomer);
								Include inc = (Include) Executions.getCurrent()
										.getDesktop().getPage("index")
										.getFellow("mainInclude");
								inc.setSrc("/master/customer/customer.zul");
							}
						}
					});
		}
	}
			
			@Command("search")
			@NotifyChange("listCustomer")
			public void search(){
				listCustomer.clear();
				//listCustomer = trHeaderPenjualanSvc.searchData(search);
			}
//			@Listen("onClick = #searchButton")
//		    public void search(){
//		        String keyword = keywordBox.getValue();
//		        List<Car> result = carService.search(keyword);
//		        carListbox.setModel(new ListModelList<Car>(result));
//		    }
//		

	public List<MstCustomerDto> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<MstCustomerDto> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public MstCustomerDto getMstCustomer() {
		return mstCustomer;
	}

	public void setMstCustomer(MstCustomerDto mstCustomer) {
		this.mstCustomer = mstCustomer;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}