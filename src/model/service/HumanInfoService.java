package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import misc.HibernateUtil;
import model.HumanInfoBean;
import model.dao.HumanInfoDAO;

public class HumanInfoService implements IService<HumanInfoBean> {

	private static HumanInfoDAO dao;
	
	public static void main(String[] args) {
		Session session = HibernateUtil.createSessionFactoty().getCurrentSession();
		dao = new HumanInfoDAO(HibernateUtil.createSessionFactoty());
		HumanInfoService service = new HumanInfoService(dao);
		Transaction trx = dao.getSession().beginTransaction();
		
		try {
			List<HumanInfoBean> select = service.select();
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
		
	}

	
	public HumanInfoService(HumanInfoDAO dao) {
		this.dao = dao;
	}
	
	
	@Override
	public HumanInfoBean insert(HumanInfoBean bean) {
		
		return dao.insert(bean);
	}

	@Override
	public HumanInfoBean select(int id) {
 		return dao.select(id);
	}

	@Override
	public List<HumanInfoBean> select() {
 		return dao.select();
	}
	
	/*依區域班別查詢資料*/
	public List<HumanInfoBean> queryByAreaShift(String area,String shift){
		/**/
 		List<HumanInfoBean> areaLists =	
				select().stream().filter(h->h.getArea().equals(area)).collect(Collectors.toList());
		List<HumanInfoBean> shiftList =
				areaLists.stream().filter(h->h.getClass_().equals(shift)).collect(Collectors.toList());
 		if (shift.equals("ALL")) return areaLists;
		else return shiftList;
		
		
		/*List<HumanInfoBean> lists = new ArrayList<>();
		for(HumanInfoBean beans:select())
		{
			if(beans.getArea()!=null) {
 			 	 if(beans.getArea().equals(area))
			 		 if(beans.getClass_().equals(shift))
			 			 lists.add(beans);
			}
		} 
		return lists;*/
		
	}
	
	public List<HumanInfoBean> selectByPage(int pages,int count)
	{
		Query<HumanInfoBean> query = dao.selectByPage();
		return query.setFirstResult((pages-1)*count)
		.setMaxResults(count)
		.getResultList();
		
	}
	public List<HumanInfoBean> select(String account,String password)
	{
		return dao.select(account,password);
	}
	
	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public HumanInfoBean update(HumanInfoBean bean) {		 
		return dao.update(bean);
	}

}
