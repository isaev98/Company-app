package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Company;
import uz.pdp.companyapp.entity.Department;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.DepartmentDTO;
import uz.pdp.companyapp.repository.CompanyRepository;
import uz.pdp.companyapp.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public ApiResponse getOne(Integer id){
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Not found",false);
        }
        return new ApiResponse("Found",true,byId.get());
    }
    public ApiResponse save(DepartmentDTO departmentDTO){
        Department department=new Department();
        department.setName(departmentDTO.getName());

        Optional<Company> byId = companyRepository.findById(departmentDTO.getCompanyId());
        if (!byId.isPresent()){
            return new ApiResponse("Company not found",false);
        }
        department.setCompany(byId.get());
        departmentRepository.save(department);
        return new ApiResponse("Saved",true,department);
    }

    public ApiResponse edit(Integer id,DepartmentDTO departmentDTO){
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Department not found",false);
        }
        Department department = byId.get();

        if (departmentDTO.getName()!=null){
            department.setName(departmentDTO.getName());
        }

        if (departmentDTO.getCompanyId()!=0){
            Optional<Company> byId1 = companyRepository.findById(departmentDTO.getCompanyId());
            if (!byId.isPresent()){
                return new ApiResponse("Company not found",false);
            }
            department.setCompany(byId1.get());
        }

        departmentRepository.save(department);

        return new ApiResponse("Saved",true,department);
    }
    public ApiResponse delete(Integer id){
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Not Found",false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
