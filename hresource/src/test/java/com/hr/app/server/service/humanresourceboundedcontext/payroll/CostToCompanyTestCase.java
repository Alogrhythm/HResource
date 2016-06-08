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
import com.hr.app.server.repository.humanresourceboundedcontext.payroll.CostToCompanyRepository;
import com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany;
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
public class CostToCompanyTestCase extends EntityTestCriteria {

    @Autowired
    private CostToCompanyRepository<CostToCompany> costtocompanyRepository;

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

    private CostToCompany createCostToCompany(Boolean isSave) throws Exception {
        EmployeeInfo employeeinfo = new EmployeeInfo();
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("Xt414Bi22czOi8YHIMn2wMfmcJSGyKQQCdXh1VpfE7JxIQhU4E");
        DepartmentType DepartmentTypeTest = new DepartmentType();
        if (isSave) {
            DepartmentTypeTest = departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        }
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("npj2izTqYYrfJIxlP4fb3yD2X6SOYriQuqk44v7q0g13KYdSKM");
        DesignationType DesignationTypeTest = new DesignationType();
        if (isSave) {
            DesignationTypeTest = designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        }
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("V0sIx9FcTpnzq5aAAzvIIuxXiJRetTUlzHMXxfAsN3lnV0MLnK");
        JobType JobTypeTest = new JobType();
        if (isSave) {
            JobTypeTest = jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("bzfwTSOxkcoIW0uesvKCRlc8MbSPrqj0uaUJ4rFURYQGdUS5ui");
        corecontacts.setPhoneNumber("uY6Emx3Jim1pYnn3D0KN");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380935316l));
        corecontacts.setNativeTitle("c7JRjCKcZAbgsO4EQz8mwGLhePWVTy146n0OrWhXCTqBVHA4A6");
        corecontacts.setEmailId("hdlGLojT94W7Rn5nc3brqyRU2z9pu5jkGUqSsqSZNVDPabmwwp");
        corecontacts.setNativeFirstName("K2ZBPLLoTzHe3QVSnwtG3ZuIBjy7DwCagvpJ7m3rcLoMwCN1zj");
        corecontacts.setFirstName("opEY21YmX4NcCIpUok79xbt31rRPBRlePizxoYHmNz0G8osk2h");
        corecontacts.setNativeLastName("7ar8HFLOa0RT3FUyowScTAOf5gvwZoI8K9TBQ9hYQWRFKHpigJ");
        Title title = new Title();
        title.setTitles("T9z99XFYUvMqT7lzJeCRbShTHmgnfkmFlzCW9cf7Gp8nruUlet");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("42uK0zgmGMNuhmdKqoBxGjjtdus0m8zJsltSxfnCQ6ZGdmjahM");
        timezone.setUtcdifference(7);
        timezone.setTimeZoneLabel("2CJQB92AfxZd5VpV7xZlzxiWKqnWCqc9E5H94SzQ4U2mxC7I0e");
        timezone.setGmtLabel("BUQB85RtaAt6F1TueYxvUu9qwB6MkLF5Yz4vyb0NeV4W7slfIO");
        timezone.setCities("4iVnmdgs3SVUkGLoW8mUTzQeeZeXKk36gpfYIepF45SMUidyNn");
        Language language = new Language();
        language.setAlpha3("JMR");
        language.setAlpha4("jzbR");
        language.setLanguageType("Dn8elQoNzcHRedmoYpG1g3huor2W9Y4i");
        language.setAlpha2("tZ");
        language.setLanguage("tKOCywvG6VjFY8wYwdErN0kEz5x6oovUV5RDVQqzqThY2cK3od");
        language.setAlpha4parentid(3);
        language.setLanguageIcon("4ieljWjg7q4JkEwnvBObvZhFqNwLhk0yQRyWI9GjbWvnIcYaVM");
        language.setLanguageDescription("ne62qPmDOEcxN1b4Pwvt1cTxZkS2tmrf1Q7QVYoQDhiFpjdgft");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("dBuHZ2KOZS3z6PbgbMkIHPI1DKb3hjKmlP97e3tF0c2GgQ51SW");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("Idb9Vx7UjJfqKcqL92V7AIqghwOJy7ThrYmDOeCDkjbOUabE1B");
        corecontacts.setPhoneNumber("g1YOj9xSKdalB7hklONB");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380935325l));
        corecontacts.setNativeTitle("caVOFDojXC0fBLz3eO8hoU9OehQd3pVXSPcUUidTQSHI57Jt1o");
        corecontacts.setEmailId("87ScVDw91eofpSmlPf90MyWpYFvOdNdGzg1trPB4k2pOx5Z8Vl");
        corecontacts.setNativeFirstName("ZDShJTtAjCnQ4UQG7bQTMCXh5oeWKEUglf7xksG5seXHw1VI5c");
        corecontacts.setFirstName("WdRDErsyvgiCYO6MqvrjVsd70tLGfoTAQk2s7C7xrhaQMwP2pQ");
        corecontacts.setNativeLastName("aGJ5NV3VJg0BDelz4aoO8rJAsbWksv1Q87ZWLyJQrJmdwxKTex");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("MXvG9on3kk9Wdcf9rCqwEADfSRPopB3ModjKpI3i61joPBEEzT");
        corecontacts.setMiddleName("lWIBl5TZPqAFBhVti3ZesIp57Xq97OkylPnFMIjhPmLAoOwhFL");
        corecontacts.setAge(30);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465380935436l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("exPC6vsSMY");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("5KjSC7y0TvQsLvBpTpBEjNkuSnVb11oKu1GkchSy4AqyObeWVA");
        communicationgroup.setCommGroupName("x0pPmECgzZofcVDo15PwXuhVUo2vQFhtCaLjtubyVwvBZERScp");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("6ivLyxjvhjKFyQJiRU3WEhoi0XBcEMOmoxosU7w5VYS2A8ToHf");
        communicationtype.setCommTypeDescription("G9p6FDnwPdwoiRc3QbuuuqwIUFcMdEMfKGOMW9iIQk4zfFipdJ");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("LPegKJwB2k");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setZipcode("eeT6Z9");
        Country country = new Country();
        country.setCapitalLongitude(8);
        country.setCountryCode1("dWB");
        country.setCountryCode2("ywN");
        country.setCurrencyCode("8uf");
        country.setIsoNumeric(460);
        country.setCapital("cgVVkWGf0X1iaZXsoeokZ7b40JQOeHqD");
        country.setCountryName("8o21kYtXGQKVVBWCXRxECUbLWyWiMsoGQhW2jb44jexuxihscn");
        country.setCountryFlag("GekpAoY6HTERiiVBU7B6Xl3pBtYHQ7sbd4ejygJbqa0x6wVECb");
        country.setCurrencySymbol("0gK80ydwndvSD8jYUBpMRvSHHoEJmrji");
        country.setCapitalLatitude(10);
        country.setCurrencyName("RMYSXpfY8ek0bwTkoLAAW3EaCPczI3EDKKXBMZOkRgQvrMFm11");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("F1GNZZRVd4FTKRRByM3cFcKImFhCotkCXOGyIqSasOHJ6RGQ6u");
        state.setStateCode(2);
        state.setStateCodeChar3("sBrPwvzY72Q4CoTqPrjRoQOl18UOsABY");
        state.setStateCapitalLatitude(11);
        state.setStateCapital("keO8Nj3PvlbnA39anDIhvrpr2GpPRBjC5NJ2QSmLwWMhYW1iLg");
        state.setStateCode(2);
        state.setStateCodeChar3("NYaTLDYfM5Thcgceg6z9gg316gAKBakG");
        state.setStateCapitalLatitude(10);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("ksPNovE4jpSiUxjUeLehWjRkWGg36Ko3");
        state.setStateName("v6GkdMlmQx6dMGBSyqv0NYGucKs9uaNDNufqDKAHEhi5M51qbD");
        state.setStateFlag("0MwDvMpes9D4ZiUIqdCDgIhoU55Ex8TJEDNaTKioz7WkBNIVs4");
        state.setStateDescription("6rHiTPyEjKsvEkzGQTnIFfVXtcopCcTyx2AzS38DyoteDVXyte");
        state.setStateCapitalLongitude(8);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("cIz3JDG1yHdwW26U7Ho3JvPPxymoQnbSPMnZyQxBqdOSq4G5e7");
        addresstype.setAddressTypeDesc("RbQ06nPUllGvGNrtrKOXx3xAJFvuJP2BJc6tHQ1p7VAoO7gH9x");
        addresstype.setAddressTypeIcon("7XOyII6YlIt612B5QQudlRq4jAqPBxpGs0gbuZgFyK2lwQIkXz");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("cKTbwm0aQVYyv62VnzB8bdUa1nnn1jqG");
        city.setCityDescription("lPnjt63BssotH4O18ks6PHs9dJPZaadBqscfom8WjkeHd5Jo7a");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("x7w0xQqoa1OU3jUFZckKxmnkZC5EFGU73TgxJksTABmxkXAnTD");
        city.setCityName("HCsd5tqYKHW6WtIlP5AIkgQPC3i0yhf6i5TeMNERy1bGpN74YW");
        city.setCityLongitude(6);
        city.setCityLatitude(7);
        city.setCityCode(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setZipcode("4veaT6");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("R68jemTsLSNmuDKFtdC45iWgpprugL4GappStUFm3s6syihBGC");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("oPxlipS0KOVspN9qNLIHwal6FGA28FtnWErbR6DrHIbUCfJngb");
        address.setLatitude("TMQnhRBXl6oprYC4lEUpZ2BbBWM8adrUVJ55HgOB4e7RUf4Jgt");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("rFWhflRKGiG");
        address.setAddress3("uh6Ji0uJsCPvcs67pRAIFqgKUP8pzZVo2A1na5MCCpM8klCmCF");
        address.setLongitude("lBwng64D8G1G81ZzLDJ8UfBRXcgy3RstbPBAUuLr1y273DWCC5");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        employeeinfo.setDeptTypeCode((java.lang.String) DepartmentTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        employeeinfo.setReportingOfficer("MCF5d4TVuRtKeqiJ2Oj0KZjM50JflLDoS0FObTu1pBrZiwGdg4");
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
        CostToCompany costtocompany = new CostToCompany();
        costtocompany.setTakeHome(3200.0d);
        costtocompany.setHra(-980.0d);
        costtocompany.setYearValue(2147483647);
        costtocompany.setConvenceAllowance(-6780.0d);
        costtocompany.setPerk(-300.0d);
        costtocompany.setBasic(-0.0d);
        costtocompany.setSpecialAllowance(2400.0d);
        costtocompany.setEducationalAllowance(-5400.0d);
        costtocompany.setTotalCTC(-9800.0d);
        costtocompany.setMedicalAllowance(6400.0d);
        costtocompany.setEmpId((java.lang.String) EmployeeInfoTest._getPrimarykey());
        costtocompany.setEntityValidator(entityValidator);
        return costtocompany;
    }

    @Test
    public void test1Save() {
        try {
            CostToCompany costtocompany = createCostToCompany(true);
            costtocompany.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            costtocompany.isValid();
            costtocompanyRepository.save(costtocompany);
            map.put("CostToCompanyPrimaryKey", costtocompany._getPrimarykey());
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
            org.junit.Assert.assertNotNull(map.get("CostToCompanyPrimaryKey"));
            CostToCompany costtocompany = costtocompanyRepository.findById((java.lang.String) map.get("CostToCompanyPrimaryKey"));
            costtocompany.setTakeHome(3600.0d);
            costtocompany.setHra(8100.0d);
            costtocompany.setYearValue(2147483647);
            costtocompany.setConvenceAllowance(-1730.0d);
            costtocompany.setPerk(1900.0d);
            costtocompany.setBasic(9700.0d);
            costtocompany.setVersionId(1);
            costtocompany.setSpecialAllowance(-5008.0d);
            costtocompany.setEducationalAllowance(-5200.0d);
            costtocompany.setTotalCTC(-3648.0d);
            costtocompany.setMedicalAllowance(-9200.0d);
            costtocompany.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            costtocompanyRepository.update(costtocompany);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CostToCompanyPrimaryKey"));
            costtocompanyRepository.findById((java.lang.String) map.get("CostToCompanyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByempId() {
        try {
            java.util.List<CostToCompany> listofempId = costtocompanyRepository.findByEmpId((java.lang.String) map.get("EmployeeInfoPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("CostToCompanyPrimaryKey"));
            costtocompanyRepository.delete((java.lang.String) map.get("CostToCompanyPrimaryKey")); /* Deleting refrenced data */
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

    private void validateCostToCompany(EntityTestCriteria contraints, CostToCompany costtocompany) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            costtocompany.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            costtocompany.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            costtocompany.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            costtocompanyRepository.save(costtocompany);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "perk", 1.097198799089477E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "totalCTC", 1.7289393463056777E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "takeHome", 1.1251396326672325E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "yearValue", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "basic", 1.751213794578319E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "hra", 1.303092178475747E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "convenceAllowance", 1.0574479899989006E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "medicalAllowance", 1.2187684762649514E19d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "educationalAllowance", 9.44696484641518E18d));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "specialAllowance", 1.2166162844499501E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CostToCompany costtocompany = createCostToCompany(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = costtocompany.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        costtocompany.setPerk(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 2:
                        costtocompany.setTotalCTC(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 3:
                        costtocompany.setTakeHome(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(costtocompany, null);
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 5:
                        costtocompany.setBasic(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 6:
                        costtocompany.setHra(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 7:
                        costtocompany.setConvenceAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 8:
                        costtocompany.setMedicalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 9:
                        costtocompany.setEducationalAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
                        failureCount++;
                        break;
                    case 10:
                        costtocompany.setSpecialAllowance(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCostToCompany(contraints, costtocompany);
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
