package com.hr.app.server.businessservice.organization.contactmanagement;
import com.hr.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.hr.app.shared.organization.contactmanagement.CoreContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CoreContactsBusinessService {

    @Autowired
    private CoreContactsRepository coreContactsRepository;

    public void update(CoreContacts entity) throws Exception {
        if (entity.isHardDelete()) {
            coreContactsRepository.delete(entity.getContactId());
        } else {
            coreContactsRepository.deleteCommunicationData(entity.getDeletedCommunicationDataList());
            coreContactsRepository.deleteAddress(entity.getDeletedAddressList());
            coreContactsRepository.update(entity);
        }
    }

    public void update(List<CoreContacts> entity) throws Exception {
        for (CoreContacts _corecontacts : entity) {
            if (_corecontacts.isHardDelete()) {
                coreContactsRepository.delete(_corecontacts.getContactId());
            } else {
                coreContactsRepository.deleteCommunicationData(_corecontacts.getDeletedCommunicationDataList());
                coreContactsRepository.deleteAddress(_corecontacts.getDeletedAddressList());
                coreContactsRepository.update(_corecontacts);
            }
        }
    }
}
