package org.example.repository;

import org.example.SpringConfig;
import org.example.db.DatabaseUtil;
import org.example.dto.TerminalDTO;
import org.example.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Repository
@Component
public class TerminalReporistory {
    @Autowired
    private TerminalDTO terminalDTO;
    @Autowired
   private static JdbcTemplate jdbcTemplate;
    public  List<TerminalDTO> getTerminal() {
        String sql = "select * from terminal";
        List<TerminalDTO> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TerminalDTO.class));
        return query;
    }


    public boolean creatTerminal(TerminalDTO terminal) {

        String sql = "insert into terminal(code,address) values ('%s','%s')";
        sql=String.format(sql,terminal.getCode(), terminal.getAddress());
        int res = jdbcTemplate.update(sql);
        return res!=0;
        }


    public boolean chesk(String code) {
        String sql = "select  chesk('%s')";
        sql=String.format(sql,code);
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }

    public boolean updateterminal(TerminalDTO terminladto,String old) {
        String sql = "update terminal set code='%s',address='%s', where code="+old;
        sql=String.format(sql,terminladto.getCode(), terminladto.getAddress());
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }

    public boolean delete(String code) {
        String sql="delete from  card where number="+code;
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }

    public List<TerminalDTO> getTerminalList() {
        String sql = "select * from terminal";
        List<TerminalDTO> terminalDTOList=  jdbcTemplate.query(sql, new BeanPropertyRowMapper(TerminalDTO.class));
        return terminalDTOList;
    }

    public boolean updateterminal_status(String code) {
        String sql = "update terminal set status='NO_ACTIVE' where code='%s' and status='%s'";
        sql=String.format(sql,code, "ACTIVE");
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }
}

