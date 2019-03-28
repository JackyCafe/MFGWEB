package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import misc.HibernateUtil;
import model.CertificationItemBean;
import model.HumanInfoBean;
import model.PersonCertificationInfoBean;
import model.dao.CertificationItemDAO;
import model.dao.HumanInfoDAO;
import model.dao.PersonCertificationInfoDAO;
import model.service.CertificationItemService;
import model.service.HumanInfoService;
import model.service.PersonCertificationInfoService;

/**
 * Servlet implementation class ReloadCertification
 */
@WebServlet(name = "reloadCertification", urlPatterns = { "/reloadCertification" })
public class ReloadCertification extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter out ;   
    private HumanInfoService his = null ;
    private CertificationItemService cis = null;
    private PersonCertificationInfoService pcis = null;
	private SessionFactory factory;
	private String area,shift;
	private String name;
    
    public ReloadCertification() {
        super();
        factory = HibernateUtil.getSessionFactory();
        his = new HumanInfoService(new HumanInfoDAO(factory));
        cis = new CertificationItemService(new CertificationItemDAO(factory));
        pcis = new PersonCertificationInfoService(new PersonCertificationInfoDAO(factory));
     }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");		
		
		area = request.getParameter("area");
		shift = request.getParameter("shift");
		out = response.getWriter();
		//人員個資
		List<HumanInfoBean> humans = his.queryByAreaShift(area, shift);
		List<CertificationItemBean> nonTrainingCertifications = cis.queryNonTraining(area); //學科、術科		
		List<CertificationItemBean> trainingCertifications = cis.queryTraining(area);  //
		List<PersonCertificationInfoBean> pcInfoCertification  = null;
		for(HumanInfoBean human:humans)
		{
			String name = human.getName();
			String workNum = human.getWorkNum();
			/*學科+術科*/
			for (CertificationItemBean nonTrainingCertification:nonTrainingCertifications )
			{
				
				String knowledge = nonTrainingCertification.getKnowledge(); //學科
				String technical = nonTrainingCertification.getTechnical();			
				boolean isKnowledgeExist = pcis.isKnowledgeExist(name, knowledge);
				boolean isTechnicalExist = pcis.isTechnicalExist(name, technical);				
				//學科與術科都不存在
				if(!isKnowledgeExist&&!isTechnicalExist)
				{
					PersonCertificationInfoBean bean = new PersonCertificationInfoBean(workNum, name, area, shift, knowledge, technical, "", (byte)0,null);
					bean.setId(0);
					PersonCertificationInfoBean insert = pcis.insert(bean); 					
				}
			} 
			
			/*只訓練不認證*/
			for (CertificationItemBean trainingCertification:trainingCertifications )
			{
				String training = trainingCertification.getTraining();
				boolean isTrainingExist =pcis.isTrainingExist(name, training);
				if(!isTrainingExist)
				{
					PersonCertificationInfoBean bean = new PersonCertificationInfoBean(workNum, name, area, shift, "", "", training	, (byte)1,null);
					bean.setId(0);
					PersonCertificationInfoBean insert = pcis.insert(bean);
				} 
				} 		
			}
		response.sendRedirect("PersonCertificationInfo.jsp");

		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		doGet(request, response);
	}

}
