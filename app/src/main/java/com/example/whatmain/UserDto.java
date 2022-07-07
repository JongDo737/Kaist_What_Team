package com.example.whatmain;

public class UserDto {
    int id;
    String username;
    String oauth2_username;
    String password;
    String birth;
    String gender;
    String provider;
    String role;
    String create_date;
    String update_date;
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
    public String getOauth2_username() {
        return oauth2_username;
    }
    public void setOauth2_username(String oauth2_username) {
        this.oauth2_username = oauth2_username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getCreate_date() {
        return create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    public String getUpdate_date() {
        return update_date;
    }
    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}
