package travel.ustc.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import travel.ustc.bean.Consumer;
import travel.ustc.bean.Coupon;
import travel.ustc.dao.ConsumerDao;
import travel.ustc.dao.CouponDao;

public class LoginAction {

	private String username;
	private String password;

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

	public String handleLogin() throws SQLException {
		System.out.println("Start to handleLogin()...");
		Consumer consumer = new Consumer(username, password);
		ConsumerDao consumerDao = new ConsumerDao();
		Consumer con = consumerDao.get(consumer);
		System.out.println("query:"+con.getCustName());
		System.out.println("query:"+con.getPassword());
		System.out.println("balance:"+con.getBalance());
		if (consumer.getCustName().equals(con.getCustName())
				&& consumer.getPassword().equals(con.getPassword())) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("custName", con.getCustName());
			return "success";
		} else {
			return "fail";
		}
	}

}
