package com.hr.app.server.businessservice.humanresourceboundedcontext.payroll;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.hr.app.server.repository.humanresourceboundedcontext.payroll.CostToCompanyRepository;
import com.hr.app.server.repository.humanresourceboundedcontext.payroll.SalaryStucFinWiseRepository;
import com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany;
import com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CTCcalucation {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private SalaryStucFinWiseRepository<SalaryStucFinWise> salaryStucFinWiseRepository;

    @Autowired
    private CostToCompanyRepository<CostToCompany> costToCompanyRepository;

    public void processCTC(CostToCompany entity) throws Exception {
        if (entity.getTotalCTC() > 0) {
            entity.setBasic(entity.getTotalCTC() * 0.30);
            entity.setHra(entity.getTotalCTC() * 0.40);
            entity.setEducationalAllowance(java.lang.Double.valueOf(2500));
            entity.setConvenceAllowance(java.lang.Double.valueOf(9600));
            entity.setMedicalAllowance(java.lang.Double.valueOf(15000));
            entity.setPerk(java.lang.Double.valueOf(0));
            entity.setSpecialAllowance(entity.getSpecialAllowance());
            com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise salaryStucFinWise1 = new com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise();
            salaryStucFinWise1.setEmpId(entity.getEmpId());
            salaryStucFinWise1.setBasic(entity.getBasic());
            salaryStucFinWise1.setHra(entity.getHra());
            salaryStucFinWise1.setMedicalAllowance(entity.getMedicalAllowance());
            salaryStucFinWise1.setSpecialAllowance(entity.getSpecialAllowance());
            salaryStucFinWise1.setConvenceAllowance(entity.getConvenceAllowance());
            salaryStucFinWise1.setEducationalAllowance(entity.getEducationalAllowance());
            salaryStucFinWise1.setYearValue(entity.getYearValue());
            salaryStucFinWise1.setPerk(entity.getPerk());
            salaryStucFinWise1.setSalaryDrawn(java.lang.Double.valueOf(0));
            salaryStucFinWise1.setTotalCTC(entity.getTotalCTC());
            salaryStucFinWise1.setTakeHome(entity.getTakeHome());
            salaryStucFinWise1.setTaxableAmount(entity.getTotalCTC());
            salaryStucFinWise1.setNonTaxableAmount(java.lang.Double.valueOf(0));
            salaryStucFinWise1.setTotalTax(java.lang.Double.valueOf(0));
            if (salaryStucFinWise1.getTaxableAmount() > 1000000) {
                salaryStucFinWise1.setTotalTax(java.lang.Double.valueOf((salaryStucFinWise1.getTaxableAmount() - 1000000) * 0.30));
                salaryStucFinWise1.setTaxableAmount(salaryStucFinWise1.getTaxableAmount() - (salaryStucFinWise1.getTaxableAmount() - 1000000));
            }
            if ((salaryStucFinWise1.getTaxableAmount() > 500000 && salaryStucFinWise1.getTaxableAmount() < 1000000)) {
                salaryStucFinWise1.setTotalTax(java.lang.Double.valueOf(salaryStucFinWise1.getTotalTax() + (salaryStucFinWise1.getTaxableAmount() - 500000) * 0.20));
                salaryStucFinWise1.setTaxableAmount(salaryStucFinWise1.getTaxableAmount() - (salaryStucFinWise1.getTaxableAmount() - 500000));
            }
            if (salaryStucFinWise1.getTaxableAmount() > 0) {
                salaryStucFinWise1.setTotalTax(java.lang.Double.valueOf(salaryStucFinWise1.getTotalTax() + 25000));
            }
            if (salaryStucFinWise1.getTotalCTC() > 0) {
                salaryStucFinWise1.setTakeHome(java.lang.Double.valueOf(salaryStucFinWise1.getTotalCTC() - salaryStucFinWise1.getTotalTax()));
            }
            entity.setTakeHome(salaryStucFinWise1.getTakeHome());
            com.hr.app.shared.humanresourceboundedcontext.payroll.CostToCompany costToCompany1 = costToCompanyRepository.save(entity);
            com.hr.app.shared.humanresourceboundedcontext.payroll.SalaryStucFinWise salaryStucFinWise2 = salaryStucFinWiseRepository.save(salaryStucFinWise1);
        }
    }
}
