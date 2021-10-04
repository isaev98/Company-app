package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.entity.Department;
import uz.pdp.companyapp.entity.Worker;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.WorkerDTO;
import uz.pdp.companyapp.repository.AddressRepository;
import uz.pdp.companyapp.repository.DepartmentRepository;
import uz.pdp.companyapp.repository.WorkerRepository;

import java.net.InetAddress;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getAll(){
        return workerRepository.findAll();

    }
    public ApiResponse getOne(Integer id){
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Worker not found",false);
        }
        return new ApiResponse("Found",true,byId);
    }

    public ApiResponse save(WorkerDTO workerDTO){
        Worker worker=new Worker();
        worker.setName(workerDTO.getName());
        worker.setPhoneNumber(workerDTO.getPhoneNumber());

        Optional<Address> optionalAddress = addressRepository.findById(workerDTO.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Address not found",false);
        }
        worker.setAddress(optionalAddress.get());

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getAddressId());
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Department not found",false);
        }
        worker.setDepartment(optionalDepartment.get());

        workerRepository.save(worker);
        return new ApiResponse("Saved",true,worker);
    }

    public ApiResponse edit(Integer id,WorkerDTO workerDTO){
        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (!workerOptional.isPresent()){
            return new ApiResponse("Worker not found",false);
        }
        Worker worker = workerOptional.get();
        worker.setName(workerDTO.getName());
        worker.setPhoneNumber(workerDTO.getPhoneNumber());

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Department not Found",false);
        }
        worker.setDepartment(optionalDepartment.get());

        Optional<Address> optionalAddress = addressRepository.findById(workerDTO.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Address not found",false);
        }
        worker.setAddress(optionalAddress.get());

        workerRepository.save(worker);
        return new ApiResponse("Edited",true,worker);
    }

    public ApiResponse delete(Integer id){
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isPresent()){
            return new ApiResponse("Not found",false);
        }
        workerRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
