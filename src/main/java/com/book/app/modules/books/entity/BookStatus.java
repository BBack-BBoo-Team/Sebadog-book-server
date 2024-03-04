package com.book.app.modules.books.entity;


public enum BookStatus {
    BEFORE_PROGRESS("진행예정"),
    IN_PROGRESS("진행중"),
    COMPLETED("진행완료");

    private final String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static BookStatus fromString(String text) {
        for (BookStatus b : BookStatus.values()) {
            if (b.status.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException(text);
    }

    public static String fromCode(BookStatus status) {
        return status.status;
    }
}

