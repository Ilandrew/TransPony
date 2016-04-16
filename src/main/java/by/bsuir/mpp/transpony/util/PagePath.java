package by.bsuir.mpp.transpony.util;

public enum PagePath {
	HOME("index.jsp"),
	HOME_ACCOUNTANT("jsp/accountant/index_accountant.jsp"),
	HOME_ADMIN("jsp/admin/index_admin.jsp"),
	HOME_DRIVER("jsp/driver/index_driver.jsp"),
	HOME_LEADER("jsp/leader/index_leader.jsp"),
	HOME_LOGISTIAN("jsp/logistian/index_logistian.jsp"),
	LOGIN_PAGE("jsp/login.jsp"),
	CREATE_CHECKPOINT_PAGE("jsp/logistian/create_checkpoint.jsp"),
	CREATE_ROUTE_PAGE("jsp/logistian/create_route.jsp"),
	SHOW_FREE_CARS_PAGE("jsp/carpage.jsp"),
	SHOW_CHECKPOINTS_PAGE("jsp/logistian/show_checkpoints.jsp"),
	SHOW_ROUTES_PAGE("jsp/logistian/show_routes.jsp"),
	SHOW_USERS_PAGE("jsp/userpage.jsp"),
	EDIT_ROUTE_PAGE("jsp/logistian/edit_route.jsp");

	private String page;

	PagePath(String page) {
		this.page = page;
	}

	public String getPage() {
		return this.page;
	}
}
