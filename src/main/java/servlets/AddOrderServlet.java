package servlets;

import HelperClasses.ClientHelper;
import HelperClasses.OrderHelper;
import entity.Client;
import entity.IModel;
import entity.Order;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.List;

@WebServlet(urlPatterns = "/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer a = Integer.parseInt(request.getParameter("param"));
        if(request.getParameter("add_order") != null){
            //calc the price
            int k = 50;
            //getting parameters
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String during = request.getParameter("during");
            //cheking fields
            boolean checked = true;
            //checking number fields
            try{
                Integer.parseInt(phone);
                Integer.parseInt(time);
                Integer.parseInt(during);
            }catch(Exception e){
                checked = false;
            }
            if(checked){
                //checking date
                List<Order> orders = new OrderHelper().getOrderList();
                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat myFormatDay = new SimpleDateFormat("dd");
                Integer addingTime = Integer.parseInt(time);
                Integer addingDuring = Integer.parseInt(during);
                if(addingTime + addingDuring > 24){
                    checked = false;
                }
                for(Order s:orders){
                /*if(((date.equals(myFormat.format(s.getDate()))) ) &&
                        ((Integer.parseInt(time) >= s.getTime() ) || (((Integer.parseInt(time) < (s.getTime())) && Integer.parseInt(time) >= s.getTime() + s.getDuration()) && Integer.parseInt(time) + Integer.parseInt(during) > s.getTime() + s.getDuration()) )&&
                        ((Integer.parseInt(time) < (s.getTime() + s.getDuration())) && Integer.parseInt(time) + Integer.parseInt(during) <= s.getTime() + s.getDuration() ) &&
                        (Integer.parseInt(time) + Integer.parseInt(during) > s.getTime()) ||
                (Integer.parseInt(time) < s.getTime() && Integer.parseInt(time) + Integer.parseInt(during) > s.getTime() + s.getDuration())){
                    checked = false;
                    break;
                }*/
                    if(!checked){
                        break;
                    }
                    if(date.equals(myFormat.format(s.getDate()))){
                        Integer addedTime = s.getTime();
                        Integer addedDuring = s.getDuration();
                        for(int i = 0; i <= addedDuring; i++){
                            for(int j = 0; j <= addingDuring; j++){
                                if(i == 0 && j == addingDuring){
                                    break;
                                }
                                if(j == 0 && i == addedDuring){
                                    break;
                                }
                                if(addedTime + i == addingTime + j){
                                    checked = false;
                                }
                            }
                        }
                    }
                }
            }


            if(checked){
                //parsing date
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date1 = null;
                try {
                    date1 = formatter.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //calculating price
                int price = k * Integer.parseInt(during);
                //checking on existing client in DB using phone
                boolean found = false;
                Client cl = null;
                List<Client> clientList = new ClientHelper().getClientList();
                for(Client s:clientList){
                    if(s.getPhone().equals(phone)){
                        cl = s;
                        found = true;
                        break;
                    }
                }
                //adding client
                Client client = null;
                if(!found){
                    cl = new Client();
                    cl.setName(name);
                    cl.setPhone(phone);
                    client = new ClientHelper().addClient(cl);
                    cl.setIdClient(client.getId());
                }
                //adding order
                Order order = new Order(date1,Integer.parseInt(during), price, Integer.parseInt(time), cl);
                new OrderHelper().addOrder(order);
                a = 1;
            }else{
                a = 2;
            }

        }
        //getList
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
        sessionForJSP.setAttribute("param", a);

        request.getRequestDispatcher("CreateOrder.jsp").forward(request, response);
    }
}
