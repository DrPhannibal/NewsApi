package com.fararuNews.error;

public class CategoryException extends RuntimeException{

    private static final long serialVersionUID = 2406228021234589183L;

    public CategoryException(String errorMessage) {
        super(errorMessage);
    }

}
