package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import misc.HibernateUtil;
import model.*;
import model.dao.*;
import model.service.*;
 
@WebServlet("/reload")
public class ReloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	 
	private HumanInfoService humanInfoService = null;
	private ClassInfoService classInfoService = null;
	private PersonClassInfoService personClassService = null;
	public ReloadServlet()
    {      
		humanInfoService = new HumanInfoService(new HumanInfoDAO(HibernateUtil.getSessionFactory()));
		classInfoService = new ClassInfoService(new ClassInfoDAO(HibernateUtil.getSessionFactory()));
		personClassService = new PersonClassInfoService(new PersonClassInfoDAO(HibernateUtil.getSessionFactory()));
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");		
		
		List<HumanInfoBean> humanList = humanInfoService.select();
		List<ClassInfoBean> classList = classInfoService.select();
		for(HumanInfoBean human:humanList) {
			for (ClassInfoBean mClass:classList)
			{
				String name = human.getName();
				String workNum = human.getWorkNum();
				String className = mClass.getClassName();
				// 符合必修職等
				if(Integer.valueOf(mClass.getGrade())<=human.getGrade())
				{
					//人名、課程不在資料庫
					if(!personClassService.isExist(name, className))
					{
						PersonClassInfoBean bean = new PersonClassInfoBean();
						bean.setId(0);
						bean.setWorkNum(workNum);
						bean.setName(name);
						bean.setClassName(className);
						bean.setPersonClassRecords(null);
						PersonClassInfoBean insert = personClassService.insert(bean );
						System.out.println(insert);
					}
				}
				
				
			}
		}
		String retUrl = request.getHeader("Referer");
		response.sendRedirect(retUrl);
		
		
		
		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		doGet(request, response);
	}

}
