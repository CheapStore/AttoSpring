package org.example.repository;

import org.example.db.DatabaseUtil;
import org.example.dto.ProfileDTO;
import org.example.enums.ProfileRole;
import org.example.enums.Status;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class ProfileRepository {

    public ProfileDTO login(ProfileDTO profileDTO) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "select * from profile where phone=? and password=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,profileDTO.getPhone());
            preparedStatement.setString(2,profileDTO.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                ProfileDTO profile=new ProfileDTO();
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                profile.setStatus(Status.valueOf(resultSet.getString("status")));
                profile.setProfileRole(ProfileRole.valueOf(resultSet.getString("profile_role")));
                connection.close();
                return profile;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean registration(ProfileDTO profile) {
        int res=0;
        try {
        Connection connection = DatabaseUtil.getConnection();
        String sql = "insert into profile(name,surname,phone,password,profile_role) values (?,?,?,?,?)";
              PreparedStatement preparedStatement = connection.prepareStatement(sql);

             preparedStatement.setString(1, profile.getName());
             preparedStatement.setString(2, profile.getSurname());
             preparedStatement.setString(3, profile.getPhone());
             preparedStatement.setString(4, profile.getPassword());
             preparedStatement.setString(5, profile.getProfileRole().toString());
            res = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res!=0;

    }

    public List<ProfileDTO> getprfile_list() {
        List<ProfileDTO> profileDTOList = new LinkedList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            Statement statement = connection.createStatement();

            String sql = "select * from profile";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ProfileDTO profileDTO=new ProfileDTO();
                profileDTO.setName(resultSet.getString("name"));
                profileDTO.setSurname(resultSet.getString("surname"));
                profileDTO.setPhone(resultSet.getString("phone"));
                profileDTO.setPassword(resultSet.getString("password"));
                profileDTO.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                profileDTO.setStatus(Status.valueOf(resultSet.getString("status")));
                profileDTO.setProfileRole(ProfileRole.valueOf(resultSet.getString("profile_role")));
                profileDTOList.add(profileDTO);
            connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileDTOList;

    }


    public boolean updateProfil(ProfileDTO profileDTO, String psw) {
        int i=0;
        try {
            Connection connection = DatabaseUtil.getConnection();
        String sql="update  profile set phone=?,status=? where password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,profileDTO.getPhone());
        preparedStatement.setString(2, profileDTO.getStatus().name());
        preparedStatement.setString(3,psw);
           i = preparedStatement.executeUpdate();
           preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return i!=0;
    }
}
