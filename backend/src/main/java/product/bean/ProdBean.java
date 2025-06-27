package product.bean;

import java.time.LocalDateTime;

public class ProdBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer prod_id;
	private String prod_name;
	private String prod_desc;
	private Integer prod_cate_id;
	private Integer prod_status;
	private LocalDateTime create_at;
	
	//get
	public Integer getProd_id() { return prod_id; }
	public String getProd_name() { return prod_name; }
	public String getProd_desc() { return prod_desc; }
	public Integer getProd_cate_id() { return prod_cate_id;}
	public Integer getProd_status() { return prod_status; }
	public LocalDateTime getCreate_at() { return create_at; }
	
	//set
	public void setProd_id(Integer prod_id) { this.prod_id = prod_id; }
	public void setProd_name(String prod_name) { this.prod_name = prod_name; }
	public void setProd_desc(String prod_desc) { this.prod_desc = prod_desc; }
	public void setProd_cate_id(Integer prod_cate_id) { this.prod_cate_id = prod_cate_id; }
	public void setProd_status(Integer prod_status) { this.prod_status = prod_status; }
	public void setCreate_at(LocalDateTime create_at) {this.create_at = create_at; }
	
}