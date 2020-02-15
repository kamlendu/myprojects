<%-- 
    Document   : index
    Created on : 16 Oct, 2008, 12:44:25 PM
    Author     : root
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentication Page</title>
    </head>
    <body>

        <%
//            out.println("User :"+request.getParameter("username"));
//            if(request.getParameter("submit") != null)
//                {
//                System.out.println("Submitted");
//                
//
//               RequestDispatcher rd = request.getRequestDispatcher("jaas/jaaslogin.jsp");
//                    rd.forward(request, response);
//                }
//                else {

        %>

        <form method="POST" action="jaas/jaaslogin.jsp">
            <table>
                <tr>
                    <td colspan="2">Login:</td>
                </tr>
                <tr>
                    <td>User Name:</td>
                    <td><input type="text" name="username"/></td>
                </tr>

                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"/></td>
                    <td><input type="reset"/></td>
                </tr>
            </table>
        </form>

        <%
       // }
        %>
    </body>
</html>