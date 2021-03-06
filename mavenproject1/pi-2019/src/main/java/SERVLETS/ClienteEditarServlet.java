/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLETS;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modal.Cliente;
import DAO.ClienteDAO;

/**
 *
 * @author mt12732
 */
@WebServlet(name = "ClienteEditarServlet", urlPatterns = {"/ClienteEditar"})
public class ClienteEditarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
        int ID = Integer.parseInt(request.getParameter("id"));
        Cliente c = null;
        try {
            c = ClienteDAO.procurarId(ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("id", ID);
        request.setAttribute("cliente", c);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("JSP-PAGES/ClienteEditar.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        int id = Integer.parseInt(request.getParameter("idCliente"));
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String complemento = request.getParameter("complemento");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String endereco = request.getParameter("endereco");
        String dataNascimento = request.getParameter("dataNascimento");
        String cep = request.getParameter("cep");
        String celular = request.getParameter("celular");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
       
        Cliente c = new Cliente(cep, complemento, endereco, cidade, estado, nome, sobrenome, rg, cpf, id, dataNascimento, email, telefone, celular);
        try {
            if (ClienteDAO.AlterarCliente(c)) {
                request.setAttribute("msgResposta", "Alterado com sucesso!");
            } else {
                request.setAttribute("msgResposta", "Não Foi possível efetuar o cadastro!");
            }
//            RequestDispatcher dispatcher
 //                   = request.getRequestDispatcher("JSP-PAGES/Home.jsp");
   //         dispatcher.forward(request, response);
   response.sendRedirect("ClienteConsulta");
        } catch (Exception e) {
            e.getLocalizedMessage();
            System.out.println(e);
        }

    }
}
