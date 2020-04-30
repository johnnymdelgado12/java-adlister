import com.mysql.cj.jdbc.Driver;
import models.Config;
import models.Contact;
import models.Contacts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLContactsDao implements Contacts {
    private Connection conn;

    public MySQLContactsDao() throws SQLException {

        Config config = new Config();

        // set up our database driver and  make a connection
        DriverManager.registerDriver(new Driver());

        // get a connection object
        this.conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUserName(),
                config.getPassword()
        );


    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> output = new ArrayList<>();
        //query the SQL DB table for all contacts
        String query = "SELECT * FROM contacts";

        //takre into account the SQL Exception that needs to be handled
        try {
            //create statement object
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // iterate through our result set and add each contact to our 'Contact' bean
            while (rs.next()) {
                output.add(
                        new Contact(
                                rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("phone_number")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;//returns the List<Contact> of all the contacts we imported from the MySQL DB
    }

    @Override
    public long saveContact(Contact contact) {


        long newlyCreatedUser = 0;
        String addContactQuerry = String.format("INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('%s', '%s', '%s')",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber(),
                contact.getId()
        );


        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(addContactQuerry, Statement.RETURN_GENERATED_KEYS);


            ResultSet ks = stmt.getGeneratedKeys();
            if (ks.next()) {
                newlyCreatedUser = ks.getLong(1);
            }
            if (newlyCreatedUser != 0){
                contact.setId(newlyCreatedUser);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return newlyCreatedUser;

    }

    @Override
    public void deleteContactById(long id) {
        String query = String.format("DELETE FROM contacts WHERE id = %d", id);

        try {
            Statement stmt = conn.createStatement();
            boolean success = stmt.execute(query);
            if (success){
                System.out.println("Contact has been deleted");
            } else {
                System.out.println("Check for syntax errors");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Contact getContactById(long id) {
        Contact returnContact = new Contact();
        String query = String.format("SELECT * FROM contacts WHERE id = %d", id);

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                returnContact.setId(id);
                returnContact.setFirstName(rs.getString("first_name"));
                returnContact.setLastName(rs.getString("last_name"));
                returnContact.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return returnContact;
    }

}
