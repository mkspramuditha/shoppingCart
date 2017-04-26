/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.CartItem;
import Entity.Item;
import Repositories.ItemRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
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
@WebServlet(name = "AddCartItem", urlPatterns = {"/addCartItem"})
public class AddCartItem extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCartItem</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCartItem at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String itemId = request.getParameter("item");
        HttpSession session = request.getSession(true);
        ArrayList<CartItem> cart = (ArrayList<CartItem>)session.getAttribute("cart");
        Item item = ItemRepository.getItemById(itemId);
        int quantity;
        if(request.getParameter("quantity") == null){
            quantity = 1;
        }
        else{
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }
        
        
        if(item != null){
            if (cart.contains(item)) {
                Iterator<CartItem> it = cart.iterator();
                while (it.hasNext()) {
                    if (it.next().getItem().getId()== item.getId()) {
                        it.remove();
                        break;
                    }
                }
                
                cart.add(new CartItem(item,quantity));

            } else {
                
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
