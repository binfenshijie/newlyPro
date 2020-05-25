package com.newly.vas.rs.base;

public interface TokenRepo {

    String getToken();

    void refreshToken();
}
