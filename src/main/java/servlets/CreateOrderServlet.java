package servlets;

import HelperClasses.HibernateUtil;
import HelperClasses.OrderHelper;
import entity.Client;
import entity.IModel;
import entity.Order;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(urlPatterns = "/CreateOrderServlet")
public class CreateOrderServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List orderList = new OrderHelper().getOrderList();
        String[] header = new OrderHelper().getOrderList().get(0).getTableHeaders();
        int j = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = "";
        for (Object s : orderList) {
            Date oldDate = ((Order) s).getDate();
            Date date = new Date();
            date2 = myFormat.format(date);
            if (oldDate.after(date) || oldDate.toString().equals(date2)) {
                j++;
            }
        }
        Object[][] array = new Object[j][header.length];
        int i = 0;
        for (Object s : orderList) {
            Date oldDate = ((Order) s).getDate();
            Date date = new Date();
            date2 = myFormat.format(date);
            if (oldDate.after(date) || oldDate.toString().equals(date2)) {
                array[i++] = ((IModel) s).getTableRowData();
            }
        }
        TableModel model = new DefaultTableModel(array, header);
        HttpSession sessionForJSP = request.getSession();
        sessionForJSP.setAttribute("tableName", "Order");
        sessionForJSP.setAttribute("tableModel", model);
        sessionForJSP.setAttribute("param", (Integer)0);

        request.getRequestDispatcher("CreateOrder.jsp").forward(request, response);
    }

}
