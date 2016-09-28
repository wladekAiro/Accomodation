/**
 * Yobi, Project Hosting SW
 *
 * Copyright 2012 NAVER Corp.
 * http://yobi.io
 *
 * @Author Ahn Hyeok Jun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wladek.accomodation.domain;

import com.wladek.accomodation.domain.accomodation.Bed;
import com.wladek.accomodation.domain.accomodation.RoomItem;
import com.wladek.accomodation.domain.accomodation.StudentProfile;
import com.wladek.accomodation.domain.enumeration.UserRole;
import com.wladek.accomodation.domain.enumeration.UserState;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends AbstractModel{

    public static final String LOGIN_ID_PATTERN = "^[a-zA-Z0-9-]+([_.][a-zA-Z0-9-]+)*$";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @NotEmpty(message = "Provide your name")
    private String name;

    @Column(unique = true, nullable = false)
    @NotEmpty
    @Pattern(regexp = LOGIN_ID_PATTERN, message = "Illegal username, use alphabets and numbers only")
    private String loginId;

    @Column(nullable = false)
    @NotEmpty(message = "Provide your password")
    private String password;

    @Transient
    @NotEmpty(message = "Provide a confirmation password")
    private String confirmPassword;

    @Column(unique = true)
    @Pattern(regexp = EMAIL_PATTERN , message = "Invalid email address")
    private String email;

    private String lang;

    private String url;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
    @OneToOne(mappedBy = "student" , fetch = FetchType.LAZY)
    private Bed bed;
    @OneToOne(mappedBy = "student" , fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private StudentProfile profile;
    @OneToMany(mappedBy = "student" , fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<RoomItem> roomItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserState getUserState() {
        return userState;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public StudentProfile getProfile() {
        return profile;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }

    public List<RoomItem> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(List<RoomItem> roomItems) {
        this.roomItems = roomItems;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
