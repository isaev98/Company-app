package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.CompanyDTO;
import uz.pdp.companyapp.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getOne(@PathVariable Integer id){
        ApiResponse one = companyService.getOne(id);
        return ResponseEntity.status(one.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(one);
    }


    @PostMapping
    public ResponseEntity<?>save(@RequestBody CompanyDTO companyDTO){
        ApiResponse save = companyService.save(companyDTO);
        return ResponseEntity.status(save.isSuccess()?HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(save);

    }

    @PutMapping
    public ResponseEntity<?>edit(@PathVariable Integer id,@RequestBody CompanyDTO companyDTO){
        ApiResponse edit = companyService.edit(id, companyDTO);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(edit);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){
        ApiResponse delete = companyService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(delete);
    }
}
