<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <%@include file="Layouts/header.jsp"%>
    <body>
        <%@include file="Layouts/navbar.jsp"%>
        <main class="columns-1 md:columns-2 max-h-screen container">
            <div class="p-10 h-auto">
                <h1 class="text-3xl font-bold">Espace Droit</h1>
                <div>
                    <% if (request.getAttribute("message") != null) { %>
                        <div class="alert alert-success" role="alert">
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
                        <div class="flex flex-col px-20 py-4">
                            <label for="name">Nom</label>
                            <input type="name" id="name" class="border-2 border-purple-500 rounded-md p-2" placeholder="name" name="name" value="<c:out value='${droit.name}' />">
                        </div>
                        <div class="flex flex-col px-20">
                            <input type="submit" value="Enregistrer" class="bg-blue-500 text-white p-2 rounded-lg">
                        </div>
                    </form>
                </div>
            </div>
            <div class="p-10">
                <h2 class="mt-5">Liste des Droits</h2>
                <table class="table-auto">
                    <thead>
                    <tr>
                        <th>Identifiant</th>
                        <th>Nom</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${droits}" var="droit">
                            <tr>
                                <td>${droit.id}</td>
                                <td>${droit.name}</td>
                                <td class="flex">
                                    <a href="DroitServlet?action=delete&id=${droit.id}" id="${droit.id}-delete" class="btn">
                                        <img src="https://img.icons8.com/ios/50/000000/delete-forever.png" width="20" height="20" alt="delete"/>
                                    </a>
                                    <a href="DroitServlet?action=edit&id=${droit.id}" id="${droit.id}-edit" class="btn">
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