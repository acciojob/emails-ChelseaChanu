package com.driver;

import java.util.Date;

public class Mail{
    private Date date;
    private String senderID;
    private String message;
    
    public Mail(Date date, String senderID, String message) {
        this.date = date;
        this.senderID = senderID;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderID() {
        return senderID;
    }
}
