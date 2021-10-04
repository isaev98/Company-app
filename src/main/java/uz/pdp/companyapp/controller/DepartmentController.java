package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.DepartmentDTO;
import uz.pdp.companyapp.service.DepartmentService;

@RestController
@RequestMapping("/api/ditrict")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getOne(@PathVariable Integer id){
        ApiResponse one = departmentService.getOne(id);
        return ResponseEntity.status(one.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(one);
    }

    @PostMapping
    public ResponseEntity<?>save(@RequestBody DepartmentDTO departmentDTO){
        ApiResponse save = departmentService.save(departmentDTO);
        return ResponseEntity.status(save.isSuccess()?HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(save);
    }

    @PutMapping("{id}")
    public ResponseEntity<?>edit(@RequestBody DepartmentDTO departmentDTO,@PathVariable Integer id){
        ApiResponse edit = departmentService.edit(id, departmentDTO);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(edit);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){
        ApiResponse delete = departmentService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(delete);
    }

}
