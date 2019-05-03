/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class offlineweb extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String email = request.getParameter("email");
            String message = request.getParameter("message");
            String phone = request.getParameter("tel");
            // System.out.println("this EMail: " + email);
            //  System.out.println("this Message: " + message);
            int status = 0;
            try {
                if (email != null && message != null) {

                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
//DROP TABLE IF EXISTS `offline_messages`;
//CREATE TABLE `offline_messages` (
//  `id` bigint(20) NOT NULL AUTO_INCREMENT,
//  `email_address` varchar(50) NOT NULL,
//  `status` tinyint(4) NOT NULL DEFAULT '0',
//  `message` varchar(100) NOT NULL,
//  `date_created` datetime DEFAULT NULL,
//  PRIMARY KEY (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=latin1;
                        connection = DriverManager.getConnection(connectionURL, username, pass);

                        String queryString = "INSERT INTO saved_chats(chat_string,type,phone_no,email_address) VALUES (?, ?, ?,?)";

                        pstatement = connection.prepareStatement(queryString);
                        pstatement.setString(1, message);
                        pstatement.setString(2, "mesoff");
                        pstatement.setString(3, phone);
                        pstatement.setString(4, email);
                        int count = pstatement.executeUpdate();
                        if (count > 0) {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Servlet offlineweb</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1> Successful !! Message Sent</h1>");
                            out.println("<h1>Message Sent: " + message + "  From: " + email + " </h1>");
                            out.println("</body>");
                            out.println("</html>");

                        } 
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
                        out.println(ex.getMessage());
                        //out.println("Unable to connect to Database.");
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet offlineweb</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1> Message Sent Failed!!</h1>");
                        out.println("<h1>Message Sent: " + message + "  From: " + email + " </h1>");
                        out.println("</body>");
                        out.println("</html>");

                    } finally {

                        connection.close();
                    }
                } else {
                    System.out.println(new java.util.Date() + " message not sent");
                }
            } catch (SQLException x) {
                x.printStackTrace();
            }
            /* TODO output your page here. You may use following sample code. */

        }
    }

    String connectionURL = "jdbc:mysql://127.0.0.1:3306/dbCHAT?zeroDateTimeBehavior=convertToNull";

    private Connection connection = null;
    private final String username = "mysql";
    private final String pass = "mysql123";

    PreparedStatement pstatement = null;

    public void connect(String email, int status, String message) throws SQLException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
