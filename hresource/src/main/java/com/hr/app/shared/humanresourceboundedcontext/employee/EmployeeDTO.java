package com.hr.app.shared.humanresourceboundedcontext.employee;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import java.sql.Date;
import com.athena.config.jsonHandler.CustomSqlDateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlDateJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeDTO implements EntityValidatorInterface {

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    private String title;

    private String firstName;

    private String lastname;

    private String gender;

    private String timezone;

    private String phno;

    private String emailid;

    private String addressType;

    private String country;

    private String state;

    private String city;

    private String zipcode;

    private String address1;

    private String address2;

    @JsonSerialize(using = CustomSqlDateJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlDateJsonDeserializer.class)
    private Date dob;

    private String comGrp;

    private String comType;

    private String comData;

    private String depttype;

    private String desgType;

    private String reportofficer;

    private String jobtype;

    private String pan;

    private String contactId;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String _title) {
        this.title = _title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        this.firstName = _firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String _lastname) {
        this.lastname = _lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String _gender) {
        this.gender = _gender;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String _timezone) {
        this.timezone = _timezone;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String _phno) {
        this.phno = _phno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String _emailid) {
        this.emailid = _emailid;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String _addressType) {
        this.addressType = _addressType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String _country) {
        this.country = _country;
    }

    public String getState() {
        return state;
    }

    public void setState(String _state) {
        this.state = _state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String _city) {
        this.city = _city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String _zipcode) {
        this.zipcode = _zipcode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String _address1) {
        this.address1 = _address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String _address2) {
        this.address2 = _address2;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date _dob) {
        this.dob = _dob;
    }

    public String getComGrp() {
        return comGrp;
    }

    public void setComGrp(String _comGrp) {
        this.comGrp = _comGrp;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String _comType) {
        this.comType = _comType;
    }

    public String getComData() {
        return comData;
    }

    public void setComData(String _comData) {
        this.comData = _comData;
    }

    public String getDepttype() {
        return depttype;
    }

    public void setDepttype(String _depttype) {
        this.depttype = _depttype;
    }

    public String getDesgType() {
        return desgType;
    }

    public void setDesgType(String _desgType) {
        this.desgType = _desgType;
    }

    public String getReportofficer() {
        return reportofficer;
    }

    public void setReportofficer(String _reportofficer) {
        this.reportofficer = _reportofficer;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String _jobtype) {
        this.jobtype = _jobtype;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String _pan) {
        this.pan = _pan;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String _contactId) {
        this.contactId = _contactId;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.dtoValidator = _validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}
