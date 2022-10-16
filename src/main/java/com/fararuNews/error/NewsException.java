package com.fararuNews.error;

public class NewsException extends RuntimeException{

    private static final long serialVersionUID = 2838553129789650537L;
    public NewsException(String errorMessage) {
        super(errorMessage);
    }

}
