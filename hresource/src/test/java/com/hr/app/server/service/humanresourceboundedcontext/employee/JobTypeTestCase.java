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
import com.hr.app.server.repository.humanresourceboundedcontext.employee.JobTypeRepository;
import com.hr.app.shared.humanresourceboundedcontext.employee.JobType;
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
public class JobTypeTestCase extends EntityTestCriteria {

    @Autowired
    private JobTypeRepository<JobType> jobtypeRepository;

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

    private JobType createJobType(Boolean isSave) throws Exception {
        JobType jobtype = new JobType();
        jobtype.setJobTypeDesc("a3nE3OoDu1zNXO5thK3flZhjbciElOEHKODbAxiGrohDai6KnU");
        jobtype.setEntityValidator(entityValidator);
        return jobtype;
    }

    @Test
    public void test1Save() {
        try {
            JobType jobtype = createJobType(true);
            jobtype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            jobtype.isValid();
            jobtypeRepository.save(jobtype);
            map.put("JobTypePrimaryKey", jobtype._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("JobTypePrimaryKey"));
            JobType jobtype = jobtypeRepository.findById((java.lang.String) map.get("JobTypePrimaryKey"));
            jobtype.setVersionId(1);
            jobtype.setJobTypeDesc("QsvbZkto38vhkEC6sMSElRrYhlABNDc8pTqbqVpYwizqELgSEv");
            jobtype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            jobtypeRepository.update(jobtype);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("JobTypePrimaryKey"));
            jobtypeRepository.findById((java.lang.String) map.get("JobTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("JobTypePrimaryKey"));
            jobtypeRepository.delete((java.lang.String) map.get("JobTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateJobType(EntityTestCriteria contraints, JobType jobtype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            jobtype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            jobtype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            jobtype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            jobtypeRepository.save(jobtype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "jobTypeDesc", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "jobTypeDesc", "nofxDhTXu0kBHdLdcAAn0FtGS57rDcYupzC00nwHWqTF1JsD3vFrgTOdkjnq9EXiWt9DV8F2nHEo1OUjxqnw6eNLIcmETW1rQPHBjiAazGVM8v3BW6b4m8YgcG9hajytpii2fBVCqUcziKXNkRXP9L5D6pHWfFtHxskvfzFusWjJYlYWx5oSagIyUWRBv0C5w3f3STeAY3cVyEeefIt9CXLP6srQleu5WfpDgN8dZ6ljfFrLEQwwAmGl0checnZBv"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                JobType jobtype = createJobType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = jobtype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(jobtype, null);
                        validateJobType(contraints, jobtype);
                        failureCount++;
                        break;
                    case 2:
                        jobtype.setJobTypeDesc(contraints.getNegativeValue().toString());
                        validateJobType(contraints, jobtype);
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
