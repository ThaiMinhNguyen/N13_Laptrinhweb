/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemo.laptrinhweb.control;

import com.nemo.laptrinhweb.dao.DAO;
import com.nemo.laptrinhweb.entity.Account;
import com.nemo.laptrinhweb.entity.Invoice;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "SearchByAjaxHoaDon", urlPatterns = {"/searchAjaxHoaDon"})
public class SearchByAjaxHoaDon extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String ngayXuat = request.getParameter("ngayXuat");
        DAO dao = new DAO();
        List<Invoice> listInvoiceByDate = dao.searchByNgayXuat(ngayXuat);
        List<Account> listAllAccount = dao.getAllAccount();
        PrintWriter out = response.getWriter(); 
        double tongGia;
        for (Invoice o : listInvoiceByDate) {
        	for (Account a : listAllAccount) {
        		if(o.getAccountID() == a.getId()) {	
        			tongGia=Math.round((o.getTongGia()) * 100.0) / 100.0;
        	out.println("<tr>\r\n"
        			+ "                  <th scope=\"row\"></th>\r\n"
        			+ "                  <td>"+o.getMaHD()+"</td>\r\n"
        			+ "                  <td>"+a.getUser()+"</td>\r\n"
        			+ "                  <td>"+tongGia+"</td>\r\n"
        			+ "                  <td>"+o.getNgayXuat()+"</td> \r\n"
        			+ "                </tr>");
        		}
        		}
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
