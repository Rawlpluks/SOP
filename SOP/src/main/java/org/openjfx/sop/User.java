/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.sop;

import java.util.ArrayList;

/**
 *
 * @author clara
 */
public class User {
    private int user_ID;
    private String username;
    private String name;
    private String password;
    private String job;
    private ArrayList<Integer> picture_IDs = new ArrayList<>();
    
    public User(int user_ID, String username, String password, String job, String name, ArrayList<Integer> picture_IDS){
        this.user_ID = user_ID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.job = job;
    }
    
    public User(String username, String name, String password, String job){
        this.username = username;
        this.name = name;
        this.password = password;
        this.job = job;
    }
    
    public User() {
        
    }
    
    public int getUser_ID(){
        return user_ID;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String name){
        this.username = username;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getJob(){
        return job;
    }
    public void setJob(String job){
        this.job = job;
    }
}
