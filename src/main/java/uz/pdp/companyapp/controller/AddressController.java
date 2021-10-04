package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.entity.Address;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        List<Address> all = addressService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        ApiResponse one = addressService.getOne(id);
        return ResponseEntity.status(one.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(one);
    }

    @PostMapping
    public ResponseEntity<?>Save(@RequestBody Address address){
        ApiResponse save = addressService.save(address);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/{}id")
    public ResponseEntity<?>Edit(@RequestBody Address address,@PathVariable Integer id){
        ApiResponse apiResponse = addressService.edit(address, id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK :HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>Delete(@PathVariable Integer id){
        ApiResponse delete = addressService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(delete);

    }


}
