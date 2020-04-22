//this DAO class will provide access to our model data, but
//will no require accessing the ListProductionDao class directly
//instead e will reference the interface

public class DaoFactory {

    private static Products productsDao;

    public static Products getProductsDao(){
        if(productsDao == null){
            productsDao = new ListProductDao();//this is the ONLY reference to the ListProductsDao class
        }
        //if it is not null, return the productsDao that already exist
        //this is an instance of the ListProductsDao class
        return productsDao;
    }
}
