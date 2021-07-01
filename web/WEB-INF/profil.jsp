<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div>Pseudo : ${userToDisplay.pseudo}</div>
            <div>Nom : ${userToDisplay.nom}</div>
            <div>Prenom : ${userToDisplay.prenom}</div>
            <div>Email : ${userToDisplay.email}</div></br></br>
        </div>

        <div class="col-xs-12 col-md-6">
            <div>Telephone : ${userToDisplay.phone} </div>
            <div>Rue : ${userToDisplay.adresse.rue} </div>
            <div>Code postal : ${userToDisplay.adresse.cpo} </div>
            <div>Ville : ${userToDisplay.adresse.ville} </div></br></br>
        </div>
    </div>

    <%--Pouvoir modifier les informations si le profil est celui de l'utilisateur--%>
    <div class="row">
        <div class="col-xs-12 col-md-6">
            <a class="btn btn-success mb-3" href="${pageContext.request.contextPath}/accueil">Back</a>
        </div>
        <c:if test="${displayBtnModif==true}">
            <div class="col-xs-12 col-md-6">
                <form method="post" action="${pageContext.request.contextPath}/modifcompte">
                    <input type="hidden" name="userId" value="${userToDisplay.id}">
                    <button class="btn btn-success mb-3" href="${pageContext.request.contextPath}/comptemodif">Modifier</button>
                </form>
            </div>
        </c:if>
    </div>
</div>

<%@ include file="fragments/footer.jsp"%>

