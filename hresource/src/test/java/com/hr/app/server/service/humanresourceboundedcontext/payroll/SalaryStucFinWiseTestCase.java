package com.hr.app.server.service.humanresourceboundedcontext.payroll;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.humanresourceboundedcontext.payroll.SalaryStucFinWiseRepository;
import com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise;
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
public class SalaryStucFinWiseTestCase extends EntityTestCriteria {

    @Autowired
    private SalaryStucFinWiseRepository<SalaryStucFinWise> salarystucfinwiseRepository;

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

    private SalaryStucFinWise createSalaryStucFinWise(Boolean isSave) throws Exception {
        EmployeeInfo employeeinfo = new EmployeeInfo();
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("ib3PihgXkdxD4Da3a4kra3vmsRYiIdy4tnHrrxjdkC5XoCs8Rb");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("z8UVVsibWf0GPpOEOzl5kLUzfXxZw7EbWp5aVMpFGc9VYaurDD");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("TH5fFUz7FmCvJodSDudLdJG0uwVek5IhZgxCLa4ADmcGWppMop");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("m3NjwXHLTEGDFAdcdVk5u4Yvr1yZn9sGTgWVvUav0yzaZ0RB1O");
        corecontacts.setPhoneNumber("GlrH1HLN2zi6i7Y9bvS7");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380936507l));
        corecontacts.setNativeTitle("hlPsW0A3KlMYtruq217reEBDsJQZIdci4Rbgdgppf6RaTjAZRx");
        corecontacts.setEmailId("Dca9Nq1oy89ZPrCfeFUVZEgT2NwPAuprfUZ0KWrw4qotM83MVG");
        corecontacts.setNativeFirstName("z2XtLApYSVFMomf0C6D3AXkLmJh9ZHtvET0kbEia1ueSHAlLz6");
        corecontacts.setFirstName("cYP1tohtXyCjW0Vj9HkRTqVjtOGrpCAA8CppkqByZonJWqnLLC");
        corecontacts.setNativeLastName("4plIZRbRr7NcORhq8StRIWJLHiBtzHlhooP7LosjpKhbWiE5Aq");
        Title title = new Title();
        title.setTitles("kb6AwJVWQlRp9w1vtxKV1Y47BGKHJGo2Flo2Ktslq7Zmck36M5");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("HPfuDw6uL2lr3YuuKRYsXxCbnq03llWvGhFCRSJ6gr6BXLrcTr");
        timezone.setUtcdifference(2);
        timezone.setTimeZoneLabel("4JzH3BFtkVknTojO4PQh9Js4NdrZMnXlyQd2tafSvtU9Ywbr30");
        timezone.setGmtLabel("TW02xZzEVBubG0eO93UftgIyRCpfMSJCfmHMtFOf3WGVHt2nAQ");
        timezone.setCities("VcVFbEPeZTnFa3e6VlliSQ0u2tAIoYUbwhQa7GwH3iSgDiM0F5");
        Language language = new Language();
        language.setAlpha3("4sK");
        language.setAlpha4("qkyW");
        language.setLanguageType("PHhX82FQpfCNx4kK63xZPk48jooiDMzx");
        language.setAlpha2("Ht");
        language.setLanguage("dLq7DJGMfBTFgnVThCZNNl7fGO4fzXCKqbZXMkFiYA4oi5jxs3");
        language.setAlpha4parentid(10);
        language.setLanguageIcon("cyDDXAYaL9VzWDA9BYLBbpkrXKXTbvNi6j0tjj8hk9S4ysuOp7");
        language.setLanguageDescription("U9WxyaujFXtfqoEk22Xld06lfWQlv4W5QYsfeTIDRUXup3gGG2");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("viHVj4GzExUvtwmp8k6C4rvg2KReDeFnm26AtBvEvxZnBFw3ee");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("v9HzJxgBx35uM05aTy652Z8zpw3PBFwanvA24l1MWSRjpWJfEL");
        corecontacts.setPhoneNumber("9JixNxxYjbQF8JbZf8Ap");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380936517l));
        corecontacts.setNativeTitle("azW5JCVfchhLKaeo1ENIsHevpqIXdChgr5wAXKxhhlgcUZejKM");
        corecontacts.setEmailId("p1fxbBJnkaJU6HD6L2LaEema4kNp7cSbREROb3Ucage5cMz91g");
        corecontacts.setNativeFirstName("KCK6iTJqtcyR7CB9iLrAlFicdYW57UXFW52AWtpXOHCD6Zm7Nw");
        corecontacts.setFirstName("6MPLHEnpC9nJFpyA26uC83UHW5480VLs3iKhJtXlXy07cFj1KO");
        corecontacts.setNativeLastName("GYsv9mhHTGuxowHLMoI0kbWiS2EJzBxMc9resfKYt3zFctcA2E");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("Gep8hsPkLiWloLDT4RBi8lmzPDJTYUwhYHWC7NSCrCastn66ns");
        corecontacts.setMiddleName("9MZD6uBiPvcg3jsptBwGQJ0gfLQGYL4vmIfcyMNXjcvi9s85oy");
        corecontacts.setAge(80);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465380936643l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("nZcEm2jaSR");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("waNFv32bVfWt1AzVDBOigEoYtp1rmQgn4SsFm2JWuCwyuh6a4m");
        communicationgroup.setCommGroupName("uoh7oJRH7L5sOlN7EK3uvizloBUFEtWlr9CeW8pDAu667tjeZe");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("SxzKO9I2DRi3sLdQT7UqXlpXYuS39EBV3WCNy9POjfvDZ99hWw");
        communicationtype.setCommTypeDescription("RT0ckq2ljm4JwOV08NJMPSgikBrAFOKqsrlidithshTDvUwT6H");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("NkECwGNmHU");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setZipcode("uqIMHo");
        Country country = new Country();
        country.setCapitalLongitude(1);
        country.setCountryCode1("j9k");
        country.setCountryCode2("3SG");
        country.setCurrencyCode("0JH");
        country.setIsoNumeric(655);
        country.setCapital("RGoivgzcst5doUQZ7BMqfnRXAZeCnrh6");
        country.setCountryName("VR7AvNdzEePus7QNU5ZsVpBDLSyACqfwcQ7wOuPaVGLoJW5Gl6");
        country.setCountryFlag("WYkVjfgeQOsMRCwL7AOjBWEc3Wzpy471wPZVyr4YLbuHI5tpzB");
        country.setCurrencySymbol("apbYpzTA9ELLGWsDDI4GLJ5UTRLejvpk");
        country.setCapitalLatitude(5);
        country.setCurrencyName("a0vwh3p7EXUDccKrpotIwIw03w4rMFMk9oBlGVduxWyo3Oreob");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("oQ6BfFkneiUUITsZhmQ2ESS5FxGrarZSTGsZ9EGOSb6pStksEq");
        state.setStateCode(2);
        state.setStateCodeChar3("QLjUCqsNMnV3xnA9GQ142DdH1itxe3iJ");
        state.setStateCapitalLatitude(3);
        state.setStateCapital("EkpoCgyCth65pYLiZVkaz29rTWqpuotA9hAC629HNjMV5oKcJ6");
        state.setStateCode(1);
        state.setStateCodeChar3("9ha0bGPRnZ0gFt9Lz7eM2SYqq16Nhk7n");
        state.setStateCapitalLatitude(3);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("S35cX9UzjwtYIQtf7jxWDcD156iU4PrJ");
        state.setStateName("LDUBGAivBHvE4E4gpj6oDqEz4yrvlxHpjzAHwC7uAWTaGw6tq8");
        state.setStateFlag("PPaKQEF3owBD92HnSf6K2hHQf0hODyTTfXB7k1Hd4g6SFNyuSK");
        state.setStateDescription("Mh8vPahOl8CSl4c0YlCLO2hVRJveEiKwx8MMj8GHXCE9Vk69LB");
        state.setStateCapitalLongitude(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("5GGzdOZ0vFzEGSlcRpUSxeF1bbOG1ZxOKBY9S3oj0SErVkHRag");
        addresstype.setAddressTypeDesc("kTOKC0Yy2spLNqgFJElxcsHyLNfZuANA6jxwOqD4BkhVKn8cF2");
        addresstype.setAddressTypeIcon("asBl5VeZGcqeMx4zY4YZ1geyZdJTOyiu0eLeXFWbVU09Ft4xuL");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("zi5pVWg2ruoruscMdUKSrK3C7vtunAc2");
        city.setCityDescription("nybQbxDB13mowM5P5F0Dg9vYwybtOEBAfr6loRVF5eMaE59PyZ");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("f0LaOW0dpiot6nQ1s3HuFJxTP1ItlaJLdd6uixEDwwqL730JGM");
        city.setCityName("hSGlVrOUYnQtLqXiwDc6ToqeKz2XxDoWChAKZdJgIPb1IxeaM1");
        city.setCityLongitude(3);
        city.setCityLatitude(9);
        city.setCityCode(1);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setZipcode("9X7pRg");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("6h0qr7s4voKMlx6V72LEtHwRWGtrZJTn7XjZDMX20jtvLbadZP");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("yYh8VT6xMY24QQ8VaqQpfgTfI2uJMe2fbve2YAGSR9lPv5Fz60");
        address.setLatitude("Jwm1Q0u6X8EYgZ47wWPPbHIAwEcUA4nzhptgOhYf10RlqWz8LK");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("Mvgj5LkEXMa");
        address.setAddress3("OcTaCSzjc8w5HUPBpk9YQNxIncrv1z8i0hEYvSQsQWf6iXQJqQ");
        address.setLongitude("qZ7nKQdhn0mmfiArakUYE27tIOxBpXvUwNjN24kzPIIUW2P4qu");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        employeeinfo.setDeptTypeCode((java.lang.String) DepartmentTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("bGDtE6eSsioyxTr7T9zktBX8WrlZjewWIwEn2ztJbkg2jakiCK");
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
        SalaryStucFinWise salarystucfinwise = new SalaryStucFinWise();
        salarystucfinwise.setBasic(6000.0d);
        salarystucfinwise.setConvenceAllowance(-6700.0d);
        salarystucfinwise.setEmpId((java.lang.String) EmployeeInfoTest._getPrimarykey());
        salarystucfinwise.setSalaryDrawn(3900.0d);
        salarystucfinwise.setHra(2500.0d);
        salarystucfinwise.setMedicalAllowance(-1200.0d);
        salarystucfinwise.setEducationalAllowance(-7700.0d);
        salarystucfinwise.setPerk(9300.0d);
        salarystucfinwise.setNonTaxableAmount(-7120.0d);
        salarystucfinwise.setTakeHome(4700.0d);
        salarystucfinwise.setTotalCTC(8700.0d);
        salarystucfinwise.setYearValue(2147483647);
        salarystucfinwise.setSpecialAllowance(-4600.0d);
        salarystucfinwise.setTotalTax(2210.0d);
        salarystucfinwise.setTaxableAmount(1900.0d);
        salarystucfinwise.setEntityValidator(entityValidator);
        return salarystucfinwise;
    }

    @Test
    public void test1Save() {
        try {
            SalaryStucFinWise salarystucfinwise = createSalaryStucFinWise(true);
            salarystucfinwise.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            salarystucfinwise.isValid();
            salarystucfinwiseRepository.save(salarystucfinwise);
            map.put("SalaryStucFinWisePrimaryKey", salarystucfinwise._getPrimarykey());
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
            org.junit.Assert.assertNotNull(map.get("SalaryStucFinWisePrimaryKey"));
            SalaryStucFinWise salarystucfinwise = salarystucfinwiseRepository.findById((java.lang.String) map.get("SalaryStucFinWisePrimaryKey"));
            salarystucfinwise.setBasic(2100.0d);
            salarystucfinwise.setVersionId(1);
            salarystucfinwise.setConvenceAllowance(-9300.0d);
            salarystucfinwise.setSalaryDrawn(8900.0d);
            salarystucfinwise.setHra(-4800.0d);
            salarystucfinwise.setMedicalAllowance(9600.0d);
            salarystucfinwise.setEducationalAllowance(-8800.0d);
            salarystucfinwise.setPerk(-3390.0d);
            salarystucfinwise.setNonTaxableAmount(-2700.0d);
            salarystucfinwise.setTakeHome(2800.0d);
            salarystucfinwise.setTotalCTC(-6400.0d);
            salarystucfinwise.setYearValue(2147483647);
            salarystucfinwise.setSpecialAllowance(-7800.0d);
            salarystucfinwise.setTotalTax(-4270.0d);
            salarystucfinwise.setTaxableAmount(5400.0d);
            salarystucfinwise.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            salarystucfinwiseRepository.update(salarystucfinwise);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByempId() {
        try {
            java.util.List<SalaryStucFinWise> listofempId = salarystucfinwiseRepository.findByEmpId((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
            if (listofempId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalaryStucFinWisePrimaryKey"));
            salarystucfinwiseRepository.findById((java.lang.String) map.get("SalaryStucFinWisePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalaryStucFinWisePrimaryKey"));
            salarystucfinwiseRepository.delete((java.lang.String) map.get("SalaryStucFinWisePrimaryKey")); /* Deleting refrenced data */
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

    private void validateSalaryStucFinWise(EntityTestCriteria contraints, SalaryStucFinWise salarystucfinwise) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            salarystucfinwise.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            salarystucfinwise.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            salarystucfinwise.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            salarystucfinwiseRepository.save(salarystucfinwise);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "yearValue", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "perk", 1.5819125517377898E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "totalCTC", 1.5486195745833611E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "takeHome", 1.7952678796154966E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "salaryDrawn", 1.4645268802026258E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "taxableAmount", 1.5181642766164384E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nonTaxableAmount", 9.365718442319129E18d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "totalTax", 1.05773804551162E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "basic", 1.1165294582774143E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "hra", 1.4491349156408904E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "convenceAllowance", 1.5456549333673996E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "medicalAllowance", 1.8186633611474643E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "educationalAllowance", 1.776013400206459E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "specialAllowance", 1.7368804569916408E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                SalaryStucFinWise salarystucfinwise = createSalaryStucFinWise(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = salarystucfinwise.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(salarystucfinwise, null);
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 2:
                        salarystucfinwise.setPerk(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 3:
                        salarystucfinwise.setTotalCTC(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 4:
                        salarystucfinwise.setTakeHome(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 5:
                        salarystucfinwise.setSalaryDrawn(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 6:
                        salarystucfinwise.setTaxableAmount(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 7:
                        salarystucfinwise.setNonTaxableAmount(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 8:
                        salarystucfinwise.setTotalTax(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 9:
                        salarystucfinwise.setBasic(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 10:
                        salarystucfinwise.setHra(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 11:
                        salarystucfinwise.setConvenceAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 12:
                        salarystucfinwise.setMedicalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 13:
                        salarystucfinwise.setEducationalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
                        failureCount++;
                        break;
                    case 14:
                        salarystucfinwise.setSpecialAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalaryStucFinWise(contraints, salarystucfinwise);
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
