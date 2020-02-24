package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class ContextLoadListener implements ServletContextListener {

    // 어플리케이션이 올라갔을 때 (아까는 서블릿에서 초기화하는 것임. 이거는 톰캣에서 초기화. 범위가 더 넓다)
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	ServletContext context = servletContextEvent.getServletContext();
    	String contextConfigLocation = context.getInitParameter("contextConfigLocation");
    	System.out.println("Application Starts..." + contextConfigLocation);
    }
    
    // 톰캣 내려갈 때
    public void contextDestroyed(ServletContextEvent arg0)  { 
        
    }
	
}
