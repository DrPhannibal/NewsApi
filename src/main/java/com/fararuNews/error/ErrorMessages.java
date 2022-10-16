package com.fararuNews.error;


public enum ErrorMessages {
    NEWS_NOT_EXIST("اخباری با این مشخصات وجود ندارد"),
    INTERNAL_ERROR("خطایی در سرور رخ داده است"),
    REGISTRATION_ERROR("خطایی در هنگام ذخیره اطلاعات رخ داده است"),
    URL_ERROR("آدرسی برای ثبت اطلاعات یافت نشد"),
    DELETE_ERROR("خطایی در هنگام حذف اطلاعات رخ داده است"),
    NO_DATA_ERROR("داده ای برای نمایش وجود ندارد");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorMessages setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}