package orders.bean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;


@WebServlet("/OrdersBean")
public class OrdersBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private int ordersId;    //訂單編號           
    private int memId;       //會員編號         
    private Timestamp ordersDate; //訂單成立日期
    private int shipId;      //運送方式編號
	private String shipName;   //運送方式
    private int shipFee;     //運費
    private int totalPrice;  //訂單總金額(商品總金額)
    private int finalPrice;  //應付金額(商品+運費)
    
    //運送方式
    public String getShipName() {
    	return shipName;
    }
    public void setShipName(String shipName) {
    	this.shipName = shipName;
    }
    
  //運送方式編號
    public int getShipId() {
		return shipId;
	}
	public void setShipId(int shipId) {
		this.shipId = shipId;
	}
	
	//運費
	public int getShipFee() {
		return shipFee;
	}
	public void setShipFee(int shipFee) {
		this.shipFee = shipFee;
	}
    
  //訂單編號
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	
	//會員編號
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	
	//訂單成立日期
	public Timestamp getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(Timestamp ordersDate) {
		this.ordersDate = ordersDate;
	}
	
	//訂單總金額(商品總金額)
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	//應付金額(商品+運費)
	public int getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}
    

}
