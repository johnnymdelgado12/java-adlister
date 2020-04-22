//this servlet will be used to listen for users trying to view all the products-
// likely by going to a page like '/products

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowProductServlet", urlPatterns = "/products/show")
public class ShowProductServlet extends HttpServlet {
    //this will require a doGet

    //this method will assume there **IS** a product id in the URL parameters

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //check to see if there is an id passed in URL patterns
        long productId = Long.parseLong(req.getParameter("id"));//checking for "id" in URL patterns

        //create he connection to DaoFactory
        Products productDao = DaoFactory.getProductsDao();
        //this will give us access to all the Products Interface methods

        //get the product by its ID from the ListProductsDao
        Product product = productDao.findById(productId);

        //et the attribute "product" top the object we just created
        req.setAttribute("product", product);
        req.getRequestDispatcher("/products/product-show.jsp").forward(req, resp);
    }
}
