<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <%@include file="/Layouts/header.jsp"%>
    <body>
    <%@include file="/Layouts/navbar.jsp"%>
    <main class="columns-1 md:columns-2 max-h-screen container">
        <div class="p-1">
            <h1 class="text-3xl font-bold">Espace Compte</h1>
            <div>
                <c:if test="${message != null}">
                    <p class="alert alert-success" role="alert">
                        <c:out value="${message}"/>
                    </p>
                </c:if>

                <c:if test="${compte != null}">
                    <form action="CompteServlet?action=update" method="post">
                </c:if>
                <c:if test="${compte == null}">
                        <form action="CompteServlet?action=add" method="post">
                </c:if>
                    <c:if test="${compte != null}">
                        <input type="hidden" name="id" class="border-2 border-purple-500 rounded-md p-2" value="<c:out value='${compte.id}' />" />
                    </c:if>
                    <div class="flex flex-col px-20 py-4">
                        <label for="email">Email</label>
                        <input type="email" placeholder="email" class="border-2 border-purple-500 rounded-md" id="email" name="email" value="<c:out value='${compte.email}'/>" />
                    </div>
                    <div class="flex flex-col px-20 py-4">
                        <label for="password">Mot de passe</label>
                        <input type="password" id="password" placeholder="password" name="password" class="border-2 border-purple-500 rounded-md" value="<c:out value='${compte.password}'/>" />
                    </div>
                    <div class="flex flex-col px-20 py-4">
                        <label for="droit">Droit ou Role</label>
                        <select name="idDroits" id="droit"class="border-2 border-purple-500 rounded-md"value="<c:out value='${compte.droit.id}'/>">
                            <c:forEach items="${droits}" var="droit">
                                <option value="<c:out value='${droit.id}'/>"><c:out value='${droit.name}'/></option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Enregistrer" class="bg-blue-500 text-white p-2 my-4 rounded-lg">
                    </div>
                </form>
            </div>
        </div>
        <div class="p-10">
            <h2 class="mt-5">Liste des comptes</h2>
            <table class="table-auto">
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
                            <td class="flex">
                                <a href="CompteServlet?action=delete&id=${compte.id}" id="${compte.id}-delete" class="btn">
                                    <img src="https://img.icons8.com/ios/50/000000/delete-forever.png" width="20" height="20" alt="delete"/>
                                </a>
                                <a href="CompteServlet?action=edit&id=${compte.id}" id="${compte.id}-edit" class="btn">
                                    <img src="https://img.icons8.com/ios/50/000000/edit.png" width="20" height="20" alt="edit"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
    <%@include file="Layouts/footer.jsp"%>
    </body>
</html>