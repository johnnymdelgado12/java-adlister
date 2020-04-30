package models;


import java.sql.*;

import com.mysql.cj.jdbc.Driver;



public class JbdcLecture {


    public static void main(String[] args) throws SQLException {
        //Instantiate a models.Config object
        Config config = new Config();

        // set up our database driver and  make a connection
        DriverManager.registerDriver(new Driver());

        // get a connection object
        Connection conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUserName(),
                config.getPassword()
        );

        // create a statement Object
        Statement stmt = conn.createStatement();

        // execute some sort of query
        // create a query string to get everything in the contacts table
        String contactsQuery = "SELECT * FROM contacts";

        ResultSet rs = stmt.executeQuery(contactsQuery);

        // display what's actually in the result set (rs) above
        while (rs.next()){
            System.out.println(rs.getString("first_name") + " | " + rs.getString("last_name") + " | " + rs.getString("phone_number"));
        }

        // if we want to create a row to our database we will:
        // 1. create a Contact object (bean)
        // 2. use our DAO to add our new contact using the
        // 3. create an SQL query to insert that Contact object into our database, as a new row
        // 4. to add to our DAO - instantiate ContactsListDao,  and ise the saveContact() method

        Contacts clDao = DaoFactory.getContactsDao();// this data access object allows us to interact with all the contacts

        //Instantiate a new Contact object
        Contact casey = new Contact(
                "Casey"
                , "Friday",
                "2105557777"
        );

        long newContactId = clDao.saveContact(casey);
        Contact newlyCreatedContact = clDao.getContactById(newContactId);

        //INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('casey', 'friday', '2105557777');

        String addContactQuerry = String.format("INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('%s', '%s', '%s')",
                newlyCreatedContact.getFirstName(),
                newlyCreatedContact.getLastName(),
                newlyCreatedContact.getPhoneNumber(),
                newlyCreatedContact.getId()
        );

        System.out.println("This is the query string we'll be sending to mySql:\n");
        System.out.println(addContactQuerry);

        // now lets actually execute this SQL query to add the new contact to our database
        stmt.executeUpdate(addContactQuerry, Statement.RETURN_GENERATED_KEYS);

        // if we add a Statement.RETURN_GENERATED_KEYS, we can work woth the actual MySQL DB tabke row ID's, and reassign
        //those ID's to our Contact objects here in our Java code
        long insertedRowId = 0;
        ResultSet ks = stmt.getGeneratedKeys();
        if (ks.next()){
            insertedRowId = ks.getLong(1);
            System.out.println("The ID of the newly inserted contact is: " + ks.getLong(1));
        }

        System.out.println("Before doing the MySql id check, " + newlyCreatedContact.getFirstName() + "'s id was at: " +
                newlyCreatedContact.getId());

        int numRows = stmt.executeUpdate(addContactQuerry);
        System.out.println("Number of rows affected: " + numRows);

        // Check to see if the id was returned, or if insertedRowId is still at its default of '0'
        if (insertedRowId != 0){
            newlyCreatedContact.setId(insertedRowId);
        }

        System.out.println("After doing the MySql id check, " + newlyCreatedContact.getFirstName() + "'s id is: " +
                newlyCreatedContact.getId());


    }
}
