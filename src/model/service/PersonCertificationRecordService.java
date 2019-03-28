package model.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.PersonCertificationInfoBean;
import model.PersonCertificationRecordBean;
import model.PersonClassRecordBean;
import model.dao.PersonCertificationInfoDAO;
import model.dao.PersonCertificationRecordDAO;

public class PersonCertificationRecordService implements IService<PersonCertificationRecordBean> {
	public static PersonCertificationRecordDAO dao;
	private static PersonCertificationInfoDAO pcidao;
	private static Transaction trx; 
	private static PersonCertificationInfoBean pcib = null;
	private static PersonCertificationRecordBean pcrb = null;
	private static PersonCertificationRecordService service = null;
	private static PersonCertificationInfoService pcis; 
	
	public PersonCertificationRecordService(PersonCertificationRecordDAO dao) {
		this.dao = dao;
	}

	/*
	 * 傳入人員名字與學科名稱，判斷是否過了
	 * */
	public boolean isPass(String name,String knowledge)
	{
		pcis = new PersonCertificationInfoService(new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory()));
		int id = pcis.findKnowledgeId(name, knowledge);
 		
		if(id>0) {
		if(pcis.select(id).getPersonCertificationRecords().size()>0)
		{
			if(pcis.select(id).getPersonCertificationRecords().get(0).getTechnicalDate()!=null)
			return true;
			else
			return false; 	
		}else
		{return false;}
		}else {return false;}
		
	}
	
	
	public boolean isFinish(String name,String training)
	{
		pcis = new PersonCertificationInfoService(new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory()));
		int id = pcis.findTrainingId(name, training);
		if(id>0) {
		if(!pcis.select(id).getPersonCertificationRecords().isEmpty())
		{
			if(pcis.select(id).getPersonCertificationRecords().get(0).getTrainingDate()!=null)
			return true;
			else
			return false; 	
		}else
		{return false;}}else {return false;	}
		
	}
	
	
	public PersonCertificationRecordBean insert(PersonCertificationInfoBean pcib,PersonCertificationRecordBean pcrb)
	{
		pcib.getPersonCertificationRecords().add(pcrb);
		pcrb.setPersonCertificationInfo(pcib);
		dao.insert(pcrb);
		return pcrb;
	}
	
	
	@Override
	public PersonCertificationRecordBean insert(PersonCertificationRecordBean bean) { 			
		return dao.insert(bean);
	}

	@Override
	public PersonCertificationRecordBean select(int id) {
 		return dao.select(id);
	}

	@Override
	public List<PersonCertificationRecordBean> select() {
 		return dao.select();
	}

	@Override
	public Boolean delete(int id) {
 		return dao.delete(id);
	}

	@Override
	public PersonCertificationRecordBean update(PersonCertificationRecordBean bean) {
 		return dao.update(bean);
	}

}
