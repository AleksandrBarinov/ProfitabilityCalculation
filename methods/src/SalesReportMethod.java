import bean.Product;
import service.SalesReportService;
import service.impl.SalesReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/salesreport")
public class SalesReportMethod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String date = req.getParameter("date");

        System.out.println(name);
        System.out.println(date);

        SalesReportService salesReportService = SalesReportServiceImpl.getInstance();
        salesReportService.generateReport(
                new Product(name,null),
                date
        );

        resp.getWriter().write("salesreport will be there");
    }
}
