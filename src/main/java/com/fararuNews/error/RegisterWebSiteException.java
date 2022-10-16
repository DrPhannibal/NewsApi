package com.fararuNews.error;

public class RegisterWebSiteException extends RuntimeException{


    private static final long serialVersionUID = 4628679232647002030L;

    public RegisterWebSiteException(String errorMessage) {
        super(errorMessage);
    }

}
