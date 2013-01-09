/**
 * 
 */
package struts2;

import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

import constant.Common;

/**
 * @author Dr
 * 
 */
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -2414529954723245070L;

	private String userName;
	private String password;

	public LoginAction() {
		super();
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

	public String getMd5Password(){
		return DigestUtils.md5Hex(password);
	}

	public String execute() {
		SqlSession sql = sqlSessionFactory.openSession();
		int ok = sql.selectOne("User.login",this);
		sql.close();
		if (ok>0) {
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
		}
		if (getPassword().length() == 0) {
			addFieldError("password",
								getText("requiredstring",
											new String[]{getText("password")}));
		}else if (getPassword().length()<8) {
			addFieldError("password",
								getText("error.length",
										new String[]{getText("password")}));
		}else if(!Pattern.compile(Common.PATTERN_PASSWORD).matcher(password).matches()){
			addFieldError("password",
					getText("error.format",
							new String[]{getText("password")}));
		}
	}
}