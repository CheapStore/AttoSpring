package org.example.service;

import org.example.dto.TransactionDTO;
import org.example.repository.TransactionReporistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionServer {
    @Autowired
    static  TransactionReporistory transactionReporistory;

    public void creat(TransactionDTO transactionDTO) {
        transactionReporistory.creat(transactionDTO);
    }
}
