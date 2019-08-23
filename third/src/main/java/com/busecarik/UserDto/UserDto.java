package com.busecarik.UserDto;

import com.busecarik.validation.FieldMatch;
import com.busecarik.validation.ValidEmail;

import javax.validation.constraints.NotNull;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserDto {

    @NotNull(message = "is required")
    private String username;

    @NotNull(message = "is required")
    private String password;

    @NotNull(message = "is required")
    private String matchingPassword;

    @ValidEmail
    @NotNull(message = "is required")
    private String email;

    private String birthday;

    private String sex;

    public UserDto() {

    }

    public UserDto(String username, String password, String matchingPassword, String email, String birthday, String sex) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
