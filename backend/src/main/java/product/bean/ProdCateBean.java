package product.bean;

public class ProdCateBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String cate_id;
	private String cate_name;
	private String parent_cate_id;
	private String cate_desc;
	
//	getter
	public String getCate_id() { return cate_id; }
	public String getCate_name() { return cate_name;}
	public String getParent_cate_id() { return parent_cate_id;}
	public String getCate_desc() { return cate_desc;}

	
//	setter
	public void setCate_id(String cate_id) { this.cate_id = cate_id; }
	public void setCate_name(String cate_name) { this.cate_name = cate_name; }
	public void setParent_cate_id(String parent_cate_id) { this.parent_cate_id = parent_cate_id; }
	public void setCate_desc(String cate_desc) { this.cate_desc = cate_desc; }

}