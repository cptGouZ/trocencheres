<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Profil"/>
    <jsp:param name="messageErreur" value="${messageErreur}"/>
    <jsp:param name="messageConfirm" value="${messageConfirm}"/>
</jsp:include>
<div class="row justify-content-center">
    <div class="col-12 col-md-8 col-lg-6">
        <div class="row justify-content-center my-5">
            <H2 class="col-12 col-sm-6">Mes crédits : ${creditdispo}</H2>
        </div>
        <div class="row justify-content-center">
            <div class="col-12 col-sm-6">Pseudo : ${userToDisplay.pseudo}</div>
            <div class="col-12 col-sm-6">Nom : ${userToDisplay.nom}</div>
            <div class="col-12 col-sm-6">Prenom : ${userToDisplay.prenom}</div>
            <div class="col-12 col-sm-6">Email : ${userToDisplay.email}</div>
            <div class="col-12 col-sm-6">Telephone : ${userToDisplay.phone}</div>
            <div class="col-12 col-sm-6">Rue : ${userToDisplay.adresse.rue}</div>
            <div class="col-12 col-sm-6">Code postal : ${userToDisplay.adresse.cpo}</div>
            <div class="col-12 col-sm-6">Ville : ${userToDisplay.adresse.ville}</div>
        </div>

        <%--Pouvoir modifier les informations si le profil est celui de l'utilisateur--%>
        <div class="row justify-content-center my-4">
            <div class="col-12 col-sm-6">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/accueil">Back</a>
            </div>
            <c:if test="${displayBtnModif==true}">
                <div class="col-12 col-sm-6">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/gestioncompte?uid=${userToDisplay.id}">Modifier</a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="fragments/footer.jsp"%>

