/**
 * 
 */
package bean;


/**
 * @author Dr
 * 
 */
public class Utente {

	private String userName;
	private String password;

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

	public String getSayWelcome() {
		// check if null?
		if ("".equals(userName) || userName == null) {
			return "";
		} else {
			return "Ajax message : Welcome " + userName;
		}
	}
}
