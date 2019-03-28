package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import misc.HibernateUtil;
import model.PersonClassInfoBean;
import model.PersonClassRecordBean;
import model.dao.PersonClassInfoDAO;
import model.dao.PersonClassRecordDAO;
import model.service.HumanInfoService;
import model.service.PersonClassInfoService;
import model.service.PersonClassRecordService;

/* 課程紀錄維護
 * 
 * */
@WebServlet("/ClassRecord.process")
public class ClassRecordServlet extends HttpServlet {
	private String strId, strPerson_class_id, name, className, strClassDate, strTestDate, strTestScore;
	private int intPerson_class_id, intScore;
	private Date classDate, testDate;
	private PersonClassInfoService personClassInfoService;
	private PersonClassRecordService personClassRecordService;
	
	private HumanInfoService humanInfoService;

	public ClassRecordServlet() {
		personClassInfoService = new PersonClassInfoService(new PersonClassInfoDAO(HibernateUtil.getSessionFactory()));
		personClassRecordService = new PersonClassRecordService(new PersonClassRecordDAO(HibernateUtil.getSessionFactory()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		strPerson_class_id = request.getParameter("person_class_id");
		intPerson_class_id = Integer.parseInt(strPerson_class_id);
		name = request.getParameter("name");
		className = request.getParameter("className");
		strClassDate = request.getParameter("class_date");
		strTestDate = request.getParameter("test_date");
		strTestScore = request.getParameter("scope");

		try {
			intScore = Integer.parseInt(strTestScore);
		} catch (Exception e) {
			intScore = 0;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if (strClassDate != null) {
			try {
				classDate = sdf.parse(strClassDate);
			} catch (ParseException e) {
				classDate = null;
				System.out.println(e.toString());
			}
		} else {
			classDate = null;
		}
		
		if (strTestDate != null) {
			try {
				testDate = sdf.parse(strTestDate);
			} catch (ParseException e) {
				testDate = null;
				
			}
		} else {
			testDate = null;
		}
		System.out.println(testDate);
        /* 由addRecord.jsp 的id 中取出 個人應選課的bean
         * */
		PersonClassInfoBean pcib = personClassInfoService.select(intPerson_class_id);
		PersonClassRecordBean pcrb;
		if(testDate == null)
			pcrb = new PersonClassRecordBean(pcib, classDate, testDate,null);
		else
			pcrb = new PersonClassRecordBean(pcib, classDate, testDate,intScore);
		pcib.getPersonClassRecords().add(pcrb); 
		personClassRecordService.insert(pcrb);		
		doGet(request, response);
		response.sendRedirect("person_class_info.jsp");
	}

}
