package cn.mldn.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Member implements Serializable {
	private Long idcard ;
	private String mid ;
	private Integer age ;
	private Date birthday ; 
	private Double salary ;
	public Long getIdcard() {
		return idcard;
	}
	public void setIdcard(Long idcard) {
		this.idcard = idcard;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Member [idcard=" + idcard + ", mid=" + mid + ", age=" + age + ", birthday=" + birthday + ", salary="
				+ salary + "]";
	}
	
	
	
}
