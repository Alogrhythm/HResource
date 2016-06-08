package com.hr.app.server.repository.humanresourceboundedcontext.payroll;
import com.hr.app.server.repository.core.SearchInterfaceImpl;
import com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for SalaryStucFinWise Master table Entity", complexity = Complexity.LOW)
public class SalaryStucFinWiseRepositoryImpl extends SearchInterfaceImpl implements SalaryStucFinWiseRepository<SalaryStucFinWise> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<SalaryStucFinWise> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise> query = emanager.createQuery("select u from SalaryStucFinWise u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("HREPR324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public SalaryStucFinWise save(SalaryStucFinWise entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("HREPR322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<SalaryStucFinWise> save(List<SalaryStucFinWise> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("HREPR322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise s = emanager.find(com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise.class, id);
        emanager.remove(s);
        Log.out.println("HREPR328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(SalaryStucFinWise entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("HREPR321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<SalaryStucFinWise> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("HREPR321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<SalaryStucFinWise> findByEmpId(String empId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalaryStucFinWise.findByEmpId");
        query.setParameter("empId", empId);
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise> listOfSalaryStucFinWise = query.getResultList();
        Log.out.println("HREPR324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "findByEmpId", "Total Records Fetched = " + listOfSalaryStucFinWise.size());
        return listOfSalaryStucFinWise;
    }

    @Transactional
    public SalaryStucFinWise findById(String applicantId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalaryStucFinWise.findById");
        query.setParameter("applicantId", applicantId);
        com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise listOfSalaryStucFinWise = (com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise) query.getSingleResult();
        Log.out.println("HREPR324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseRepositoryImpl", "findById", "Total Records Fetched = " + listOfSalaryStucFinWise);
        return listOfSalaryStucFinWise;
    }
}
