package com.hr.app.server.repository.appbasicsetup.userrolemanagement;
import com.hr.app.server.repository.core.SearchInterface;
import com.hr.app.config.annotation.Complexity;
import com.hr.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for UserRoleBridge Transaction table", complexity = Complexity.MEDIUM)
public interface UserRoleBridgeRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByRoleId(String roleId) throws Exception;

    public List<T> findByUserId(String userId) throws Exception;

    public T findById(String roleUserMapId) throws Exception;
}
