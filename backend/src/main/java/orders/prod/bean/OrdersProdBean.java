package orders.prod.bean;

public class OrdersProdBean {
	private int prodId;        //商品編號
	private String prodName;   //商品名稱
	private String prodImg;    //商品圖片
	private int prodPrice;     //商品單價
	
	
	//商品編號
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	//商品名稱
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	
	//商品圖片
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
	
	//商品單價
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	
}
