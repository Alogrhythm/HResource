package com.hr.app.server.service.humanresourceboundedcontext.attendance;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.humanresourceboundedcontext.attendance.AttendanceSummaryRepository;
import com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary;
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
import com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo;
import com.hr.app.server.repository.humanresourceboundedcontext.employee.EmployeeInfoRepository;
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
public class AttendanceSummaryTestCase extends EntityTestCriteria {

    @Autowired
    private AttendanceSummaryRepository<AttendanceSummary> attendancesummaryRepository;

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

    private AttendanceSummary createAttendanceSummary(Boolean isSave) throws Exception {
        EmployeeInfo employeeinfo = new EmployeeInfo();
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("3Jv1adItqiZS8GgF1J3nRx8Q7cAdGyt8ovDUQJxHD8J6CZ9hLE");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("gb6eEAT9pWOCKhHi9RdX23vMw2QRul7Ph5TgOK2StLiFoxGvcb");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("5ByzsSDnMQ84scTiYy6c34jO0smXg4t0REJKkMqfJmt9AmzGvi");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("xccWyYS6F0vbb3wefjCX89vVpWscDQFOpYKBhf8lXHMvHeuntl");
        corecontacts.setPhoneNumber("zDr4QF5PHDVgUMK6ugHL");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380937750l));
        corecontacts.setNativeTitle("XjWKwp2R7TxmPFAxH9P3DAdsA2eWRGM9xK210mb5xeJpxIOdAr");
        corecontacts.setEmailId("WXHKvRuzQAqepumLh5Cs6EQtp7Gdjnke51wK6CsjqhxNqCLyxj");
        corecontacts.setNativeFirstName("VKWPFjzy0WDTfgDmGaAdYDqzz5ZJgkZ7ECdldGOxSJsJNGmHWR");
        corecontacts.setFirstName("nujBhfWJL09M6a5fhf2b6ibfSKDOjUnR5OO9OEnpK1N6AZbqb2");
        corecontacts.setNativeLastName("sFyxE1o1DXe4HldxwIfAnqdGgZTYBjpp3sf8Zr6w9EBRP55WWq");
        Title title = new Title();
        title.setTitles("fg2XnsypZgY9VGAVBBl84p07L5gLDdlHru7o0KTTCdeOECKKzD");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("PWFRS0S3XIE8jFpL4ksgOkP0RXSflMAyoDD9heM3m5fWlIG8zB");
        timezone.setUtcdifference(1);
        timezone.setTimeZoneLabel("fvSAAW0QPPXtyqH9rajI9aCYLAe8tPDH5SGEqDMszcuU73WEjZ");
        timezone.setGmtLabel("jJTexL5I9V0ZpYLa1pj8GXvRcCRVBOaSFusOPA84pjA8eaiRLa");
        timezone.setCities("TkmhxzOB90QvylrUOt4CXxVnvoJfD8zDYwlmdMQSfo5rfRvQlJ");
        Language language = new Language();
        language.setAlpha3("Hjp");
        language.setAlpha4("PBiJ");
        language.setLanguageType("lSvSzIPjzw3rENjnVNeTh0tkuN6whNHW");
        language.setAlpha2("RD");
        language.setLanguage("isH5GyE87NoaRFjxok7927YYf7u05aAueCnEp1lFS29QgFwfv9");
        language.setAlpha4parentid(8);
        language.setLanguageIcon("ozEYkYV5sbns9CeRXgSVy98OrmDO2rQQSmSaPXcZi7sns5YcdA");
        language.setLanguageDescription("EtfcKiT6iy3zlKX7577LRXyAYBRxJdWaKfItrNkwv5oxlIVZe2");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("4TMIaX6BDp5ZdbBaBEzmuhnbZmuT2AvVPXh0KPSOwxD10Oo6yf");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("r3IozgYsaZk4mpPPy3UDwrZtaRo8rdRg3ItYl2O5fV06rv5wgW");
        corecontacts.setPhoneNumber("kKeKj8MGC9JAo3JOvbo8");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380937759l));
        corecontacts.setNativeTitle("m8rL3FHkhrjufzAikXv22nRAEdPxZqG2g7X54vHWVisOdVleVG");
        corecontacts.setEmailId("E7tqhml1DQbgKjB0NRwzzCSNiKKCJ8qBdayiF6r6nKG8GrDP8M");
        corecontacts.setNativeFirstName("AN4J2uGWaNaydccg1KeQcyBJ7j0k4OILZWgmEhpKt0ubsoeJW3");
        corecontacts.setFirstName("wNA3snmICcOsdPRwRBwMOYxq1epw9TJ5rDwLpHiIiBD4H8RMdY");
        corecontacts.setNativeLastName("yvHkPN8hBmK4Pgc9V6MAmOb1oqwAoKEDDiTbmkLiDzNCMv536b");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("Tg5ZevNUm3jaC8mo2Q9bxxAkVmPx4z5wA1GpRxjgDlX05BhsVD");
        corecontacts.setMiddleName("Rnm9eHTSuI3TSUuO9tBwEtAI7WJXBMX4moi6PcDpDfya0MlCHJ");
        corecontacts.setAge(4);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465380937871l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("lauUyeS2lg");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("5sYr5AePoli9sPGKC3EbgX6fSts2z3kJJCwAuXZ3atBs24MIZS");
        communicationgroup.setCommGroupName("aucJfYTNee1RrznL5MTorzFJJWo8yEHxsxsDUFCQFIGtazT3H5");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("KAIbe9VL6gGN9wSZAV3vqpakLGT7gGiWbdpeMjn0m7WGF74Iqq");
        communicationtype.setCommTypeDescription("EIIECyXMwmkm97ozt3KpTaLdh7XmRw0Oi99NngeHDPeX3WPyVJ");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("Gh9Ibty3qy");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setZipcode("qVHBdn");
        Country country = new Country();
        country.setCapitalLongitude(11);
        country.setCountryCode1("4CV");
        country.setCountryCode2("7Zd");
        country.setCurrencyCode("fmq");
        country.setIsoNumeric(223);
        country.setCapital("UCCNAYzaUwqDmlmHujkxT3f6eet0sV1r");
        country.setCountryName("PxDAV2lDUcKRkvWVV2XbCW3koYtxkqmTznHWsgrewMGlFKOfXj");
        country.setCountryFlag("zW0oLfxrpNNG5EavywhOAT7aYPLaNzc6Av4SSk9sHsFneKz5Kl");
        country.setCurrencySymbol("fmwlILAoCBs7FzupqiozdTmCeX31XZhU");
        country.setCapitalLatitude(3);
        country.setCurrencyName("SZ4tX4sKuLWvnXT79yVpjf4AK7KlMrX4n2JT5B2ROrXwZOBvFk");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("lmO8IsssljQWckey0XidWRSMFM1FA3q5woAECuOy9oVlGrt8ww");
        state.setStateCode(2);
        state.setStateCodeChar3("e9WENk26gpoB7D5zyFWeyoGftHSSnXe5");
        state.setStateCapitalLatitude(1);
        state.setStateCapital("9imZ8HX0LquVOHhHkExyX1DpzTPY0HDMXZT6gpY7Yt2Lsr0UkU");
        state.setStateCode(2);
        state.setStateCodeChar3("JLHtXeVd3tmeQbMId60LENE4KdzcID6Z");
        state.setStateCapitalLatitude(3);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("2b2PWcf1s72I3n38Sohnktqbs2eccpyz");
        state.setStateName("SLQajWoU3n4TfLdMcyaPFP2TthMhRpSRNMrQHLvRiItdy2xvRU");
        state.setStateFlag("YLqwbxBgsFJd7CPmcV5Zcbhirws6AIava9BVEuALyT0NhF97Zh");
        state.setStateDescription("0q2G8n6yrZaULXp7XvEhE2e3YBqWTB2s5xSxfoCdBGkGmtqJjF");
        state.setStateCapitalLongitude(11);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("UDEBp5kVXMP9Q8iFTmdwIwFLfNKOYkG7RzJQT88hfH9oQ64fEc");
        addresstype.setAddressTypeDesc("jRUQfBXjTocluW4jyl7BplTLufQ0TtSCzr4noLSt7cK7Rf1Q7p");
        addresstype.setAddressTypeIcon("EjLuGcGCflTuQ2j9Z7GNQobVy0ayjzUKkZAC0l2NAc5C2FAsxh");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("ioSmKb2zTiUX3hrgxRipNiAu6eX0oCw9");
        city.setCityDescription("UHNCnqEY9iq2qEqln0tKqgHI8qVQVkpRqJ8jwCInDsGpVfb5cA");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("0m2DtfGIot4fAp48jBziwOqhockus3xLPl1IyI9OnI1b9oVq3Q");
        city.setCityName("wHknUQFHQ808VN28PSgFRwmeDOaONfANXHSzCEVA0Berb8OhGG");
        city.setCityLongitude(10);
        city.setCityLatitude(3);
        city.setCityCode(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setZipcode("qoyTaF");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("m6ISzHcYEMFBC75tzskNk6WaPCcFWtQB1xbc8Cwy3UvXLif40u");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("KH38O0GqHyxvMYRPNymOZ592BZn4zDoVEng0t6HJPmXnVnMEcJ");
        address.setLatitude("5LKqjCv4Aa8N1dG9uYrXD3m1vLRjzLzvTTd3mYty6d5h3ZvNBg");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("BnDaCjDluBo");
        address.setAddress3("2mtfK1J14jQdk3REoFe7Byxn2COb9dD0TzBaGUeq46v9tFx8TO");
        address.setLongitude("o8YTjDZKPZF7T4A48TTRpdPAcTHLUQ6WkCVEcJ3J1wPc5K5oka");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        employeeinfo.setDeptTypeCode((java.lang.String) DepartmentTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("8FW3ds7MZHDxvaPffA5tzEq6diGFh1kTOekxZmZmASoOLjxpiQ");
        employeeinfo.setDesignationTypeCode((java.lang.String) DesignationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setJobTypeCode((java.lang.String) JobTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setPan(valueGenerator.randomValueGenerate("String", 256, 1));
        corecontacts.setContactId(null);
        employeeinfo.setCoreContacts(isSave ? corecontactsRepository.save(corecontacts) : corecontacts);
        if (isSave) {
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        }
        EmployeeInfo EmployeeInfoTest = new EmployeeInfo();
        if (isSave) {
            EmployeeInfoTest = employeeinfoRepository.save(employeeinfo);
            map.put("EmployeeInfoPrimaryKey", employeeinfo._getPrimarykey());
        }
        AttendanceSummary attendancesummary = new AttendanceSummary();
        attendancesummary.setPrivilegeLeave(2147483647);
        attendancesummary.setMonthValue(2147483647);
        attendancesummary.setMaternityLeave(2147483647);
        attendancesummary.setCasualLeave(2147483647);
        attendancesummary.setYearValue(2147483647);
        attendancesummary.setSickLeave(2147483647);
        attendancesummary.setAbsent(2147483647);
        attendancesummary.setEmpId((java.lang.String) EmployeeInfoTest._getPrimarykey());
        attendancesummary.setPresent(2147483647);
        attendancesummary.setEntityValidator(entityValidator);
        return attendancesummary;
    }

    @Test
    public void test1Save() {
        try {
            AttendanceSummary attendancesummary = createAttendanceSummary(true);
            attendancesummary.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            attendancesummary.isValid();
            attendancesummaryRepository.save(attendancesummary);
            map.put("AttendanceSummaryPrimaryKey", attendancesummary._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private EmployeeInfoRepository<EmployeeInfo> employeeinfoRepository;

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
            org.junit.Assert.assertNotNull(map.get("AttendanceSummaryPrimaryKey"));
            AttendanceSummary attendancesummary = attendancesummaryRepository.findById((java.lang.String) map.get("AttendanceSummaryPrimaryKey"));
            attendancesummary.setPrivilegeLeave(2147483647);
            attendancesummary.setMonthValue(2147483647);
            attendancesummary.setMaternityLeave(2147483647);
            attendancesummary.setCasualLeave(2147483647);
            attendancesummary.setYearValue(2147483647);
            attendancesummary.setSickLeave(2147483647);
            attendancesummary.setAbsent(2147483647);
            attendancesummary.setVersionId(1);
            attendancesummary.setPresent(2147483647);
            attendancesummary.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            attendancesummaryRepository.update(attendancesummary);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AttendanceSummaryPrimaryKey"));
            attendancesummaryRepository.findById((java.lang.String) map.get("AttendanceSummaryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByempId() {
        try {
            java.util.List<AttendanceSummary> listofempId = attendancesummaryRepository.findByEmpId((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
            if (listofempId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AttendanceSummaryPrimaryKey"));
            attendancesummaryRepository.delete((java.lang.String) map.get("AttendanceSummaryPrimaryKey")); /* Deleting refrenced data */
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

    private void validateAttendanceSummary(EntityTestCriteria contraints, AttendanceSummary attendancesummary) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            attendancesummary.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            attendancesummary.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            attendancesummary.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            attendancesummaryRepository.save(attendancesummary);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "monthValue", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 2, "yearValue", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "present", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "absent", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "privilegeLeave", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "casualLeave", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 7, "sickLeave", null));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AttendanceSummary attendancesummary = createAttendanceSummary(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = attendancesummary.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
                        failureCount++;
                        break;
                    case 7:
                        field.setAccessible(true);
                        field.set(attendancesummary, null);
                        validateAttendanceSummary(contraints, attendancesummary);
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
