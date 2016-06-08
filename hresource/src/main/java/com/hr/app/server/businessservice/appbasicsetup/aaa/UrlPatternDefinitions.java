package com.hr.app.server.businessservice.appbasicsetup.aaa;
public interface UrlPatternDefinitions {
	public String[] getPublicUrlPatterns();

	public String[] getAuthorizedUrlPatterns();

	public String[] getRequestFilterIgnoreUrlPatterns();
	
	public String getMainPageControllerUrlPattern();
	
}
