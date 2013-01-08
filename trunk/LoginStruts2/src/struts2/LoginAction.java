/**
 * 
 */
package struts2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Dr
 * 
 */
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -6206808590907715484L;

	private SqlSessionFactory sqlSessionFactory=null;

	private String userName;
	private String password;
	private Date myBirthday;

	private Pattern pattern;

	public LoginAction() {
		String resource = "mybatis-config.xml";
		InputStream inputStream=null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(inputStream!=null){
			try {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pattern = Pattern.compile( "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");
	}

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
		}else if(!pattern.matcher(password).matches()){
			addFieldError("password",
					getText("error.format",
							new String[]{getText("password")}));
		}
	}
}