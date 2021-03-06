<%@ page import="bo.Article" %>
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Encherir"/>
    <jsp:param name="messageErreur" value="${messageErreur}"/>
    <jsp:param name="messageConfirm" value="${messageConfirm}"/>
</jsp:include>

<fmt:parseDate value="${ article.dateFin }" pattern="yyyy-MM-dd'T'HH:mm" var="dateFin" type="both" />

<div class="row">
    <div class="col">
        <div class="row justify-content-center">
            <span class="col-2">${article.article}</span>
        </div>
        <div class="row justify-content-center">
            <div class="col-4">
                <span class="input-group-text">Description: </span>
                <textarea cols="56">${article.description}</textarea>
            </div>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Catégorie : ${article.categorie.libelle}</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Meilleur offre : ${empty enchere.montant ? article.prixInitiale : enchere.montant} pts par ${enchere.user.pseudo} </span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Mise à prix : ${article.prixInitiale} crédits</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Fin de l'enchère : <fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "short" value="${ dateFin }"/></span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Retrait : ${article.adresseRetrait.rue += article.adresseRetrait.cpo += article.adresseRetrait.ville}</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Vendeur : ${article.utilisateur.pseudo}</span>
        </div>
        <c:if test="${'encherir'.equals(affichagejsp)}">
            <div class="row justify-content-center">
                <div class="col-4">
                    <form action="${pageContext.request.contextPath}/encherir" method="post" class="input-group mb-3">
                        <span class="input-group-text">Ma propal : <sup class="text-danger">*</sup></span>
                        <input type="hidden" name="idArticle" value="${article.id}"/>
                        <input type="text" class="form-control" placeholder="Montant" name="montant" required value="${enchere.montant +1}">
                        <button type="submit" class="btn btn-secondary" name="btn" value="encherir">Enchérir</button>
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${'retirer'.equals(affichagejsp)}">
            <div class="row justify-content-center">
                <div class="col-4">
                    <form action="${pageContext.request.contextPath}/retrait" method="post" class="input-group mb-3">
                        <input type="hidden" name="idArticle" value="${article.id}"/>
                        <button type="submit" class="btn btn-secondary" name="btn" value="retrait">Retirer</button>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
</div>
<%@ include file="fragments/footer.jsp"%>
