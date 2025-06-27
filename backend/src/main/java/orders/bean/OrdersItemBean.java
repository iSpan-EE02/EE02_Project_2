package orders.bean;

public class OrdersItemBean {
//	private int itemId;     //訂單明細編號
//	private int ordersId;   //訂單編號
	private String prodName;//查詢單筆時可查到商品名稱(JOIN)
	private String prodImg; //查詢單筆時可看到商品圖片(JOIN)
	private int quantity;   //數量
	private int unitPrice;  //商品單價
	private int subtotal;   //小計(quantity*unitPrice)
	private int prodId;     //商品編號
	
	
	
	//訂單明細編號
//	public int getItemId() {
//		return itemId;
//	}
//	public void setItemId(int itemId) {
//		this.itemId = itemId;
//	}
//	
//	//訂單編號
//	public int getOrdersId() {
//		return ordersId;
//	}
//	public void setOrdersId(int ordersId) {
//		this.ordersId = ordersId;
//	}
	
	//商品編號
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	//數量
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//商品單價
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	//小計(quantity*unitPrice)
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	//商品名稱(JOIN)
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	//商品圖片(JOIN)
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
	
	
	
}
