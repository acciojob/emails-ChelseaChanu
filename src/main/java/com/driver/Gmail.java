package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private ArrayList<String> inboxMessage = new ArrayList<>();
    private ArrayList<Date> inboxDate = new ArrayList<>();
    private ArrayList<String> trashMessage = new ArrayList<>();
    private ArrayList<Date> trashDate = new ArrayList<>();

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inboxMessage.size()==inboxCapacity){
            Date oldestDate = Collections.min(inboxDate);
            int indexOfOldestDate = inboxDate.indexOf(oldestDate);
            inboxMessage.remove(indexOfOldestDate);
            inboxDate.remove(oldestDate);

            inboxDate.add(date);
            inboxMessage.add(message);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(inboxMessage.contains(message)){
            int indexOfMessage = inboxMessage.indexOf(message);
            Date removedDate = inboxDate.remove(indexOfMessage);
            inboxMessage.remove(message);
            trashMessage.add(message);
            trashDate.add(removedDate);
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inboxMessage.isEmpty()){
            return null;
        }
        else{
            Date latestDate = Collections.max(inboxDate);
            int latestMessageIndex = inboxDate.indexOf(latestDate);
            return inboxMessage.get(latestMessageIndex);
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inboxMessage.isEmpty()){
            return null;
        }
        else{
            Date oldestDate = Collections.min(inboxDate);
            int oldestMessageIndex = inboxDate.indexOf(oldestDate);
            return inboxMessage.get(oldestMessageIndex);
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int ans = 0;
        for(int i =0;i<inboxDate.size();i++){
            Date currentDate = inboxDate.get(i);
            if((currentDate.compareTo(start)==0 || currentDate.compareTo(start)>0) && (currentDate.compareTo(end)==0 || currentDate.compareTo(end)<0)){
                ans++ ;
            }
        }
        return ans;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxMessage.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashMessage.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashDate.clear();
        trashMessage.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
