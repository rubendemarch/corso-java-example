/**
 * 
 */
package struts2;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Dr
 * 
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 8036774387141746878L;

	protected SqlSessionFactory sqlSessionFactory=null;

	public BaseAction() {
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
	}
}