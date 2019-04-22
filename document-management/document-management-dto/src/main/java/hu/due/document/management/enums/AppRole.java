package hu.due.document.management.enums;

public enum AppRole {
    ADMIN("Adminisztrátor"), //
    USER("Felhasználó");

    private String caption;

    private AppRole(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

}
