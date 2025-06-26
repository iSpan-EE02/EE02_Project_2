package product.bean;

public class ProdImagesBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer image_id;
	private Integer prod_id;
	private String image_url;
	private Integer is_primary;
	private Integer sort_order;
	
	public Integer getImage_id() {
		return image_id;
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public Integer getIs_primary() {
		return is_primary;
	}
	public Integer getSort_order() {
		return sort_order;
	}
	
	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public void setIs_primary(Integer is_primary) {
		this.is_primary = is_primary;
	}
	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}
	
	
	
	
	

}