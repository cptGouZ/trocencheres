<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="fragments/header.jsp"%>
<div class="row">
  <form class="col" method="post" action="${pageContext.request.contextPath}/gestionCompte">

    <%--Pseudo / Nom--%>
    <div class="row">
      <div class="col input-group mb-3">
        <span class="col-2 input-group-text" id="idPseudo">Pseudo</span>
        <input type="text" class="col form-control" placeholder="Pseudo" name="pseudo" aria-label="Username" aria-describedby="basic-addon1">
      </div>
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idNom">Nom :</span>
        <input type="text" class="form-control" placeholder="Nom" name="nom">
      </div>
    </div>

    <%--Prénom / Email--%>
    <div class="row">
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idPrenom">Prénom :</span>
        <input type="text" class="form-control" placeholder="Prénom" name="prenom">
      </div>
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idEmail">Email :</span>
        <input type="email" class="form-control" placeholder="E-mail" name="email">
      </div>
    </div>

    <%--Téléphone / Rue--%>
    <div class="row">
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idTel">Tél :</span>
        <input type="text" class="form-control" placeholder="Téléphone" name="telephone">
      </div>
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idRue">Rue :</span>
        <input type="text" class="form-control" placeholder="Rue" name="rue">
      </div>
    </div>

    <%--Code Postal / Ville--%>
    <div class="row">
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idCpo">Code Postal :</span>
        <input type="text" class="form-control" placeholder="Code Postal" name="cpo">
      </div>
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idVille">Ville</span>
        <input type="text" class="form-control" placeholder="Ville" name="ville">
      </div>
    </div>

    <%--Mot de passe Actuel--%>
    <div class="row">
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idPassword">Mot de passe :</span>
        <input type="password" class="form-control" placeholder="Mot de passe" name="password">
      </div>
    </div>

    <%--Nouveau mot de passe / Confirmation--%>
    <div class="row">
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idNewPassword">Nouveau mot de passe :</span>
        <input type="password" class="form-control" placeholder="Nouveau mot de passe" name="newPassword">
      </div>
      <div class="col input-group mb-3">
        <span class="input-group-text" id="idConfirmation">Confirmation :</span>
        <input type="password" class="form-control" placeholder="Confirmation" name="confirmation">
      </div>
    </div>
    <%--Créer / Annuler--%>
    <div class="row">
      <div class="col">
        <button type="submit" class="btn btn-secondary" value="creer">Créer</button>
      </div>
      <div class="col">
        <a href="${pageContext.request.contextPath}/accueil">Annuler</a>
        <%--<button type="submit" class="btn btn-secondary">Annuler</button>--%>
      </div>
    </div>
    <%--Enregistrer / Supprimer--%>
    <div class="row">
      <div class="col">
        <button type="submit" class="btn btn-secondary">Secondary</button>
      </div>
      <div class="col">
        <button type="submit" class="btn btn-secondary">Secondary</button>
      </div>
    </div>
  </form>
</div>

<%@ include file="fragments/footer.jsp"%>
