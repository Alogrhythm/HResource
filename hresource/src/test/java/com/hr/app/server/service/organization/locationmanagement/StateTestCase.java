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
import com.hr.app.server.repository.organization.locationmanagement.StateRepository;
import com.hr.app.shared.organization.locationmanagement.State;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(1);
        country.setCountryCode1("wCH");
        country.setCountryCode2("ZHD");
        country.setCurrencyCode("Yes");
        country.setIsoNumeric(490);
        country.setCapital("vyd4ggGbsXdgfQFbJQTvbE76BdlrYCsn");
        country.setCountryName("WGswcBMsUoqlxy1LnCMYNyRzzHgheBU8gtmjvkL7QmcgimzvCQ");
        country.setCountryFlag("PuS56aP1NY244DhQeVaXCRxoQDTx3KC9n44QC4FbNfodyxopTT");
        country.setCurrencySymbol("irn4Ev8L8wsUmsWmIJFawJHGigLHYFkm");
        country.setCapitalLatitude(1);
        country.setCurrencyName("18teVvJFJqoQ9qJygQvuHZkYFcxSbXGCtQgPRiMz2LYOVFRK8i");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("ifWt13DIwTKjChIfjO0SXl3Xxze6smo3wPabUY2IUyJJHl00xJ");
        state.setStateCode(2);
        state.setStateCodeChar3("2FDHgA3FKWgiu13nCASCxM46aHAJxIBV");
        state.setStateCapitalLatitude(5);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar2("obSVzpYrJLHiKPtK4RJ9IEPFgkBHFopj");
        state.setStateName("0EPleRHXQQvcZEjKF3D58Z4c4GeMUDf7VKVHAAyWo48nDLl8fF");
        state.setStateFlag("XpxAPicECLfhcVfyHCfNevRKEl5zsjSwIs1cCGIJ3EoJYXISvs");
        state.setStateDescription("2iDrl9FBz4ppojNysKpnQvOXhODDkdMvlaFlYs4ZwUG6mEGawI");
        state.setStateCapitalLongitude(7);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapital("ID4Rvt0H9b3uRIteT7sOMpejxZeq9d9wZQ6y7JDTVcXGeci38K");
            state.setStateCode(1);
            state.setVersionId(1);
            state.setStateCodeChar3("BVRQiUfmD9pXish6qt3kbMHEp0fXjs2F");
            state.setStateCapitalLatitude(7);
            state.setStateCodeChar2("5uELMwOPRptdIGWdir95ZZL1DGpu6Vtw");
            state.setStateName("UQmdyh6OoMU24L0MWo8wKSQcEL9SQXl7XnrpbZ8aFouYHhqTKR");
            state.setStateFlag("AJqNYz7oPEIGBQPkmrjAhwuYF8kIFoWJKH8JDOwWoPpoWlgnTw");
            state.setStateDescription("CuD3GOynrap45rflyAW4iETl4oWRrv2smEofALqNBAIZ9gFyjF");
            state.setStateCapitalLongitude(11);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "qxaGigSoPDmMzEByf54gND9ig4p8eBdR9nSnBt2BGDGEMJAGKiY2zmuQInCo32LnFL0vlW0kkqP0pZJC4UcOSiOW7MiTDCtsb74k5Fdu5VO0fxO6EVcO4KhSnBgyoihuw1Sy58dUX7XnfwI7LakNHDADMqBUpQMKgp2ZCA96IqKV19QTVk6iqRbIXW1n7FB2lAI0GIyBDIT3pixkUwkEepYVSkEoAeZgUUIRZzJflzoQ1Q9g4XiEkohp9EQOwjLy7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "xsh5bsEQYRaZcrlx2VKMNm6aqp24fZxuU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "LzAxg42geeHS0cEeEVyYMDReH1tZdPCRY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "RQuwXGK5loV7779TuW21s2IedtzkrqELOq8h2UUc2GEu8HJhILOZUWFFZLiT4wOFgPqivoxVC63F3mpEeUIXIxPxUr1xDSnmTcjoes8PbPPVICt4owNtQjz6nOYG7WNs4npmraPKUltl3OA4FhAsaovxRzOxIZlyO8bfiMvrzJ3OJpE2lHb2n9JEHMs6TuPGPe6QOgX4GOoNE5h3MqbYnZB62saiYV7LDe06PCUc7SZ0SAnz4gJWe45B4F1JrnUN2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "GAKPmGrMTF727K2vTJnip90m7gkM8sU0kdbuMjo2ojJH0UQkqKc1wtVRvQaiqXYdJPW4gCMZOJ75fdDPdTi7TsAJodgZ8EbA2lpjt174DNGBfKPldRUrPXnhqR3XofBrF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "0jQvalEXY49lCojtGRH23KFCa2SQgVnF0ipkiwPHls17nTXJNi2pl3VnzuQRTZiz4pafHSQqVYjTdswQzwtCBuhjKeuy3EWLlGKvj9XKe8EK5YSko2tfXJPDJ5rJw0jhw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 18));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
