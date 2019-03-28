package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misc.HibernateUtil;
import model.dao.PackageInfoDAO;
import model.service.PackageInfoService;

 
@WebServlet("/validation")
public class validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter out;   
     
    public validation() {
        super();
     }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String lots=request.getParameter("lots");
		  out = response.getWriter();
		  PackageInfoService service = new PackageInfoService(new PackageInfoDAO(HibernateUtil.getSessionFactory()));	
		  boolean isExist = service.select(lots);
		  out.print(isExist); 
		doGet(request, response);
	}

}
