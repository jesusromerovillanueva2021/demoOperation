package com.example.demoOperation.repository;

import com.example.demoOperation.dto.OperationDto;
import com.example.demoOperation.entity.Operation;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface OperationRepository extends ReactiveCrudRepository<Operation, Integer> {

    //Flux<OperationDto> findByAccountId(int idAccount);
}
