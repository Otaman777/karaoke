<%@page import="javax.swing.table.TableModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <meta charset="UTF-8">
    <title>Create Order</title>
    <style>
        .margins {
            margin: 10px
        }
        .text {
            color: rgba(0, 112, 249, 0.95);
            font-family: Bradley Hand ITC;
            font-weight: bold;
            font-size: xx-large;
        }
        .button {
            text-align: center;
            background-color: #0070f9; /* Green */
            border: none;
            color: #f984d5;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 36px;
        }
    </style>
</head>
<body style="background-image: url(images/backgr.jpg)">
<%
    String tableName = (String) (session.getAttribute("tableName"));
    TableModel tableModel = (TableModel) (session.getAttribute("tableModel"));
%>

<div style="text-align: center">
    <h1 class="text" style="font-size: 65pt">ORDER</h1>
</div>

    <form action="CreateOrderServlet" name="fillingInform" method="POST" style="text-align: center">
        <label class="text">Your name: <input type="text" class="margins" style=" border: #FF97FF"></label>
        <br><label class="text">Phone: <input type="text" class="margins" style=" border: #FF97FF"></label>
        <br><label class="text">Date: <input type="date" class="margins" style=" border: #FF97FF"><input type="text" style=" border: #FF97FF"></label>
        <br><label class="text">Lasting: <input type="text" class="margins" style=" border: #FF97FF">hour(s) (Minimum 1 hour)</label>
        <br><button class="button text" type="submit" value="order"> </button>
        <!-- onclick="http://localhost:8080/Karaoke/test" -->
    </form>
<h3 style="text-align: center" class="text"> Table <%=tableName%></h3>
<table class="table table-hover" style='width:30%;margin:0 auto;'>
    <tr>
        <%
            int nCol = tableModel.getColumnCount();
            for (int i = 0; i < nCol; i++) {
                String h = tableModel.getColumnName(i);
        %>
        <th scope="col" width="100"><%=h%></th>
        <%
            }
        %>
    </tr>
    <%
        int nRow = tableModel.getRowCount();
        for (int r = 0; r < nRow; r++) {
    %>
    <tr>
        <%
            for (int j = 0; j < nCol; j++) {
                Object obj = tableModel.getValueAt(r, j);
                String str = "null";
                if (obj != null) {
                    if (j == 1 || j == 2) {
                        str = tableModel.getValueAt(r, j).toString();
                        str += ":00";
                    } else
                        str = tableModel.getValueAt(r, j).toString();
                }
        %>
        <td width="100" align="center"><%=str%></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>


</body>
</html>