<header class="bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500 flex justify-between shadow">
    <h1 class="text-4xl text-white font-bold p-6">Securite WEB</h1>

    <c:if test="${not empty sessionScope}">
        <nav class="flex" >
            <a href="CompteServlet" class="text-white font-bold p-6 hover:text-gray-300">Compte</a>
            <a href="DroitServlet" class="text-white font-bold p-6 hover:text-gray-300">Droit</a>
            <a href="LogoutServlet" class="text-white font-bold p-6 hover:text-gray-300">Deconnexion</a>
        </nav>
    </c:if>
</header>