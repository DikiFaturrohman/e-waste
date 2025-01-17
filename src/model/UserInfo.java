/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String profilePicturePath;
    private String role; // Tambahkan atribut role


    // Konstruktor tanpa argumen
    public UserInfo() {}

    // Konstruktor dengan parameter
public UserInfo(String username, String password, String email, String phone, String address) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.address = address;
}


    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
     public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    // Getter dan Setter untuk profilePicturePath
    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
    
        public String getRole() {
        return role;
    }

        public void setRole(String role) {
        this.role = role;
    }
}
