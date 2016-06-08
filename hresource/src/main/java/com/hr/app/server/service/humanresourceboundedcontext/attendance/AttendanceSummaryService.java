package com.hr.app.server.service.humanresourceboundedcontext.attendance;
import com.hr.app.config.annotation.Complexity;
import com.hr.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.hr.app.shared.humanresourceboundedcontext.attendance.AttendanceSummary;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Service for AttendanceSummary Master table Entity", complexity = Complexity.LOW)
public abstract class AttendanceSummaryService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(AttendanceSummary entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<AttendanceSummary> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(AttendanceSummary entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<AttendanceSummary> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByEmpId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
