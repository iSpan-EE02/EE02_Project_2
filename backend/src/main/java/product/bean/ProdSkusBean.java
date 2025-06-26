package product.bean;

import java.math.BigDecimal;

public class ProdSkusBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer sku_id;
	private Integer prod_id;
	private String sku_code;
	private BigDecimal price;
	private Integer stock_quantity;
	
	public Integer getSku_id() {
		return sku_id;
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public String getSku_code() {
		return sku_code;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Integer getStock_quantity() {
		return stock_quantity;
	}
	public void setSku_id(Integer sku_id) {
		this.sku_id = sku_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public void setSku_code(String sku_code) {
		this.sku_code = sku_code;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setStock_quantity(Integer stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	
	
	
	
	
}