package com.marouane.book_library_api.projections;

public interface BookSummary {
    String getIsbn();
    String getTitle();
    Long getAuthorId();
}
