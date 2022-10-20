package com.alkan.AlkanStone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Attachment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true , nullable = false)
	private String orginalFileName;
	@Column(nullable = false)
	private String contentType;
	@Column(nullable = false)
	private long size;
	@OneToOne(mappedBy = "attachment")
	private Images images;
	@OneToOne(mappedBy = "attachment")
	private Gallery gallery;
	@OneToOne(mappedBy = "attachment")
	private PostImage postImage;
	
	public Attachment() {}
	
	public Attachment(String orginalFileName, String contentType, long size) {
		super();
		this.orginalFileName = orginalFileName;
		this.contentType = contentType;
		this.size = size;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrginalFileName() {
		return orginalFileName;
	}
	public void setOrginalFileName(String orginalFileName) {
		this.orginalFileName = orginalFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	
}
