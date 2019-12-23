package servlets;

import HelperClasses.HibernateUtil;
import HelperClasses.OrderHelper;
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
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/CreateOrderServlet")
public class CreateOrderServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List orderList = new OrderHelper().getOrderList();
        String[] header = new OrderHelper().getOrderList().get(0).getTableHeaders();
        int j = 0;
        for (Object s : orderList) {
            Date oldDate = ((Order) s).getDate();
            Date date = new Date();
            if (oldDate.after(date)) {
                j++;
            }
        }
        Object[][] array = new Object[j][header.length];
        int i = 0;
        for (Object s : orderList) {
            Date oldDate = ((Order) s).getDate();
            Date date = new Date();
            if (oldDate.after(date)) {
                array[i++] = ((IModel) s).getTableRowData();
            }
        }
        TableModel model = new DefaultTableModel(array, header);
        HttpSession sessionForJSP = request.getSession();
        sessionForJSP.setAttribute("tableName", "Order");
        sessionForJSP.setAttribute("tableModel", model);

//        for (Order order: new OrderHelper().getOrderList()
//             ) {
//            try {
//                System.out.println("ORDER = " + order.);
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//        }
        request.getRequestDispatcher("CreateOrder.jsp").forward(request, response);
    }

}
