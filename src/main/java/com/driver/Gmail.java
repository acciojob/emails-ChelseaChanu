package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    private int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private ArrayList<Mail> inbox = new ArrayList<>();
    private ArrayList<Mail> trash = new ArrayList<>();

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size()==inboxCapacity){
            //Date oldestDate = inbox.stream().map(Mail::getDate).min(Date::compareTo).get();
            Mail mailToBeAdded = new Mail(date, sender, message);
            //for(int i=0;i<inbox.size();i++){
                Mail currentMail = inbox.get(0);
                //if(currentMail.getDate().compareTo(oldestDate)==0){
                    inbox.remove(currentMail);
                    trash.add(currentMail);
                    inbox.add(mailToBeAdded);
                    //break;
               // }
            //}
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<inbox.size();i++){
            Mail mail = inbox.get(i);
            if(mail.getMessage().equals(message)){
                inbox.remove(mail);
                trash.add(mail);
                break;
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        //String messageToReturn = "";
        if(inbox.isEmpty()){
            return null;
        }
        else{
            //Date latestDate = new Date(Long.MIN_VALUE);;
            //for(int i=0;i<inbox.size();i++){
                Mail mail = inbox.get(inbox.size()-1);
                // if(mail.getDate().compareTo(latestDate)>0){
                //     messageToReturn = mail.getMessage();
                // }
            //}
            return mail.getMessage();
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        //String messageToReturn = "";
        if(inbox.isEmpty()){
            return null;
        }
        else{
            //Date oldestDate = new Date(Long.MAX_VALUE);;
            //for(int i=0;i<inbox.size();i++){
                Mail mail = inbox.get(0);
                return mail.getMessage();
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int ans = 0;
        for(int i =0;i<inbox.size();i++){
            Mail mail = inbox.get(i);
            Date currentDate = mail.getDate();
            if((currentDate.compareTo(start)==0 || currentDate.compareTo(start)>0) && (currentDate.compareTo(end)==0 || currentDate.compareTo(end)<0)){
                ans++ ;
            }
        }
        return ans;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
