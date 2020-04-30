package models;//Interface for our models.Contact bean (to be implemented by our DAO)

import models.Contact;

import java.util.List;

public interface Contacts {
    // List all the contacts (List<models.Contacts>)
    List<Contact> getContacts();
    //method for saving a contact
    //will essentially get the length of List<models.Contact> and +1
    long saveContact(Contact contact);
    //method for deleting a contact
    void deleteContactById(long id);
    //method for getting contact by id
    Contact
    getContactById(long id);
}
