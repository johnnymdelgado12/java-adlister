public interface Products {

    //this method will return an object of type 'Product',
    //search by 'id'
    Product findById(long id);

    //this method will insert a 'Product' into our table
     //he return of this will be said products ID
    long createProduct(Product product);
}