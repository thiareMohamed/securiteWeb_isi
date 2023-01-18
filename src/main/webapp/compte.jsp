<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <%@include file="Layouts/header.jsp"%>
    <body>
    <%@include file="Layouts/navbar.jsp"%>
    <main class="container flex">
        <section>
            <h2>Espace Compte</h2>
            <div>
                <c:if test="${message != null}">
                    <div class="alert alert-success" role="alert">
                        <c:out value="${message}"/>
                    </div>
                </c:if>
                <form action="CompteServlet" method="post">
                    <input type="email" placeholder="email" name="email">
                    <input type="password" placeholder="password" name="password">
                    <input type="submit" value="Enregistrer">
                </form>
            </div>
        </section>
        <section>
            <h2>Liste des comptes</h2>
            <table>
                <thead>
                <tr>
                    <th>Identifiant</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${comptes}" var="compte">
                        <tr>
                            <td>${compte.id}</td>
                            <td>${compte.email}</td>
                            <td>
                                <a href="CompteServlet?action=delete&id=${compte.id}">Supprimer</a>
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