package uz.pdp.companyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyapp.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
