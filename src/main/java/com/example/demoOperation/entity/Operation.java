package com.example.demoOperation.entity;

import java.util.Date;

import com.example.demoOperation.dto.OperationDto.OperationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("operation")
public class Operation {
    @Id
    private int id;
    private Date dateOperation;
    private double amount;
    OperationType type;
    private int idAccount;
}
