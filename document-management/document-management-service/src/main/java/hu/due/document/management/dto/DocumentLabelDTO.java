package hu.due.document.management.dto;

import hu.due.document.management.service.entity.Document;

public class DocumentLabelDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Document document;
	private String label;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
