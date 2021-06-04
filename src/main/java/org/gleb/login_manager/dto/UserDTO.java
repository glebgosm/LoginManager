package org.gleb.login_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {
    @JsonProperty("user_name")
    @NotBlank
    @Size(min = 3, max = 12)
    private String userName;

    //@JsonProperty("password")
    @NotBlank
    @Size(min = 6, max = 15)
    @Pattern.List( {
            @Pattern(regexp="^[a-zA-Z0-9]+$"),
            @Pattern(regexp=".*[a-z].*"),
            @Pattern(regexp=".*[A-Z].*"),
            @Pattern(regexp=".*[0-9].*")
    } )
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
