package model.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.PersonClassInfoBean;
import model.PersonClassRecordBean;
import model.dao.PersonClassInfoDAO;
import model.dao.PersonClassRecordDAO;

public class PersonClassRecordService implements IService<PersonClassRecordBean> {

	private static PersonClassRecordDAO dao;

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		PersonClassInfoDAO dao = new PersonClassInfoDAO(factory);
		Transaction trx = dao.getSession().beginTransaction();
		PersonClassRecordService service = new PersonClassRecordService(new PersonClassRecordDAO(factory));
		PersonClassInfoBean pcib =null;
		PersonClassRecordBean pcrb = null;

		try {
			pcib = dao.select(1);
			pcrb = new PersonClassRecordBean(pcib, new Date(), null, null);
			pcrb.setId(0);			
			service.insert(pcrb);
			pcib.getPersonClassRecords().add(pcrb);
			dao.insert(pcib);
			//System.out.println(pcrb);
			trx.commit();
		} catch (Exception e) {
			trx.rollback();
			for (StackTraceElement st : e.getStackTrace()) {
				System.out.println(st.toString());
			}
		}
 

	}

	public PersonClassRecordService(PersonClassRecordDAO dao) {
		this.dao = dao;
	}

	
	public PersonClassRecordBean insert(PersonClassInfoBean pcib, PersonClassRecordBean pcrb) {
		pcib.getPersonClassRecords().add(pcrb);
		pcrb.setPersonClassInfo(pcib);
		return dao.insert(pcrb);
	}

	@Override
	public PersonClassRecordBean insert(PersonClassRecordBean bean) {
		return dao.insert(bean);
	}

	@Override
	public PersonClassRecordBean select(int id) {
		return dao.select(id);
	}

	@Override
	public List<PersonClassRecordBean> select() {
		return dao.select();
	}

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public PersonClassRecordBean update(PersonClassRecordBean bean) {
		return dao.update(bean);
	}

}
