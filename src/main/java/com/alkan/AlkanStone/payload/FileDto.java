package com.alkan.AlkanStone.payload;

import com.alkan.AlkanStone.entity.Attachment;

public class FileDto {
	private Attachment attachment;
	private byte[] content;
	
	public FileDto() {}
	
	public FileDto(Attachment attachment, byte[] content) {
		super();
		this.attachment = attachment;
		this.content = content;
	}
	
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
