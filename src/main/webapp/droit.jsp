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
                    <c:if test="${droit != null}">
                    <form action="DroitServlet?action=update" method="post">
                        </c:if>
                        <c:if test="${droit == null}">
                        <form action="DroitServlet?action=add" method="post">
                            </c:if>
                            <c:if test="${droit != null}">
                                <input type="hidden" name="id" value="<c:out value='${droit.id}' />" />
                            </c:if>
                        <input type="name" placeholder="name" name="name" value="<c:out value='${droit.name}' />">
                        <input type="submit" value="Enregistrer" name="action">
                    </form>
                </div>
            </section>
            <section>
                <h2>Liste des Droits</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Identifiant</th>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${droits}" var="droit">
                            <tr>
                                <td>${droit.id}</td>
                                <td>${droit.name}</td>
                                <td>
                                    <a href="DroitServlet?action=delete&id=${droit.id}" id="${droit.id}-delete">Supprimer</a>
                                    <a href="DroitServlet?action=edit&id=${droit.id}" id="${droit.id}-edit">Modifier</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
        </main>
        <%@include file="Layouts/footer.jsp"%>
    </body>
</html>