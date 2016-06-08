package com.hr.app.server.service.appbasicsetup.usermanagement;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.hr.app.shared.appbasicsetup.usermanagement.Login;
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
import com.hr.app.shared.appbasicsetup.usermanagement.User;
import com.hr.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.hr.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.hr.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.hr.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.hr.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.hr.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.hr.app.shared.appbasicsetup.usermanagement.Question;
import com.hr.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.hr.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("9grkWAuQrXy1YvxQySLj8B8uFD03S3pRey5SEARMgsiHEFqro2");
        corecontacts.setPhoneNumber("9V7TBTfXCekW4srl1pLQ");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380921104l));
        corecontacts.setNativeTitle("x7lmfiw4aN64vqSeSuuN6VcpzOjV37ry3BosNOAnRxfh7m5F8X");
        corecontacts.setEmailId("XEyu4dOAzjnyA7s6sImPEeKtRXzsgYlR67lNE12BsNsGa5Lms0");
        corecontacts.setNativeFirstName("5lw2DCTwQC7PcVev2OZ5ebXlg0LzopOtbez0lPni1e4PEb5yRs");
        corecontacts.setFirstName("EK6rHxDTLmfZcNwuAsF0BokdLoh2sxQ8t7TeSyEWwUSG1QYlhZ");
        corecontacts.setNativeLastName("Z3wIZPoNzLcujD1J6nmB6xDgTCmw2m3v0Id1rMT996iZvv2tpl");
        Title title = new Title();
        title.setTitles("itTJqt1RDgpOl2prdy56QY48HXzSlA43FNz47cIfxAUPzkvPmg");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("SuKf6QjA1x96lldrkix2iQ2BPlOTUYGZ2JlTjvNZNxUpiJoDxB");
        timezone.setUtcdifference(6);
        timezone.setTimeZoneLabel("OyuCFsfsXVL5qvduso03vENbc9EBgScIT2Q13UqWvEktqSIlMV");
        timezone.setGmtLabel("aEodRJFmLYbFmjoKEVPFO73hCsWlUDaEBXtZWjSAtuwdh0tZfV");
        timezone.setCities("zJGgQoUsp3LpldZC4rmb5suWDGhkPpFyDVK7lc7NXhAetWWWhk");
        Language language = new Language();
        language.setAlpha3("nBx");
        language.setAlpha4("qXyB");
        language.setLanguageType("T9s0mYh9P5thi0iN2Sd0XwzG1Dt1EfuC");
        language.setAlpha2("lq");
        language.setLanguage("dGTZgNgilrx0g3i7QLwRb7OsPmc4u4ZCeuXEVNVRPJy5ZVgPug");
        language.setAlpha4parentid(4);
        language.setLanguageIcon("YcsQgu0gcEWpqw1jsqBNm1Mg3WZx23gf9bA3QYDDbgR6AlF8rQ");
        language.setLanguageDescription("4Z2fvUxrY9wksHrAu0xRuSDxcQq3Bq4IILGJ760iCLUI3KyCfR");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("IhWggC33FCt6M2BePvsw8YjbG2avotf9JyzDGG4pEVavhUDlkG");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("uKB2xxYn4pOGAEcbhymCLMI2Es1QwTEGwPxDkooPJVhmwWhM3M");
        corecontacts.setPhoneNumber("C1mleKeqtuiGqUVYXxJM");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465380921124l));
        corecontacts.setNativeTitle("jeqkkrHdf7Ss4GHXb4iTjYCXQGZX2n8kKwtLIHIw00H2nggbI1");
        corecontacts.setEmailId("aCY9dlAzvZDCM2QZ1usK570fbtLo82Wef6a0t5LWROzEp5FccD");
        corecontacts.setNativeFirstName("vMjVyqlXQ6HVC1oyVlMgLikmVbcm6btPfvsZfNYsDw7bAFS16A");
        corecontacts.setFirstName("IJJx7RfTTEyC58odQY32TO8UGqS7sFG5xaWMRBN1mMp87Zuurp");
        corecontacts.setNativeLastName("SgsIVlx3diRtXEN2s6JZLHlMTiQnSTk2kD8Yn87DIPz1JGc2S5");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("5fUThVWSLwWPovD1nuYqO5BGoNoZLizQ1xlIDhj0YMWGjTLTwt");
        corecontacts.setMiddleName("vWbEoEOzkq8jT7cKp3n1egZC3GUV8YELI5EwMWR0BtocBgehLL");
        corecontacts.setAge(7);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465380921236l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("zJJ4ktCRj9");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("BVyhL0811YA3dVXmeBzfXpQK6Ttjox7S64bGsSWEqyy0WYjyNj");
        communicationgroup.setCommGroupName("cyN9iO7csXSETzSeSNKZhV4qqnN5zzd5prJbS30orf91XhHK9R");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("q414FUDXJCRzMRUVomgY9veUN6lgATsAGTTBD1mFe1xxqckglR");
        communicationtype.setCommTypeDescription("STKiCW8Q8GfD7on3KXcpc7LP9xoMNyYNT4G3mFn9RU45OQKkAx");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("65lBbqON1a");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setZipcode("NcBOrZ");
        Country country = new Country();
        country.setCapitalLongitude(10);
        country.setCountryCode1("Kfz");
        country.setCountryCode2("9yV");
        country.setCurrencyCode("Z0d");
        country.setIsoNumeric(266);
        country.setCapital("EKx09uBEnQD9Y2BuVum3yGNghy9Bi2yJ");
        country.setCountryName("jMRQ8vHbQaz0BX4PY3gQRfGLVQ9pKWG2h1xb4oMjpZQUL4JRqn");
        country.setCountryFlag("sqvcLByvzXdsko7HbKsepWfrT3mJ9QYys3O38CDeu2fyWC2FhC");
        country.setCurrencySymbol("YfjtkzkJgBXzD8iWmIKAzHqkCVXApl4A");
        country.setCapitalLatitude(10);
        country.setCurrencyName("AA1owWRFPiyd22uvJS00GOzL2mEdrWC3kL1teiWxTPqZxgWOVC");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("nsMg4wuxhFSZqlgf6w9X9J8IF1qckyLokPyGwQ7lEosHFuVqxv");
        state.setStateCode(2);
        state.setStateCodeChar3("FqfOqNJB2rw0O3F6YraAHkGfkpFy7oeK");
        state.setStateCapitalLatitude(9);
        state.setStateCapital("qMwvHoJecqwFla6XjGJbLgNnINsiocw6rBZtPEfChvRiwbHAzb");
        state.setStateCode(1);
        state.setStateCodeChar3("B0ZdigStlTkQwwT1AFUaa1kuL0F7L9wo");
        state.setStateCapitalLatitude(1);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("jkYK1kXUbJYiFZIfoNTVI7mWpga9SqoU");
        state.setStateName("7KxmuM5sI1tns7MpO6TGG3RVjnCQRcNlb20YvU4nDwzFgxPpu3");
        state.setStateFlag("NjUNEdwboJOHLjTjvlslh2K3B8EXrLSt58H2yxMZjSNt7vG01P");
        state.setStateDescription("YQAXfX2iPc8BebZT2LC3o8BaZDjLVaXTzdDaQigwTcG2i7vIAL");
        state.setStateCapitalLongitude(7);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("LefYH7rXRGoJRCp255Mwb0WB5TgeFc7cYto5oHzFFX6AbPgCIH");
        addresstype.setAddressTypeDesc("M6bPJJFtqpZv5fXzwDm4IZYQYMVMxw85IxYraGLrHBVgIsS7XD");
        addresstype.setAddressTypeIcon("zjP7S9ClkLdcpkeYfDT7L4IbNAKhtxicfVoifN5mRz4avrPVF6");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("H7soUNmYIflDCzsT7gRu6vsTus6BQ4Bj");
        city.setCityDescription("BbJcvEA3GS5atLy7ybP9jNqjpFVnsQcoScQmWgPHv9mwqm1o7h");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("9Fuj7WHGx4jPaaw8q01WnOr38QmoxRigY8ekZEsiRFYUdf4n0G");
        city.setCityName("wKwNQy0IdyPS1cVRxP2c5TCzo2ejx3DvnGi7c5tk8WOzg9Q69D");
        city.setCityLongitude(10);
        city.setCityLatitude(3);
        city.setCityCode(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setZipcode("MvNSoc");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("1iZz0BGJHu1NYOiY9DPD8P6upXt0hWgEDCsKV2stAtoTOJ6YMr");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("ZdZPwGsWhFZAJf5iUQETznlOOPYWYPNRu6f7qQQqbrDFQQpYvD");
        address.setLatitude("RzeAxvKEehHO7NUS4Yk3qwMoFoAvjBkXS90VijwtetdKzkAxGB");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("LjgfCBdt5Li");
        address.setAddress3("k5oPQW67HGLV8HOVMVcjC6jHA6lSTkSxzFBNIxr2egMgwEmkwI");
        address.setLongitude("eGr5LJEmv3WEL7G30O3JfJ805Pg7N2Y5NWTkSW6rDYhntBm0gk");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        User user = new User();
        user.setSessionTimeout(1845);
        user.setUserAccessCode(32369);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465380921564l));
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("T9rFpO4kr6Fh2YeQ3RVZRLrFrOZ3LdeSPIv3ceojX94LDqtlJ9");
        useraccesslevel.setLevelHelp("FPBUFipwLvyJMZGLUINvGUMtp13SaMBgLbcWOSlxMfpKMtnXZN");
        useraccesslevel.setLevelIcon("jmAYygx0TGgXvy6mo6uDBdaEh7s84IILb4U7ovy3sPDsLoduH1");
        useraccesslevel.setLevelName("twc7Rk6YPpZlHI29ch6cR2D6pf35j7CqZuiTOhNybNVslLdiot");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("dECsxWIBzuudlb7aIg8fa69LF1496fT6ZdR1eeFQ5sM8POsv2m");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("86C6avdUGaplVXiQ3h7cOz5GK1MbLm2rTgMOMIUaFooIu8fl39");
        useraccessdomain.setDomainIcon("O87tkvYVluKP5OyIK26sopjVNP8nO55whNAkprlh6vlmYrWug4");
        useraccessdomain.setDomainDescription("roidEltFLqg53RnvmF85BZIxy2ng57dSfObhIZQr7BoF19aL3V");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setSessionTimeout(2923);
        user.setUserAccessCode(57641);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465380921584l));
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordAlgo("0ClwngeTbDWgmOEBlKlobPHI6JF5AjHhv2meaG2MWT9Wga9fuB");
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465380921646l));
        user.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("jgiB7aiSJGub1iqME22dMgjs3vYb9QZkPew25OokjUkTid4Omu");
        Question question = new Question();
        question.setQuestionDetails("Tp1gjJK0Jq");
        question.setQuestion("1GcTfQdjIrLOUha7V13SCR4TnVxeeXXmUYq5TAJ7zPL4iVeFuR");
        question.setQuestionIcon("yv0VWmMd13Fb4i9cgSPJ1mCuqAiueM4cBEPZL5Q7nxDuHlvfXN");
        question.setLevelid(1);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("xyTBhZk4C6A6BMCRTBuJzWmTHhoZYSvsmp5BOFhHZzVhXx4AzA");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setPassword("fGdIrc1EAhSq7eZCPIlvnFIdLccdBF8zacFDOIaIEqQZXobtoM");
        userdata.setOneTimePassword("tKb6Fl5CJvthnOZS1yVmWJosKyxHiDOY");
        userdata.setPassword("taAAV7IPrlKV7ejVygjDTudFabt7eDDAy6rnLWS26f7ozEGYE5");
        userdata.setOneTimePassword("CDdxpuzGvbM2pjKWNZLwTmgwHkKxQqB1");
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(7);
        userdata.setLast5Passwords("GIMUxHhwcXSefpV0EQUS4xbHUyx4LZNbC08EDzY60FetzfDtZm");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465380921861l));
        user.setUserData(userdata);
        Login login = new Login();
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthImage("gBiotUvy9vMe4kUNb8WnkTAzcthVM8TZ");
        login.setFailedLoginAttempts(2);
        login.setLoginId("57Xd3s5uxhTQ5nQvuKtKy6ZZshh4YMDWA88EeqGoECGYf3GkPR");
        login.setServerAuthText("Myqqm1ppzgiKlZ1c");
        login.setIsAuthenticated(true);
        user.setUserId(null);
        login.setUser(user);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("Pbo1hwo70DQHAZsz6cwLugpJamBSPNBT");
            login.setFailedLoginAttempts(7);
            login.setLoginId("FxhRajc3o8djvLlDIPpYhkM4ScgZMarZR7EuK9EZ6aAHEKFGLj");
            login.setVersionId(1);
            login.setServerAuthText("iNuZt2DqFfgkrC5n");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "C9S4cihqIrbtpoasepJUiahObRBhvygS3A6KnCePMaEkiD3HODuL865iqG5TLddqaa4QH3iL3oxljOdR5E9GZBMjtfiJKFrQi4MbOmNktsdH2J43PNCQv7D0nfpdcbv7KXAFK6I1nJmu5RKlnLu9yrft8q8ZZ73ZzJxkTLo96iwobUjfatjjxpUK9AjDE46b9QZc6pfeM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "29MCrN2clVdNPDBFuUmpIxd2HQuSSCo2e"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "pVasFf0g0jjFhAw0T"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
