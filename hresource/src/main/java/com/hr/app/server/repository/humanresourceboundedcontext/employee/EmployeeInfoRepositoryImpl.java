package com.hr.app.server.repository.humanresourceboundedcontext.employee;
import com.hr.app.server.repository.core.SearchInterfaceImpl;
import com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo;
import org.springframework.stereotype.Repository;
import com.hr.app.config.annotation.Complexity;
import com.hr.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for EmployeeInfo Master table Entity", complexity = Complexity.LOW)
public class EmployeeInfoRepositoryImpl extends SearchInterfaceImpl implements EmployeeInfoRepository<EmployeeInfo> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<EmployeeInfo> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo> query = emanager.createQuery("select u from EmployeeInfo u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public EmployeeInfo save(EmployeeInfo entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("HREEM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<EmployeeInfo> save(List<EmployeeInfo> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("HREEM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo s = emanager.find(com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo.class, id);
        emanager.remove(s);
        Log.out.println("HREEM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(EmployeeInfo entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("HREEM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<EmployeeInfo> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("HREEM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<EmployeeInfo> findByDeptTypeCode(String deptTypeCode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("EmployeeInfo.findByDeptTypeCode");
        query.setParameter("deptTypeCode", deptTypeCode);
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo> listOfEmployeeInfo = query.getResultList();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "findByDeptTypeCode", "Total Records Fetched = " + listOfEmployeeInfo.size());
        return listOfEmployeeInfo;
    }

    @Transactional
    public List<EmployeeInfo> findByDesignationTypeCode(String designationTypeCode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("EmployeeInfo.findByDesignationTypeCode");
        query.setParameter("designationTypeCode", designationTypeCode);
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo> listOfEmployeeInfo = query.getResultList();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "findByDesignationTypeCode", "Total Records Fetched = " + listOfEmployeeInfo.size());
        return listOfEmployeeInfo;
    }

    @Transactional
    public List<EmployeeInfo> findByJobTypeCode(String jobTypeCode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("EmployeeInfo.findByJobTypeCode");
        query.setParameter("jobTypeCode", jobTypeCode);
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo> listOfEmployeeInfo = query.getResultList();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "findByJobTypeCode", "Total Records Fetched = " + listOfEmployeeInfo.size());
        return listOfEmployeeInfo;
    }

    @Transactional
    public List<EmployeeInfo> findByContactId(String contactId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("EmployeeInfo.findByContactId");
        query.setParameter("contactId", contactId);
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo> listOfEmployeeInfo = query.getResultList();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "findByContactId", "Total Records Fetched = " + listOfEmployeeInfo.size());
        return listOfEmployeeInfo;
    }

    @Transactional
    public EmployeeInfo findById(String empId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("EmployeeInfo.findById");
        query.setParameter("empId", empId);
        com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo listOfEmployeeInfo = (com.hr.app.shared.humanresourceboundedcontext.employee.EmployeeInfo) query.getSingleResult();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeInfoRepositoryImpl", "findById", "Total Records Fetched = " + listOfEmployeeInfo);
        return listOfEmployeeInfo;
    }
}
