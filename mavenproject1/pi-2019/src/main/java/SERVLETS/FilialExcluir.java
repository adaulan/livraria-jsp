/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLETS;

import DAO.FilialDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nadso
 */
@WebServlet(name = "FilialExcluir", urlPatterns = {"/FilialExcluir"})
public class FilialExcluir extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("JSP-PAGES/ConsultaFilial.jsp");
        dispatcher.forward(request, response);
    
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IDFilial = Integer.parseInt(request.getParameter("id"));
        try {
            if(FilialDAO.delFilial(IDFilial)){
                request.setAttribute("msgResposta", "Excluido com sucesso!");
                response.sendRedirect("ConsultaFilial");
            } else {
                request.setAttribute("msgResposta", "Não Foi possível realizar a exclusão!");
                response.sendRedirect("ConsultaFilial");
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
            System.out.println(e);
        }
    }
}
