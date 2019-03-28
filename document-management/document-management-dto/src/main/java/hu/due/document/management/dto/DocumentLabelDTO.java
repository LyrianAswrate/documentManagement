package hu.due.document.management.dto;

public class DocumentLabelDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private DocumentDTO document;
	private String label;

	public DocumentDTO getDocument() {
		return document;
	}

	public void setDocument(DocumentDTO document) {
		this.document = document;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
