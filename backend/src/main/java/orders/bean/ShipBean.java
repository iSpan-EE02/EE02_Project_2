package orders.bean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ShipBean")
public class ShipBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//訂單物流
    private int shipId;         //物流編號    
    private String shipName;  //運送方式
    private int shipFee;        //運費
    
  //物流編號
	public int getShipId() {
		return shipId;
	}
	public void setShipId(int shipId) {
		this.shipId = shipId;
	}
	
	
	//運送方式
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	//運費
	public int getShipFee() {
		return shipFee;
	}
	public void setShipFee(int shipFee) {
		this.shipFee = shipFee;
	}
    
}
