package com.hr.app.server.businessservice.appinsight.alarms;
import com.hr.app.shared.appinsight.alarms.ArtLogDomain;

import org.springframework.http.HttpEntity;

import com.athena.server.pluggable.utils.bean.ResponseBean;

public abstract class ArtLogAlarmService {

	public abstract HttpEntity<ResponseBean> getLogConfigDetails() ;

	public abstract HttpEntity<ResponseBean> updateLogAlarm(ArtLogDomain logDomain);

	public abstract HttpEntity<ResponseBean> getListOfAlarms() ;

	public abstract HttpEntity<ResponseBean> getGridData(String domainId) ;
}
