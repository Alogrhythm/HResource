package com.hr.app.server.repository.appbasicsetup.usermanagement;
import com.hr.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;



public interface ArtAppNotificationTemplateRepository {

	public ArtAppNotificationTemplate findById(String templateId) throws Exception;
}
