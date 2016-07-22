package com.mpandg.mpandgbluetooth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result<T> {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("errorcode")
    @Expose
    private Integer errorcode;
    @SerializedName("result")
    @Expose
    private T result;

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The errorcode
     */
    public Integer getErrorcode() {
        return errorcode;
    }

    /**
     * @param errorcode The errorcode
     */
    public void setErrorcode(Integer errorcode) {
        this.errorcode = errorcode;
    }

    /**
     * @return The result
     */
    public T getResult() {
        return result;
    }

    /**
     * @param result The result
     */
    public void setResult(T result) {
        this.result = result;
    }

}