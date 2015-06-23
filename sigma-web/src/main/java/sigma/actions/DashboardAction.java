package sigma.actions;

import java.util.LinkedHashMap;

import sigma.bo.AtencionBO;
import sigma.dto.DashboardDTO;

public class DashboardAction extends BaseAction {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 8510842817663365742L;
	private static final String JSON = "json";
	private AtencionBO atencionBO;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String showDashboard() throws Exception {
		DashboardDTO dashboardDTO = atencionBO.obtenerDashboard();
		createCustomJSONResponse(dashboardDTO);
		return JSON;
	}

	public void setAtencionBO(AtencionBO atencionBO) {
		this.atencionBO = atencionBO;
	}

}
