package crowdfund.bean;

import java.io.Serializable;
import java.sql.Date;

public class CampaignBean implements Serializable{
	private Integer campaignID;
	private String campaignName;
	private String campaignCategory;
	private Double goalAmount;
	private Double currentAmount;
	private Date startDate;
	private Date endDate;
	private String coverImage;
	private String status;
	private Integer creatorID;
	private String description;
	
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public Integer getCampaignID() {
		return campaignID;
	}
	public void setCampaignID(Integer campaignID) {
		this.campaignID = campaignID;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getCampaignCategory() {
		return campaignCategory;
	}
	public void setCampaignCategory(String campaignCategory) {
		this.campaignCategory = campaignCategory;
	}
	public Double getGoalAmount() {
		return goalAmount;
	}
	public void setGoalAmount(Double goalAmount) {
		this.goalAmount = goalAmount;
	}
	public Double getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCreatorID() {
		return creatorID;
	}
	public void setCreatorID(Integer creatorID) {
		this.creatorID = creatorID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
