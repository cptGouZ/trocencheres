<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="fragments/header.jsp"%>


<div class="row g-3">
    <div class="col-auto">
        <br/>
        <br/>
        <br/>
        <%
            Utilisateur user2 = (Utilisateur) request.getAttribute("user");
            out.print("<div>Pseudo : " + user2.getPseudo() + "</div>");
            out.print("<div>Nom : " + user2.getNom() + "</div>");
            out.print("<div>Prenom : " + user2.getPrenom() + "</div>");
            out.print("<div>Email : " + user2.getEmail() + "</div>");
            out.print("<div>Telephone : " + user2.getTelephone() + "</div>");
            out.print("<div>Rue : " + user2.getAdresse().getRue() + "</div>");
            out.print("<div>Code postal : " + user2.getAdresse().getCpo() + "</div>");
            out.print("<div>Ville : " + user2.getAdresse().getVille() + "</div>");
        %>
    </div>
</div>


<%--Voir les informations du vendeur si le profil n'est pas celui de l'utilisateur--%>
<c:if test="${empty sessionScope.get(connectedUser.id)}">
    <div class="row g-3">
        <div class="col-auto">
            <form action="http://localhost:8080/trocencheres_war_exploded/accueilS" method="get">
                <button class="btn btn-primary mb-3" type="submit">Back</button>
            </form>
        </div>
    </div>
</c:if>

<%--Pouvoir modifier les informations si le profil est celui de l'utilisateur--%>
<c:if test="${!empty sessionScope.get(connectedUser.id)}">
    <div class="row g-3">
        <div class="col-auto">
            <form action="http://localhost:8080/trocencheres_war_exploded/accueilS" method="get">
                <button class="btn btn-primary mb-3" type="submit">Back</button>
            </form>
            <form action="http://localhost:8080/trocencheres_war_exploded/gestioncompte" method="get">
                <button class="btn btn-primary mb-3" type="submit">Modifier</button>
            </form>
        </div>
    </div>
</c:if>

<%@ include file="fragments/footer.jsp"%>

