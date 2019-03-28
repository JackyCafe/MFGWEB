package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import misc.HibernateUtil;
import model.PackageInfoBean;
import model.dao.ClassInfoDAO;
import model.dao.PackageInfoDAO;
import model.service.ClassInfoService;
import model.service.PackageInfoService;

/**
 * Servlet implementation class PackageServlet
 */
@WebServlet("/Package.process")
public class PackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
    String name;   
    String reactor_Lot1,lot1,lot2,lot3,reactor_Lot2,reactor_Lot3;
    String prod;
    String box;
    String action;
    float w1,w2,w3,total_w;
    
    PackageInfoBean bean;
	private SessionFactory factory;
	private PackageInfoDAO dao;
	private PackageInfoService service;
    
    public PackageServlet() {
        super();
        w1 = w2 = w3 = total_w = 0;
        factory = HibernateUtil.getSessionFactory();
    	dao = new PackageInfoDAO(factory );
    	service = new PackageInfoService(dao);
        
     }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		Date date1 = new Date();
		action = request.getParameter("action");
		out = response.getWriter();
 		if(action.equals("add")) {
			reactor_Lot1 = request.getParameter("reactor_Lot1");
		 
			name = request.getParameter("name");
			System.out.println("name:"+name);
			box = request.getParameter("box");
 			prod = request.getParameter("product1");
			lot1 = request.getParameter("lot1");
			lot2 = request.getParameter("lot2");
			lot3 = request.getParameter("lot3");	
			reactor_Lot2 = request.getParameter("reactor_Lot2");
			reactor_Lot3 = request.getParameter("reactor_Lot3");
			try {
				w1 = Float.parseFloat(request.getParameter("weight1"));	
			} catch (Exception e) {
				out.print("w1:"+e.toString());
			}
			
			try {
				w2 = Float.parseFloat(request.getParameter("weight2"));	
				w2 = lot2.trim().length()==0?0:w2;
			} catch (Exception e) {
				out.print("w2:"+e.toString());

			}
			try {
				w3 = Float.parseFloat(request.getParameter("weight3"));
				w3 = lot3.trim().length()==0?0:w3;
			} catch (Exception e) {
				out.print("w3:"+e.toString());
			}
			total_w = w1 + w2+ w3;
			
	 		bean = new PackageInfoBean(date1, date1 ,reactor_Lot1, box
					, prod, lot1, w1, lot2, w2, lot3, w3, total_w, name,reactor_Lot2,reactor_Lot3,false,null);
	 		 
			bean.setId(0);
 			service.insert(bean);
			String retUrl = request.getHeader("Referer");
			request.getSession().setAttribute("reactor_Lot", reactor_Lot1);
   	 		response.sendRedirect(retUrl);
		}
		doGet(request, response);
	}

}
