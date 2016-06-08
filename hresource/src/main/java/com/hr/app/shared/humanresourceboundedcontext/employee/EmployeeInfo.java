package com.hr.app.shared.humanresourceboundedcontext.employee;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.hr.app.config.annotation.Complexity;
import com.hr.app.config.annotation.SourceCodeAuthorClass;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hr.app.shared.organization.contactmanagement.CoreContacts;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.hr.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.hr.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_EmployeeInfo_M")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "EmployeeInfo", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "EmployeeInfo.findByDeptTypeCode", query = "select e from EmployeeInfo e where e.systemInfo.activeStatus=1 and e.deptTypeCode=:deptTypeCode"), @javax.persistence.NamedQuery(name = "EmployeeInfo.findByDesignationTypeCode", query = "select e from EmployeeInfo e where e.systemInfo.activeStatus=1 and e.designationTypeCode=:designationTypeCode"), @javax.persistence.NamedQuery(name = "EmployeeInfo.findByJobTypeCode", query = "select e from EmployeeInfo e where e.systemInfo.activeStatus=1 and e.jobTypeCode=:jobTypeCode"), @javax.persistence.NamedQuery(name = "EmployeeInfo.findByContactId", query = "select e from EmployeeInfo e where e.systemInfo.activeStatus=1 and e.coreContacts.contactId=:contactId"), @javax.persistence.NamedQuery(name = "EmployeeInfo.findById", query = "select e from EmployeeInfo e where e.systemInfo.activeStatus=1 and e.empId =:empId") })
public class EmployeeInfo implements Serializable, CommonEntityInterface, Comparator<EmployeeInfo> {

    @Column(name = "reportingOfficer")
    @JsonProperty("reportingOfficer")
    @NotNull
    @Size(min = 1, max = 256)
    private String reportingOfficer;

    @Column(name = "pan")
    @JsonProperty("pan")
    @NotNull
    @Size(min = 1, max = 256)
    private String pan;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "empId")
    @JsonProperty("empId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String empId;

    @Column(name = "deptTypeCode")
    @JsonProperty("deptTypeCode")
    private String deptTypeCode;

    @Column(name = "designationTypeCode")
    @JsonProperty("designationTypeCode")
    private String designationTypeCode;

    @Column(name = "jobTypeCode")
    @JsonProperty("jobTypeCode")
    private String jobTypeCode;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private CoreContacts coreContacts;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getReportingOfficer() {
        return reportingOfficer;
    }

    public void setReportingOfficer(String _reportingOfficer) {
        if (_reportingOfficer != null) {
            this.reportingOfficer = _reportingOfficer;
        }
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String _pan) {
        if (_pan != null) {
            this.pan = _pan;
        }
    }

    public String getPrimaryKey() {
        return empId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String _empId) {
        this.empId = _empId;
    }

    public String getDeptTypeCode() {
        return deptTypeCode;
    }

    public void setDeptTypeCode(String _deptTypeCode) {
        this.deptTypeCode = _deptTypeCode;
    }

    public String getDesignationTypeCode() {
        return designationTypeCode;
    }

    public void setDesignationTypeCode(String _designationTypeCode) {
        this.designationTypeCode = _designationTypeCode;
    }

    public String getJobTypeCode() {
        return jobTypeCode;
    }

    public void setJobTypeCode(String _jobTypeCode) {
        this.jobTypeCode = _jobTypeCode;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    public CoreContacts getCoreContacts() {
        return coreContacts;
    }

    public void setCoreContacts(CoreContacts _coreContacts) {
        this.coreContacts = _coreContacts;
    }

    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        System.out.println("Setting logged in user info for " + recordType);
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    @Override
    public int compare(EmployeeInfo object1, EmployeeInfo object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((reportingOfficer == null ? " " : reportingOfficer));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (empId == null) {
            return super.hashCode();
        } else {
            return empId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo other = (com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo) obj;
            if (empId == null) {
                return false;
            } else if (!empId.equals(other.empId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
