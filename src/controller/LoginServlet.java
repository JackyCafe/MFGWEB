package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;

import misc.HibernateUtil;
import model.dao.HumanInfoDAO;
import model.service.HumanInfoService;
import model.*;
import java.util.List;
import java.util.Map;


@WebServlet( urlPatterns = { "/vertify" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter out;
	public HumanInfoService service;   

    public void init()
    {
    	
    	SessionFactory factory = HibernateUtil.getSessionFactory();
		HumanInfoDAO dao = new HumanInfoDAO(factory );
		service = new HumanInfoService(dao );
     
    }
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
	    response.setCharacterEncoding("utf-8");
	    request.setCharacterEncoding("utf-8");
	    String account = request.getParameter("account");
 		String password = request.getParameter("password");
 
 		out = response.getWriter();
  		Map<String, String[]> maps = request.getParameterMap();
 	    int size = maps.size();
 	    String[] name = new String[size];
 	    int i= 0 ;
 	    
 	    for(String key:maps.keySet())
 	    {
 	    	name[i] = key;
  	    	i++;
  	    	 
 	    }
 	    
 	   if (name[size-1].equals("login"))
	    {
	         List<HumanInfoBean> loginUser = service.select(account,password);
	    	if  (loginUser.size()>0)
	    	 {
	             HttpSession session = request.getSession();
	             session.setAttribute("account", loginUser.get(0));
	             String retUrl = request.getHeader("Referer");
	      		 response.sendRedirect(retUrl);
	    	 } 
	    	 
	     
	    }
		
 	 //Session 記錄
	   /* HttpSession session = request.getSession();
	    session.setAttribute("account", account);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);  	 
	  */  
	}

}
