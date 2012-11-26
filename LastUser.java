import javax.servlet.*;
import javax.servlet.http.*;

public class LastUser implements HttpSessionAttributeListener {
  static Object user=new Object();


  public void attributeAdded(HttpSessionBindingEvent e)  {
  

	user= e.getValue();
	
	
  }
  public void attributeRemoved(HttpSessionBindingEvent e)  {
    
  }
    public void attributeReplaced(HttpSessionBindingEvent e)  {
    
  }
  public static Object getuser() {
    return user;

  }

}