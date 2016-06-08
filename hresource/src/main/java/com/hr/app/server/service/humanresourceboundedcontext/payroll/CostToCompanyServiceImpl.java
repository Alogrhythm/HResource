package com.hr.app.server.service.humanresourceboundedcontext.payroll;
import org.springframework.web.bind.annotation.RestController;
import com.hr.app.config.annotation.Complexity;
import com.hr.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.hr.app.server.repository.humanresourceboundedcontext.payroll.CostToCompanyRepository;
import com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.athena.server.pluggable.utils.bean.FindByBean;
import com.hr.app.server.businessservice.humanresourceboundedcontext.payroll.CTCcalucation;

@RestController
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Service for CostToCompany Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/CostToCompany")
public class CostToCompanyServiceImpl extends CostToCompanyService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CostToCompanyRepository<CostToCompany> costToCompanyrepo;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany> lstcosttocompany = costToCompanyrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("HREPR124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CostToCompany"));
        responseBean.add("data", lstcosttocompany);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyServiceImpl", "findAll", "CostToCompany");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<CostToCompany> entity, @RequestHeader("isArray") boolean request) throws Exception {
        costToCompanyrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CostToCompany"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyServiceImpl", "save", "CostToCompany");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        costToCompanyrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CostToCompany"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyServiceImpl", "delete", "CostToCompany");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody CostToCompany entity) throws Exception {
        costToCompanyrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CostToCompany"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyServiceImpl", "update", "CostToCompany");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<CostToCompany> entity, @RequestHeader("isArray") boolean request) throws Exception {
        costToCompanyrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CostToCompany"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyServiceImpl", "update", "CostToCompany");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findByEmpId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByEmpId(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany> lstcosttocompany = costToCompanyrepo.findByEmpId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("HREPR124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CostToCompany"));
        responseBean.add("data", lstcosttocompany);
        Log.out.println("HREPR124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyServiceImpl", "save", "CostToCompany");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany lstcosttocompany = costToCompanyrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("HREPR124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CostToCompany"));
        responseBean.add("data", lstcosttocompany);
        Log.out.println("HREPR124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CostToCompanyServiceImpl", "save", "CostToCompany");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @Autowired
    private CTCcalucation ctccalucation;

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody CostToCompany entity) throws Exception {
        com.spartan.pluggable.logger.alarms.AppAlarm appAlarm = Log.getAlarm("HREPR247100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new com.athena.server.pluggable.utils.bean.ResponseBean(appAlarm);
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        ctccalucation.processCTC(entity);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CTCcalucation", "processCTC"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "com.hr.app.server.businessservice.humanresourceboundedcontext.payroll.CTCcalucation", "processCTC");
        return new org.springframework.http.ResponseEntity<com.athena.server.pluggable.utils.bean.ResponseBean>(responseBean, httpStatus);
    }
}
