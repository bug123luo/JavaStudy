package com.ubo.common.terminal;

import dudu.service.core.terminal.TerMinalBasicMessage;


public class ResponsMessage extends TerMinalBasicMessage {

    /**
     *
     */
    private static final long serialVersionUID = -6875108799101150277L;

    private String state;
    private String token;

    public ResponsMessage() {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
