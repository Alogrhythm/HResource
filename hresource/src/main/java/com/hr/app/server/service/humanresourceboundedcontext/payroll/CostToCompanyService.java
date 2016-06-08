package com.hr.app.server.service.humanresourceboundedcontext.payroll;
import com.hr.app.config.annotation.Complexity;
import com.hr.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Service for CostToCompany Master table Entity", complexity = Complexity.LOW)
public abstract class CostToCompanyService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(CostToCompany entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<CostToCompany> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(CostToCompany entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<CostToCompany> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByEmpId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
