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
import com.hr.app.server.repository.humanresourceboundedcontext.payroll.SalaryStucFinWiseRepository;
import com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise;
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

@RestController
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Service for SalaryStucFinWise Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/SalaryStucFinWise")
public class SalaryStucFinWiseServiceImpl extends SalaryStucFinWiseService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private SalaryStucFinWiseRepository<SalaryStucFinWise> salaryStucFinWiserepo;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise> lstsalarystucfinwise = salaryStucFinWiserepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("HREPR124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        responseBean.add("data", lstsalarystucfinwise);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "findAll", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody SalaryStucFinWise entity) throws Exception {
        salaryStucFinWiserepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "save", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<SalaryStucFinWise> entity, @RequestHeader("isArray") boolean request) throws Exception {
        salaryStucFinWiserepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "save", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        salaryStucFinWiserepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "delete", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody SalaryStucFinWise entity) throws Exception {
        salaryStucFinWiserepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "update", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<SalaryStucFinWise> entity, @RequestHeader("isArray") boolean request) throws Exception {
        salaryStucFinWiserepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("HREPR123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "update", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findByEmpId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByEmpId(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise> lstsalarystucfinwise = salaryStucFinWiserepo.findByEmpId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("HREPR124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        responseBean.add("data", lstsalarystucfinwise);
        Log.out.println("HREPR124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "save", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise lstsalarystucfinwise = salaryStucFinWiserepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("HREPR124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "SalaryStucFinWise"));
        responseBean.add("data", lstsalarystucfinwise);
        Log.out.println("HREPR124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalaryStucFinWiseServiceImpl", "save", "SalaryStucFinWise");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
