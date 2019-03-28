package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.HumanInfoBean;
import model.dao.HumanInfoDAO;
import model.service.HumanInfoService;

/**
 * Servlet implementation class addHuman
 */
 
 @WebServlet(
		   description = "上傳", 
		  urlPatterns = { "/Human.process" }, 
		  initParams = { 
		    @WebInitParam(name = "upload-path"
		      , value = "C:\\Users\\lhm05\\git\\MFGWEB\\WebContent\\upload")
		  })
 @MultipartConfig 

public class HumanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String workNum, name, account, password, sex, duty, resignation, grade, class_, area, email, permission, birth, tel,
			contact_persion, contact_persion_tel, address, dormitory,pages;
	Date duty_date, resignation_date, birthday;
	int id, igrade;
	List<String> lists = new ArrayList<>();
	List<HumanInfoBean> humans = new ArrayList<>();
	HumanInfoBean human;
 	Date dutyDate;
 	SessionFactory factory;
 	HumanInfoDAO dao;
 	HumanInfoService service;
	HumanInfoBean bean;
	
 	public HumanServlet() {
 		
		super();
		factory = HibernateUtil.getSessionFactory();
		dao = new HumanInfoDAO(factory);
		service = new HumanInfoService(dao);

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
		pages = request.getParameter("pages");
		bean = getHumanInfo(request); //由request 取得相關資訊後，轉成bean 
		
		//如果來的action 是add，執行新增
		if (request.getParameter("action").equals("add"))
			doAdd(request, response);
		//如果來的是update，執行編輯
		else if(request.getParameter("action").equals("update"))
			doUpdate(request, response);
		if (request.getParameter("action").equals("upload"))
			doUpload(request, response);
		response.sendRedirect("role_info.jsp?pages="+pages);

 	}

	
	
	/*上傳檔案*/
	private void doUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
 		try {
			Part part = request.getPart("uploadfile");
 			String filename = part.getSubmittedFileName(); 
   		    String uploadPath = getServletConfig().getInitParameter("upload-path");
		    filename = uploadPath+"\\"+filename;
 		    BufferedInputStream bin = new BufferedInputStream(part.getInputStream());		   
  		    readfile(bin);
  		  
  		    
 		} catch (ServletException e) {
			System.out.println(e.toString());
 			e.printStackTrace();
		}
		  resolveFileToBean();
		  saveBeanToDB();
 	}
	
	/*讀檔並存回list*/
	private  void readfile(InputStream fin) {
 		try {
 			InputStreamReader reader = new InputStreamReader(fin, "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String text;
			while ((text = br.readLine()) != null) {
					lists.add(text);
			}
				br.close();				
				fin.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			} 	 	
 }
	
	/* 將CSV 檔 轉成bean
	 *  
	 * */
	private  void resolveFileToBean() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		for (String list : lists) {
			String[] strArray = list.split(",");
			for (int i = 0; i < strArray.length; i++) { 
				workNum = strArray[0];
 				name = strArray[3];
				sex = strArray[4];		
				tel = strArray[13];
 				try {
					dutyDate = sdf.parse(strArray[5]);
				} catch (ParseException e) {
					dutyDate = null;
 					e.printStackTrace();
				}
				try {
					igrade = Integer.parseInt(strArray[7]);
				}catch (Exception e) {
					igrade = 0;
 				}
				
				try {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yy/MM/dd");
					birthday = sdf1.parse(strArray[12]);
				} catch (Exception e) {
					birthday = null ;
				} 
			} 		 
 			human = new HumanInfoBean();
			human.setId(0);
			human.setWorkNum(workNum);
			human.setAccount(null);
			human.setPassword("p@ssw0rd");
			human.setName(name);
			human.setSex(sex);
			human.setDutyDate(dutyDate);
			human.setGrade(igrade);
			human.setPermission("R");	
			human.setBirthday(birthday);
			human.setTel(tel);
			humans.add(human);
 		}
  
	}

	
	/*呼叫HumanInfoService 來加入資料*/
	private void saveBeanToDB()
	{
 		for (HumanInfoBean bean : humans)
		{
			service.insert(bean);
		}
	 	 
	}
	
   /* private String getFilename(String body){
    		int start = body.indexOf("filename=\"");
    		String temp = body.substring(start+10);
    		String filename = temp.substring(0, temp.indexOf("\""));
    		int s = filename.lastIndexOf("\\");
    		filename = filename.substring(s+1);  
    		return filename;
  }*/

	/*執行新增*/
    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		bean.setId(0);
		service.insert(bean);
		 
	}
    
    private void doUpdate(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
    	id = Integer.valueOf(request.getParameter("id"));
		bean.setId(id);
		service.update(bean);
      	
    }
    
    /*取得基本資訊*/
	private HumanInfoBean getHumanInfo(HttpServletRequest request) {
		workNum = request.getParameter("workNum");
		name = request.getParameter("name");
		account = request.getParameter("account");
		password = request.getParameter("password");
		sex = request.getParameter("sex");
		duty = request.getParameter("duty_date");
		resignation = request.getParameter("resignation_date");
		grade = request.getParameter("grade");
		
		try {
			igrade = Integer.valueOf(grade);
		} catch (Exception e) {
			igrade = 1;
		}
		class_ = request.getParameter("class");

		area = request.getParameter("area");
		email = request.getParameter("email");
		permission = request.getParameter("permission");
		birth = request.getParameter("birthday");
		tel = request.getParameter("tel");
		contact_persion = request.getParameter("contact_persion");
		contact_persion_tel = request.getParameter("contact_persion_tel");
		address = request.getParameter("address");
		dormitory = request.getParameter("dormitory");
		duty_date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			duty_date = sdf.parse(duty);
		} catch (Exception e) {
			duty_date = null;
		}

		try {
			resignation_date = sdf.parse(resignation);
		} catch (Exception e) {
			resignation_date = null;
		}

		try {
			birthday = sdf.parse(birth);
		} catch (Exception e) {
			birthday = null;
		}
		HumanInfoBean bean = new HumanInfoBean(workNum, account, password, name, sex, duty_date, resignation_date,
				igrade, class_, area, email, permission, birthday, tel, contact_persion, contact_persion_tel, address,
				dormitory);
		return bean;
	}

	 
}
