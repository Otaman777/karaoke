package servlets;

import HelperClasses.DiskHelper;
import HelperClasses.GroupHelper;
import HelperClasses.HibernateUtil;
import entity.IModel;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ListGroupServlet")
public class ListGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List groupList = new GroupHelper().getGroupList();
        String[] header = new GroupHelper().getGroupList().get(0).getTableHeaders();
        Object[][] array = new Object[groupList.size()][header.length];
        int i = 0;
        for (Object s : groupList) {
            array[i++] = ((IModel) s).getTableRowData();
        }
        TableModel model = new DefaultTableModel(array, header);
        HttpSession sessionForJSP = request.getSession();
        sessionForJSP.setAttribute("tableName", "Group");
        sessionForJSP.setAttribute("tableModel", model);
        request.getRequestDispatcher("ListTables.jsp").forward(request, response);
    }
}
