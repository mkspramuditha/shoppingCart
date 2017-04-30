/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Category;
import Entity.Item;
import Entity.User;
import Repositories.ItemRepository;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author shan
 */
@WebServlet(name = "EditItem", urlPatterns = {"/editItem"})
public class EditItem extends HttpServlet {

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
            out.println("<title>Servlet EditItem</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditItem at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        Item item = ItemRepository.getItemById(id);
        request.setAttribute("id", id);
        request.setAttribute("name", item.getName());
        request.setAttribute("price", item.getPrice());
        request.setAttribute("currentCategory", item.getCategory().getId());
        request.setAttribute("itemCode", item.getItemCode());
        request.setAttribute("quantity", item.getQuantity());
        request.setAttribute("image", item.getImage());
        
        ArrayList<Category> categories = ItemRepository.getCategories();
        
        request.setAttribute("categories", categories);
        
        request.getRequestDispatcher("dashboard/editItem.jsp").forward(request, response);
        
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
        String id = null;
        String name = null;
        String itemCode= null;
        String price= null;
        String quantity= null;
        String category = null;
        String image = null;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("login");
            return;
        }
//        request.getServletContext().getRealPath("/uploads");
        String path = request.getServletContext().getRealPath("/uploads");
        System.out.println(path);
        
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String fileName = new File(item.getName()).getName();
//                        new File(path).mkdirs();
                        File directory = new File(path + File.separator + fileName);
                        image = fileName;
                        item.write(directory);
                    }
                    else{
                        if("name".equals(item.getFieldName())){
                            name = item.getString();
                        }
                        else if("id".equals(item.getFieldName())){
                            id = item.getString();
                        }
                        else if("itemCode".equals(item.getFieldName())){
                            itemCode = item.getString();
                        }
                        else if("price".equals(item.getFieldName())){
                            price = item.getString();
                        }
                        else if("quantity".equals(item.getFieldName())){
                            quantity = item.getString();
                        }
                        else if("category".equals(item.getFieldName())){
                            category = item.getString();
                        }
                    }
                }
           
               //File uploaded successfully
                System.out.println("done");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        
                 

        boolean status = ItemRepository.editItem(name, itemCode, price, quantity, category,image,id);
        String message;
        System.out.println(status);
        if(status){
             message = "Item saved successfully";
             response.sendRedirect("dashboard");
        }
        else{
            message = "Item not saved !!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("dashboard/addItem.jsp").forward(request, response);
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
