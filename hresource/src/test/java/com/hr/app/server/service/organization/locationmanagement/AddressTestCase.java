package com.hr.app.server.service.organization.locationmanagement;
import com.hr.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hr.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hr.app.server.repository.organization.locationmanagement.AddressRepository;
import com.hr.app.shared.organization.locationmanagement.Address;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.hr.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.hr.app.shared.organization.locationmanagement.Country;
import com.hr.app.server.repository.organization.locationmanagement.CountryRepository;
import com.hr.app.shared.organization.locationmanagement.State;
import com.hr.app.server.repository.organization.locationmanagement.StateRepository;
import com.hr.app.shared.organization.locationmanagement.AddressType;
import com.hr.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.hr.app.shared.organization.locationmanagement.City;
import com.hr.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Address createAddress(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(4);
        country.setCountryCode1("RxD");
        country.setCountryCode2("WfC");
        country.setCurrencyCode("BMI");
        country.setIsoNumeric(541);
        country.setCapital("UPMd099cPzCyfKaK5DUSt9gwrcCvZvVL");
        country.setCountryName("ngwdLPy4hmGIjAz6oELytvJRKnIdMh94mlzgPcXTCg6HwvhOVq");
        country.setCountryFlag("tDPSNiAs5Yb1IZUGAacOftVxQvB1hSb0fJqfkFlUmuPcHutlk5");
        country.setCurrencySymbol("0HBAQzUeQ9UGqHvzFfrscc4qHGaetx52");
        country.setCapitalLatitude(11);
        country.setCurrencyName("JOERWM2anYV3nbD0iXeMd8cmp1UlNUL1Eqbe4ZDMxR8ZYiFThp");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("MhXnIgdeN6ZIBXslzAghRfAQj07hrJEGYFSssJHVjatFGAJpJb");
        state.setStateCode(2);
        state.setStateCodeChar3("vlWu31jaP7Ke7PXlV1vVpPPaBB2e9wSa");
        state.setStateCapitalLatitude(3);
        state.setStateCapital("k34SPZvViAGFfAiD7fQcTfJdjHoawSuctPgYChgls9s1xoDC8G");
        state.setStateCode(1);
        state.setStateCodeChar3("KD51a5lgQ4bvPpPDmRxtyyaB87w6eGeA");
        state.setStateCapitalLatitude(8);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("e3Ifm24Zy1pf3RAkWx5LJ42DJtEhZn6q");
        state.setStateName("KKpdN3giRcTZJuYUl4HBnO1ucCOZVm0B6Tb7caYOGrCNKnFaoz");
        state.setStateFlag("Dv5iz2vFiH35dJnrt8ifGmgVr0ndEDPPWMsQjomn2HL2dcdkC6");
        state.setStateDescription("TBfpFzLGzlt4kLhwoBxQeuvHPxWsPrNb58ZIroEZ1X5tSuRHA3");
        state.setStateCapitalLongitude(5);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("dHF6Kr4XGmfCFKRNxpReWL2Hlc3zA3z2Bdw98efHDWDSRlclAk");
        addresstype.setAddressTypeDesc("mJXvA0x9zBg9ROGiWvcYtbDK0qMjYbLJrOdPPNSWT6CUuOtNOh");
        addresstype.setAddressTypeIcon("MlPQQk7SAkaqh0Bb6bdKZtdYkPwtBqgY3N0NzWfqfVItGU9w0I");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("1cUDYAqsofc8qnCYtkNErPEFpmwRDcfY");
        city.setCityDescription("pZlAX89oiy6l20gHMl4Wo4WkIaradzN2e0OsYPCLhBCDE3yXn1");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("HmP7aGM9C7GThiNOlCjA6mZKX1y5ETSZQHsLvKi6P3ouCcW9Zw");
        city.setCityName("ml21t31EoeTd2r9brds5pSD2a7kYkT9jxY4C39Wq3efFVKgAit");
        city.setCityLongitude(3);
        city.setCityLatitude(5);
        city.setCityCode(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setZipcode("kocxIT");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("WfWgkuNi2lfiSvaKXrGlI6lXwcreBLEoBnuG4VQo9cVdIm6Gpq");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("Vow3TTCXqFEpSDVuPbHuXATzaFcs7BEaKidruwXjbr4oaYzE8r");
        address.setLatitude("mGeRc9Wmv8O1wPEYt03iOc9HOzdO1EUJa5ASCnuKh3yVRCYzk3");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("VxfnwPjH20i");
        address.setAddress3("lqjhcoiMX5bsnCr7fUdghhgHwBcl4dNhURy6w8qsdRMw7zf9rH");
        address.setLongitude("VyBOJ8U4yZdpqq9DHjwCLugB4nSuj3FTu9Vf6bfVQdVbsOwEuu");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setZipcode("ePcjf3");
            address.setAddress1("jb1kY6ktW7KRKNov9EKpyxwrFTTlQsNtBLVcOWAR0sLYCvtelA");
            address.setAddress2("bnn4iwXFto9saTneX6th9kvdBz8zEBkIiCivcBerEWSxD6XlmP");
            address.setLatitude("dW4JD8fKh1YojvNfgZb40O4ZEVuLFyrmwDbW6K5L7zORTR5cJX");
            address.setAddressLabel("jJto2vCfyjy");
            address.setAddress3("hQJIRiCuYDYPrQHsKU4uhTXbSiYnOOZszDPjB5ABVz9iKJizv2");
            address.setLongitude("EpA0yV6Uj1ca477k5mRglP0FiJ9UKUL6of75iDQp9hSJu9ZuXZ");
            address.setVersionId(1);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "eBMjw8zjmbWt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "CidjeJo5DbmifYuMx54L0acrVfgcZPxoQuDRvKfDEPVbLBEOWRI3T3who"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "qMv9jAY0GigzwpjTBJ9WdltWZ3abUfo191KNwAndNiTxwVQI5kFJhtWD5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "Dz4bKASSAJgAGgVVSPtvtvi8Lyn5ZvS9IAIekhW9Pek5hyu0wL4i7gzOg"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "oQ2ZgOP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "DqIlGZXtRDlrcmn1HxxsgMt3NuKpT6V2eqzgZgIecBm7qta07can7GVnUluJGNXJN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "VcHwIY56usH7BXPI4ZpZSyCLvZO1A15KiXdrjSSLrP3kPPBSBRBz14R2peHJkv80v"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
