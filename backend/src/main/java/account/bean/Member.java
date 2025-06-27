package account.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Member {
	private int memId;
    private String memName;
    private String memGd;
    private String memPn;
    private String memIn;
    private String memAdr;
    private String memMail;
    private Date memBd;
    private String memPw;
    public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	private Timestamp memDct;
    
    private MemberRank rank;           
    private Employee memUpdateBy;      
    private Timestamp memUpdateAt;
    private Member giftToUser;    
    
    public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemGd() {
		return memGd;
	}
	public void setMemGd(String memGd) {
		this.memGd = memGd;
	}
	public String getMemPn() {
		return memPn;
	}
	public void setMemPn(String memPn) {
		this.memPn = memPn;
	}
	public String getMemIn() {
		return memIn;
	}
	public void setMemIn(String memIn) {
		this.memIn = memIn;
	}
	public String getMemAdr() {
		return memAdr;
	}
	public void setMemAdr(String memAdr) {
		this.memAdr = memAdr;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public Date getMemBd() {
		return memBd;
	}
	public void setMemBd(Date memBd) {
		this.memBd = memBd;
	}

	public Timestamp getMemDct() {
		return memDct;
	}
	public void setMemDct(Timestamp memDct) {
		this.memDct = memDct;
	}
	public MemberRank getRank() {
		return rank;
	}
	public void setRank(MemberRank rank) {
		this.rank = rank;
	}
	public Employee getMemUpdateBy() {
		return memUpdateBy;
	}
	public void setMemUpdateBy(Employee memUpdateBy) {
		this.memUpdateBy = memUpdateBy;
	}
	public Timestamp getMemUpdateAt() {
		return memUpdateAt;
	}
	public void setMemUpdateAt(Timestamp memUpdateAt) {
		this.memUpdateAt = memUpdateAt;
	}
	public Member getGiftToUser() {
		return giftToUser;
	}
	public void setGiftToUser(Member giftToUser) {
		this.giftToUser = giftToUser;
	}
	     

}
