package org.example.repository;

import org.example.dto.TransactionDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
@Repository
public class TransactionReporistory {

    public boolean creat(TransactionDTO transactionDTO) {
        int i=0;
        try {
            Connection connection = org.example.db.DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "insert into  transactions set card_number='%s',amount='%s',profile_phone='%s' ";
            String format = String.format(sql, transactionDTO.getCard_number(), transactionDTO.getAmount(), transactionDTO.getProfile_phone());
             i = statement.executeUpdate(format);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
return i!=0;
    }
}
