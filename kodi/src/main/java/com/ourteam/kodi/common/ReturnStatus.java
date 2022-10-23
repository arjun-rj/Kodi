package com.ourteam.kodi.common;

public class ReturnStatus<T> {
    public boolean status;
    public T data;
    public String message;

    public ReturnStatus(boolean status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
