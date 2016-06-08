package com.hr.app.server.repository.humanresourceboundedcontext.employee;
import com.hr.app.server.repository.core.SearchInterface;
import com.hr.app.config.annotation.Complexity;
import com.hr.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for EmployeeInfo Master table Entity", complexity = Complexity.LOW)
public interface EmployeeInfoRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByDeptTypeCode(String deptTypeCode) throws Exception;

    public List<T> findByDesignationTypeCode(String designationTypeCode) throws Exception;

    public List<T> findByJobTypeCode(String jobTypeCode) throws Exception;

    public List<T> findByContactId(String contactId) throws Exception;

    public T findById(String empId) throws Exception;
}
