package com.hr.app.server.repository.humanresourceboundedcontext.attendance;
import com.hr.app.server.repository.core.SearchInterfaceImpl;
import com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for AttendanceSummary Master table Entity", complexity = Complexity.LOW)
public class AttendanceSummaryRepositoryImpl extends SearchInterfaceImpl implements AttendanceSummaryRepository<AttendanceSummary> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<AttendanceSummary> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary> query = emanager.createQuery("select u from AttendanceSummary u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("HREAT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public AttendanceSummary save(AttendanceSummary entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("HREAT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<AttendanceSummary> save(List<AttendanceSummary> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("HREAT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary s = emanager.find(com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary.class, id);
        emanager.remove(s);
        Log.out.println("HREAT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(AttendanceSummary entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("HREAT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<AttendanceSummary> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("HREAT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<AttendanceSummary> findByEmpId(String empId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("AttendanceSummary.findByEmpId");
        query.setParameter("empId", empId);
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary> listOfAttendanceSummary = query.getResultList();
        Log.out.println("HREAT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "findByEmpId", "Total Records Fetched = " + listOfAttendanceSummary.size());
        return listOfAttendanceSummary;
    }

    @Transactional
    public AttendanceSummary findById(String attendenceSummaryId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("AttendanceSummary.findById");
        query.setParameter("attendenceSummaryId", attendenceSummaryId);
        com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary listOfAttendanceSummary = (com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary) query.getSingleResult();
        Log.out.println("HREAT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AttendanceSummaryRepositoryImpl", "findById", "Total Records Fetched = " + listOfAttendanceSummary);
        return listOfAttendanceSummary;
    }
}
