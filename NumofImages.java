import javax.servlet.*;
import javax.servlet.http.*;

public class NumofImages implements ServletContextAttributeListener {
  private static int sum_photos = -2;
  

  public void attributeAdded(ServletContextAttributeEvent scab) {

   sum_photos++;

  }

  
  public void attributeRemoved(ServletContextAttributeEvent scab) {
  	
  }

 
  public void attributeReplaced(ServletContextAttributeEvent scab) {
 
  }
  
  public static int getNum(){
	
	return sum_photos; 

	
	}
	

}