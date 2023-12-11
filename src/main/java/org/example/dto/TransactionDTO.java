package org.example.dto;

import lombok.*;
import org.example.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class TransactionDTO {
//    card_number,amount,terminal_code,type,created_date
    private String card_number;
    private double amount;
    private String profile_phone;
    private String terminal_code;
    private TransactionType transactionType;
    private LocalDate transactionTime;
}
