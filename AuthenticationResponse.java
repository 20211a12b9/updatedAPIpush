package com.vms.medxbid.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private final Integer userId;
    private final String jwt;
    private final String userType;;


    public AuthenticationResponse(String jwt,Integer userId,String userType) {
        this.jwt = jwt;
        this.userId = userId;
        this.userType = userType;

    }

    public String getJwt() {
        return jwt;
    }
    public Integer getUserId() {
        return userId;
    }
    public String getUserType() {
        return userType;
    }

}
