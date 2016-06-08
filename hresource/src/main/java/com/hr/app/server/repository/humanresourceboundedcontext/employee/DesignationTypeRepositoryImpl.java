package com.hr.app.server.repository.humanresourceboundedcontext.employee;
import com.hr.app.server.repository.core.SearchInterfaceImpl;
import com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for DesignationType Master table Entity", complexity = Complexity.LOW)
public class DesignationTypeRepositoryImpl extends SearchInterfaceImpl implements DesignationTypeRepository<DesignationType> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<DesignationType> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType> query = emanager.createQuery("select u from DesignationType u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DesignationTypeRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public DesignationType save(DesignationType entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("HREEM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DesignationTypeRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<DesignationType> save(List<DesignationType> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("HREEM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DesignationTypeRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType s = emanager.find(com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType.class, id);
        emanager.remove(s);
        Log.out.println("HREEM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DesignationTypeRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(DesignationType entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("HREEM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DesignationTypeRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<DesignationType> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("HREEM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DesignationTypeRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public DesignationType findById(String designationTypeCode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("DesignationType.findById");
        query.setParameter("designationTypeCode", designationTypeCode);
        com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType listOfDesignationType = (com.hr.app.shared.humanresourceboundedcontext.employee.DesignationType) query.getSingleResult();
        Log.out.println("HREEM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DesignationTypeRepositoryImpl", "findById", "Total Records Fetched = " + listOfDesignationType);
        return listOfDesignationType;
    }
}
