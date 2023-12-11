package org.example.repository;

import org.example.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
@Repository
public class TransactionReporistory {
    @Autowired
   private JdbcTemplate jdbcTemplate;

    public boolean creat(TransactionDTO transactionDTO) {
        String sql = "insert into  transactions set card_number='%s',amount='%s',profile_phone='%s' ";
        int update = jdbcTemplate.update(sql, transactionDTO.getCard_number(), transactionDTO.getAmount(), transactionDTO.getProfile_phone());
        return update!=0;
    }
}
