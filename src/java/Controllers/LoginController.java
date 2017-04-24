/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.User;
import Repositories.UserRepository;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shan
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
//        System.out.println("sdss");
        request.getRequestDispatcher("site/login.jsp").forward(request, response);
        
        
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
//        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user =  UserRepository.getUserByUsername(email);
        System.out.println("called");

        if(user == null){
            request.setAttribute("error", "email does not exists");
            request.getRequestDispatcher("site/login.jsp").forward(request,response); 
        }
        if(user != null){
            System.out.println(user.getPassword());
            if(user.getPassword() == null ? password == null : user.getPassword().equals(password)){
                HttpSession session = request.getSession(true);
                session.setMaxInactiveInterval(20*60);
                session.setAttribute("user", user);
                if("ROLE_USER".equals(user.getRole().getMetacode())){
                    System.out.println("dashboard");
                    response.sendRedirect("dashboard");
                }
                else if ("ROLE_ADMIN".equals(user.getRole().getMetacode())) {
                    response.sendRedirect("site/login.jsp");
                }
            }
            else{
                request.setAttribute("error", "incorrect password");
                request.getRequestDispatcher("site/login.jsp").forward(request,response);
            }
        }
        
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
