package sn.thiare.securiteweb_isi.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sn.thiare.securiteweb_isi.Dao.CompteDao;
import sn.thiare.securiteweb_isi.Dao.CompteImpl;
import sn.thiare.securiteweb_isi.entity.dto.CompteDto;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private CompteDao compteDao;

    public void init() throws ServletException {
        compteDao = new CompteImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        CompteDto compteDto = this.compteDao.findByEmail(email);
        if (compteDto != null) {
            if (compteDto.getPassword().equals(password)) {
                request.setAttribute("compte", compteDto);
                //use session to store the user
                HttpSession session = request.getSession();
                session.setAttribute("compte", compteDto);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Mot de passe incorrect");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Compte inexistant");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
