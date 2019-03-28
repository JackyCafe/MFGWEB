package model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.mysql.jdbc.Util;
import misc.HibernateUtil;
import model.HumanInfoBean;
import model.PackageInfoBean;
import misc.Utils;

public class PackageInfoDAO extends DAO  implements IDAO<PackageInfoBean>{

	
	
	public static SessionFactory factory;
	public static Session session;
	private static Transaction trx;
	 

	public static void main(String[] args) {
 	}
	
	public PackageInfoDAO(SessionFactory factory) {
		super(factory);
 	}

	 


	@Override
	public PackageInfoBean select(PackageInfoBean bean) {
		if (bean != null) {
			bean = this.getSession().get(PackageInfoBean.class, bean.getId());			
		}
		return bean;
	}

	@Override
	public PackageInfoBean select(int id) {		
		return this.getSession().get(PackageInfoBean.class, id);
	}

	@Override
	public List<PackageInfoBean> select() {		
		return this.getSession()
				.createQuery("from PackageInfoBean",PackageInfoBean.class)
				.getResultList();
	}
	
	
	/*20190103 修改，因應packingReport 調整 */

	public List<PackageInfoBean> select (String sd)
	{
		Date date1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.YYMMDD);
		try {
			date1 = sdf.parse(sd);
		} catch (ParseException e) {
 			e.printStackTrace();
		}
 		
		return null;
		
	}
	
	
	/* */
	public List<PackageInfoBean> select (String sd,String ed,String lot_id)
	{
		Date sdd = null;
		Date edd = null;
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.YYMMDD);
		try {
			sdd = sdf.parse(sd);
		} catch (ParseException e) {
 			e.printStackTrace();
		}
		
		try {
			edd = sdf.parse(ed);
		} catch (ParseException e) {
 			e.printStackTrace();
		}
		
		return this.getSession()
				.createQuery("from PackageInfoBean where date1>=:sd and date1 <=:ed"
				 + " and (reactorLot=:lot_id or reactorLot2 =:lot_id or  reactorLot3 =:lot_id )",PackageInfoBean.class)
				.setParameter("sd", sdd)
				.setParameter("ed", edd)
				.setParameter("lot_id", lot_id)
				.getResultList();
		
		
		/*return null;*/
		
	}
	
/* 2019/01/03 mark ， because 要改 packingReport.jsp
 * Hibernate hql group by 轉成list
 * 再由 list 中拆出 product、 weight 後塞入hashmap
 * 再存放至list 結構中 傳回 
 * 
	public List<HashMap<String,Double>> selectWeight(String sd) {		
 		List<HashMap<String,Double>> lists = null;
 		HashMap<String,Double> h = null;
		Date date1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			date1 = sdf.parse(sd);
			System.out.println(""+date1);
		} catch (ParseException e) {
 			e.printStackTrace();
		}

		List<?> list = this.getSession()
				.createQuery("select product,sum(totalWeight) from PackageInfoBean "
						+ "where date1=:date1 group by product")
				.setParameter("date1", date1).list();
		Iterator<?> it = list.iterator();
		while(it.hasNext())
		{
			Object[] result = (Object[])it.next();
			String prod = (String) result[0];
			Double weight = (Double)result[1];
			h.put(prod, weight);
			lists.add(h);
 		}	
		
     	return 	lists;		
	}
	
	*/
	
	@Override
	public PackageInfoBean insert(PackageInfoBean bean) {
		PackageInfoBean tmp = select(bean);
		System.out.println(bean);
		if (tmp == null) 
		{
			this.getSession().save(bean);
 		}
		return bean;
 	}

	@Override
	public Boolean delete(PackageInfoBean bean) {
		PackageInfoBean tmp = select(bean.getId());
		if (tmp != null) {
			return delete(bean.getId());
		} else {
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		PackageInfoBean temp = select(id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PackageInfoBean update(PackageInfoBean bean) {
		PackageInfoBean tmp = select(bean.getId());
		if (tmp != null) {
			tmp.setId(bean.getId());
			tmp.setDate1(bean.getDate1());
			tmp.setTime1(bean.getTime1());
			tmp.setBox(bean.getBox());
			tmp.setProduct(bean.getProduct());
			tmp.setReactorLot(bean.getReactorLot());
			tmp.setLot1(bean.getLot1());
			tmp.setWeight1(bean.getWeight1());
			tmp.setLot2(bean.getLot2());
			tmp.setWeight2(bean.getWeight2());
			tmp.setLot3(bean.getLot3());
			tmp.setWeight3(bean.getWeight3());
			tmp.setRecorder(bean.getRecorder());
			tmp.setTotalWeight(bean.getTotalWeight());
			tmp.setReactorLot2(bean.getReactorLot2());
			tmp.setReactorLot3(bean.getReactorLot3());
		}
		return tmp;
	}

 

	
	
	
}
