/**
 * 
 */
package struts2;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Dr
 * 
 */
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -6206808590907715484L;

	private String userName;
	private String password;
	private Date myBirthday;

	public Date getMyBirthday() {
		return myBirthday;
	}

	public void setMyBirthday(Date myBirthday) {
		this.myBirthday = myBirthday;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() {
		if (this.userName.equals("admin") && this.password.equals("admin123")) {
			return SUCCESS;
		} else {
			addActionError(getText("error.login"));
			return ERROR;
		}
	}

	public void validate() {
		if (getUserName().length() == 0) {
			addFieldError("userName",
								getText("requiredstring",
											new String[]{getText("userName")}));
		} else if (!getUserName().equals("Eswar")) {
			addFieldError("userName", getText("error.user"));
		}
		if (getPassword().length() == 0) {
			addFieldError("password",
								getText("requiredstring",
											new String[]{getText("password")}));
		}
	}

}