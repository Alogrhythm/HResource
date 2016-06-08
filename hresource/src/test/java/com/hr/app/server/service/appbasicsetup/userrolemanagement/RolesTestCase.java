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
import com.hr.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.hr.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.hr.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.hr.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.hr.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleIcon("VPaVfb4i7WCaOklOf6K6c0cuLVvSTzFL4yfwJWf2MVRPiwLRWn");
        roles.setRoleName("JHFMLwPn3OL4BVI7YflpR2Y0NhCdtGWWXLIxYTJ5PKY5zeSqmT");
        roles.setRoleDescription("n86PgSXRsvZ1QwkcWiWYC3jfcuNC9J2BPKBq6yUzB942DVkXxb");
        roles.setRoleHelp("ExRqGslZlHtCuoEkL5D46ITf1LaNpvSfb5XrEuSm8VB1LUk0sx");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsExecute(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setRefObjectId("LZe3J9N68vp3TGfORMkWJXwiQKQGTYYMuvPnHwTDhU8uFomjZF");
        appmenus.setAppType(1);
        appmenus.setAppId("1E6yaHJwfmG6Xxvm1BFB8zQc2hw2w44dylONU61WXZXZr4pcvi");
        appmenus.setMenuAction("esjO0t7zDky42r6ZammeDKxXIut2a3A33XqxMqEP3rxrx3fesQ");
        appmenus.setUiType("hTK");
        appmenus.setMenuLabel("uafhXKn3DsXI5LzxOz0eMK7pIANDlba0FEHoe89fJIPaFbSMwO");
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuIcon("xk5R2q7TgBhedom7N5uEyVgLJt1WJ8Qk94cichijRIv6kro5SO");
        appmenus.setMenuCommands("WtKa0v8yldBbKY027Bv8pBtncD77zraC6oNPrZgHMWsKRs7i7g");
        appmenus.setAutoSave(true);
        appmenus.setMenuTreeId("dWjrLoeyW5tFlfGLLuZoKBG76qihK6LYYS5q6ebCJfrBev2q2c");
        appmenus.setMenuAccessRights(4);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setRoles(roles);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setVersionId(1);
            roles.setRoleIcon("8JUp1rn1wkSMLgKQLtbmAPSByoYqfdbEy771ySxUN8ViPGSZXD");
            roles.setRoleName("XtLbzV4dtcopwPAuBzsC21DgQC182RYeXY22gTDFbMVeAmpe3U");
            roles.setRoleDescription("3Lml30apgI3mDPhzWSzMwjTgsCbazwp0VGTQA8g5VMXMedJR8G");
            roles.setRoleHelp("yg015AewDzOfUz8bxMk5AOqBx5IwEqui92xHd6C6JYCgFDL8ni");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "ByW8mHdiMfqggke7KImqWodE3dXIxsFAwsfZvYa7wvZTbHc2SXsRmxz5XfHEeXg8iKQKZf19deUSdF1etnYBirtBtTmFOhGJ7mxxXqKxZNDjJTEHfra0Tc6pkHdWakYWeLUeRLAyEljXJdoM3D68mX51gssDN16RIkyPEFWLQ6hHNhHO2e1Jxk2OTtVOPT82DVaBcuqyDduaE5ICMfveWFP92xdKgZ8F5AtcN0q67ZfvXLKxaKEfxEUea6wo8zXNv"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "yLdkiXUr3fOAi3v5IX6LSj0o9OOEvNLuZRXowExk334HxdzhkX9tADDiG23DljD49b9piDmalIhhKaS4Ozsr1VHcOMfLIgDEKIWP9cH19rXIbW5Hw8nWh3A98nzk5gwBoAGp5VPXMRrXOfDvTW5k5kG2MMDZHUQXt4yF09PoWrbAT1Bodr23Y74f2kAY0UImBb2xl0MwxwSfPLrNvaYwoYOSIwDV0gN1kAKSUb5okfDPyuCrjNNLVOJy6HisdArdG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "WihJgoDSIwGLyJuf6P7hjEikft3JqtwQA5ASC6TW4ZjgyQ0SQBO6LS4guiGV7Iofr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "Nr0rdiXl15VY3a9vDAxScbsY9jkVLynK7bjJHNFdkSoHTh0Y9Cr44K83vMQP3VNgsnF6uTeL8CTFMHUUD9Chvpak8VnqvwZONflscTZRzJr9NSKwPjVNo90iMOHuCd2xF7DhiTOblkTRFx8rzSPImEQgOVWgCg9jif7bq3NKiGdvrbRMSBZOWYzmahPrg4HrxQi4Dg4nexnTPHQz2E5lu5LJpQcTpOcN21ssuX8x63DB2hakrU5DrXkL0StWfpW6r"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
