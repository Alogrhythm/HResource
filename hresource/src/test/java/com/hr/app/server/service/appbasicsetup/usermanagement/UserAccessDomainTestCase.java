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
import com.hr.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.hr.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("Pmiz4DQiToOTfHCpHK8uifkftXZRrPI30jYn0rXJ4d239770Q3");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("EvDpKzJkLnzVAuLqOlzmdxT0qWk7Ym7fFI5g16rA7lwJ6MbtuC");
        useraccessdomain.setDomainIcon("YvuIEXbKq84mgtMVjixW7ilxJkS6gcahyfttqI2hyyWPPUuVZE");
        useraccessdomain.setDomainDescription("V0Qp7dXEFOmeCSfK1txgZXNpwyszAN6Gn20f8rpC2H4hMBLTRI");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainName("HlFecahc0PMX9Nvnhml3BI71sN1eGSOCYNbKgaWqRW6sg8fbEf");
            useraccessdomain.setUserAccessDomain(23749);
            useraccessdomain.setDomainHelp("tLEnlNRLxqvkmE1fsrt9jOKm2nkTswPM0vjStMx0Sn5Z9DSMGf");
            useraccessdomain.setDomainIcon("SgpEmDbEhhFTMjybavjXervucQo67SDTabl1ojJKnS1A3XZ8FP");
            useraccessdomain.setDomainDescription("wxGvEDkgctQlLhOldtVaAJ0MsFsdpa9mXZkwkts9qvrqhSVbaR");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 102534));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "3wHnORhyBfZqS4WK4yXe4iKQLcwE8td1tkuUKJjXGpO47cKXLXVap9BxgW7IGLMgp4jKQOZx2W5zV5gNPvbzkkmKqL8CtccstAElXNJaDZQXEcYJCTtBP0pW7eaUY1K0d9apEBlyJgH3ndPYdt9m5c7yxjcf4Cvr0oe4M5tK0NEyAkaqyBXkl9Jz268bN44iMXJO6z39F3U49S2uhERn0vO8ovunSXBabc3tEHYiCgK5EsyOYZMr1w2ECS4Ph6qi5"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "frBfdJN5alzLF2VviNeXrc8ypM98iMXa9EEQaK5ikfefLWzIBNW7rZ54eFVDp6yNZiKibUfVNS4S6l35he69njmlq7NGQrk6LPfQRR4zru7bbwKZsoU61XEmdpqaNLUkB4A4pOQR0UmBUkvSkf9ljyAzVH2V29fakCfZD6TLm4gv6egqG9RYoOGPP5kejMJBOYj190IK28hgG4HWZCjoBKxemacIaOZA1tLyXJcnFFEkWqPXlmzFslxTAgE6VUxDZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "wZik9cGnrs8nzNdyePNJbfCJBxpHTmzIm8V72r2GqrhFZFXvcF5RlvqvxLJvAwyWFh0z36kTvQOpwNtEKY0DGe43XhfvBkMPt8JWZEWnuV6ygAriLLKfoJW4bsQ3xfnF1lFQTlOQ94p4eHag7YHYon6CnKL5lgRgxGVMAdyaXlSjFbKHGbzRV47baQlVXYdOudn7up0fWMcMcsUYLT7MXmUEK5YreyphY0miyvBgyFO2jVtsAPeDr2J5NVHoyNFiT9R5BQ1cDwzxhMg0rbqD2X1GSM9fKexbSHpm9SsJVhRGLwsDvxBRjhZJB2ZkVmEqH5585w2Njwz731bWUv3Rc4NeWJgNh4sAWRakA0u3utcRY0YhSe9qYvEzk8xUKLVG5IlyY9WlawPrLwDPc4AvK7sx9gDnVuXwQqo2eYnRzNn9qi8GyI8II1kmBte0gOscaO2Hh6AzVmt6ANTkyyZhldFyis883Lkb2G8e3eKNRiUgoMlIcxYdW8CPBobzBc9D6NHYdj0VB1Irg26Hvp1Fj6r2EDayFgwYavxrqwgFxksYL23r3qJz5g5EJ89kZW9taebTwMzveNX2SrbwisBiPC6jNllNko0veS3z0sPzUXB4KST7VRPGbPK2qWJQHJEm7FnQnayLTUFwpqSMAvIdgozhcveHWZBatN3mdm8bxqCOMxM3303lZCZsQ8vdw3yqIKaJLTe2txFXAGebRsTefE9kwZB5SYUeJkQde3yi1evellyP29TEQXdPSlK1NvvH6zCS2q13BedmaxKPINsdVYpIRXBVYvH3gtWMCgsaAMoPvgvvyyv152PNLKNSPr3Gz7wAP6qMeDcztDvMO7G4aOVqWz2rwwrRBfy5s2SH3XqLpalmGygfgNeJFWBCeSzy9oRnIsYDGoRviXIbsswcisCu2PQCS0rOOgLEPrf4AgIArrkEphkUM9bYvspLb2lq7kj6pGHZ8AWdupFYMIh2kljCvf76OtFiV62FZWS3UZHuoM8hCHLaeM6Pqvji5xhGVvnl6IFunrmqHlB9vZAAOvenuKRZTFw4cKOxBXgArTyDE3MndTt5lwijjbddkFc18hO3tSOO3pQaWdJxaVPIP6eyXAV1zEl0ZE0eiKUH3xp6vto0nlIgUcWd74aWzYGXM3x3kWx3t7jvG1fqntknlfKtW97iScOwpUUEqCUiWa3tkkZBFpFBtsUNEbIy031AZzwm4xvMe0Qza9gwXzk1G6tsBUhXBwWfgCESAdfq9KZSihealD6cjOxpDqvvDujZwSCkqt61bAlOjCxTDMdEN9UVInSYHwfvYkdobnKzTkqaAjF7FHdPnWScTBcJsxuUg4ypCAn4XEVmtz9L4wq5RkVZ7ysT1QrKsxJKcHDhrjJdVJJhYOHsfAdGLawamQHvAebBdxZ7qbfYRwPUxJjCjrFdXoXnA3eSuP2ey654CPoZjVf4sEeqYudl1Vvya4MFZR0g6wTyfNe1QAbr3FmyIdnLr9vUllON22DcR4HTiFW8lNpeDdCnh9W7i3qU3VLjl85EWlDIJkqe5zGT2esgYwBdHGo0C8x4w9Rp82mQ6UoEULYp2U5vapCi1S4hcxLP5WXmjeql8S0jyTVbSNqrY2mW8OgBC5LZcJSRm1PrnoicV9LHMnTjpIB5Yy64Kto349j8IDZOJXR1qSGdMkFrBUxk7YKjcxsw7w9YMpITYBDsnlNtyGvbzB7owUsZvJEJZT7SshmxBSxyOdkYCzYemOx5lk3ywiPclm9U6gUMP2OgmkfTxt3xyqArq4TBBv1QxgibgGUrjFTzg1GOq6L22q5aI8hTNLwGYnDQRO04dh71fjH9MwbFC1l1TCwAVxftspj3JipJ88Qu9d8E1stvva19biDN0fMUxKPIYN2bByR7xH4iN2QhUQpcGcJ8nnDSotishFrMwFz0mKxw4D5u5uUcVEMdOEY2iUi6xWPz96ICCuZMFh6m06iccE3ceFxVKQiwNlmc8gvoW253VfQ5T3HXN6fl9deC9d99PVCxpr4k9JwdB3bIQR1fDPckWsJLG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "Crg4583hqGfwASiRIII5LDsmrhe0xbPUPehvGohZ3j1zySflX9bM3DkNDGQZR0IRC5WKmVvFll5EHQ4pcWH5CcIeiWeAV4CXla4On8BDy3coxBW8NS64JdrdODKCvUMHu2KciBG33xi7r3t02NS6tzHSKM2UlHGpLKYz82WlYMPVth5BnFpVbvwjB0iWghl6mWJdeOGEvAIeniJmMPatkv7l3ZWVaXuY59zDgvi6KxsmMGo1poaLfMDrYyrSVtN10"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
