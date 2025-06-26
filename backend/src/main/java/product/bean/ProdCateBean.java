package product.bean;

public class ProdCateBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cate_id;
	private String cate_name;
	private Integer parent_cate_id;
	private String cate_desc;
	private Boolean is_parent;
	
	//getter
	public Integer getCate_id() { return cate_id;}
	public String getCate_name() { return cate_name;}
	public Integer getParent_cate_id() { return parent_cate_id;}
	public String getCate_desc() { return cate_desc;}
	public Boolean getIs_parent() { return is_parent;}
	
	//	setter
	public void setCate_id(Integer cate_id) { this.cate_id = cate_id;}
	public void setCate_name(String cate_name) { this.cate_name = cate_name;}
	public void setParent_cate_id(Integer parent_cate_id) { this.parent_cate_id = parent_cate_id; }
	public void setCate_desc(String cate_desc) { this.cate_desc = cate_desc;}
	public void setIs_parent(Boolean is_parent) {this.is_parent = is_parent;}
	
}