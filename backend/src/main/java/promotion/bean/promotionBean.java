package promotion.bean;


public class promotionBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String promotionId;
	private String title;
	private String description;
	private String type;
	private String validityType;
	private String validityFrom;
	private String validityTo;
	private String validityDays;
	private String active;
	private String createdAt;
	private String updatedAt;
	private String note;
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValidityType() {
		return validityType;
	}
	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}
	public String getValidityFrom() {
		return validityFrom;
	}
	public void setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
	}
	public String getValidityTo() {
		return validityTo;
	}
	public void setValidityTo(String validityTo) {
		this.validityTo = validityTo;
	}
	public String getValidityDays() {
		return validityDays;
	}
	public void setValidityDays(String validityDays) {
		this.validityDays = validityDays;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "promotionBean [promotionId=" + promotionId + ", title=" + title + ", description=" + description
				+ ", type=" + type + ", validityType=" + validityType + ", validityFrom=" + validityFrom
				+ ", validityTo=" + validityTo + ", validityDays=" + validityDays + ", active=" + active
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", note=" + note + ", image=" + image + "]";
	}

	
}

