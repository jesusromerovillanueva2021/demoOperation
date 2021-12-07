package com.example.demoOperation.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demoOperation.dto.OperationDto;
import com.example.demoOperation.dto.OperationDto.OperationType;
import com.example.demoOperation.entity.Operation;
import com.example.demoOperation.repository.OperationRepository;
import com.example.demoOperation.utils.AppUtils;
import com.mongodb.client.model.Collation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.web.reactive.server.DefaultWebFluxTagsProvider;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperationService {
    @Autowired
    private OperationRepository repository;

    public Flux<OperationDto> getOperations(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<OperationDto> getOperation(int id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<OperationDto> saveOperation(Mono<OperationDto> operationDtoMono){
        return operationDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<OperationDto> updateOperation(Mono<OperationDto> operatinDtoMono, int id){
        return repository.findById(id)
                .flatMap(p->operatinDtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);       
    }

    public Mono<Void> deleteOperation(int id){
        return repository.deleteById(id);
    }

    public Mono<List<OperationDto>> getOperationsByIdAccount(int idAccount){
    	Flux<OperationDto> operationsAll=repository.findAll()
    			.map(AppUtils::entityToDto);
    	
    	Mono<List<OperationDto>> operationFiltered=Flux.from(operationsAll)
    			.filter(o->o.getIdAccount()==idAccount)
    			.collect(Collectors.toList());
		return operationFiltered;
    }
    
    /*public double getSumDepositsByIdAccount(int idAccount){
    	Flux<OperationDto> operationsAll=repository.findAll()
    			.map(AppUtils::entityToDto);
    	
    	Mono<List<Double>> listAmounts=Flux.from(operationsAll
    			.filter(o->o.getIdAccount()==idAccount)
    			.filter(o->o.getType()==OperationType.Deposit)
    			.map(o->o.getAmount()))
    			.collect(Collectors.toList());  			

		return 0;
    }*/
    
    public Mono<List<Double>> getDepositsByIdAccount(int idAccount){
    	Flux<OperationDto> operationsAll=repository.findAll()
    			.map(AppUtils::entityToDto); 	
    	Mono<List<Double>> listAmounts=Flux.from(operationsAll
    			.filter(o->o.getIdAccount()==idAccount)
    			.filter(o->o.getType()==OperationType.Deposit)
    			.map(o->o.getAmount()))
    			.collect(Collectors.toList());
		return listAmounts;
    }
    
    public Mono<List<Double>> getRetirementsByIdAccount(int idAccount){
    	Flux<OperationDto> operationsAll=repository.findAll()
    			.map(AppUtils::entityToDto); 	
    	Mono<List<Double>> listAmounts=Flux.from(operationsAll
    			.filter(o->o.getIdAccount()==idAccount)
    			.filter(o->o.getType()==OperationType.Retirement)
    			.map(o->o.getAmount()))
    			.collect(Collectors.toList());
		return listAmounts;
    }
    
}
