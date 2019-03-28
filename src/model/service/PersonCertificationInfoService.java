package model.service;

import java.util.ArrayList;
import java.util.List;
 import java.util.stream.Collectors;

import org.hibernate.Transaction;

import misc.HibernateUtil;
import model.CertificationItemBean;
import model.PersonCertificationInfoBean;
import model.dao.PersonCertificationInfoDAO;

public class PersonCertificationInfoService implements IService<PersonCertificationInfoBean> {
	private static PersonCertificationInfoDAO dao;
	private static Transaction trx;

	public static void main(String[] args) {
		PersonCertificationInfoDAO dao = new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory());
		PersonCertificationInfoService service = new PersonCertificationInfoService(dao);
	}

	public PersonCertificationInfoService(PersonCertificationInfoDAO dao) {
		this.dao = dao;
	}

	/* 判斷學科存在嗎? */
	public boolean isKnowledgeExist(String name, String knowledge) {
		boolean isKnowledgeExist = false;
		for (PersonCertificationInfoBean bean : select()) {
			if (bean.getName().equals(name)) {
				if (bean.getKnowledge().equals(knowledge)) {
					isKnowledgeExist = true;
					return isKnowledgeExist;
				}
			}
		}
		return isKnowledgeExist;
	}

	/* 判斷術科存在嗎? */
	public boolean isTechnicalExist(String name, String technical) {
		boolean isTechnicalExist = false;
		for (PersonCertificationInfoBean bean : select()) {
			if (bean.getName().equals(name)) {
				if (bean.getKnowledge().equals(technical)) {
					isTechnicalExist = true;
					return isTechnicalExist;
				}
			}
		}
		return isTechnicalExist;
	}

	/* 判斷訓練存在嗎? */
	public boolean isTrainingExist(String name, String training) {
		boolean isTrainingExist = false;
		for (PersonCertificationInfoBean bean : select()) {
			if (bean.getName().equals(name)) {
				if (bean.getTraining().equals(training)) {
					isTrainingExist = true;
					return isTrainingExist;
				}
			}
		}
		return isTrainingExist;
	}

	/* 依姓名 學科 找出id */

	public int findKnowledgeId(String name, String knowledge) {
		int id = -1;
		for (PersonCertificationInfoBean bean : select()) {
			if (bean.getName().equals(name)) {
				if (bean.getKnowledge()!=null&&bean.getKnowledge().equals(knowledge)) {
					id = bean.getId();
				}
			}
		}
		return id;
	}

	public int findTechnicalId(String name, String technical) {
		int id = -1;
		for (PersonCertificationInfoBean bean : select()) {
			if (bean.getName().equals(name)) {
				if (bean.getTechnical()!=null&&bean.getTechnical().equals(technical)) {
					id = bean.getId();
				}
			}
		}
		return id;
	}

	public int findTrainingId(String name, String training) {
		int id = -1;
		for (PersonCertificationInfoBean bean : select()) {
			if (bean.getName().equals(name)) {
				if (bean.getTraining().equals(training)) {
					id = bean.getId();
				}
			}
		}
		return id;
	}
	
	/**
	 * 傳入科目(certifation)，確認是屬於學科、術科或認證
	 * */

 	public String findCategory(String certifation) {
		String result = " ";
 		for (PersonCertificationInfoBean bean : select()) {
			if (bean.getKnowledge()!=null &&
					bean.getKnowledge().equals(certifation)) {
				result = "knowledge";
				break;
			}
			if (bean.getTechnical()!=null &&
					bean.getTechnical().equals(certifation)) {
				result = "technical";
				break;
			}
			if (bean.getTraining().equals(certifation)) {
				result = "training";
				break;
			}
		}
		return result;

	}

	/** Select 有三個參數(科目、班別、是否為認證科目)
	 * 1. trainonly 為false 時，
	 *    比對 PersonCertificationInfoBean.getKnowledge的值是否等於 certification，
	 *    此時getKnowledge() 不應為null
	 *    如果是的話，再看班別是選全部還是各班，如果是ALL 時 傳回certifications。
	 *    如果是各班時，再由certifications 的List 中找出shift 與班別的關係 傳回shift。
	 *    
	 * 2. trainonly 為true 時，
	 *    比對 PersonCertificationInfoBean.getTraining的值是否等於 certification，
	 *    此時getKnowledge() 應為null
	 *    如果是的話，再看班別是選全部還是各班，如果是ALL 時 傳回certifications。
	 *    如果是各班時，再由certifications 的List 中找出shift 與班別的關係 傳回shift。
	 */

	public List<PersonCertificationInfoBean> select(String certification, String shift,boolean trainonly) {
		
		if (trainonly == false) {			
			List<PersonCertificationInfoBean> knowledge = new ArrayList<>();
			knowledge = select().stream().filter(pc->pc.getKnowledge()!=null).collect(Collectors.toList());  			 
			/* 取得符合認證的人*/
			List<PersonCertificationInfoBean> certifications = knowledge.stream()
					.filter(pc -> pc.getKnowledge().equals(certification)).collect(Collectors.toList());
 			
			List<PersonCertificationInfoBean> shifts = certifications.stream().filter(pc -> pc.getShift().equals(shift))
					.collect(Collectors.toList());			

	 		if (shift.equals("ALL")) { 				 
				return certifications;}
			else
				return shifts;
		}else {
			List<PersonCertificationInfoBean> knowledge = new ArrayList<>();
			knowledge = select().stream().filter(pc->pc.getKnowledge()==null).collect(Collectors.toList()); 
 			 
			/* 取得符合認證的人*/
			List<PersonCertificationInfoBean> certifications = knowledge.stream()
					.filter(pc -> pc.getTraining().equals(certification)).collect(Collectors.toList());
 			
			List<PersonCertificationInfoBean> shifts = certifications.stream().filter(pc -> pc.getShift().equals(shift))
					.collect(Collectors.toList());			

	 		if (shift.equals("ALL")) { 				 
				return certifications;}
			else
				return shifts;
		}  
 	}

	 
	@Override
	public PersonCertificationInfoBean insert(PersonCertificationInfoBean bean) {
		return dao.insert(bean);
	}

	@Override
	public PersonCertificationInfoBean select(int id) {
		return dao.select(id);
	}

	@Override
	public List<PersonCertificationInfoBean> select() {
		return dao.select();
	}

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public PersonCertificationInfoBean update(PersonCertificationInfoBean bean) {
		return dao.update(bean);
	}

}
