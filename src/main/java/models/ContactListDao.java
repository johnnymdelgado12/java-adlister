package models;

import java.util.ArrayList;
import java.util.List;

public class ContactListDao implements Contacts {
    //we need a list variable to hold all the contacts
    private List<Contact> contacts = new ArrayList<>();



    // List all the contacts (List<models.Contacts>)
    @Override
    public List<Contact> getContacts(){
        return contacts;
    }
    //method for saving a contact
    //will essentially get the length of List<models.Contact> and +1
    @Override
    public long saveContact(Contact contact){
        //check to see if this is the first contact, and if so, add 1
        if(contact.getId() == 0){
            //if we are setting up the very first contact, we want to add 1 so our SQL table
            //doesnt start with a primary key of 0, but rather start with 1
            contact.setId(contacts.size() + 1);
            contacts.add(contact);
        }else {
            //we already have the correct id coming from the MySQL table, so lets just set the contact
            //in the ArrayList (contacts) with the ID that was passed in
            contacts.set((int) contact.getId() - 1, contact);
        }
        //return the ID of the newly saved contact
        return contact.getId();
    }

    //method for deleting a contact
    public void deleteContactById(long id){
        //dont need a return value
        //do a .remove to the passed in ID to remove that contact from the Arraylist
        contacts.remove((int) id - 1);
    }


    //method for getting contact by id
    @Override
    public Contact getContactById(long id){
        return contacts.get((int) id - 1);
    }


    public static void main(String[] args) {

        Contacts contactDao = new ContactListDao();
        System.out.println();
        List<Contact>allContacts = contactDao.getContacts();

        for (Contact contact : allContacts){
            System.out.println(contact.getFirstName());
        }

    }
}
