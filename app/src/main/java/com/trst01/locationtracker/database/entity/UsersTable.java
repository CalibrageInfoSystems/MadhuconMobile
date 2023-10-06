package com.trst01.locationtracker.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class UsersTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("UsersId")
    @Expose(serialize = false, deserialize = false)
    private int usersId;

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getRoleld() {
        return roleld;
    }

    public void setRoleld(Integer roleld) {
        this.roleld = roleld;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public Boolean getIPRestriction() {
        return IPRestriction;
    }

    public void setIPRestriction(Boolean IPRestriction) {
        this.IPRestriction = IPRestriction;
    }

    public Boolean getAdminGate() {
        return isAdminGate;
    }

    public void setAdminGate(Boolean adminGate) {
        isAdminGate = adminGate;
    }

    public Boolean getGross() {
        return isGross;
    }

    public void setGross(Boolean gross) {
        isGross = gross;
    }

    public Boolean getTare() {
        return isTare;
    }

    public void setTare(Boolean tare) {
        isTare = tare;
    }

    public Boolean getDumpYard() {
        return isDumpYard;
    }

    public void setDumpYard(Boolean dumpYard) {
        isDumpYard = dumpYard;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedByUserId() {
        return CreatedByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        CreatedByUserId = createdByUserId;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getUpdatedByUserId() {
        return UpdatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        UpdatedByUserId = updatedByUserId;
    }

    @SerializedName("Id")
    @Expose
    private String id;


    @SerializedName("EmplId")
    @Expose
    private String emplId;


    @SerializedName("UserName")
    @Expose
    private String userName;


    @SerializedName("Password")
    @Expose
    private String password;


    @SerializedName("FirstName")
    @Expose
    private String firstName;


    @SerializedName("MiddleName")
    @Expose
    private String MiddleName;


    @SerializedName("LastName")
    @Expose
    private String lastName;

    @SerializedName("EmailId")
    @Expose
    private String emailId;


    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;


    @SerializedName("Roleld")
    @Expose
    private Integer roleld;


    @SerializedName("IsAdmin")
    @Expose
    private Boolean isAdmin;


    @SerializedName("IPAddress")
    @Expose
    private String IPAddress;


    @SerializedName("IPRestriction")
    @Expose
    private Boolean IPRestriction;


    @SerializedName("IsAdminGate")
    @Expose
    private Boolean isAdminGate;


    @SerializedName("IsGross")
    @Expose
    private Boolean isGross;


    @SerializedName("IsTare")
    @Expose
    private Boolean isTare;


    @SerializedName("IsDumpYard")
    @Expose
    private Boolean isDumpYard;

    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;

    @SerializedName("CreatedByUserId")
    @Expose
    private String CreatedByUserId ;

    @SerializedName("UpdatedDate")
    @Expose
    private String UpdatedDate;

    @SerializedName("UpdatedByUserId")
    @Expose
    private String UpdatedByUserId ;

}