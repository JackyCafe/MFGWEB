package model;
// default package
// Generated 2018/8/10 �U�� 02:34:36 by Hibernate Tools 5.1.8.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PersonClassInfo generated by hbm2java
 */
@Entity
@Table(name = "person_class_info", catalog = "mfg_world")
public class PersonClassInfoBean implements java.io.Serializable {

	private Integer id;
	private String workNum;
	private String name;
	private String className;
	private Byte onDuty;
	//private Set<PersonClassRecordBean> personClassRecords = new HashSet<PersonClassRecordBean>(0);
	private List<PersonClassRecordBean> personClassRecords = new ArrayList<PersonClassRecordBean>(0);
	public PersonClassInfoBean() {
	}

	public PersonClassInfoBean(String workNum, String name, String className, Byte onDuty
			, List<PersonClassRecordBean> personClassRecords) {
		this.workNum = workNum;
		this.name = name;
		this.className = className;
		this.personClassRecords = personClassRecords;
		this.onDuty = onDuty;
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "work_num", length = 5)
	public String getWorkNum() {
		return this.workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}

	@Column(name = "name", length = 6)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "class_name", length = 20)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name ="onDuty")	
	public Byte getOnDuty()
	{
		return this.onDuty;
	}
	
	public void setOnDuty(Byte onDuty) {
		this.onDuty = onDuty;
	}
	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personClassInfo")
	public List<PersonClassRecordBean> getPersonClassRecords() {
		return this.personClassRecords;
	}

	public void setPersonClassRecords(List<PersonClassRecordBean> personClassRecords) {
		this.personClassRecords = personClassRecords;
	}

	@Override
	public String toString() {
		return "PersonClassInfoBean [id=" + id + ", workNum=" + workNum + ", name=" + name + ", className=" + className
				+",onDuty="+onDuty+ ", personClassRecords=" + null + "]";
	}

	
}
