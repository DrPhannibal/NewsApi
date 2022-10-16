package com.fararuNews.error;

public class WebSiteException extends RuntimeException{


    private static final long serialVersionUID = 9010237392613331536L;

    public WebSiteException(String errorMessage) {
        super(errorMessage);
    }

}
