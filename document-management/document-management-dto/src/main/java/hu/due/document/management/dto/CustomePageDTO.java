package hu.due.document.management.dto;

import java.io.Serializable;
import java.util.List;

public class CustomePageDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<T> content;
    private long totalElements;

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalElements() {
        return totalElements;
    }

}
