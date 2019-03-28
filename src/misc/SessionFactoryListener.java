package misc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import misc.HibernateUtil;

/**
  *
 */
@WebListener
public class SessionFactoryListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SessionFactoryListener() {
     }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	HibernateUtil.closeSessionFactory();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("contextInitialized");
    	HibernateUtil.getSessionFactory();
    }
	
}