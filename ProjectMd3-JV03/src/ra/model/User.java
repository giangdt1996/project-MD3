package ra.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String username;
    private String password;
//    private String address;
//    private  String phoneNumber;
    private boolean status = true;
    private Set<RoleName> roles = new HashSet<>();

    public User() {
    }

    public User(int id, String name, String username, String password, boolean status, Set<RoleName> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;

        this.status = status;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        String role ;
        if(roles.contains(RoleName.ADMIN)){
            role= "ADMIN";
        }else if(roles.contains(RoleName.MANAGER)){
            role = "MANAGER";
        }else {
            role = "USER";
        }
        return "ID : "+id + " | Name : "+name + "| Username : "+username + " | Role : "+role + "| Status : "+(status?"UnLock":"Lock") ;
    }
}
