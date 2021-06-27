<%@ page import="bo.Utilisateur" %>
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="defaultPseudo" value="value=\"${userConnected.pseudo}\""/>
<c:set var="defaultNom" value="value=\"${userConnected.nom}\""/>
<c:set var="defaultPrenom" value="value=\"${userConnected.prenom}\""/>
<c:set var="defaultEmail" value="value=\"${userConnected.email}\""/>
<c:set var="defaultPhone" value="value=\"${userConnected.phone}\""/>
<c:set var="defaultRue" value="value=\"${userConnected.adresse.rue}\""/>
<c:set var="defaultCpo" value="value=\"${userConnected.adresse.cpo}\""/>
<c:set var="defaultVille" value="value=\"${userConnected.adresse.ville}\""/>

<%@ include file="fragments/header.jsp"%>
<div class="row">
    <%--Message d'erreur de connexion--%>
    <p class="col text-decoration-underline text-danger">
        ${empty messageErreur ? "" : messageErreur}
    </p>

    <form method="post" action="${pageContext.request.contextPath}/gestioncompte?userId=${userId}" class="col">
        <%--Pseudo / Nom--%>
        <div class="row">
            <div class="col input-group mb-3">
                <span class="col-2 input-group-text" id="idPseudo">Pseudo</span>
                <input type="text" class="col form-control" placeholder="Pseudo" name="pseudo"
                    ${!empty userConnected.pseudo ? defaultPseudo : null}
                    ${empty sessionScope.get('userConnected') ? "" : "disabled"}
                >

            </div>
            <div class="col input-group mb-3">
                <span class="input-group-text" id="idNom">Nom :</span>
                <input type="text" class="form-control" placeholder="Nom" name="nom"
                    ${!empty userConnected.nom ? defaultNom : null}>
            </div>
        </div>

        <%--Prénom / Email--%>
        <div class="row">
            <div class="col input-group mb-3">
                <span class="input-group-text" id="idPrenom">Prénom :</span>
                <input type="text" class="form-control" placeholder="Prénom" name="prenom"
                    ${!empty userConnected.prenom ? defaultPrenom : null}>
            </div>
            <div class="col input-group mb-3">
                <span class="input-group-text" id="idEmail">Email :</span>
                <input type="email" class="form-control" placeholder="E-mail" name="email"
                    ${!empty userConnected.email ? defaultEmail : null}
                    ${empty sessionScope.get('userConnected') ? "" : "disabled"}
                >
            </div>
        </div>

        <%--Téléphone / Rue--%>
        <div class="row">
            <div class="col input-group mb-3">
                <span class="input-group-text" id="idTel">Tél :</span>
                <input type="text" class="form-control" placeholder="Téléphone" name="tel"
                    ${!empty userConnected.phone ? defaultPhone : null}>
            </div>
            <div class="col input-group mb-3">
                <span class="input-group-text" id="idRue">Rue :</span>
                <input type="text" class="form-control" placeholder="Rue" name="rue"
                    ${!empty userConnected.adresse.rue ? defaultRue : null}>
            </div>
        </div>

        <%--Code Postal / Ville--%>
        <div class="row">
            <div class="col input-group mb-3">
                <span class="input-group-text" id="idCpo">Code Postal :</span>
                <input type="text" class="form-control" placeholder="Code Postal" name="cpo"
                    ${!empty userConnected.adresse.cpo ? defaultCpo : null}>
            </div>
            <div class="col input-group mb-3">
                <span class="input-group-text" id="idVille">Ville</span>
                <input type="text" class="form-control" placeholder="Ville" name="ville"
                    ${!empty userConnected.adresse.ville ? defaultVille : null}>
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
                <input type="password" class="form-control" placeholder="Confirmation" name="confirmPassword">
            </div>
        </div>

        <%--Créer / Annuler
        Afficher en cas de création de compte--%>
        <c:if test="${empty sessionScope.get('userConnected')}">
            <div class="row">
                <div class="col">
                    <button type="submit" class="btn btn-secondary" name="action" value="creer">Créer</button>
                </div>
                <div class="col">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/accueilS">Annuler</a>
                </div>
            </div>
        </c:if>
      
        <%--Enregistrer / Supprimer
        Afficher si le profil de session est celui de l'utilisateur--%>
        <c:if test="${!empty sessionScope.get('userConnected')}">
            <div class="row">
                <div class="col">
                    Credit : 640
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <button type="submit" class="btn btn-secondary" name="action" value="maj">Enregistrer</button>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-secondary" name="action" value="supprimer">Supprimer</button>
                </div>
            </div>
        </c:if>
    </form>
</div>

<%@ include file="fragments/footer.jsp"%>