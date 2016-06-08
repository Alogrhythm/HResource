package com.hr.app.server.service.humanresourceboundedcontext.employee;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.humanresourceboundedcontext.employee.EmployeeInfoRepository;
import com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.hr.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType;
import com.hr.app.server.repository.humanresourceboundedcontext.employee.DepartmentTypeRepository;
import com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType;
import com.hr.app.server.repository.humanresourceboundedcontext.employee.DesignationTypeRepository;
import com.hr.app.shared.humanresourceboundedcontext.employee.JobType;
import com.hr.app.server.repository.humanresourceboundedcontext.employee.JobTypeRepository;
import com.hr.app.shared.organization.contactmanagement.CoreContacts;
import com.hr.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.hr.app.shared.organization.contactmanagement.Title;
import com.hr.app.server.repository.organization.contactmanagement.TitleRepository;
import com.hr.app.shared.organization.locationmanagement.Timezone;
import com.hr.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.hr.app.shared.organization.locationmanagement.Language;
import com.hr.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.hr.app.shared.organization.contactmanagement.Gender;
import com.hr.app.server.repository.organization.contactmanagement.GenderRepository;
import com.hr.app.shared.organization.contactmanagement.CommunicationData;
import com.hr.app.shared.organization.contactmanagement.CommunicationGroup;
import com.hr.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.hr.app.shared.organization.contactmanagement.CommunicationType;
import com.hr.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.hr.app.shared.organization.locationmanagement.Address;
import com.hr.app.server.repository.organization.locationmanagement.AddressRepository;
import com.hr.app.shared.organization.locationmanagement.Country;
import com.hr.app.server.repository.organization.locationmanagement.CountryRepository;
import com.hr.app.shared.organization.locationmanagement.State;
import com.hr.app.server.repository.organization.locationmanagement.StateRepository;
import com.hr.app.shared.organization.locationmanagement.AddressType;
import com.hr.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.hr.app.shared.organization.locationmanagement.City;
import com.hr.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class EmployeeInfoTestCase extends EntityTestCriteria {

    @Autowired
    private EmployeeInfoRepository<EmployeeInfo> employeeinfoRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private EmployeeInfo createEmployeeInfo(Boolean isSave) throws Exception {
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("IbM9RZMUvzxAC2WxZEBb7jjY5A2Vv1mVmAGKhrJij7UYXOx89u");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("hK9sjduO9mxLQqAeupWXzgV9IQnvnWf6roQpQv8CnFfqgculfY");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("bzoebMM1Y1repV9G0KyyrJb6sDCUvb9XsTMCnFiuSVWDm8kZ2h");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("ksXr0x9t7uUPxP6Yed0BT1Gxu3XF5PFBtVhvKIJ78mt63Q14Mg");
        corecontacts.setPhoneNumber("cIR24YEhP1JB8WjeRedF");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380931779l));
        corecontacts.setNativeTitle("FThBYohxu3F2AWEbto7lRD42Odk04WFEoWTLQoAg6o0h1UnS6C");
        corecontacts.setEmailId("X6fr86XOo8ipPXNt3IZ6OrYs1etdUj2xjfG9NFec5tlYGcEUOp");
        corecontacts.setNativeFirstName("pugUF3YPm1pDaf83YQvhKGGAZJnUo9eyQnqpXwsltER7oHbfz8");
        corecontacts.setFirstName("HOmiEsrUaqYTqm480QTq5SPNobdd5dawfyBDOGNkHAyxxedpST");
        corecontacts.setNativeLastName("oFbzQqSWkckOxCjn8VAnEgoTxNZBWG2ANX7bR8iWGk22HqsMHi");
        Title title = new Title();
        title.setTitles("rgRyGx0H5ZEGIzNM2CqyYBDCUyKVaxzaTtTo84AFUa3McYRsMW");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("3DbiXI8JgJ566u8BTsXAqB3jxmaGYopOTYZp2rMIR9vYMW8r7F");
        timezone.setUtcdifference(10);
        timezone.setTimeZoneLabel("sttrYicge6RltX4K4BFEQgmWoc4MeDAGGAxqPuAL2cJdSOZ6Ff");
        timezone.setGmtLabel("o8kjw3kteyeDCgis6wpkdiFvMi75Kufh3xLSu3GrrcuSV0LwSZ");
        timezone.setCities("4ys09Qd481uY8GVDjRiY7p5WbnLC6DY1WFAPFxO03Z4f9lekHB");
        Language language = new Language();
        language.setAlpha3("aLj");
        language.setAlpha4("xUT6");
        language.setLanguageType("C36O48NTJkcCKV0sfjiJXDHDIgOTtMcE");
        language.setAlpha2("HL");
        language.setLanguage("f58YRob94RgQHdxsRWwi5bPdH64HRt2s0KssHcNdhEo9dXVXvx");
        language.setAlpha4parentid(11);
        language.setLanguageIcon("ULWLzOm1QSsZvz5b7WyeFLlUzXojF3tx0JubcThiBhwQW4ZnWX");
        language.setLanguageDescription("pwXeTOFkRR133kOUIAMywe0wDbHoyP7bfkh5ikN6Hl9cNt8Mbp");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("VyIsg7rnuAwfRMx6bXGjJ1AlsbalKXTMZF5kkcdt1HB56kdX1C");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("9jgeWLJfDHj20wz5oBwhewYSWE3NMk75enFsjPwiIqCT8hT0cC");
        corecontacts.setPhoneNumber("ybC4MZlx1r2l7NiXYkV7");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380931794l));
        corecontacts.setNativeTitle("qtMn9tJGS0qrRT4TZvemLxUeknbWXRryxKKhqBYVQswHEwhLCd");
        corecontacts.setEmailId("21OOQvrjdG7avua4za2MLjEGHfmeVAkzjJgyhVM7cwOLjZ12JS");
        corecontacts.setNativeFirstName("nAO0v2aYlXkQsjlLLFD4iLnMKYxJfQz6R9kRHi9FBuhHzyzcrr");
        corecontacts.setFirstName("Ghy3y0z68Cvc8PaqNTPeV5Vbgj8d8c6JcgjJJX4o3kC1u2yvAG");
        corecontacts.setNativeLastName("4U38DsJaRH53sXFnOeblD2XnJMKh7k7vH0MVQoReNmPGlmaYgi");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("1giBBWdufTNEOdoVERdmmZbj0QnmG5r5lkwIMRp9MZVDOJF8yA");
        corecontacts.setMiddleName("iQlx8AHEt6iB5SffI9hMe1iQoU7GZEsXoSY7nMITqhO9f6AFGc");
        corecontacts.setAge(96);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465380931906l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("eqOF8N1uNZ");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("IH7y7hsZvS1TxziJKgsiyZlA6GqRaU7C635EYfst7eNbXvYL0Y");
        communicationgroup.setCommGroupName("20ggjWOf7VHRtwdfxQvio1lFtlkl3pVBQpCTaM9H7gAHpVkq2I");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("N1yFCJ1SThOzHkc0KORujYZOufcvngctZhBfeYxET4y3WpvRaG");
        communicationtype.setCommTypeDescription("FOg9M4hdBZ5Bf4VuyLESQEwLc3HL5tZSD0iEORJaHnduTstRAT");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("eNg8QvMPt9");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setZipcode("q2oWdq");
        Country country = new Country();
        country.setCapitalLongitude(3);
        country.setCountryCode1("ysD");
        country.setCountryCode2("2Iv");
        country.setCurrencyCode("ctz");
        country.setIsoNumeric(274);
        country.setCapital("xUPsptyG10cqdgcAqiyEYiIxjKbrgATq");
        country.setCountryName("61XtkxSFAnEfRNi5wUtienX3SjjdxrBwsjjljqpATcxBG9usWm");
        country.setCountryFlag("cuKlrqrdct79VISTx5t2WdjzPterLuNl2eEqLtpdSBBjFylgrg");
        country.setCurrencySymbol("LFN7LXsATILAodrGWyVHBqVh3H3WHFOl");
        country.setCapitalLatitude(2);
        country.setCurrencyName("iNrrfS87ucRJYBe7Iz6qyeYcZZmWNzG4U5Cxb7OJlvAoMnnKdF");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("CsydCaPZIQHYJYCRzGZ54pRLIDcvn0EeOdMlLOsEvmbpQTCdTT");
        state.setStateCode(2);
        state.setStateCodeChar3("u0NXWy5HHrGBChb5elb5JtioC5NCVtrb");
        state.setStateCapitalLatitude(9);
        state.setStateCapital("2uYdCaFryq93c16BrZ9p7TkO8n2jIZKL9sSQDPxsByGX7RGrZJ");
        state.setStateCode(1);
        state.setStateCodeChar3("HaqwYbfZG76WQ1LjoCad8kab9lUHSnmv");
        state.setStateCapitalLatitude(6);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("VJfaO2BPlhyLogAK3g0PIGBSgGKMVYek");
        state.setStateName("ueYkeyvCFf6cfYAyTwNZyg1t7JAKdZh2KGcjlmTJ5cFz9IDaGO");
        state.setStateFlag("m2h1CBPSWEs1aBPZLbuDpkTT4bJsgWy7Bl45eOh3n3TffgUqjn");
        state.setStateDescription("C4ZVP8oFOFXqmJrSpFB8AKmJHb0TV99myXL3ghscOhKKEHQyp0");
        state.setStateCapitalLongitude(3);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("Y8IJXHdOdwb9ibdB4K1Dz6A93hwd95211xLhMhk5sZjdhjAnsB");
        addresstype.setAddressTypeDesc("IyF71oT5WJ5YF1vn7H4sBHD8HzQi2JstPZcbYwH955LhePV6zh");
        addresstype.setAddressTypeIcon("MQUKXYV8XDlvbEGJEFdzIYz1lpZBXwpF7VrHs5sFf2XiWk5GUZ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("i6THa8FAH9FxX1NigeIQIRjrOnhSg4s6");
        city.setCityDescription("TAVjkMIfTkhrhvTUBZZCATIAf3zztsn0Q40VCjntc6u2LfjC3Y");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("SPiZEhUjuU389ACJ94Tw6ujNGyiV5TgUnpmelGh3GoSgfGVNLz");
        city.setCityName("oylydWzrzZktef0ZcfOVxehSGTmgKGUjjHx8fXPyGIbbwFAeUg");
        city.setCityLongitude(5);
        city.setCityLatitude(11);
        city.setCityCode(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setZipcode("hWAseP");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("Hi0bjgBTW6y1VCBNv6vMWfGX37AywLE2Kcqvms3n60smvX1Kqv");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("7mAfGzN21KgUXOOqPUuSUussx12QfHxZHeVcAbkuunCX8Rz2bs");
        address.setLatitude("fNPxJFKOhSr61ypn7fXpmaSgf3OssYSMOAYsbts4pBsFbN3etC");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("LDek4qAAyA0");
        address.setAddress3("QqqFIhSn9Jf2rJfuZqgYJ4UmzExISsFotFVrcmxd1QHEXDOyoG");
        address.setLongitude("M5GUzNQl5Np527tOL1tYCuKIZNzV04ZxWazhSoiBy43Pws5Fd7");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        EmployeeInfo employeeinfo = new EmployeeInfo();
        employeeinfo.setDeptTypeCode((java.lang.String) DepartmentTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("4Y1baaeuowUDkeqdxFVETwHL7j8154IruBeQu8MDa2aLWqts71");
        employeeinfo.setDesignationTypeCode((java.lang.String) DesignationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setJobTypeCode((java.lang.String) JobTypeTest._getPrimarykey());
        employeeinfo.setPan(valueGenerator.randomValueGenerate("String", 256, 1));
        corecontacts.setContactId(null);
        employeeinfo.setCoreContacts(isSave ? corecontactsRepository.save(corecontacts) : corecontacts);
        if (isSave) {
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        }
        employeeinfo.setEntityValidator(entityValidator);
        return employeeinfo;
    }

    @Test
    public void test1Save() {
        try {
            EmployeeInfo employeeinfo = createEmployeeInfo(true);
            employeeinfo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            employeeinfo.isValid();
            employeeinfoRepository.save(employeeinfo);
            map.put("EmployeeInfoPrimaryKey", employeeinfo._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private DepartmentTypeRepository<DepartmentType> departmenttypeRepository;

    @Autowired
    private DesignationTypeRepository<DesignationType> designationtypeRepository;

    @Autowired
    private JobTypeRepository<JobType> jobtypeRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("EmployeeInfoPrimaryKey"));
            EmployeeInfo employeeinfo = employeeinfoRepository.findById((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
            employeeinfo.setReportingOfficer("SSrt11nXPmMorCeVJsoqMPJvZvb3REtIQGv5qg0zWJtAPf0sg6");
            employeeinfo.setPan("AoWLpHNaBDmWNc3prUkBLGs9nuaxij6G5x3tD8WvOXdLDVKFNz");
            employeeinfo.setVersionId(1);
            employeeinfo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            employeeinfoRepository.update(employeeinfo);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydeptTypeCode() {
        try {
            java.util.List<EmployeeInfo> listofdeptTypeCode = employeeinfoRepository.findByDeptTypeCode((java.lang.String) map.get("DepartmentTypePrimaryKey"));
            if (listofdeptTypeCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydesignationTypeCode() {
        try {
            java.util.List<EmployeeInfo> listofdesignationTypeCode = employeeinfoRepository.findByDesignationTypeCode((java.lang.String) map.get("DesignationTypePrimaryKey"));
            if (listofdesignationTypeCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByjobTypeCode() {
        try {
            java.util.List<EmployeeInfo> listofjobTypeCode = employeeinfoRepository.findByJobTypeCode((java.lang.String) map.get("JobTypePrimaryKey"));
            if (listofjobTypeCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("EmployeeInfoPrimaryKey"));
            employeeinfoRepository.findById((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("EmployeeInfoPrimaryKey"));
            employeeinfoRepository.delete((java.lang.String) map.get("EmployeeInfoPrimaryKey")); /* Deleting refrenced data */
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            jobtypeRepository.delete((java.lang.String) map.get("JobTypePrimaryKey")); /* Deleting refrenced data */
            designationtypeRepository.delete((java.lang.String) map.get("DesignationTypePrimaryKey")); /* Deleting refrenced data */
            departmenttypeRepository.delete((java.lang.String) map.get("DepartmentTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateEmployeeInfo(EntityTestCriteria contraints, EmployeeInfo employeeinfo) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            employeeinfo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            employeeinfo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            employeeinfo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            employeeinfoRepository.save(employeeinfo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "reportingOfficer", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "reportingOfficer", "f3p6TtMMiKI4UqUTPfldByq8GSUcImihnxU28jlJhZ3Pt4yp2YlCjT2C5O2RU2t0ukxVpLiDbWnzOkIrSOemxW91G8vGfyosjNXWFsdr97JtHP3hQvvMwP7uoNm8d6nW8NM4pIo3OzagLlQwwVpmjttXRVMqWO7yQWvlILbLO1y3wGfGtG24WsZGEHPfdSES5zuPhDwLerdiPvk9QjMqoQhYFwYG6USWwRPtdwOFpfKOXbq8VCmGr1wlObEVFRnwZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "pan", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "pan", "q5yDsT8LzQVz60cle3CGQGpcND29r7HGE3HIb9w1gvEitYWPofmrfwEPEiAU8IRF2eBFe3sXV8cgqUb3Rl9ZX06KfBJf8umOQo2xXayK0QFTT8Q9aAwoNCQCbqtTbl17PbCdgrKwrje2rJovf5PcGrPxZnYjm9FqexWjgTZuYHMVewg75bhSd4xQsi1cfgyl1iKQg9j6srmuLLaBJBgr2ZUqOTFtc4t4PEIYWFck2CgKhUcDYCjMU8Slk97tjUVLV"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 5, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        EmployeeInfo employeeinfoUnique = employeeinfoRepository.findById((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                EmployeeInfo employeeinfo = createEmployeeInfo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = employeeinfo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(employeeinfo, null);
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 2:
                        employeeinfo.setReportingOfficer(contraints.getNegativeValue().toString());
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(employeeinfo, null);
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 4:
                        employeeinfo.setPan(contraints.getNegativeValue().toString());
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                    case 5:
                        employeeinfo.setPan(employeeinfoUnique.getPan());
                        validateEmployeeInfo(contraints, employeeinfo);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
