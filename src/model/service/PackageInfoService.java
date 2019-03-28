package model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import misc.Utils;
import model.HumanInfoBean;
import model.PackageInfoBean;
import model.dao.HumanInfoDAO;
import model.dao.PackageInfoDAO;

public class PackageInfoService implements IService<PackageInfoBean> {

	private static PackageInfoDAO dao;
	
	public static void main(String[] args) {
		Session session = HibernateUtil.createSessionFactoty().getCurrentSession();
		dao = new PackageInfoDAO(HibernateUtil.getSessionFactory());
		PackageInfoService service = new PackageInfoService(dao);
		/*PackageInfoBean bean = new PackageInfoBean(new Date(), new Date()
				,"box", "product","123", "lot1", 10
				, "lot2", 10, "lot3", 10, 30, "YH");	
		bean.setId(0);
		Transaction trx = null;
		// Test insert function OK
		
		try {
			trx  = dao.getSession().beginTransaction();
			PackageInfoBean insert = service.insert(bean);
			System.out.println(insert);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s: e.getStackTrace())
			{
				System.out.println(s.toString());
			}
			System.out.println(e.toString());
			trx.rollback();
		}
		
		
		
		
		
		
		//select 
		try {
			trx  = dao.getSession().beginTransaction();
			List<PackageInfoBean> select = service.select();
			System.out.println(select);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s: e.getStackTrace())
			{
				System.out.println(s.toString());
			}
			System.out.println(e.toString());
			trx.rollback();
		}
		
		*/
		
	}

	
	public PackageInfoService(PackageInfoDAO dao) {
		this.dao = dao;
	}
	
	
	@Override
	public PackageInfoBean insert(PackageInfoBean bean) {
 		return dao.insert(bean);
	}

	@Override
	public PackageInfoBean select(int id) {
 		return dao.select(id);
	}

	@Override
	public List<PackageInfoBean> select() {
 		return dao.select();
	}
	
	
	public boolean select(String lots)
	{
		 int count = (int)select().stream()
		.filter(p->p.getLot1().equals(lots)||p.getLot2().equals(lots)||p.getLot3().equals(lots)).count();	 
 		 return count>0?true:false;
 		 
		 
 		
	}
	
	/* Select 方法，輸入起始日期與結束日期。
	 * 
	 * */
	
	public List<PackageInfoBean> select(String sd,String ed)
	{
 		sd = sd.replaceAll("/", "-"); 	
 		ed = ed.replaceAll("/", "-");
 		Date start_date = Utils.String2Date(sd,Utils.YYMMDD);
 		Calendar c = Calendar.getInstance();
 		c.setTime(start_date);
 		c.add(Calendar.DATE	, -1);
 		start_date = c.getTime();
 		
 		Date end_date = Utils.String2Date(ed, Utils.YYMMDD); 		
 		c.setTime(end_date);
 		c.add(Calendar.DATE	, 1);
 		end_date = c.getTime();
		
 		//由select 方法中跑for each方法，找尋日期符合區間者
 		List<PackageInfoBean> lists = new ArrayList<>(); 
   		for(PackageInfoBean bean:select())
 		{
  			 if(bean.getDate1().after(start_date) && 
 					bean.getDate1().before(end_date)	 )
 			 {
 				 lists.add(bean);
 			 }
 		}
 		
		return lists;
		
	} 
	
	
	/* queryPackageList.jsp 查詢介面使用
	 * 	 */
	public  List<PackageInfoBean> select(String sd,String ed,String lot_id)
	{   
 		return select(sd,ed).stream()
			   .filter(box->box.getReactorLot().equals(lot_id)||box.getReactorLot2().equals(lot_id)||box.getReactorLot3().equals(lot_id))
 			   .collect(Collectors.toList());	
	}
	
	
	
	public List<PackageInfoBean> selectWeight(String date1) {
		
 		return dao.select(date1);
	}
	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public PackageInfoBean update(PackageInfoBean bean) {		 
		return dao.update(bean);
	}
	
	 

}
