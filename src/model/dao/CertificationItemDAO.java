package model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.CertificationItemBean;

public class CertificationItemDAO extends DAO implements IDAO<CertificationItemBean> {

	public static void main(String[] args) {
		test();
	}
	
	
	
	private static void test() {
 		CertificationItemDAO dao = new CertificationItemDAO(HibernateUtil.getSessionFactory());
 		Transaction trx = dao.getSession().beginTransaction();
 		try {
 			List<CertificationItemBean> select = dao.select();
 			System.out.println(select);
 			trx.commit();
 		}catch (Exception e) {
			trx.rollback();
		}
		
	}



	public CertificationItemDAO(SessionFactory factory) {
		super(factory);	 
	}
	
	@Override
	public CertificationItemBean select(CertificationItemBean bean) {
		if (bean != null)		
			return select(bean.getId());
		else return null;
	}

	@Override
	public CertificationItemBean select(int id) {
		CertificationItemBean select =	this.getSession().get(CertificationItemBean.class, id);
		if(select != null ) return select;
		else return null;
	}

	public CertificationItemBean select(String knowledge) {
		CertificationItemBean select =	this.getSession().get(CertificationItemBean.class, knowledge);
		if(select != null ) return select;
		else return null;
	}
	@Override
	public List<CertificationItemBean> select() {		
		return this.getSession()
				.createQuery("from CertificationItemBean",CertificationItemBean.class)
				.getResultList();
	}

	@Override
	public CertificationItemBean insert(CertificationItemBean bean) {		
		if (select(bean)== null) 
			 this.getSession().save(bean);
		return bean;
	}

	@Override
	public Boolean delete(CertificationItemBean bean) {
		return delete(bean.getId());
		
	}

	@Override
	public Boolean delete(int id) {
		CertificationItemBean tmp = select(id);
		if ( tmp != null)
		{
			this.getSession().delete(tmp);
			return true;
		}else {
			return false;
		}
 		
	}

	@Override
	public CertificationItemBean update(CertificationItemBean bean) {
		CertificationItemBean tmp = select(bean.getId());
		if(tmp !=null)
		{
			tmp.setArea(bean.getArea());
			tmp.setKnowledge(bean.getKnowledge());
			tmp.setTechnical(bean.getTechnical());
			tmp.setTraining(bean.getTraining());
			tmp.setTrainingOnly(bean.getTrainingOnly());
		}
		return tmp;
	}

}
