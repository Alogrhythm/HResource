package com.hr.app.server.service.appbasicsetup.userrolemanagement;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.hr.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setRefObjectId("TMGvhHhAJFum9U37TvzYBeEiH7Nf6ZYlxtfpi5SFazlmJCoDy9");
        appmenus.setAppType(1);
        appmenus.setAppId("9jzf2pYWid7zuhXFPH8jyJCEb5CZEqrjeoVQKGMkfXATUoCHeZ");
        appmenus.setMenuAction("7OM5NFUdmyf01QorXoY58fBHK8FnKL5Z1fZfiKjCJFVQrDMbZc");
        appmenus.setUiType("u1Z");
        appmenus.setMenuLabel("OOZp1RcYnKPgRZzhWvZnLaJuHsul9LdLKCofDlySuPPwUyC1HA");
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuIcon("q7skNrRoonsc24to8YRSXVhfb3P3L6Y21lwMJMrFJHefojqe5H");
        appmenus.setMenuCommands("bCCW2EcMfL9h0SBGkqUQMAYCD9aIpH2SImoWkNnOhALXWyy4TN");
        appmenus.setAutoSave(true);
        appmenus.setMenuTreeId("6nmFus8x5himPkdSaApmppkOTv3O0E7UWUrOUgg0b94Jk5VKjP");
        appmenus.setMenuAccessRights(4);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setRefObjectId("rZpSeixiL1IcRf3jdkimsThsViKw17BiZJi6o1EtPIhtiGxjpN");
            appmenus.setAppType(1);
            appmenus.setAppId("nDrPojCVrDLKUmFjxtZZChppUs12zCvag7JXxm07d4iKVYcJpo");
            appmenus.setMenuAction("CRnPVEgl142JWj1T9I0eFBlvwDfnTrz54EpYXrkuJYEivY8eGe");
            appmenus.setUiType("Obd");
            appmenus.setMenuLabel("Jnmxbf8qgR0NMvsNfxBLhEsHSUibjQSvjZ5rowJtszxSjsaKzA");
            appmenus.setMenuIcon("ymQDKz2kYFvgM7YNmKKa75GuS4q5U8F5rhZfbRIjlqfUUpuIrx");
            appmenus.setMenuCommands("fuoUMA5i40RrdC0texmAYgx323DVVwSQCXfEq2gjjGZvs9sD00");
            appmenus.setMenuTreeId("oOtMSHhravQJkUjBKlNs9PIUlaoWm5aLMp5w2bzs4Fy60azrEr");
            appmenus.setVersionId(1);
            appmenus.setMenuAccessRights(3);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "ske78oBX3MMmMWZ0S69g5ejSz3QbRspulyZ2X1sedvv17qQlrC64WXTa3pbtd6B92GUJFaI1UNFdqQhJhtggRnzzHsLpYBbKOPPZdL2W6ZNxdahRGTBEcZfRBupNS4Hvl4WqYXdzuXVgCVoNohG42ytcWHF7pD5G59V7YJQMuY6QoPTbARU28yw5rvcXlTotpUR2p7KK6e4cqn2S0zvgqOievN8kYIlrG7u9oKu5ob3FuJEoSmuRwmITLBzCYoEOe"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "U1g6NPHFYrfHlRLqyB7dBLIiYFxUEiKLgVdydpGq1CgGNRnWlSX42Ebd8UKf5D5STk764veZe8FZg76SrlMMp9lDNWQ0taLxCreoQpoH5BouPbQYIVyHnsEjobTtJaAqbr9b9uC6s16I1NtMHamhlMdLp77YE4rsiY7KUPAUQoQgMgOY3rOhWInGgUAdwuUYGkbWNqemmEeWX95yCUI6ZMB1fUZBMBveJJwLwMc6j3fKdaw0iDN0eEa5n4hfZwSeh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "Fuf8PJllMTL6lo7jJwj7ZWMGG4cwA73GrA1QRzQwJY4ytuNl0ZYZk6Qu3AA4ZZd6e9QpNe5wrFF8iS2j7X01MKXIlYJrPiHgkX06cOKDc9F8AvJR8ztWXu9Khazn1e4lc1p9dZLpte786XH37g2HEPP8X7q3hdlLXRMbBSzicPFM81mkMbWA5JBNOc809BTUPUykpCsZurGepzKQ0xhaL1yHHXgdxF1o7iFLQdGAG3sh7OXtpAt3QmMgi9dr9nELV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "7SBpt5Dw0nBZX0YaLb8LpNpxPxMcltkV2U6Lmzr2njgDKu4UTeawDOWYMaAODvmIN"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "H5jZ2SgyEXQ9LGdBcP7uzf9lhIxTsUUXVli6p2UmsL1bp8JSEvRhB6myJkUItYCUrd40CM8aEUNCr5umJmD6pWd8DU9Wsx8SYLMD9EsFxL5tt1U0PgWLHlun36tJ7QqwM66jZcTmrVWPl5Ty0YBODRLXnNu19hqG6PiXC7wXCYbb1xjiX9Edlh1LYwHc7mgFJudu4tvFoxvObpUMp6PxeNPoXyA0JACpbUXqbQNBBZzu988wYRja4Q70KFaTJdffG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "eki3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "NLI1FVA6ejFzT50wKaQZeC80RrCLuBEAGzTFoAw8f7DhRijPJaHje5zcoJU3dBd4fUpaYE6NFxLe4OFcFlARvcENwX6iqZ9ccU1ehiO7lVwAGUnKOr6fuqcAO1XwWWT905hhaWC9Q4EWZsJUlzUmcEF2jeCYftm0zVpk0fDq1C05jd1CSZsDXDlRaaaND5wwVaHEg1BW2olDCRbgeSdAKYeNTkAyMVhwZYKSd2mcIk2oNzXdJaCmyrURQrPVzeMKr"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "lI67ZSeUUiVF6KirvQLtsbjRdT8QifXFj2ubAx8GGmRUb0NLlKHqqbPBy1Y5koJC8rFeFoxyCtvpFxohP8eQbvM7EyWtKnGOA4eEplfwqXTO9d7LNboGv3RsPw19z07BlofRzgbW09jiESfBqcXRcv2KH0TYzv8M53VSz3hpmEw8TJzcQCEtPXhlgw4KnGMRGImx9Sppi63jzPJuRccAAbXbkide4A5axZKkQeadUIbQbyr7b4NnjGaV3tfJHjp3I"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
