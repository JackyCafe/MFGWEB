package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import misc.HibernateUtil;
import model.PersonClassInfoBean;
import model.PersonClassRecordBean;
import model.dao.PersonClassInfoDAO;
import model.service.PersonClassInfoService;

/**
 * Servlet implementation class queryPersonRecord
 */
@WebServlet("/queryPersonRecord")
public class QueryPersonRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PersonClassInfoDAO pcid ;
    private String classname;
    private PersonClassInfoService service;
    public QueryPersonRecord()
    {
     	pcid = new PersonClassInfoDAO(HibernateUtil.getSessionFactory());
     	service = new PersonClassInfoService(pcid);
    }
    
    
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pcid = new PersonClassInfoDAO(HibernateUtil.getSessionFactory());
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
	    request.setCharacterEncoding("UTF-8");
		classname = request.getParameter("mclass_");
		List<PersonClassInfoBean> select =  service.select(classname);
		 String html =" ";
		 String classDate ="";
		 int i =1;
		for(PersonClassInfoBean bean: select)
		{
			List<PersonClassRecordBean> items = new ArrayList<>();
			for(PersonClassRecordBean e :bean.getPersonClassRecords())
				items.add(e);
			if (items.size()>0)
			html = html+"<tr><td>"+bean.getName()+"</td>"
					+"<td>"+bean.getClassName()+"</td>"+"<td>"+items.size()+"</td>"
					+"<td>"+ items.get(items.size()-1).getClassDate() +"</td>"
					+"<td>"+ items.get(items.size()-1).getTestDate() +"</td>"
					+"<td>"+ items.get(items.size()-1).getTestScore() +"</td></tr>";
		}
		response.getWriter().println(html);
		
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
	    response.setCharacterEncoding("utf-8");
	    request.setCharacterEncoding("utf-8");
		classname = request.getParameter("mclass_");
		response.getWriter().println(classname);
		doGet(request, response);
	}

}
