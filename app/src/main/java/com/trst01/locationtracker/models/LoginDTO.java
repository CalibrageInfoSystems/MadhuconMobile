package com.trst01.locationtracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDTO {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("EmplId")
        @Expose
        private Integer emplId;
        @SerializedName("FirstName")
        @Expose
        private String firstName;
        @SerializedName("LastName")
        @Expose
        private String lastName;
        @SerializedName("UserName")
        @Expose
        private String userName;
        @SerializedName("MobileNumber")
        @Expose
        private Long mobileNumber;
        @SerializedName("EmailId")
        @Expose
        private String emailId;
        @SerializedName("RoleName")
        @Expose
        private String roleName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getEmplId() {
            return emplId;
        }

        public void setEmplId(Integer emplId) {
            this.emplId = emplId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Long getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(Long mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

    }
