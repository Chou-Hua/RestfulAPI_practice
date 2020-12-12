package com.andy.springtest.Model;

import org.springframework.stereotype.Component;

@Component
public class PlayerInfo {
    private int account;
    private int ID;
    private String Name; //要跟firebase 的大小寫一致

    public int getAccount(){
        return account;
    }
    public  void setAccount(int account){
        this.account = account;
    }
    public  int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public  String getName(){
        return Name;
    }
    public  void setName(String Name){
        this.Name = Name;
    }
}
