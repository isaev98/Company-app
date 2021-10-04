package uz.pdp.companyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyapp.payload.ApiResponse;
import uz.pdp.companyapp.payload.WorkerDTO;
import uz.pdp.companyapp.service.WorkerService;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(workerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        ApiResponse one = workerService.getOne(id);
        return ResponseEntity.status(one.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(one);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody WorkerDTO workerDTO){
        ApiResponse save = workerService.save(workerDTO);
        return ResponseEntity.status(save.isSuccess()?HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody WorkerDTO workerDTO){
        ApiResponse edit = workerService.edit(id, workerDTO);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){
        ApiResponse delete = workerService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(delete);
    }
}
