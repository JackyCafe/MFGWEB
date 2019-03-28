package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import misc.HibernateUtil;
import model.HumanInfoBean;

public class HumanInfoDAO extends DAO  implements IDAO<HumanInfoBean>{
 	public static SessionFactory factory;
	public static Session session;
	private static Transaction trx;
	 

	public static void main(String[] args) {
		test();		
	}
	
	public HumanInfoDAO(SessionFactory factory) {
		super(factory);
 	}

	private static void test() {
		factory = HibernateUtil.getSessionFactory();
		session = factory.getCurrentSession();
		HumanInfoDAO dao = new HumanInfoDAO(factory);
		// insert
		try {
			trx = dao.getSession().beginTransaction();
			HumanInfoBean bean = new HumanInfoBean();			
			bean.setId(0);		
			bean.setWorkNum("00211");
			bean.setAccount("Simon");
			bean.setPassword("p@ssw0rd");
			bean.setName("王東為");
			bean.setSex("F");
			bean.setDutyDate(null);
			bean.setResignationDate(null);
			bean.setGrade(2);
			bean.setClass_("");
			bean.setArea("");
			bean.setEmail("");
			bean.setPermission("R");
			bean.setBirthday(null);
			bean.setTel("");
			bean.setContactPersion("");
			bean.setContactPersionTel("");
			HumanInfoBean insert = dao.insert(bean);
			System.out.println("insert" + insert);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.println(s.toString());
			System.out.println(e.getMessage());
		}

		/* update*/ 
		try {
			session = factory.getCurrentSession();
			trx = dao.getSession().beginTransaction();
			HumanInfoBean bean = new HumanInfoBean();			
			bean.setId(2);			
			bean.setWorkNum("00211");
			bean.setAccount("Simon");
			bean.setPassword("p@ssw0rd");
			bean.setName("王東為");
			bean.setSex("F");
			bean.setDutyDate(null);
			bean.setResignationDate(null);
			bean.setGrade(2);
			bean.setClass_("");
			bean.setArea("");
			bean.setEmail("");
			bean.setPermission("A");
			bean.setBirthday(null);
			bean.setTel("");
			bean.setContactPersion("");
			bean.setContactPersionTel(""); 
			HumanInfoBean update = dao.update(bean);			
			System.out.println("update" + update);
			trx.commit();

		} catch (Exception e) {

		}
		// Select
		try {
			session = factory.getCurrentSession();
			trx = session.beginTransaction();
			List<HumanInfoBean> select = dao.select();
			System.out.println("select" + select);
			trx.commit();

		} catch (Exception e) {

		}
		
		/* delete
		try {
					session = factory.getCurrentSession();
					trx = session.beginTransaction();
					Boolean delete = dao.delete(3);
					trx.commit();
					System.out.println("delete "+ delete);

				} catch (Exception e) {

				}*/
	}

	
	public List<HumanInfoBean> select(String account,String password)
	{
		return this.getSession()
				.createQuery("from HumanInfoBean where account=:account and password =:password"
						,HumanInfoBean.class)
				.setParameter("account", account)
				.setParameter("password", password)				
				.getResultList();
		
		 
	}
	
	public Query<HumanInfoBean> selectByPage()
	{
		Query<HumanInfoBean> query =this.getSession()
				.createQuery("from HumanInfoBean order by workNum "	,HumanInfoBean.class);
		return query;
	}

	@Override
	public HumanInfoBean select(HumanInfoBean bean) {
		if (bean != null) {
			bean = this.getSession().get(HumanInfoBean.class, bean.getId());			
		}
		return bean;
	}

	@Override
	public HumanInfoBean select(int id) {		
		return this.getSession().get(HumanInfoBean.class, id);
	}

	@Override
	public List<HumanInfoBean> select() {		
		return this.getSession()
				.createQuery("from HumanInfoBean where resignationDate is NULL order by class_,workNum",HumanInfoBean.class)
				.getResultList();
	}

	@Override
	public HumanInfoBean insert(HumanInfoBean bean) {
		HumanInfoBean tmp = select(bean);
		if (tmp == null) {
			this.getSession().save(bean);
			System.out.println("tmp bean " + bean);
		}
		return bean;
 	}

	@Override
	public Boolean delete(HumanInfoBean bean) {
		HumanInfoBean tmp = select(bean.getId());
		if (tmp != null) {
			return delete(bean.getId());
		} else {
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		HumanInfoBean temp = select(id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public HumanInfoBean update(HumanInfoBean bean) {
		HumanInfoBean tmp = select(bean.getId());
		if (tmp != null) {
			tmp.setId(bean.getId());			
			tmp.setWorkNum(bean.getWorkNum());
			tmp.setAccount(bean.getAccount());
			tmp.setPassword(bean.getPassword());
			tmp.setName(bean.getName());
			tmp.setSex(bean.getSex());
			tmp.setDutyDate(bean.getDutyDate());
			tmp.setResignationDate(bean.getResignationDate());
			tmp.setGrade(bean.getGrade());
			tmp.setClass_(bean.getClass_());
			tmp.setArea(bean.getArea());
			tmp.setEmail(bean.getEmail());
			tmp.setPermission(bean.getPermission());
			tmp.setBirthday(bean.getBirthday());
			tmp.setTel(bean.getTel());
			tmp.setContactPersion(bean.getContactPersion());
			tmp.setContactPersionTel(bean.getContactPersionTel()); 
			tmp.setAddress(bean.getAddress());
			tmp.setDormitory(bean.getDormitory());
		}
		return tmp;
	}

 

	
	
	
}
