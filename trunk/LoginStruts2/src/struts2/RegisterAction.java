/**
 * 
 */
package struts2;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import constant.Common;

/**
 * @author Dr
 * 
 */
public class RegisterAction extends BaseAction {
	
	private String userName;
	private String password;
	private String password2;
	private String email;
	private String name;
	private String surName;
	private String phone;
	private String mobilePhone;
	private Date birthDay;

	public RegisterAction() {
		super();
	}

	public String getUserId(){
		return userName;
	}
	public Date getRegisterDay(){
		return Calendar.getInstance().getTime();
	}

	public int getIdRole(){
		return 0;
	}

	public String getMd5Password(){
		return DigestUtils.md5Hex(password);
	}

	public String execute() {
		SqlSession sql = sqlSessionFactory.openSession();
		int ok=0;
		try {
			ok = sql.insert("User.add",this);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (ok>0) {
				sql.commit();
			} else {
				sql.rollback();
			}
			sql.close();
		}
		if (ok>0) {
			return SUCCESS;
		} else {
			addActionError(getText("error.save"));
			return ERROR;
		}
	}

	public void validate() {
		if (StringUtils.isEmpty(userName)) {
			addFieldError("userName",
								getText("requiredstring",
											new String[]{getText("userName")}));
		}
		if (StringUtils.isEmpty(name)) {
			addFieldError("name",
								getText("requiredstring",
											new String[]{getText("name")}));
		}
		if (StringUtils.isEmpty(surName)) {
			addFieldError("surName",
								getText("requiredstring",
											new String[]{getText("surName")}));
		}
		if (StringUtils.isEmpty(email)) {
			addFieldError("email",
								getText("requiredstring",
											new String[]{getText("email")}));
		}else if(!Pattern.compile(Common.PATTERN_MAIL).matcher(email).matches()){
			addFieldError("email",
					getText("error.format",
							new String[]{getText("email")}));
		}
		if (StringUtils.isEmpty(password)) {
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
		if (StringUtils.isEmpty(password2)) {
			addFieldError("password2",
								getText("requiredstring",
											new String[]{getText("password")}));
		}else if (getPassword2().length()<8) {
			addFieldError("password2",
								getText("error.length",
										new String[]{getText("password")}));
		}else if(!Pattern.compile(Common.PATTERN_PASSWORD).matcher(password2).matches()){
			addFieldError("password",
					getText("error.format",
							new String[]{getText("password")}));
		}
		if(!password.equals(password2)){
			addFieldError("password",
					getText("error.password"));
		}
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password2
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param password2 the password2 to set
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * @param surName the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}