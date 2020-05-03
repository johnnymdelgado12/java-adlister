package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import models.Config;

import java.sql.*;

public class MySQLUsersDao implements Users{

    private Connection connection = null;
    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUserName(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String query ="SELECT * FROM users WHERE username LIKE ?";
            String search = "%" + username + "%";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,search);;

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                 );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Long insert(User user) {
        long id = -1;
        try {
            PreparedStatement stmt = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getLong(1);
        } catch (SQLException e){
            System.err.printf(e.getMessage());
        }
        return id;
    }


    private String getInsertQuery(){
        return "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    }
}


