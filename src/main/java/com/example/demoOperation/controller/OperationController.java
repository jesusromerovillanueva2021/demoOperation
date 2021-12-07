package com.example.demoOperation.controller;

import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialArray;

import com.example.demoOperation.dto.OperationDto;
import com.example.demoOperation.service.OperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/operations")
public class OperationController {
    @Autowired
    private OperationService service;

    @GetMapping
    public Flux<OperationDto> getOperations(){
        return service.getOperations();
    }

    @GetMapping("/{id}")
    public Mono<OperationDto> getOperation(@PathVariable int id){
        return service.getOperation(id);
    }

    @PostMapping
    public Mono<OperationDto> saveOperation(@RequestBody Mono<OperationDto> operationDtoMono){
        return service.saveOperation(operationDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<OperationDto> updateOperation(@PathVariable int id, @RequestBody Mono<OperationDto> operationDtoMono) {
        return service.updateOperation(operationDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteOperation(@PathVariable int id){
        return service.deleteOperation(id);
    }

    @GetMapping("/operationsbyaccount/{idAccount}")
    public Mono<List<OperationDto>> getOperationsByAccount(@PathVariable int idAccount){   
    	return service.getOperationsByIdAccount(idAccount);
    }
    
    @GetMapping("/depositsbyaccount/{idAccount}")
    public Mono<List<Double>> getDepositsByIdAccount(@PathVariable int idAccount){
    	return service.getDepositsByIdAccount(idAccount);
    }
    
    @GetMapping("/retirementsbyaccount/{idAccount}")
    public Mono<List<Double>> getRetirementsByIdAccount(@PathVariable int idAccount){
    	return service.getRetirementsByIdAccount(idAccount);
    }
    
    /*@GetMapping("/sumdepositsbyaccount/{idAccount}")
    public Map<Double, Double> getSumDepositsByIdAccount(@PathVariable int idAccount) {
    	return service.getSumDepositsByIdAccount(idAccount);
    }*/
    
}
