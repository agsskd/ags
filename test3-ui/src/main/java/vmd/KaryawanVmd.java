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

import com.dto.MstKaryawanDto;

import pagevmd.NavigationVmd;
import util.JsonUtil;
import util.RestResponse;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class KaryawanVmd extends NavigationVmd {

	private static Logger logger = LoggerFactory.getLogger(BaseVmd.class);

	private final String WS_URL = "http://localhost:8083/test1";

	private List<MstKaryawanDto> listKaryawan = new ArrayList<>();
	private MstKaryawanDto mstKaryawan = new MstKaryawanDto();
	private boolean readonly = false;

	@Init
	public void load() {

		// List<MstBarangDto> mstBarangDto = new ArrayList<>();

		String uri = WS_URL + "/karyawan/all";
		System.out.println(uri + "=====================================");
		RestResponse restResponse = new RestResponse();
		BaseVmd bs = new BaseVmd();
		restResponse = bs.callWs(uri, null, HttpMethod.GET);

		try {
			listKaryawan = JsonUtil.mapJsonToListObject(
					restResponse.getContents(), MstKaryawanDto.class);
		} catch (Exception e) {
			System.out.println("Unable to Convert JSON!");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("karyawan ======================="
				+ restResponse.getStatus());
		System.out.println("KARYAWAN ======================="
				+ restResponse.getContents());

	}

	@Command("add")
	@NotifyChange({ "includeSrc", "p" })
	public void add() {
		MstKaryawanDto mstSupplier = new MstKaryawanDto();
		Sessions.getCurrent().setAttribute("obj", mstSupplier);
		Include inc = (Include) Executions.getCurrent().getDesktop()
				.getPage("index").getFellow("mainInclude");
		inc.setSrc("/master/karyawan/karyawanedit.zul");
		// SidebarPage p = new SidebarPage("Barang Edit", "Barang Edit Label",
		// "Icon Uri", "/master/barang/barangedit.zul");
		// onNavigate(p);
		// Messagebox.show(getIncludeSrc());
		// Executions.sendRedirect("/master/barang/barangedit.zul");
	}

	@Command("edit")
	@NotifyChange({ "includeSrc", "p" })
	public void edit() {
		if (mstKaryawan.getKodeKaryawan() == null) {
			Messagebox.show("Pilih data yang akan di edit");
		} else {
			Sessions.getCurrent().setAttribute("obj", mstKaryawan);
			Include inc = (Include) Executions.getCurrent().getDesktop()
					.getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawanedit.zul");
		}
	}

	@Command("delete")
	public void delete() {
		if (mstKaryawan.getKodeKaryawan() == null) {
			Messagebox.show("Pilih data yang akan di delete");
		} else {

			Messagebox.show("Apakah yakin mau dihapus", "perhatian",
					new Button[] { Button.YES, Button.NO },
					Messagebox.QUESTION, Button.NO,
					new EventListener<Messagebox.ClickEvent>() {

						@Override
						public void onEvent(ClickEvent event) throws Exception {

							if (Messagebox.ON_YES.equals(event.getName())) {

								String uriSave = WS_URL + "/karyawan/delete/"
										+ mstKaryawan.getKodeKaryawan();
								RestResponse restResponse = new RestResponse();
								BaseVmd bs = new BaseVmd();

								restResponse = bs.callWs(uriSave, null,
										HttpMethod.DELETE);
								Clients.showNotification(
										restResponse.getMessage(),
										Clients.NOTIFICATION_TYPE_INFO, null,
										null, 1500);

								Sessions.getCurrent().setAttribute("obj",
										mstKaryawan);
								Include inc = (Include) Executions.getCurrent()
										.getDesktop().getPage("index")
										.getFellow("mainInclude");
								inc.setSrc("/master/karyawan/karyawan.zul");
							}
						}
					});

		}
	}

	
	public List<MstKaryawanDto> getListKaryawan() {
		return listKaryawan;
	}

	public void setListKaryawan(List<MstKaryawanDto> listKaryawan) {
		this.listKaryawan = listKaryawan;
	}

	public MstKaryawanDto getMstKaryawan() {
		return mstKaryawan;
	}

	public void setMstKaryawan(MstKaryawanDto mstKaryawan) {
		this.mstKaryawan = mstKaryawan;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
}