package uz.pdp.companyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyapp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
