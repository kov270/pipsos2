import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("*")
public class ControllerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getPathInfo().substring(1);
        if (name.equals("check"))
            request.getServletContext().getRequestDispatcher("/check").forward(request, response);
        else {
            request.getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xString = request.getParameter("X");
        String yString = request.getParameter("Y");
        String RString = request.getParameter("R");
        if (xString == null || yString == null || RString == null) {
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/check").forward(request, response);
        }
    }

}
