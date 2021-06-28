<%@ page import="bo.Article" %>
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="fragments/header.jsp"%>
<% Article article = new Article();
out.print(article.getArticle());%>
<c:set var="article" value="${<% article %>}"/>

<div class="row">
    <div class="col">
        <div class="row justify-content-center">
            <span class="col-2">${article.article}</span>
        </div>
        <span class="row justify-content-center">
            <div class="col-4">
                <span class="input-group-text">Description: </span>
                <textarea cols="56">${article.description}</textarea>
            </div>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Catégorie : ${article.categorie.libelle}</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Meilleur offre : $arecupérer crédits par LeRafleur</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Mise à prix : ${article.prixInitiale} crédits</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Fin de l'enchère : ${article.dateFin} à 23h59</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Retrait : 10 allé des alouettes 44800 St-Herblain</span>
        </div>
        <div class="row justify-content-center">
            <span class="col-4 input-group-text" >Vendeur : jojo l'asticot</span>
        </div>
        <div class="row justify-content-center">
            <div class="col-4">
                <div class="input-group mb-3">
                    <span class="input-group-text" id="idMise">Ma propal : <sup class="text-danger">*</sup></span>
                    <input type="text" class="form-control" placeholder="Nom" name="nom" required value="175">
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="fragments/footer.jsp"%>
