import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowProductServlet", urlPatterns = "/ads")
public class ShowAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //check to see if there is an id passed in URL patterns
        long adId = Long.parseLong(req.getParameter("id"));//checking for "id" in URL patterns

        //create he connection to DaoFactory
        Ads adDao = DaoFactory.getAdsDao();
        //this will give us access to all the Products Interface methods

        //get the product by its ID from the ListProductsDao
        Ad ad = (Ad) adDao.findById(adId);

        //et the attribute "product" top the object we just created
        req.setAttribute("ad", ad);
        req.getRequestDispatcher("/products/product-show.jsp").forward(req, resp);

    }
}




    //this will require a doGet

    //this method will assume there **IS** a product id in the URL parameters


