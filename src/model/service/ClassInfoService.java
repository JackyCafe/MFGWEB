package model.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.ClassInfoBean;
import model.HumanInfoBean;
import model.dao.ClassInfoDAO;
import model.dao.HumanInfoDAO;

public class ClassInfoService implements IService<ClassInfoBean> {

	private static ClassInfoDAO dao;
	
	public static void main(String[] args) {
		Session session = HibernateUtil.createSessionFactoty().getCurrentSession();
		dao = new ClassInfoDAO(HibernateUtil.createSessionFactoty());
		ClassInfoService service = new ClassInfoService(dao);
		Transaction trx = dao.getSession().beginTransaction();
	/*	
		try {
			List<?> levelCount = service.getLevelCount();
			for(int i = 0;i<levelCount.size();i++)
			{
				Object[] row = (Object [])levelCount.get(i);
				System.out.println(row[0]+":"+row[1]);
			}
			
		}catch(Exception e)
		{
			
		}
		
		
		/*try {
			ClassInfoBean bean = new ClassInfoBean();
			bean.setId(0);
			bean.setClassName("結晶矽作業區介紹");
			bean.setLevel("2");
			bean.setGrade("1");
			ClassInfoBean insert = service.insert(bean);
			System.out.println(insert);
			trx.commit();
		}catch (Exception e) {
			trx.rollback();
		}
		
		
		
		try {
			trx = dao.getSession().beginTransaction();					
			List<ClassInfoBean> select = service.select();
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

	
	public ClassInfoService(ClassInfoDAO dao) {
		this.dao = dao;
	}
	
	 
	
	
	public List<String> getClassNameList()
	{
		String hql = "select className from ClassInfoBean order by level";
		return (List<String>) dao.getSession().createQuery(hql).getResultList();
	}
	
	@Override
	public ClassInfoBean insert(ClassInfoBean bean) {
		
		return dao.insert(bean);
	}

	@Override
	public ClassInfoBean select(int id) {
 		return dao.select(id);
	}

	@Override
	public List<ClassInfoBean> select() {
 		return dao.select();
	}

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public ClassInfoBean update(ClassInfoBean bean) {		 
		return dao.update(bean);
	}

}
