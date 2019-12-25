<%--
  Created by IntelliJ IDEA.
  User: maksi
  Date: 22.12.2019
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
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
    <title>List of groups</title>
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
<button class="btn btn-light" style="margin: 10px"><a href="index.html">Home page</a></button>
<%
    String tableName = (String) (session.getAttribute("tableName"));
    TableModel tableModel = (TableModel) (session.getAttribute("tableModel"));
%>

<div style="text-align: center">
    <h1 class="text" style="font-size: 65pt"><%=tableName%></h1>
</div>
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
        <th scope="col" width="100">Songs</th>
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
                        str = tableModel.getValueAt(r, j).toString();
                }
        %>
        <td width="100" align="center"><%=str%></td>
        <%
            }
        %>
        <td width="100" align="center"><a href="ListSongsGrServlet?id=<%=tableModel.getValueAt(r,0)%>">Go</a></td>
    </tr>
    <%
        }
    %>
</table>


</body>
</html>