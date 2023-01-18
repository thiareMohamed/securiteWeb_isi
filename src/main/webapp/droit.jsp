<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <%@include file="Layouts/header.jsp"%>
    <body>
        <%@include file="Layouts/navbar.jsp"%>
        <main>
            <section>
                <h2>Espace Droit</h2>
                <div>
                    <% if (request.getAttribute("message") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("message") %>
                    </div>
                    <% } %>
                    <form action="DroitServlet" method="post">
                        <input type="name" placeholder="name" name="name">
                        <input type="submit" value="Enregistrer">
                    </form>
                </div>
            </section>
            <section>
                <h2>Liste des Droits</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Identifiant</th>
                        <th>Droit</th>
                    </tr>
                    </thead>
                </table>
            </section>
        </main>
        <%@include file="Layouts/footer.jsp"%>
    </body>
</html>