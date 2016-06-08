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
import com.hr.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.hr.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("mwMqupaOSXwwN51Yf07IHVzzr1e7ydNohRYArJErYD9eFUSqT7");
        useraccesslevel.setLevelHelp("zprE0PlwaGjyebxa0aIIvio5h8UUpbrKobd35aNEX4AKFI7lug");
        useraccesslevel.setLevelIcon("2li4rb5v2Fs7A7UChPqwfAs5DMMZ6gVNTySIyeestGCMMSNocp");
        useraccesslevel.setLevelName("n2aYvBfRuylBMj6Ku6yvnhwAJlwFhOQhTmksIfbIxwtlfZJd8z");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setUserAccessLevel(35015);
            useraccesslevel.setLevelDescription("Si71ZFfpzC2K7nW9nN1hzwjliMvIPnSPthQgyHoUYUy6asaHue");
            useraccesslevel.setLevelHelp("O9LKritkhf0H0etbSxcQjBkxbjFQewYV7dUqD5i1dnRYpHPveA");
            useraccesslevel.setLevelIcon("C3Mok6gxlXkAZ3CdXTpBtoctXW9A4nVOs7BCHaONdSgiX3VCqH");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelName("gUoaJckzqEUywRx0zhldLEAeCQcjawA6IV1jguCwbhtOO3vzm4");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 125585));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "Jk29lxVLRWUlvmNDJmsGK5I5EVrzkctT6P3iw1gtLWocWU1eE6GqUqUs3pUnXx8AmINfghujEvY6tlTTtvf9y8dapEUPuR9nM7N7JNVhArTAxRsXHCslC8jkdmN02IuYeC4XtSXQlU1cVSjUz6DP8yMEtvT5sRP5cfAR8GK8iQjbpLsQVlSvuacT0Tfw1FHASM41cxlnUKc2jpCGaj0ImB8D8srnYbs3qJvekmPtR1X6c6gZSOwoLFuk5BLpdmLgf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "yXv4g9HLViLYw01zPlElmb14Qhg1niZZ0Dr0pxRwoKBZo66zmu3WF74kmxmYTLTvxocITxcBPKp3akc15rsEtAW5oJMGtRkuCNHcLxS9f2mCIqITYwkjapMYql6foaffs0K1jtw83zXknrUT6ycM26KuINVUAiEpPZwbBsuCryxT0qja1ZznWb7cv3vpgtswdP1qIJYW53rCHTNj2zEQGyItSvPVjWtVbuIEjDyusVOhKFDej0cttrPwp11r4PeZ2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "ohLitSUoBUeOmwFOkGb2uzcv6clHeBro8iMUPRcKMHpKASPjd96Y1HFp7W9VzbdS55TxbmT3Te0msTLN38x8SFANdHREh9wVMuVEvcHmgiRYWwmTLAl6d4GQT7LfezYlgFfizbR6UNI2ic6gTCC2y1TiiWrCkebq2a1YRH1DRB8roPYVI1gs4cafdKDdJ7QsZfOAnLuoqMGKuqi4s9its6Ho8NVkHPmpH7M08Et8dssgkNdg4BWkOa360TfpNutHQ"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
