package account.bean;

import java.sql.Timestamp;

public class Employee {
	private int empId;
	private String empName;
	private int empPhoneNo;
    private String empMail;
    private Timestamp empDCT;
    private String empACC;
    private Department department;
    private String empPassword;
    private int empUpdateBy;
    private Timestamp empUpdateAt;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpPhoneNo() {
		return empPhoneNo;
	}
	public void setEmpPhoneNo(int empPhoneNo) {
		this.empPhoneNo = empPhoneNo;
	}
	public String getEmpMail() {
		return empMail;
	}
	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}
	public java.sql.Timestamp getEmpDCT() {
		return empDCT;
	}
	public void setEmpDCT(java.sql.Timestamp empDCT) {
		this.empDCT = empDCT;
	}
	public String getEmpACC() {
		return empACC;
	}
	public void setEmpACC(String empACC) {
		this.empACC = empACC;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public int getEmpUpdateBy() {
		return empUpdateBy;
	}
	public void setEmpUpdateBy(int empUpdateBy) {
		this.empUpdateBy = empUpdateBy;
	}
	public java.sql.Timestamp getEmpUpdateAt() {
		return empUpdateAt;
	}
	public void setEmpUpdateAt(java.sql.Timestamp empUpdateAt) {
		this.empUpdateAt = empUpdateAt;
	}

	
}

