package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misc.HibernateUtil;
import model.CertificationItemBean;
import model.dao.CertificationItemDAO;
import model.service.CertificationItemService;

/**
 * Servlet implementation class CertificationItemServlet
 *  設定認證區域，By case設定 區域、學科、術科、是否只有訓練不需認證
 *  ex 緩衝墊
 * 
 *  來源View :
 *  1.新增  => addCertification.jsp
 *  2.編輯 => CertificationItemInfo.jsp -->editCertification.jsp
 *  3.刪除=> CertificationItemInfo.jsp
 */
@WebServlet("/Certification.process")
public class CertificationItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String area,knowledge,technical,training,straining_only;   
    private CertificationItemService service ;	
    private String action;
    private int id;
    
    public CertificationItemServlet() {
    		service = new CertificationItemService(new CertificationItemDAO(HibernateUtil.getSessionFactory()));
     }

 
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");		
		action = request.getParameter("action");	
		id = Integer.parseInt(request.getParameter("id"));		
		service.delete(id);
		response.sendRedirect("CertificationItemInfo.jsp");
 
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		area = request.getParameter("area");  // 區域
		knowledge = request.getParameter("knowledge"); //學科
		technical = request.getParameter("technical"); //術科
		training = request.getParameter("training"); //訓練
		straining_only = request.getParameter("training_only"); //只訓練不測驗
 		//有打勾為1，沒打勾為0
		byte training_only = 0;
		if(straining_only!=null)
			training_only = (byte) (straining_only.equals("on")?1:0);
		else 
			training_only = 0;
 
		
		action = request.getParameter("action");
		
		//新增
		if(action.equals("add"))
		{
			CertificationItemBean bean = new CertificationItemBean(area, knowledge, technical,training,training_only);
			bean.setId(0);
			service.insert(bean);			
			response.sendRedirect("CertificationItemInfo.jsp");
		}
			
		//編輯
		if(action.equals("update"))
		{
			id = Integer.valueOf(request.getParameter("id"));
			CertificationItemBean bean = new CertificationItemBean(area, knowledge, technical,training,training_only);
			bean.setId(id);
			service.update(bean);
			response.sendRedirect("CertificationItemInfo.jsp");
		}
		
	}

}
