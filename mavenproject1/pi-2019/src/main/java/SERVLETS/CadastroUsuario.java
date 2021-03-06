/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLETS;

import DAO.CargosDAO;
import DAO.FilialDAO;
import DAO.UsuarioDAO;
import Modal.Cargos;
import Modal.Filial;
import Modal.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author antonio.ncgjunior
 */
@WebServlet(name = "CadastroUsuario", urlPatterns = {"/CadastroUsuario"})
public class CadastroUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        request.setCharacterEncoding("UTF-8");
        try {
            List<Cargos> listarCargo = CargosDAO.listarCargo();
            request.setAttribute("listarCargo", listarCargo);

            List<Filial> listarFilial = FilialDAO.listarFilial();
            request.setAttribute("listarFilial", listarFilial);
        } catch (Exception e) {
            System.out.println(e);
        }
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("JSP-PAGES/CadastroDeUsuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String Nome = request.getParameter("nome");
        String CPF = request.getParameter("CPF");
        String DatNasc = request.getParameter("dataNascimento");
        String Cargo = request.getParameter("cargo");
        String username = request.getParameter("username");
        String senhaAberta = request.getParameter("Senha");
        String Celular = request.getParameter("celular");
        String Email = request.getParameter("email");
        String Filial = request.getParameter("filial");
        Usuario user = new Usuario(Nome, CPF, DatNasc, Celular, Email, Filial, username, senhaAberta, Cargo);
        try {
            if (UsuarioDAO.inserir(user)) {
                request.setAttribute("msgResposta", "Cadastrado com sucesso!");
                response.sendRedirect("ConsultaUsuario");
            } else {
                request.setAttribute("msgResposta", "Não Foi possível efetuar o cadastro!");
                response.sendRedirect("ConsultaUsuario");
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
            System.out.println(e);
        }
        //response.sendRedirect("ConsultaUsuario");
//        RequestDispatcher dispatcher
        //              = request.getRequestDispatcher("JSP-PAGES/ConsultaUsuario.jsp");
        //    dispatcher.forward(request, response);
    }
}
