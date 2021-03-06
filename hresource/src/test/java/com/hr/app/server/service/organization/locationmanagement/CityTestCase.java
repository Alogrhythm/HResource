package com.hr.app.server.service.organization.locationmanagement;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.organization.locationmanagement.CityRepository;
import com.hr.app.shared.organization.locationmanagement.City;
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
import com.hr.app.shared.organization.locationmanagement.Country;
import com.hr.app.server.repository.organization.locationmanagement.CountryRepository;
import com.hr.app.shared.organization.locationmanagement.State;
import com.hr.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(9);
        country.setCountryCode1("lyP");
        country.setCountryCode2("8DT");
        country.setCurrencyCode("5or");
        country.setIsoNumeric(730);
        country.setCapital("hVXTbgFiAx2gyVTEqfj5AcE7ZOIYSTXE");
        country.setCountryName("8AMwNfv0SeX6eBwHcAyEyGdGaESarJvRrJJj9ZUxfa3q7cgoif");
        country.setCountryFlag("1vjtxPPsQcLxD5prhdHSV7oeH8vYYcqTi48zKLyXGBCPwk7bEd");
        country.setCurrencySymbol("f6tpI24QENSwv9Ksv7pTE8mo05kcUTfN");
        country.setCapitalLatitude(4);
        country.setCurrencyName("cNN66vlsqtCeunZi874DiBoIAnc207kf3WnCriAct4NG8MBp7k");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("PRlpByaNoRa364JWiX6EjQUuHclp1L9d5zHZ57ZCF6ytoLxXAH");
        state.setStateCode(1);
        state.setStateCodeChar3("gJGQnyERyCdUiSXw7ck4LDX8QsWK2rsz");
        state.setStateCapitalLatitude(4);
        state.setStateCapital("yw6yoTXDOeHPOswiKRMppGSt0uftm7T2K8bLLiiDvQpe2ZUXvO");
        state.setStateCode(2);
        state.setStateCodeChar3("enNDgwoPPsKTQZeCqb3QgbZg3pdl0MnP");
        state.setStateCapitalLatitude(1);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("NQplSRuf8JYQpt4bW1Pfs1t23oyuAuHM");
        state.setStateName("grnrfPSbY81BALHKBiTCU4jSHrg4zI3NPseX2V1xBfqW3yw0PA");
        state.setStateFlag("3WKlZDUkZLOAQoBZVDR8P7cW2TLnTCKfsZDXLEIZOet3AOf6G7");
        state.setStateDescription("XaG8uOM5azdxAPGLhNjVvZax5LvrDVHTZEPcvAf52NoLsAQbIK");
        state.setStateCapitalLongitude(9);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("TRlcN8w9K7WwTEudGE7YQwsh4hQ2gylq");
        city.setCityDescription("0YRGfjG5hjH7Ev1Xp0Wd1Em5V2igQMaAiFdlsotSZT9N5rqzPM");
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setCityFlag("zr0ngPlqdj1QFzVd5ZmApnS4BzB5Zy8R7mrjn7UFoig1ua26YD");
        city.setCityName("KJR2xL4xMpfu5wog2YO5ogJ7wWfl9rn4QVcvUdUPfyjCXRWC4a");
        city.setCityLongitude(9);
        city.setCityLatitude(8);
        city.setCityCode(3);
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCodeChar2("bPyHwzBGzCkeuEaRomJpOkMQ2759HsTM");
            city.setCityDescription("XmVJJIpFAvU20pO1tB7d6RHTjPWgyn1o1FrroXgCRnoM15sImp");
            city.setVersionId(1);
            city.setCityFlag("7CuINoNeGVzvT5pNDkXsF5ZNErMYHQtlzuVXbtwXEsQ0DTkSUx");
            city.setCityName("VFfBnKjywQQflBWVLLWZjjWitPZwWI0h0HtaKwYZXZEXXmB0Y4");
            city.setCityLongitude(11);
            city.setCityLatitude(10);
            city.setCityCode(1);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "YyeCj3WXwuW6kdsCVNDZwC3mXOneXREoxfVaoen2iZEdNjzDT5Y2NqThOaAmU2z4LsiuHAag3HilWKzFvqIUeql5wB7n8MzRvNp5zMsu4kNGx6s2mrFvg5vmpflfwwLRD6fxDjtQLALsssrU1bfzp3lk7sjcBbdMnZ8HIWjxe5bbZQWbNIOzEp90im6jgmCI8yn2ryQLtN3RpDROE45gZjLkGjNz42NIa7xa2wYoUtRBQ3vR8U6BZFp7geOy5iRVj"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "8fWrhQPm5eq0xlkdfjOVayF6Ne1SwiXXf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "8wJQWluUWqiF3pOx2vy8Q0P76o0f6hpKwmXMc4uAa7BaOGlmnBlvD5BEao5zpl1S2M2zQ60octEzoXuezD6EdXSlzoMdzDjjBsf2SkKwvGAZDBAs4XwAmuRQNQ0eqKiiI"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "ac3jWevOgdbUUdOzrbQ3gSaSn1C4Y4xCLU9dOdsRyzwJEdnFlM7yKpS358kpk4zTGFCcvKHpQNFLy7753qUHkYC9Io8gwswUQw5yOfqzTC4Hidd4bh0GHSYuipEdUmxSz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 19));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
