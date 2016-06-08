package com.hr.app.server.service.organization.contactmanagement;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.hr.app.shared.organization.contactmanagement.CoreContacts;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("BU40wueFmZ4lVHtVNnGRNrTPUV5FAnji1loZAEOg72g0i4oAcJ");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("rUxf28Xo36MMxG8pVwNFSjsPuDlrMVUC3HYtIBh9p3seWGDcwi");
        timezone.setUtcdifference(1);
        timezone.setTimeZoneLabel("vSGinBA5BDAUbUESHhiaprKAWikll9eYwxJBYkYuInKmJDrE40");
        timezone.setGmtLabel("WvACmmiPUOsERmam28ClAFUy2ZrjQmdjZfyKdTMUul2N11gVpk");
        timezone.setCities("DSCGktSaYK44D6GaJCYl88zzIRxASAcxDELevaXjArZLloW2l4");
        Language language = new Language();
        language.setAlpha3("o27");
        language.setAlpha4("ofTU");
        language.setLanguageType("4VddhYvnw9R3pGkRGmbNTTZpQPxcQfiU");
        language.setAlpha2("gs");
        language.setLanguage("54SlQed3P734ywpFkAeNTGFMIsHYmRuXynFZ8UD7GQR1zr7VWw");
        language.setAlpha4parentid(11);
        language.setLanguageIcon("qBYKXqDjrjUkIIQ4kZdHUmNicY8QsLPyYKshWh1z9nQPG8iE3B");
        language.setLanguageDescription("UPAoI8bOE6Sx65F6uO3G3ysYYwciNmiAz92ioOM4IQE1xttXOW");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("Q1bn6OiXS4nZ3Zu6IPp6qGvoaRBhtWglXYVVwei1tcIfoZxgGj");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("cnzzNzdMpu7KcECQ4dRmrEG21oJpK0ZEBwAENwdSsNKVNbpETk");
        corecontacts.setPhoneNumber("URvWdnL3okB5dFSJayjU");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380902334l));
        corecontacts.setNativeTitle("4rRC2yHmVg7BVbIiI0ULHQsptv0z2UpDjCnm40VBm2q2Vkvh4G");
        corecontacts.setEmailId("B03dh4RZ0j9u38qSjwxnsJmhyQS4u9vAjpkuympiI3CCeByTrr");
        corecontacts.setNativeFirstName("sM2c8XAMjNTUYzlXyj2Z6aqI1QHU9rvX9opBF1NEOxJ2l6NS6m");
        corecontacts.setFirstName("ub4EmJ03ep3lAKR2yst0wP6NqXtyb9tM23QyMmtd5tHtl5goR9");
        corecontacts.setNativeLastName("jGvwDNsgoKE501gm2C2OAk5wpECTp3KePWiqAZaQEihYg09Mxk");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("mtV4LMbZnyfNVH9Ntcvu94oG8UHNO8lGIGEgukd657iuQlAlOI");
        corecontacts.setMiddleName("hPZGk6xJUNGIhJQ0zC2mfJJnEXUDCSWfOrgH32MMVNqfKujUwy");
        corecontacts.setAge(112);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465380902461l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("tKO47YwJp9");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("H5s8ui6vIpfpHAatjL8JZlQQc4gKI6wkjzxug5m2igjGrgjS7b");
        communicationgroup.setCommGroupName("7ZG8pkvbiF9CtxIjK5Ht8rzXsiinSCXKsfNvWvXLnyEZxNIoRo");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("ZYfNZg1j8ZD4z5SNFqP1CMkBRrnVpP4HOkhrmVcxmLrPVDhQt8");
        communicationtype.setCommTypeDescription("amnQZMxLzIgw6p4zjiCY5ErIpQkEkm4y5ZAiuBaQctN9a7Qrc9");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("kSvxl3mRjw");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setZipcode("dxJTj0");
        Country country = new Country();
        country.setCapitalLongitude(1);
        country.setCountryCode1("Xms");
        country.setCountryCode2("SIy");
        country.setCurrencyCode("yah");
        country.setIsoNumeric(396);
        country.setCapital("DJaKkVxDOlREUKFVQ6mVAzSmz3FMwfHR");
        country.setCountryName("yIllMxKTVwp9Ey4qZRAB4fD2xc9O6XscRop6IsawWsLMu7h1lo");
        country.setCountryFlag("DsBQRIT8rCyVJX0cNLCIhRa624lOj1uUW1IeK62a6NIj311hK5");
        country.setCurrencySymbol("i8sWE28KyhSxv47aR41Hv1ZgE7MziN9E");
        country.setCapitalLatitude(1);
        country.setCurrencyName("NTxquTf0mQ3VFoJTGPk7HqNzGqnCKK2a9OB0dM4J98mqgV8AvJ");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("fJ0g5ZABGer1OyDTV7AN2fuuKaRZxDNcYzUxSJQnXHBykB2iIX");
        state.setStateCode(1);
        state.setStateCodeChar3("cO35BHDgkLmu9W8NUketX5wvAtxOl49E");
        state.setStateCapitalLatitude(7);
        state.setStateCapital("YepLgtwLw96olMCld21vMWXW1PLRQYJ4eeq9cgl2elqwbXcjC6");
        state.setStateCode(1);
        state.setStateCodeChar3("MGRpsFp5ksI0ws1St4d3IUEtDW8kodyN");
        state.setStateCapitalLatitude(8);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("ZQIvKIhSJwrwRLJMFA72jtPk9wMPvaio");
        state.setStateName("4Pk3Uf0eYP149P7logdDqWLCkApTUpILwMZy3pNYZB7dUFjz0M");
        state.setStateFlag("6pLGJPdyUjcDS9ahScRVRAKZhp5wKITuQLbxynK9zljMfuu65S");
        state.setStateDescription("C4OvSJQdOfpCkCUdn6F8ZnfWtfra6ogZdHV58hUkVLvJiOnISa");
        state.setStateCapitalLongitude(7);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("vgxdAvrNvQsomPo8iUAHyqwV7Iv5fhZhkNbvWr9c365akFT7UL");
        addresstype.setAddressTypeDesc("VKfxgJwgOFQeI6tnOnFiufaYBRwapGuUHgpx3cYIj4xBdkMC0B");
        addresstype.setAddressTypeIcon("Srbp6lBvLn6Jw62PuJ3gOyFYmNsSZr87QwSG3F50k4u3kMCO8r");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("mXcVnSm4Xf8zzoeWW83qtkvglBKDAfs0");
        city.setCityDescription("rWRF6ZAm0yF1Jv5YDmUVw4ZWNr7OiDyESXrilFpo8XLkog0cRU");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("MDd8qlxwoAHXsULZyAOx3t4QeFiMGrJb3rYSOf4l5xtWlnCqGI");
        city.setCityName("9SknnVIqzKraUOAJQs7RuS6yBEQp1ZAUOFMDBJBQNZkobhjVli");
        city.setCityLongitude(11);
        city.setCityLatitude(2);
        city.setCityCode(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setZipcode("traYAS");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("qjXVpvqiLtn2P10D00Ww9PyvtBFizpdTp18qjCKfiHoSBRdU6B");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("dnhoCATJsTD3dUcsKlCqG1NElDnxjbUyJYqvxlgiklDTFQoimR");
        address.setLatitude("HOc4FSmQHgxtVaQ47MaMEF6HNwGTQiv4fZGS8yTN5Jf9h4TK1v");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("3HYO0KUAN2o");
        address.setAddress3("DdAkwNQf5w8WFY7KSIWs427yMspTARalCywpf5pco3VIIgOpNi");
        address.setLongitude("5qQUhycNke6aF9MdxKLCoctQ79E2YwSl0JvzHYgafRBIJVJa26");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeMiddleName("tIkf0fhbVNtlqLh2YI3lu5rzwSCxc6460vRQXZ077sGEOLxT7F");
            corecontacts.setPhoneNumber("NR4PdOLpQMUkI3rvjmnw");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465380902802l));
            corecontacts.setNativeTitle("xQfIHcrvk4VK9jbawDGYJivgxRB8iq1wsKI8lXPZt7fEZSkYdL");
            corecontacts.setEmailId("I8bJN3Mz6FnE5PCMGaTi6f14Xf2lyzj4xYKArNZyXSs1XTKe5v");
            corecontacts.setNativeFirstName("gY3jYekG82O1Tiiuj2n6c8w4RB4UjzW62LWzdd3wjzC8ZHsjCD");
            corecontacts.setFirstName("XoCGyLQOYmqMRY8tV9MIrUrSkcoFvXPzv1kko0jbd84Ja2wCtN");
            corecontacts.setNativeLastName("FOmHqcO3nA6JMLfJ201XUekYyVpx85edd1to5KBhRpKVpCby34");
            corecontacts.setLastName("KpBon1K0lY71y3bzDuLwDr3PwYlerUUa7EJK4hTGhbOEpxhpwp");
            corecontacts.setMiddleName("EmybVQTzjt0vQKMd2yCkG7ir1QafCapEbXzGyGrVXE1Og3ajIU");
            corecontacts.setAge(125);
            corecontacts.setVersionId(1);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465380902929l));
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
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
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "vtftYKnAcxDAPlFOutcdE7Cn5YNiOvt07Q1k6BUxIVJQ3tqZhCztFEV2RF9KZw3Xh6MW70jb0irnNwr23CLwFRKCyiblEv9TVSqFNS0YFkAh7TVVSgc5XZManH8bNvhgTkUHpn4Yww6dKGWTsOBeoPeiflXl5NN6PeJboqw6z9UjJ48tMmeCceeGP2Z1xeb37aT3V7yDkpFFlHtPQWCutPup3VKiVW8JWUMuwrQFsn6LTKRdAAuEa3OfzWnlrz6hT"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Mg1FjJoBsy3V2Vn4WexJhGD25xOqws16c8M4KwfdA3NieZMhk5IKxdPKCJ021jVjS7Xj1BvaBgMxdlFAqCbF4Xp8NiZ5PKi6mbD7zlA4bhgScfwAKsA5hesWMmTLjc8RlEdZVTuEwc6mi0w7XuC99oa4Pida9lnZovDbC7d4mJFUueVoyLMQctUA0OA0SqnEpGC8pGg01gLiH6VqR2OYXM1kD0PN41AAzF88s0YzYeVjzNZz9Z8Cqb97SEGNKgsKQ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "dqMggjL21cczqP9eThPuYYamY9Oj918QwhnV1L772yhdoOEYZ2BqPqBfHG2INgn6fr26kDHYWdxEFOeK4nlJMSsOl1Nq40P2gbCxTfNDdAbCadiqLPSeUbFdJS2ilySBQPDFxdyVcUI0LCkhYTElprS78ShvZRvdEnKkowYsklcNuEzlGRYqTkiyChYBf6ouy4zzOPHvSgFaxl3AQXDIw2tZ44QAa3H2BLzOHMJChlHJTXmYkAGPE60rVEWZyvIvu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "RH6vzgtIoTg8FrxFB5zgrR2Clugcafp0wRKntzKiVHeylKdB2U3ItKACqnJampuuA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "GXlwVpvC9o7mvpFWlLVrcZcxKnY2t1HE1Q5hAwE3O9LamLzWFmyXznlUMulQSWk9Z6JbAngvVqd5cyoIOwezxihDevd1pCV2Qi7ermHnlLr0n2tuMVcwWWNm0l1pXVR5H60ivPW8fuGSBNkzQMI2w6uzsDaEvhh2vPkNbSFWLU3Vbeocj7niKhZ3xCYegJl6WVnQrZGNrYzDJDWzmCJCBXzlGxJFGRUWztX7Bfq4etvX37XXTuO4fTuW2hkIbawgz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "LSYuyMbdvL1yjYLZcrtyQNKGnPmWLYa4MyHGFMLhFk1CNvhznGcUC9dwYI2ZEO5D8UyGCaxZWOkN1BFZssjr0rG9bPuMj6OfQu1a66LJZbFoNF6KHYW04Ovid0XaSFXIqCV6n9660IYPTYLT9qooMYxT9jYBPfnl5000pCYZvaBSXtypKA182enbIOaXMaFrmmoSyDkcDxx591s2r1OEKvTEaQUnNDDFwviDpMaWq4idFDD5qT1Swmq7s7tpS35L8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "xcOvINoktaUfP9fscaaqIwjQhKjTSgrZCWRkL2ClIHAvWsyrC2BuH7Gz3Ysn63HOkDHKBBJFOjgtzY6ERIwSW8Ij7qSZUCAGpYXuKbkd1o00kD4blOa0k29b9EPtocAATEiv7SXX3W7aOHOxns7woGlYfb8sOMz4lQ8FaS75qxzCpqIFZfZAN8gp71TObSEN3c9fw0laS78WrsMKVFqe2yTNLiOLZ8upvQJSaxXUDIAPf2uRtESHjKoJfJ1avPoPO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 145));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "m9gxuWqvErZoau7B1APvoNQrvTdr43fLmK83NhaB4CMGXSXeHFflMXpiAkXyhlsQ9p0ZSCrps7KQGsGwK8NyhbRb6e6FXMmQJYXdBu1981hBJIVn4de7TlxFWs06W3FnyW7TYqJ6VwfsOzn7G0aAfoQwao7YNcIjPuPPHZaozkc0q81gA1VgOjGr6e5CZl5PBaxx5Pjg4u8WEFzSQ55E6bye9JebeKSrsKv4iu7hEyBKO5UgHxcNOWMDGdiM6QEsi"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "e7ahsHfj6dDXKcKWYC0s5"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
