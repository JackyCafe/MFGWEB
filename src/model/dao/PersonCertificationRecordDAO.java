package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.PersonCertificationInfoBean;
import model.PersonCertificationRecordBean;
import model.PersonClassRecordBean;

public class PersonCertificationRecordDAO extends DAO implements IDAO<PersonCertificationRecordBean> {
		private static PersonCertificationRecordDAO dao;
		private static PersonCertificationInfoDAO pcidao;
		private static Transaction trx ;
	public static void main(String[] args) {
		dao = new PersonCertificationRecordDAO(HibernateUtil.getSessionFactory());
		pcidao = new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory());
		test();
	}
	
	
	public static void test()
	{
		try {		
			trx = dao.getTransaction();
			PersonCertificationInfoBean pcib = pcidao.select(8);
			PersonCertificationRecordBean pcrb = new PersonCertificationRecordBean(pcib 
					, new Date(), 100, new Date(), 100, new Date(),"林彥亨"); 
			pcrb.setId(0);
			pcib.getPersonCertificationRecords().add(pcrb);
			pcidao.update(pcib);
			PersonCertificationRecordBean insert = dao.insert(pcrb);
			System.out.println(insert);
			trx.commit();
		}catch (Exception e) {
			for (StackTraceElement st : e.getStackTrace())
			{
				System.out.println(st.toString());
			}
		}
		
		
		
	}
	
	
	
	//建構
	public PersonCertificationRecordDAO(SessionFactory factory) {
		super(factory);
 	}

	@Override
	public PersonCertificationRecordBean select(PersonCertificationRecordBean bean) {
 		return select(bean.getId());
	}

	@Override
	public PersonCertificationRecordBean select(int id) {
 		return this.getSession().get(PersonCertificationRecordBean.class, id);
	}

	@Override
	public List<PersonCertificationRecordBean> select() {
 		return this.getSession()
 				.createQuery("from PersonCertificationInfoBean", PersonCertificationRecordBean.class)
 				.getResultList();
	}

	@Override
	public PersonCertificationRecordBean insert(PersonCertificationRecordBean bean) {
		PersonCertificationRecordBean tmp ;
		this.getSession().save(bean);
		return  bean ;
	}

	@Override
	public Boolean delete(PersonCertificationRecordBean bean) {		
 		return delete(bean.getId());
	}

	@Override
	public Boolean delete(int id) {
		PersonCertificationRecordBean tmp = select(id) ; 
		if(tmp != null)
		{
			this.getSession().delete(tmp);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public PersonCertificationRecordBean update(PersonCertificationRecordBean bean) {
		PersonCertificationRecordBean tmp = select(bean);
		if(tmp != null) 
		{
			tmp.setKnowledgeDate(bean.getKnowledgeDate());
			tmp.setKnowledgeScore(bean.getKnowledgeScore());
			tmp.setTechnicalDate(bean.getKnowledgeDate());
			tmp.setKnowledgeScore(bean.getKnowledgeScore());
			tmp.setTrainingDate(bean.getTrainingDate());
		}
		return bean;
	}

	  

}
