package model.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.PersonClassInfoBean;
import model.PersonClassRecordBean;

public class PersonClassRecordDAO extends DAO implements IDAO<PersonClassRecordBean> {
	
	public PersonClassRecordDAO(SessionFactory factory) {
		super(factory);
 	}

	public static SessionFactory factory;
	private static Session session;
	private static Transaction trx;
 	
	public static void main(String[] args) {
		factory = HibernateUtil.getSessionFactory();
		session = factory.getCurrentSession();
		PersonClassRecordDAO personClassRecordDAO = new PersonClassRecordDAO(factory);
		PersonClassInfoDAO personClassInfoDAO =new PersonClassInfoDAO(factory);
		PersonClassRecordBean bean ;
		PersonClassInfoBean pcibBean ;
		 
		try {
			trx = personClassRecordDAO.getSession().beginTransaction();
 			
 			System.out.println(personClassRecordDAO.select());
			trx.commit();
		} catch (Exception e) {
			trx.rollback();
		}
		
		
	}
	
	@Override
	public PersonClassRecordBean select(PersonClassRecordBean bean) {
		PersonClassRecordBean select = null;
		if (bean!=null)
		{
			select = this.getSession().get(PersonClassRecordBean.class, bean.getId());
		}
 		return select;
	}

	@Override
	public PersonClassRecordBean select(int id) {
		PersonClassRecordBean select = null;
		select = this.getSession().get(PersonClassRecordBean.class, id);		
 		return select; 		
	}

	@Override
	public List<PersonClassRecordBean> select() {
 		return this.getSession()
 				.createQuery("from PersonClassRecordBean", PersonClassRecordBean.class)
 				.getResultList();
	}
	
 
	

	@Override
	public PersonClassRecordBean insert(PersonClassRecordBean bean) {
		PersonClassRecordBean insert = null;
		if(bean!=null)
		{
			this.getSession().save(bean);
 		}
 		return bean;
	}

	@Override
	public Boolean delete(PersonClassRecordBean bean) {
		if(bean !=null)
		{
			this.getSession().delete(bean);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		PersonClassRecordBean delete;
		delete = select(id);
		if(delete !=null)
		{
			this.getSession().delete(delete);
		 	return true;
		}else {
			return false;}
	}

	@Override
	public PersonClassRecordBean update(PersonClassRecordBean bean) {
		PersonClassRecordBean tmp = select(bean);
		if(tmp != null) 
		{
			tmp.setClassDate(bean.getClassDate());
			tmp.setTestDate(bean.getTestDate());
			tmp.setTestScore(bean.getTestScore());
			tmp.setPersonClassInfo(bean.getPersonClassInfo());		 
 		return tmp;
		}else {
			return null;
		}
	}

	 

}
