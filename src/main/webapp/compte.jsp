<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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

                <c:if test="${compte != null}">
                    <form action="CompteServlet?action=update" method="post">
                </c:if>
                <c:if test="${compte == null}">
                        <form action="CompteServlet?action=add" method="post">
                </c:if>
                    <c:if test="${compte != null}">
                        <input type="hidden" name="id" value="<c:out value='${compte.id}' />" />
                    </c:if>
                    <input type="email" placeholder="email" name="email" value="<c:out value='${compte.email}'/>" />
                    <input type="password" placeholder="password" name="password" value="<c:out value='${compte.password}'/>" />
                    <select name="idDroits" value="<c:out value='${compte.droit.id}'/>">
                        <c:forEach items="${droits}" var="droit">
                            <option value="<c:out value='${droit.id}'/>"><c:out value='${droit.name}'/></option>
                        </c:forEach>
                    <input type="submit" value="Enregistrer" name="action">
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
                    <th>Droit</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${comptes}" var="compte">
                        <tr>
                            <td>${compte.id}</td>
                            <td>${compte.email}</td>
                            <td><c:forEach items="${droits}" var="droit">
                                <c:if test="${droit.id == compte.droit.id}">
                                    ${droit.name}
                                </c:if>
                            </c:forEach></td>
                            <td>
                                <a href="CompteServlet?action=delete&id=${compte.id}" id="${compte.id}-delete">Supprimer</a>
                                <a href="CompteServlet?action=edit&id=${compte.id}" id="${compte.id}-edit">Modifier</a>
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