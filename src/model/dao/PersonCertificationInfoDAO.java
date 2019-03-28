package model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.PersonCertificationInfoBean;

public class PersonCertificationInfoDAO extends DAO implements IDAO<PersonCertificationInfoBean> {
	private static Transaction trx = null;
	public static void main(String[] args) {
	
		PersonCertificationInfoDAO dao = new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory());
		
		//insert
		try {
			trx = dao.getTransaction();
			PersonCertificationInfoBean bean =
					new PersonCertificationInfoBean("00409", "林彥亨", "CVD", "NB", "安裝", "安裝","",(byte)0,null);
			bean.setId(0);
			PersonCertificationInfoBean insert = dao.insert(bean);
			System.out.println(insert);
			trx.commit();			
		} catch (Exception e) {
			for(StackTraceElement st: e.getStackTrace())
				System.out.println(st.toString());
			System.out.println(e.getMessage());
			
			trx.rollback(); 
		}
		
		/*update
		try {
			trx = dao.getTransaction();
			PersonCertificationInfoBean bean =
					new PersonCertificationInfoBean("00409", "林彥亨", "CVD", "NA", "", "","緩衝墊",(byte)1,null);
			bean.setId(1);
			PersonCertificationInfoBean update = dao.update(bean);
			System.out.println(update);
			trx.commit();
			
		} catch (Exception e) {
			trx.rollback(); 
		}
		
		//delete
		try {
			trx = dao.getTransaction();
			boolean delete = dao.delete(0);
			System.out.println(delete);
			trx.commit();
			
		} catch (Exception e) {
			trx.rollback(); 
		}
		
		// select
		try {
			trx = dao.getTransaction();
			List<PersonCertificationInfoBean> select = dao.select();
			System.out.println(select);
			trx.commit();
			
		} catch (Exception e) {
			trx.rollback(); 
		}
		*/
	}
 	
	public PersonCertificationInfoDAO(SessionFactory factory) {
		super(factory);
 	}

	@Override
	public PersonCertificationInfoBean select(PersonCertificationInfoBean bean) {
 		return select(bean.getId());
	}

	@Override
	public PersonCertificationInfoBean select(int id) {
 		return this.getSession().get(PersonCertificationInfoBean.class, id);
	}

	@Override
	public List<PersonCertificationInfoBean> select() {
 		return this.getSession()
 				.createQuery("from PersonCertificationInfoBean order by shift,workNum", PersonCertificationInfoBean.class)
  				.getResultList();
	}

	@Override
	public PersonCertificationInfoBean insert(PersonCertificationInfoBean bean) {
		if(select(bean)==null)
		{
			this.getSession().save(bean);
	 		return bean;
		}
 		return null;
	}

	@Override
	public Boolean delete(PersonCertificationInfoBean bean) {
		return delete(bean.getId());
	}

	@Override
	public Boolean delete(int id) {
		if(select(id)!=null)
		{
			this.getSession().delete(id);
			return true;
		}else {		
			return false;
		}
	}

	@Override
	public PersonCertificationInfoBean update(PersonCertificationInfoBean bean) {
		PersonCertificationInfoBean tmp = select(bean);
		if(tmp != null) {
			tmp.setWorkNum(bean.getWorkNum());
			tmp.setName(bean.getName());
			tmp.setArea(bean.getArea());
			tmp.setShift(bean.getShift());
			tmp.setKnowledge(bean.getKnowledge());
			tmp.setTechnical(bean.getTechnical());
			tmp.setTraining(bean.getTraining());
			tmp.setTrainingOnly(bean.getTrainingOnly());
		}
 		return null;
	}

}
