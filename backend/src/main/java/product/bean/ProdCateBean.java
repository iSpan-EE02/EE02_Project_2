package product.bean;

public class EmpBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String empno;
	private String ename;
	private String hiredate;
	private String salary;
	private String deptno;
	private String title;
	private String dname;

	public String getEmpno() {  return empno;  }
	public String getEname() {  return ename;  }
	public String getHiredate() {  return hiredate;  }
	public String getSalary() {  return salary;  }
	public String getDeptno() {  return deptno;  }
	public String getTitle() {  return title;  }
	public String getDname() {  return dname;  }

	public void setEmpno(String empno) {  this.empno = empno;  }
	public void setEname(String ename) {  this.ename = ename;  }
	public void setHiredate(String hiredate) {  this.hiredate= hiredate;  }
	public void setSalary(String salary) {  this.salary = salary;  }
	public void setDeptno(String deptno) {  this.deptno= deptno;	}
	public void setTitle(String title) {  this.title= title;	}
	public void setDname(String dname) {  this.dname= dname;	}
}