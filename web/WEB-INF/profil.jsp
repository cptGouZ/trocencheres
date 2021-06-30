<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Profil"/>
    <jsp:param name="messageErreur" value="${messageErreur}"/>
    <jsp:param name="messageConfirm" value="${messageConfirm}"/>
</jsp:include>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-md-6">
            <div>Pseudo : ${userDisplayed.pseudo}</div>
            <div>Nom : ${userDisplayed.nom}</div>
            <div>Prenom : ${userDisplayed.prenom}</div>
            <div>Email : ${userDisplayed.email}</div></br></br>
        </div>

        <div class="col-xs-12 col-md-6">
            <div>Telephone : ${userDisplayed.phone} </div>
            <div>Rue : ${userDisplayed.adresse.rue} </div>
            <div>Code postal : ${userDisplayed.adresse.cpo} </div>
            <div>Ville : ${userDisplayed.adresse.ville} </div></br></br>
        </div>
    </div>

    <%--Pouvoir modifier les informations si le profil est celui de l'utilisateur--%>
    <div class="row">
        <div class="col-xs-12 col-md-6">
            <a class="btn btn-success mb-3" href="${pageContext.request.contextPath}/accueilS">Back</a>
        </div>
        <c:if test="${userConnected.id==userDisplayed.id}">
            <div class="col-xs-12 col-md-6">
                    <a class="btn btn-success mb-3" href="${pageContext.request.contextPath}/comptemodif">Modifier</a>
            </div>
        </c:if>
    </div>
</div>

<%@ include file="fragments/footer.jsp"%>

