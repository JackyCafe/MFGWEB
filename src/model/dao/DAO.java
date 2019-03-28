package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DAO<T> {
	static public SessionFactory factory;
	public static Transaction trx; 
	public DAO(SessionFactory factory) { this.factory = factory; }
	public Session getSession() { return factory.getCurrentSession();}
	public Transaction  getTransaction() { 
	    if (trx ==null) trx = getSession().beginTransaction();
		return  trx.isActive()? getSession().getTransaction():getSession().beginTransaction();
		
	}
	
	 
	 
}
