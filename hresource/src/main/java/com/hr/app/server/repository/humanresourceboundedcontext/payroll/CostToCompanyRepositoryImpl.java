package com.hr.app.server.repository.humanresourceboundedcontext.payroll;
import com.hr.app.server.repository.core.SearchInterfaceImpl;
import com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for CostToCompany Master table Entity", complexity = Complexity.LOW)
public class CostToCompanyRepositoryImpl extends SearchInterfaceImpl implements CostToCompanyRepository<CostToCompany> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<CostToCompany> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany> query = emanager.createQuery("select u from CostToCompany u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("HREPR324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public CostToCompany save(CostToCompany entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("HREPR322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<CostToCompany> save(List<CostToCompany> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("HREPR322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany s = emanager.find(com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany.class, id);
        emanager.remove(s);
        Log.out.println("HREPR328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(CostToCompany entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("HREPR321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<CostToCompany> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("HREPR321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<CostToCompany> findByEmpId(String empId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("CostToCompany.findByEmpId");
        query.setParameter("empId", empId);
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany> listOfCostToCompany = query.getResultList();
        Log.out.println("HREPR324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "findByEmpId", "Total Records Fetched = " + listOfCostToCompany.size());
        return listOfCostToCompany;
    }

    @Transactional
    public CostToCompany findById(String salaryId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("CostToCompany.findById");
        query.setParameter("salaryId", salaryId);
        com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany listOfCostToCompany = (com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany) query.getSingleResult();
        Log.out.println("HREPR324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyRepositoryImpl", "findById", "Total Records Fetched = " + listOfCostToCompany);
        return listOfCostToCompany;
    }
}
