package sn.thiare.securiteweb_isi.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sn.thiare.securiteweb_isi.Dao.CompteDao;
import sn.thiare.securiteweb_isi.Dao.CompteImpl;
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
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "add":
                        add(request, response);
                        break;
                    case "delete":
                        delete(request, response);
                        break;
                    case "update":
                        update(request, response);
                        break;
                    case "edit":
                        edit(request, response);
                        break;
                    default:
                        break;
                }
            }
            List<CompteDto> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CompteDto compteDto = new CompteDto();
        compteDto.setEmail(request.getParameter("email"));
        compteDto.setPassword(request.getParameter("password"));

        int ok = compteDao.create(compteDto);
        String message = "";
        if (ok == 1) {
            message = "Compte créé avec succès";
            request.setAttribute("message", message);
            List<CompteDto> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        } else {
            message = "Erreur lors de la création du compte";
            request.setAttribute("message", message);
            List<CompteDto> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        int ok = compteDao.deleteById(Integer.parseInt(id));
        String message = "";
        if (ok == 1) {
            message = "Compte supprimé avec succès";
            request.setAttribute("message", message);
            List<CompteDto> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        } else {
            message = "Erreur lors de la suppression du compte";
            request.setAttribute("message", message);
            List<CompteDto> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CompteDto compteDto = new CompteDto();
        compteDto.setId(Integer.parseInt(request.getParameter("id")));
        compteDto.setEmail(request.getParameter("email"));
        compteDto.setPassword(request.getParameter("password"));

        int ok = compteDao.update(compteDto);
        String message = "";
        if (ok == 1) {
            message = "Compte modifié avec succès";
            request.setAttribute("message", message);
            List<CompteDto> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        } else {
            message = "Erreur lors de la modification du compte";
            request.setAttribute("message", message);
            List<CompteDto> comptes = compteDao.findAll();
            request.setAttribute("comptes", comptes);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        CompteDto compteDto = this.compteDao.findById(Integer.parseInt(id));
        request.setAttribute("compte", compteDto);
        List<CompteDto> comptes = compteDao.findAll();
        request.setAttribute("comptes", comptes);
        request.getRequestDispatcher("/compte.jsp").forward(request, response);
    }

}
