import java.util.ArrayList;
import java.util.List;

public class ListProductDao implements Products {
    //list all the products, find a product by ID, create a product

    //this will list all the products in the database table
    private List<Product> products;

    //constructor for this DAO class
    public ListProductDao(){
        this.products = new ArrayList<>();

        //add some dummy data

        Product hammer = new Product();
        hammer.setId(1);
        hammer.setTitle("Hammer");
        hammer.setPriceInCents(3000);
        hammer.setDescription("A bad hammer");

        Product xbox = new Product();
        xbox.setId(2);
        xbox.setTitle("Xbox");
        xbox.setPriceInCents(50000);
        xbox.setDescription("gaming system");
        products.add(hammer);
        products.add(xbox);
    }

    //Implements our interface requirements (from Products.java)

    @Override
    public Product findById(long id) {
        //we want to return the 'product' object for the ID passed in
        //this will return the full row in the database
        //i.e. id | title | priceInCCents | description (the full row)
        //return products.get
        return products.get((int)id-1);
    }

    @Override
    public long createProduct(Product product) {
        //create anproduct and insert into our array list (eventually, the DB)
        //assign an ID
        product.setId(products.size()+1);//same as auto increment
                                        //this ID will always be unique
        //Add a new product to ArrayList
        products.add(product);//When we call the createProduct method,
        //we are sending in a 'Product' type object
        //this will add that object to the ArrayList
        return product.getId();
    }
}
