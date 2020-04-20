import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.applet.Applet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {


        String name = req.getParameter("name");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (name != null){
            try {
                out.println("<h1>Hello " + name + "!</h1>");
            } catch (Exception e) {
                response.resetBuffer();
                out.println("<h1>Hello, World!</h1>");
            }
        } else {
            out.println("<h1>Hello, World!</h1>");
        }

//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/banner");
//        if (dispatcher != null){
//            dispatcher.include(req, response);
//        }

    }
//    @Override
//    public void destroy(){
//        getServletContext().log("destroy() called");
//    }
}
