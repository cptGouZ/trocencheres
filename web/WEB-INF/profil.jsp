<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="fragments/header.jsp"%>


    <div class="row g-3">
        <div class="col-auto">
            <div>Pseudo : ${userDisplayed.pseudo}</div>
            <div>Nom : ${userDisplayed.nom}</div>
            <div>Prenom : ${userDisplayed.prenom}</div>
            <div>Email : ${userDisplayed.email}</div>
            <div>Telephone : ${userDisplayed.phone} </div>
            <div>Rue : ${userDisplayed.adresse.rue} </div>
            <div>Code postal : ${userDisplayed.adresse.cpo} </div>
            <div>Ville : ${userDisplayed.adresse.ville} </div>

        </div>
    </div>


    <%--Pouvoir modifier les informations si le profil est celui de l'utilisateur--%>
    <div class="row g-3">
        <div class="col-auto">
            <form action="${pageContext.request.contextPath}/accueilS" method="get">
                <button class="btn btn-primary mb-3" type="submit">Back</button>
            </form>
            <c:if test="${empty sessionScope.get(connectedUser.id)}">
            <form action="${pageContext.request.contextPath}/gestioncompte?userId=${userDisplayed.id}" method="get">
                <button class="btn btn-primary mb-3" type="submit">Modifier</button>
            </form>
            </c:if>
        </div>
    </div>

<%@ include file="fragments/footer.jsp"%>

