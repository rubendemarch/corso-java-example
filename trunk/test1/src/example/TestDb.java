/**
 * 
 */
package example;


/**
 * @author Dr
 *
 */
public class TestDb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("START");
		
		Db d =new Db();
		d= null;
		System.out.println("END");
		int cnt=0;
		while(cnt<5){
			cnt++;
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
