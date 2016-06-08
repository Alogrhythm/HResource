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
import com.hr.app.server.repository.humanresourceboundedcontext.employee.DepartmentTypeRepository;
import com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType;
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
public class DepartmentTypeTestCase extends EntityTestCriteria {

    @Autowired
    private DepartmentTypeRepository<DepartmentType> departmenttypeRepository;

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

    private DepartmentType createDepartmentType(Boolean isSave) throws Exception {
        DepartmentType departmenttype = new DepartmentType();
        departmenttype.setDepartmentTypeDesc("XiMkDFgxZEEFqsHoJB08XfSY8U7FQBCtalne1UsjZjTuyHF3qv");
        departmenttype.setEntityValidator(entityValidator);
        return departmenttype;
    }

    @Test
    public void test1Save() {
        try {
            DepartmentType departmenttype = createDepartmentType(true);
            departmenttype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            departmenttype.isValid();
            departmenttypeRepository.save(departmenttype);
            map.put("DepartmentTypePrimaryKey", departmenttype._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("DepartmentTypePrimaryKey"));
            DepartmentType departmenttype = departmenttypeRepository.findById((java.lang.String) map.get("DepartmentTypePrimaryKey"));
            departmenttype.setDepartmentTypeDesc("ZmbC8lirG2KAT9tqJ8AHMY8CwYN7g690RATvcnkNveYdS5yCU4");
            departmenttype.setVersionId(1);
            departmenttype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            departmenttypeRepository.update(departmenttype);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("DepartmentTypePrimaryKey"));
            departmenttypeRepository.findById((java.lang.String) map.get("DepartmentTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("DepartmentTypePrimaryKey"));
            departmenttypeRepository.delete((java.lang.String) map.get("DepartmentTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateDepartmentType(EntityTestCriteria contraints, DepartmentType departmenttype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            departmenttype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            departmenttype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            departmenttype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            departmenttypeRepository.save(departmenttype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "departmentTypeDesc", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "departmentTypeDesc", "7xBuOEqcSTnmJYOC8Ol4CIROsXOqdA2Nikpf8chpW03hvVOi7BsYsBuL2TUC2XLY1YmJXXiW72HWHmPB4m7SVauMouPSCyhEPgBXJ2HVpm4yfNTPlcLefv1OOcPo2W2sX6jLmCCdxWXF1o4vsQQSQtJ6WLzWwteJR0k3F8V0cXFZY16TeM1SBI8Blj9na1zeXiWNEY44eKb35D1hOPkKv6QlFPGbaPbpwng15k315Qa1wCUQbti1g7bZv7Kn7mTZL"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                DepartmentType departmenttype = createDepartmentType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = departmenttype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(departmenttype, null);
                        validateDepartmentType(contraints, departmenttype);
                        failureCount++;
                        break;
                    case 2:
                        departmenttype.setDepartmentTypeDesc(contraints.getNegativeValue().toString());
                        validateDepartmentType(contraints, departmenttype);
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
