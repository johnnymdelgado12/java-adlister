import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAdsServlet", urlPatterns = "/ads")
public class ShowAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //create he connection to DaoFactory
        Ads adsDao = DaoFactory.getAdsDao();


        //this will give us access to all the Ads Interface methods

        List<Ad> ads = adsDao.all();

        //et the attribute "product" top the object we just created
        req.setAttribute("ads", ads);
        req.getRequestDispatcher("/ads/index.jsp").forward(req, resp);

    }
}




    //this will require a doGet

    //this method will assume there **IS** a product id in the URL parameters


