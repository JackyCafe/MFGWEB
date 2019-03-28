package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.ClassInfoBean;

public class ClassInfoDAO extends DAO implements IDAO<ClassInfoBean>{

	

	public static SessionFactory factory;
	public static Session session;
	private static Transaction trx;
	
	 
	public ClassInfoDAO(SessionFactory factory) {
		super(factory);
 	}
	public static void main(String[] args) {
		test();
		
	}

	private static void test() {
		factory = HibernateUtil.getSessionFactory();
		session = factory.getCurrentSession();
		ClassInfoDAO dao = new ClassInfoDAO(factory);
		 
		// Select
		try {
			session = factory.getCurrentSession();
			trx = session.beginTransaction();
			List<ClassInfoBean> select = dao.select();
			
			trx.commit();

		} catch (Exception e) {
			for(StackTraceElement st :e.getStackTrace())
			System.out.println(st.toString());
			trx.rollback();
		}	
		
		 
	}


	@Override
	public ClassInfoBean select(ClassInfoBean bean) {
		if (bean != null) {
			bean = this.getSession().get(ClassInfoBean.class, bean.getId());			
		}
		return bean;
	}

	@Override
	public ClassInfoBean select(int id) {		
		return this.getSession().get(ClassInfoBean.class, id);
	}

	@Override
	public List<ClassInfoBean> select() {		
		return this.getSession()
				.createQuery("from ClassInfoBean order by id",ClassInfoBean.class)
				.getResultList();
	}

	@Override
	public ClassInfoBean insert(ClassInfoBean bean) {
		ClassInfoBean tmp = select(bean);
		if (tmp == null) {
			this.getSession().save(bean);
			System.out.println("tmp bean " + bean);
		}
		return bean;
 	}

	@Override
	public Boolean delete(ClassInfoBean bean) {
		ClassInfoBean tmp = select(bean.getId());
		if (tmp != null) {
			return delete(bean.getId());
		} else {
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		ClassInfoBean temp = select(id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ClassInfoBean update(ClassInfoBean bean) {
		ClassInfoBean tmp = select(bean.getId());
		if (tmp != null) {
			tmp.setId(bean.getId());
			tmp.setClassName(bean.getClassName()); //課程名稱
			tmp.setLevel(bean.getLevel());//level
			tmp.setGrade(bean.getGrade());//適用職等
		}
		return tmp;
	}

	 

	
	
	
}
