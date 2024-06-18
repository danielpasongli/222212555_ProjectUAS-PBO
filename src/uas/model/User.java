/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.model;

/**
 *
 * @author DANIEL
 */
public class User {
    private String user_id;
    private String username;
    private String password;
    private String name;
    private int status;
    
    public User(){
        
    }
    
    public User(String user_id, String username, String password, String name, int status){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.status = status;
    }
    
    public String getUserID(){
        return user_id;
    }
    
    public void setUserID(String user_id){
        this.user_id = user_id;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getStatus(){
        return status;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
}
