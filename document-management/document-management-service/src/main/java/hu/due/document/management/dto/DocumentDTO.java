package hu.due.document.management.dto;

import java.util.Date;
import java.util.List;

import hu.due.document.management.service.entity.User;

public class DocumentDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String regnumber;
	private String filename;
	private byte[] content;
	private Long contentSize;
	private String description;
	private User createUser;
	private Date createDate;
	private User modifyUser;
	private Date modifyDate;
	private List<DocumentLabelDTO> labels;

	public String getRegnumber() {
		return regnumber;
	}

	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Long getContentSize() {
		return contentSize;
	}

	public void setContentSize(Long contentSize) {
		this.contentSize = contentSize;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(User modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setLabels(List<DocumentLabelDTO> labels) {
		this.labels = labels;
	}

}
