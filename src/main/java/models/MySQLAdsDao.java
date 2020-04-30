package models;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {

    private Connection connection;

    public MySQLAdsDao() throws SQLException {
        Config config = new Config();
        DriverManager.registerDriver(new Driver());

        this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUserName(),
                config.getPassword()
        );

    }

    @Override
    public List<Ad> all() {
        List<Ad> output = new ArrayList<>();
        String query = "SELECT * FROM adlister";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                output.add(
                        new Ad(
                                rs.getLong("id"),
                                rs.getLong("user_id"),
                                rs.getString("title"),
                                rs.getString("description")
                        )
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public Long insert(Ad ad) {
        long newlyCreatedAd = 0;
        String addAdQuerry = String.format("INSERT INTO adlister (id, user_id, title, description) VALUES (%d, %d, '%s', '%s')",
                ad.getId(),
                ad.getUserId(),
                ad.getTitle(),
                ad.getDescription()
        );
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(addAdQuerry, Statement.RETURN_GENERATED_KEYS);
            ResultSet ks = statement.getGeneratedKeys();
            if (ks.next()) {
                newlyCreatedAd = ks.getLong(1);
            }
            if (newlyCreatedAd != 0){
                ad.setId(newlyCreatedAd);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return newlyCreatedAd;
    }
}
