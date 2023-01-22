package sn.thiare.securiteweb_isi.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sn.thiare.securiteweb_isi.Dao.CompteDao;
import sn.thiare.securiteweb_isi.Dao.CompteImpl;
import sn.thiare.securiteweb_isi.Dao.DroitDao;
import sn.thiare.securiteweb_isi.Dao.DroitImpl;
import sn.thiare.securiteweb_isi.entity.dto.CompteDto;
import sn.thiare.securiteweb_isi.entity.dto.DroitDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DroitServlet", value = "/DroitServlet")
public class DroitServlet extends HttpServlet {

    private DroitDao droitDao;

    @Override
    public void init() throws ServletException {
        droitDao = new DroitImpl();
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
            List<DroitDto> droits = droitDao.getAllDroit();
            request.setAttribute("droits", droits);
            request.getRequestDispatcher("/droit.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DroitDto droitDto = new DroitDto();
        droitDto.setName(request.getParameter("name"));

        int ok = droitDao.create(droitDto);
        String message = "";
        if (ok == 1) {
            message = "Droit créé avec succès";
            request.setAttribute("message", message);
            List<DroitDto> droits = droitDao.getAllDroit();
            request.setAttribute("droits", droits);
            request.getRequestDispatcher("/droit.jsp").forward(request, response);
        } else {
            message = "Erreur lors de la création du droit";
            request.setAttribute("message", message);
            List<DroitDto> droits = droitDao.getAllDroit();
            request.setAttribute("droits", droits);
            request.getRequestDispatcher("/droit.jsp").forward(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        int ok = droitDao.deleteById(id);
        String message = "";
        if (ok == 1) {
            message = "Droit supprimé avec succès";
            request.setAttribute("message", message);
            List<DroitDto> droits = droitDao.getAllDroit();
            request.setAttribute("droits", droits);
            request.getRequestDispatcher("/droit.jsp").forward(request, response);
        } else {
            message = "Erreur lors de la suppression du droit";
            request.setAttribute("message", message);
            List<DroitDto> droits = droitDao.getAllDroit();
            request.setAttribute("droits", droits);
            request.getRequestDispatcher("/droit.jsp").forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DroitDto droitDto = new DroitDto();
        droitDto.setId(Integer.parseInt(request.getParameter("id")));
        droitDto.setName(request.getParameter("name"));

        int ok = droitDao.updateDroit(droitDto);
        String message = "";
        if (ok == 1) {
            message = "Droit modifié avec succès";
            request.setAttribute("message", message);
            List<DroitDto> droits = droitDao.getAllDroit();
            request.setAttribute("droits", droits);
            request.getRequestDispatcher("/droit.jsp").forward(request, response);
        } else {
            message = "Erreur lors de la modification du droit";
            request.setAttribute("message", message);
            List<DroitDto> droits = droitDao.getAllDroit();
            request.setAttribute("droits", droits);
            request.getRequestDispatcher("/droit.jsp").forward(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        DroitDto droitDto = droitDao.getDroitById(id);
        request.setAttribute("droit", droitDto);
        List<DroitDto> droits = droitDao.getAllDroit();
        request.setAttribute("droits", droits);
        request.getRequestDispatcher("/droit.jsp").forward(request, response);
    }
}
