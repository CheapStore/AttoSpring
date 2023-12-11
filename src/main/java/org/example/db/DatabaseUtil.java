package org.example.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@Setter
@Getter
@Component
public class DatabaseUtil {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createProfileTable() {

            String sql = "   create table  if not exists  profile(" +
                    "                name varchar not null ," +
                    "                surname varchar not null ," +
                    "                phone varchar(13) primary key," +
                    "                password varchar," +
                    "                created_date timestamp default now()," +
                    "                status varchar default  'ACTIVE'," +
                    "                profile_role varchar" +
                    ");";
         jdbcTemplate.execute(sql);
    }

    public void createCardTable() {

        String sql = "  create table  if not exists  card(" +
                "                number varchar(16)primary key ," +
                "                exp_date date," +
                "                balance double precision," +
                "                status varchar default  'NO_ACTIVE'," +
                "                phone varchar(13) references profile (phone)," +
                "                created_date timestamp default now()" +
                ");";

jdbcTemplate.execute(sql);
    }
    public void createTerminalTable() {


            String sql = "   create table if not exists terminal(" +
                    "        code varchar  primary key," +
                    "        address varchar not null,   " +
                    "        status varchar default 'ACTIVE'," +
                    "        created_date timestamp default now()" +
                    "      );";
       jdbcTemplate.execute(sql);
    }

    public void createTransactionTable() {


            String sql = "   create table if not exists transactions(" +
                    "        card_number varchar(16)  primary key," +
                    "        amount double precision,   " +
                    "        profile_phone   varchar , "+
                    "        terminal_code varchar ," +
                    "        transaction_type varchar," +
                    "        transaction_time timestamp default now()" +
                    "      );";
      jdbcTemplate.execute(sql);
    }

//    public static Connection getConnection() {
//        try {
//            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
//                    "jdbc_user", "123456"); // <2>
//            return con;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }





    }
