package org.example.repository;

import org.example.db.DatabaseUtil;
import org.example.dto.ProfileDTO;
import org.example.enums.ProfileRole;
import org.example.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class ProfileRepository {
    @Autowired
   private JdbcTemplate jdbcTemplate;

    public List<ProfileDTO> login(ProfileDTO profileDTO) {
        String sql = "select * from profile where phone='%s' and password='%s'";
        sql=String.format(sql,profileDTO.getPhone(),profileDTO.getPassword());
        List<ProfileDTO> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProfileDTO.class));
        return query;

    }

    public boolean registration(ProfileDTO profile) {
        String sql = "insert into profile (name,surname,phone,password,profile_role) values ('%s','%s','%s','%s','%s')";
        sql=String.format(sql,profile.getName(), profile.getSurname(), profile.getPhone(), profile.getPassword(), profile.getProfileRole());
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }


    public List<ProfileDTO> getprfile_list() {
        String sql = "select * from profile";
        List<ProfileDTO> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProfileDTO.class));
        return query;
    }


    public boolean updateProfil(ProfileDTO profileDTO, String psw) {
        String sql="update  profile set phone='%s',status='%s' where password='%s'";
        sql=String.format(sql,profileDTO.getPhone(), profileDTO.getStatus(), psw);
        int update = jdbcTemplate.update(sql);
        return update!=0;
    }
}
