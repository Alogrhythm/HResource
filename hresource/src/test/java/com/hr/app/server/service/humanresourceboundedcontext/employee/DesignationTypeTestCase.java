package com.hr.app.server.service.humanresourceboundedcontext.employee;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.humanresourceboundedcontext.employee.DesignationTypeRepository;
import com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType;
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
public class DesignationTypeTestCase extends EntityTestCriteria {

    @Autowired
    private DesignationTypeRepository<DesignationType> designationtypeRepository;

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

    private DesignationType createDesignationType(Boolean isSave) throws Exception {
        DesignationType designationtype = new DesignationType();
        designationtype.setDesignationTypeDesc("8U4rxAW3UU84CViklS6CPcJGuOjYam9cGpIdCE0unNNiaQnHgB");
        designationtype.setEntityValidator(entityValidator);
        return designationtype;
    }

    @Test
    public void test1Save() {
        try {
            DesignationType designationtype = createDesignationType(true);
            designationtype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            designationtype.isValid();
            designationtypeRepository.save(designationtype);
            map.put("DesignationTypePrimaryKey", designationtype._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("DesignationTypePrimaryKey"));
            DesignationType designationtype = designationtypeRepository.findById((java.lang.String) map.get("DesignationTypePrimaryKey"));
            designationtype.setVersionId(1);
            designationtype.setDesignationTypeDesc("UrfzdmoF1EXA0h56E8ILwec7Wb2Ay6JsVlxj7QVtkgd1I4EZPK");
            designationtype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            designationtypeRepository.update(designationtype);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("DesignationTypePrimaryKey"));
            designationtypeRepository.findById((java.lang.String) map.get("DesignationTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("DesignationTypePrimaryKey"));
            designationtypeRepository.delete((java.lang.String) map.get("DesignationTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateDesignationType(EntityTestCriteria contraints, DesignationType designationtype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            designationtype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            designationtype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            designationtype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            designationtypeRepository.save(designationtype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "designationTypeDesc", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "designationTypeDesc", "uiaAJmr4MjQSPdOexR1hnePIGptwFimSFitsmozbvlpabxnTE0dlgNoewC0gAi96XB5AOfiIBYEd0Asnb2IVZE7MrP9ICbIYdJUbxya5cEWoJo0y8SeU0yyYvdhRUQIiOjeC0DkUfaxO4ULG79HdnKOApFFp0PPQ5AZyGmOyXFSYeOhD3lxlkdxMhb39fWCSdMFNQQs2GtibBENX8sGrteVbojZHFM7i0gCoBR9smEwED6Puf3mK2oxin2V8rUUO3"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                DesignationType designationtype = createDesignationType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = designationtype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(designationtype, null);
                        validateDesignationType(contraints, designationtype);
                        failureCount++;
                        break;
                    case 2:
                        designationtype.setDesignationTypeDesc(contraints.getNegativeValue().toString());
                        validateDesignationType(contraints, designationtype);
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
