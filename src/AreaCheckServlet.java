import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "/check")
public class AreaCheckServlet extends HttpServlet {

    private ServletConfig config;

    public static class Point {
        double x;
        double y;
        double R;
        boolean isInArea;

        Point(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.R = r;
        }
    }

    private static boolean checkArea(double x, double y, double R) {
        if (x >= 0 && y <= 0 && y >= x / 2 - R / 2) {
            return true;
        }
        if (x >= 0 && y >= 0 && x * x + y * y <= R * R) {
            return true;
        }
        if (x <= 0 && y <= 0 && x >= -R && y >= -R) {
            return true;
        }
        return false;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder data = new StringBuilder();
        HttpSession session = request.getSession();
        if (session.getAttribute("data") == null) {
            data.append("No Data");
        } else {
            ArrayList<Point> pts = (ArrayList<Point>) session.getAttribute("data");
            printPoints(data, pts);
        }
        session.setAttribute("table", data.toString());
        getServletContext().getRequestDispatcher("/results.jsp").forward(request, response);
    }

    private static void printPoints(StringBuilder data, ArrayList<Point> list) {
        for (Point p : list) {
            data.append("<tr>");

            data.append("<td>");
            data.append(String.format("%.2f", p.x));
            data.append("</td>");

            data.append("<td>");
            data.append(String.format("%.2f", p.y));
            data.append("</td>");

            data.append("<td>");
            data.append(String.format("%.2f", p.R));
            data.append("</td>");

            data.append("<td>");
            data.append(p.isInArea ? "Yes" : "No");
            data.append("</td>");

            data.append("</tr>");
        }
    }

    private static void printPoint(StringBuilder data, Point p) {
        data.append("<tr>");

        data.append("<td>");
        data.append(String.format("%.2f", p.x));
        data.append("</td>");

        data.append("<td>");
        data.append(String.format("%.2f", p.y));
        data.append("</td>");

        data.append("<td>");
        data.append(String.format("%.2f", p.R));
        data.append("</td>");

        data.append("<td>");
        data.append(p.isInArea ? "Yes" : "No");
        data.append("</td>");

        data.append("</tr>");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("data") == null) {
            session.setAttribute("data", new ArrayList<Point>());
        }

        try {
            Point p = new Point(
                    Double.parseDouble(request.getParameter("X")),
                    Double.parseDouble(request.getParameter("Y")),
                    Double.parseDouble(request.getParameter("R"))
            );

            p.isInArea = checkArea(p.x, p.y, p.R);
            ((ArrayList<Point>) session.getAttribute("data")).add(p);
        } catch (Exception e) {
            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

        StringBuilder data = new StringBuilder();
        ArrayList<Point> pts = (ArrayList<Point>) session.getAttribute("data");
        printPoints(data, pts);
        session.setAttribute("table", data.toString());

        if (request.getParameter("silent") != null && request.getParameter("silent").equals("on")) {

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            StringBuilder builder = new StringBuilder();
            printPoint(builder, pts.get(pts.size() - 1));

            out.print("{" + "\"in_area\":" + (pts.get(pts.size() - 1).isInArea ? "true" : "false") + "," +
                    "\"data\":" + "\"" + builder.toString() + "\"" + "}");
            out.flush();
        } else {
            getServletContext().getRequestDispatcher("/results.jsp").forward(request, response);
        }
    }

}
