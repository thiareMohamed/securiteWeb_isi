<!DOCTYPE html>
<html>
<%@ include file="Layouts/header.jsp" %>
<body>
<%@ include file="Layouts/navbar.jsp" %>
<main class="columns-1 md:columns-2 max-h-screen container">
    <div class="p-28 hidden md:flex">
        <img src="https://www.vaadata.com/blog/wp-content/uploads/2015/02/web_protection.jpg" alt="Logo">
    </div>
    <div class="p-10">
        <div class="bg-purple-100 rounded-lg p-6 py-10">
            <h2 class="text-3xl font-bold text-center">Connexion</h2>
            <form action="LoginServlet" method="post">
                        <span class="flex flex-col px-20 py-4 text-red-500 font-bold text-center">
                            <c:if test="${requestScope.message != null}">
                                ${requestScope.message}
                            </c:if>
                        </span>
                <div class="flex flex-col px-20 py-4">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" class="border-2 border-purple-500 rounded-md p-2">
                </div>
                <div class="flex flex-col  px-20 py-4">
                    <label for="password">Mot de passe</label>
                    <input type="password" name="password" id="password" class="border-2 border-purple-500 rounded-md p-2">
                </div>
                <div class="flex flex-col px-20 py-4">
                    <button type="submit" class="bg-purple-500 text-white font-bold p-2 rounded-md hover:bg-purple-600">Connexion</button>
                </div>
            </form>
        </div>
    </div>
</main>
<%@ include file="Layouts/footer.jsp" %>
</body>
</html>