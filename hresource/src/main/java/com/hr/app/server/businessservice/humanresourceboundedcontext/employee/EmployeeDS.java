package com.hr.app.server.businessservice.humanresourceboundedcontext.employee;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.hr.app.server.repository.humanresourceboundedcontext.employee.EmployeeInfoRepository;
import com.hr.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.hr.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo;
import com.hr.app.shared.organization.contactmanagement.CoreContacts;
import com.hr.app.shared.organization.locationmanagement.Timezone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeDTO;

@Component
public class EmployeeDS {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CoreContactsRepository<CoreContacts> coreContactsRepository;

    @Autowired
    private EmployeeInfoRepository<EmployeeInfo> employeeInfoRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    public void empMethod(EmployeeDTO empdtoInp) throws Exception {
        if (empdtoInp != null) {
            com.hr.app.shared.organization.locationmanagement.Timezone timezone = timezoneRepository.findById(empdtoInp.getTimezone());
            com.hr.app.shared.organization.contactmanagement.CoreContacts coreContacts = new com.hr.app.shared.organization.contactmanagement.CoreContacts();
            coreContacts.setEmailId(empdtoInp.getEmailid());
            coreContacts.setFirstName(empdtoInp.getFirstName());
            coreContacts.setLastName(empdtoInp.getLastname());
            coreContacts.setPhoneNumber(empdtoInp.getPhno());
            coreContacts.setTimezone(timezone);
            coreContacts.setTitleId(empdtoInp.getTitle());
            coreContacts.setGenderId(empdtoInp.getGender());
            com.hr.app.shared.organization.locationmanagement.Address address2 = new com.hr.app.shared.organization.locationmanagement.Address();
            address2.setAddress1(empdtoInp.getAddress1());
            address2.setAddress2(empdtoInp.getAddress2());
            address2.setAddressTypeId(empdtoInp.getAddressType());
            address2.setCityId(empdtoInp.getCity());
            address2.setCountryId(empdtoInp.getCountry());
            address2.setStateId(empdtoInp.getState());
            address2.setZipcode(empdtoInp.getZipcode());
            com.hr.app.shared.organization.contactmanagement.CommunicationData communicationData1 = new com.hr.app.shared.organization.contactmanagement.CommunicationData();
            communicationData1.setCommData(empdtoInp.getComData());
            communicationData1.setCommGroupId(empdtoInp.getComGrp());
            communicationData1.setCommType(empdtoInp.getComType());
            coreContacts.addAddress(address2);
            coreContacts.addCommunicationData(communicationData1);
            com.hr.app.shared.organization.contactmanagement.CoreContacts coreContacts6 = coreContactsRepository.save(coreContacts);
            com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo employeeInfo10 = new com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo();
            employeeInfo10.setCoreContacts(coreContacts6);
            employeeInfo10.setDeptTypeCode(empdtoInp.getDepttype());
            employeeInfo10.setDesignationTypeCode(empdtoInp.getDesgType());
            employeeInfo10.setPan(empdtoInp.getPan());
            employeeInfo10.setReportingOfficer(empdtoInp.getReportofficer());
            employeeInfo10.setJobTypeCode(empdtoInp.getJobtype());
            com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo employeeInfo11 = employeeInfoRepository.save(employeeInfo10);
        }
    }
}
