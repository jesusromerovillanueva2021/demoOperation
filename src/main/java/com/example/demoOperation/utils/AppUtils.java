package com.example.demoOperation.utils;

import com.example.demoOperation.dto.OperationDto;
import com.example.demoOperation.entity.Operation;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

public class AppUtils {
    public static OperationDto entityToDto(Operation operation){
        OperationDto operationDto=new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);
        return operationDto;
    }

    public static Operation dtoToEntity(OperationDto operationDto){
        Operation operation=new Operation();
        BeanUtils.copyProperties(operationDto, operation);
        return operation;
    }
}
