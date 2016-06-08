package com.hr.app.server.repository.humanresourceboundedcontext.employee;
import com.hr.app.server.repository.core.SearchInterfaceImpl;
import com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for DepartmentType Master table Entity", complexity = Complexity.LOW)
public class DepartmentTypeRepositoryImpl extends SearchInterfaceImpl implements DepartmentTypeRepository<DepartmentType> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<DepartmentType> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType> query = emanager.createQuery("select u from DepartmentType u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DepartmentTypeRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public DepartmentType save(DepartmentType entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("HREEM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DepartmentTypeRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<DepartmentType> save(List<DepartmentType> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("HREEM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DepartmentTypeRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType s = emanager.find(com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType.class, id);
        emanager.remove(s);
        Log.out.println("HREEM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DepartmentTypeRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(DepartmentType entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("HREEM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DepartmentTypeRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<DepartmentType> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("HREEM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DepartmentTypeRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public DepartmentType findById(String departmentTypeCode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("DepartmentType.findById");
        query.setParameter("departmentTypeCode", departmentTypeCode);
        com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType listOfDepartmentType = (com.hr.app.shared.humanresourceboundedcontext.employee.DepartmentType) query.getSingleResult();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DepartmentTypeRepositoryImpl", "findById", "Total Records Fetched = " + listOfDepartmentType);
        return listOfDepartmentType;
    }
}
