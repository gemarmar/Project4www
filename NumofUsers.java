

import javax.servlet.*;
import javax.servlet.http.*;

public class NumofUsers implements HttpSessionListener {
  static int users = 0;

  public void sessionCreated(HttpSessionEvent e) {
    users++;
  }
  public void sessionDestroyed(HttpSessionEvent e) {
    if (users > 0){
    users--;
	}
  }
  public static int getUsers() {
    return users;
  }
}