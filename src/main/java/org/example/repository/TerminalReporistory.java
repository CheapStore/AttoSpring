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
   private static JdbcTemplate jdbcTemplate;
    public  List<TerminalDTO> getTerminal() {
        String sql = "select * from terminal";
        List<TerminalDTO> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TerminalDTO.class));
        return query;
    }

    public boolean creatTerminal(TerminalDTO terminal) {
        String sql = "insert into terminal(code,address) values (?,?)";
        int res = jdbcTemplate.update(sql, terminal.getCode(), terminal.getAddress());
        return res!=0;
        }


    public boolean chesk(String code) {
        boolean execute = false;
        try {
            Connection connection = org.example.db.DatabaseUtil.getConnection();
            String sql = "select  chesk(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"'"+code+"'");
            execute = preparedStatement.execute();
            System.out.println("execute = " + execute);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return execute;

    }

    public boolean updateterminal(TerminalDTO terminladto,String old) {
        String sql = "update terminal set code=?,address=? where code=?";
        int update = jdbcTemplate.update(sql, terminladto.getCode(), terminladto.getAddress(), old);
        return update!=0;
    }

    public boolean delete(String code) {
        String sql="delete from  card where number=?";
        int update = jdbcTemplate.update(sql, code);
        return update!=0;
    }

    public List<TerminalDTO> getTerminalList() {
        String sql = "select * from terminal";
        List <TerminalDTO>query = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TerminalDTO.class));
        return query;

    }

    public boolean updateterminal_status(String code) {
        String sql = "update terminal set status='NO_ACTIVE' where code=? and status=?";
        int update = jdbcTemplate.update(sql, code, "ACTIVE");
        return update!=0;
    }
}

