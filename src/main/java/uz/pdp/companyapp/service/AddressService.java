package uz.pdp.companyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     *
     *
     * @return List with all addresses
     */
    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    public ApiResponse getOne(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(address -> new ApiResponse("Found", true, address))
                .orElseGet(() -> new ApiResponse("Not Found", false));
    }

    public ApiResponse save(Address address){
         addressRepository.save(address);
         return new ApiResponse("Saved",true);
    }

    public ApiResponse edit(Address address,Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Address not found",false);
        }
        Address address1 = optionalAddress.get();
        if (address1.getStreet()!=null){
            address1.setStreet(address.getStreet());
        }
        if (address1.getHomeNumber()!=0){
            address1.setHomeNumber(address.getHomeNumber());
        }
        addressRepository.save(address1);
        return new ApiResponse("Saved",true);
    }

    public ApiResponse delete(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Not Found",false);
        }
        addressRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
