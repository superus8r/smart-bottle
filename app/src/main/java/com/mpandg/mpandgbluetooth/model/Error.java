
package com.mpandg.mpandgbluetooth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("errorname")
    @Expose
    private String errorname;

    /**
     * @return The errorname
     */
    public String getErrorname() {
        return errorname;
    }

    /**
     * @param errorname The errorname
     */
    public void setErrorname(String errorname) {
        this.errorname = errorname;
    }

}