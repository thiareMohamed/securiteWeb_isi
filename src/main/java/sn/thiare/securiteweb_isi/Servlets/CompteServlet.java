package sn.thiare.securiteweb_isi.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sn.thiare.securiteweb_isi.Dao.CompteDao;
import sn.thiare.securiteweb_isi.Dao.CompteImpl;
import sn.thiare.securiteweb_isi.entity.ComptesEntity;
import sn.thiare.securiteweb_isi.entity.dto.CompteDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CompteServlet", value = "/CompteServlet")
public class CompteServlet extends HttpServlet {

    private CompteDao compteDao;

    @Override
    public void init() throws ServletException {
        compteDao = new CompteImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ComptesEntity> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equals("delete")) {
            String id = request.getParameter("id");
            try {
                this.compteDao.deleteById(Integer.parseInt(id));
                this.doGet(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ComptesEntity comptesEntity = new ComptesEntity();
        comptesEntity.setEmail(request.getParameter("email"));
        comptesEntity.setPassword(request.getParameter("password"));

        int ok = compteDao.create(comptesEntity);
        String message = "";
        if (ok == 1) {
            message = "Compte créé avec succès";
            request.setAttribute("message", message);
            this.doGet(request, response);
        } else {
            message = "Erreur lors de la création du compte";
            request.setAttribute("message", message);
            this.doGet(request, response);
        }


    }
}
