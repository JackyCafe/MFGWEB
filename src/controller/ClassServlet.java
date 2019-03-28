package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import misc.HibernateUtil;
import model.ClassInfoBean;
import model.dao.ClassInfoDAO;
import model.dao.HumanInfoDAO;
import model.service.ClassInfoService;
import model.service.HumanInfoService;
 
/* 課程資訊維護
 * 
 * */
@WebServlet("/Class.process")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String level,grade,className;    
    int id; 
    SessionFactory factory;
    ClassInfoDAO dao;
    ClassInfoService service;
    
    public ClassServlet() {
    	factory = HibernateUtil.getSessionFactory();
    	dao = new ClassInfoDAO(factory );
    	service = new ClassInfoService(dao);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	String action = request.getParameter("action");
	 	int id = 0 ;
	 	try {
	 		id = Integer.parseInt(request.getParameter("id"));
	 	}catch(Exception e){id = 0;}
	 	
	 	if (action.equals("delete")) {
	 		boolean delete = service.delete(id);
	 		System.out.println(delete);
	 		response.sendRedirect("class_info.jsp");
	 	}
	 	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		try {
		id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			id = 0;
		}
		level = request.getParameter("level");
		grade = request.getParameter("grade");
		className = request.getParameter("className");	
		String action = request.getParameter("action");
		ClassInfoBean bean = new ClassInfoBean(level,grade,className);	
		if(action.equals("add"))
		{
			bean.setId(0);
			ClassInfoBean insert = service.insert(bean);
			System.out.println(insert);
			response.sendRedirect("class_info.jsp");
		}if(action.equals("update"))
		{
			bean.setId(id);
			ClassInfoBean update = service.update(bean);
			System.out.println(update);
			response.sendRedirect("class_info.jsp");
		}		
		doGet(request, response);
	}

}
