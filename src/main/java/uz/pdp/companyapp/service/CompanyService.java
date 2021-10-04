package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.entity.Company;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.CompanyDTO;
import uz.pdp.companyapp.repository.AddressRepository;
import uz.pdp.companyapp.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company>getAll(){
        return companyRepository.findAll();
    }

    public ApiResponse getOne(Integer id){
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()) { return new ApiResponse("Not found",false);}

        return new ApiResponse("Found",true,byId.get());
    }

    public ApiResponse save(CompanyDTO companyDTO){
        Company company=new Company();
        company.setCorpName(companyDTO.getCorpName());
        company.setDirectorName(companyDTO.getDirectorName());

        Optional<Address> byId = addressRepository.findById(companyDTO.getAddressId());
        if (!byId.isPresent()){
            return new ApiResponse("Address not found",false);
        }
        company.setAddress(byId.get());

        companyRepository.save(company);

        return new ApiResponse("Saved",true,company);

    }
    public ApiResponse edit(Integer id,CompanyDTO companyDTO){
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Company not found",false);
        }
        Company company = byId.get();
        if (companyDTO.getCorpName()!=null){
            company.setCorpName(companyDTO.getCorpName());
        }
        if (companyDTO.getDirectorName()!=null){
            company.setDirectorName(companyDTO.getDirectorName());
        }

        if(companyDTO.getAddressId()!=0){
            Optional<Address> byId1 = addressRepository.findById(companyDTO.getAddressId());
            if (!byId1.isPresent()){
                return new ApiResponse("Address not found",false);
            }
            company.setAddress(byId1.get());
        }

        Company save = companyRepository.save(company);

        return new ApiResponse("Edited",true,save);
    }

    public ApiResponse delete(Integer id){
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Company not found",false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("Deleted",false);
    }
}
