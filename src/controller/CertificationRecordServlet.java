package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.HumanInfoBean;
import model.PersonCertificationInfoBean;
import model.PersonCertificationRecordBean;
import model.dao.PersonCertificationInfoDAO;
import model.dao.PersonCertificationRecordDAO;
import model.service.PersonCertificationInfoService;
import model.service.PersonCertificationRecordService;

 
@WebServlet("/CertificationRecord.process")
public class CertificationRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sid ;
	private String name,workNum,shift,area,knowledge,sKnowledgeDate
	,sKnowledgeScore,technical,sTechnicalDate,sTechnicalScore,sTrainingDate; //姓名
	private PrintWriter out;
	private Date knowledgeDate,technicalDate,trainingDate;
	private PersonCertificationInfoService pcis;
	private PersonCertificationRecordService pcrs;
	private int id,knowledgeScore,technicalScore;
	private PersonCertificationInfoBean pcib;
	private PersonCertificationRecordBean pcrb;
	private PersonCertificationRecordDAO pcrd ;
	private String trainer;
	private Transaction trx;
	
    public CertificationRecordServlet() {
        super();
        pcrd = new PersonCertificationRecordDAO(HibernateUtil.getSessionFactory());
        pcis = new PersonCertificationInfoService(new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory()));
        pcrs = new PersonCertificationRecordService(pcrd);
        name=workNum=shift=area=knowledge=sKnowledgeDate
            	=sKnowledgeScore=technical=sTechnicalDate=sTechnicalScore=sTrainingDate="";
     }

    @Override
    public void init() throws ServletException {
    	
     	super.init();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
		doGet(request, response);
	}


	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		sid = request.getParameter("person_class_id");
		name = request.getParameter("name");
		workNum = request.getParameter("worknum");
		area = request.getParameter("area");
		shift = request.getParameter("shift");
		knowledge = request.getParameter("knowledge");
		sKnowledgeDate = request.getParameter("knowledgedate");
		sKnowledgeScore = request.getParameter("knowledgescore");
		technical = request.getParameter("technical");
		sTechnicalDate = request.getParameter("technicaldate");
		sTechnicalScore = request.getParameter("technicalscore");
		sTrainingDate = request.getParameter("trainingdate");
		System.out.print(sTrainingDate	);	
		id = Integer.valueOf(sid); //
		try {
			technicalScore = Integer.valueOf(sTechnicalScore);
		}catch (Exception e) {
			technicalScore = 0;
		}
		try {
			knowledgeScore = Integer.valueOf(sKnowledgeScore);
		}catch (Exception e) {
			knowledgeScore = 0; 
 		}
		out = response.getWriter();
		 
	
		pcib = pcis.select(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			knowledgeDate = sdf.parse(sKnowledgeDate);
			
		} catch (ParseException e) {
			knowledgeDate = null;
  			e.printStackTrace();
		}

		try {
 			technicalDate = sdf.parse(sTechnicalDate);
 		} catch (ParseException e) {
 			technicalDate = null;
			response.getWriter().append(e.toString());
 			e.printStackTrace();
		}
		
		try {
 			trainingDate = sdf.parse(sTrainingDate);
		} catch (ParseException e) {
			trainingDate = null;
  			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		HumanInfoBean owner = (HumanInfoBean) session.getAttribute("account");
		trainer = owner.getName() ;
		
		pcrb = new PersonCertificationRecordBean(pcib, knowledgeDate, knowledgeScore
				, technicalDate, technicalScore, trainingDate, trainer);
		pcrb.setId(0);
		pcrb.setPersonCertificationInfo(pcib);		
		PersonCertificationRecordBean bean = pcrs.insert(pcrb);			
		System.out.println(bean);			
		pcib.getPersonCertificationRecords().add(pcrb);
		pcis.update(pcib);
		out.println(pcrb);
		response.sendRedirect("PersonCertificationInfo.jsp");
	}

}
