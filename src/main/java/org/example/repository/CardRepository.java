package org.example.repository;

import org.example.db.DatabaseUtil;
import org.example.dto.CardDTO;
import org.example.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class CardRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public boolean createCard(CardDTO card) {
        String sql = "insert into card(number,exp_date,balance,phone) values ('%s','%s','%s','%s')";
        sql=String.format(sql, card.getNumber(), card.getExp_date(), card.getBalance(), card.getPhone());
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }

    public List<CardDTO> getCardList() {
        String sql = "select * from card";
        List<CardDTO> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CardDTO.class));
        return query;
    }


    public boolean update( CardDTO card) {
        String sql = "update card set number='%s',exp_date='%s'";
        sql=String.format(sql,card.getNumber(), card.getExp_date());
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }


    public boolean chesk(String newnumber) {
        String sql = "select cardchek('%s')";
        sql=String.format(sql,newnumber);
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }

    public boolean updateStatus(String number) {
        String sql = "update  card set status='ACTIVE' where number='%s'";
        sql=String.format(sql,number);
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }


    public boolean deletecard1(String number) {
        String sql="delete from  card where number="+number;
        int update = jdbcTemplate.update(sql);
        return update!=0;

    }

    public void createCardTable() {
        String sql = "create table  if not exists  card("+
                "                number varchar(16)primary key ," +
                "                exp_date date," +
                "                balance double precision," +
                "                status varchar default  'NO_ACTIVE'," +
                "                phone varchar(13) references profile (phone)," +
                "                created_date timestamp default now()" +
                ");";
        jdbcTemplate.execute(sql);
    }

}
