<%@ page import="bo.Utilisateur" %>
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="defaultPseudo" value="value=\"${userToDisplay.pseudo}\""/>
<c:set var="defaultNom" value="value=\"${userToDisplay.nom}\""/>
<c:set var="defaultPrenom" value="value=\"${userToDisplay.prenom}\""/>
<c:set var="defaultEmail" value="value=\"${userToDisplay.email}\""/>
<c:set var="defaultPhone" value="value=\"${userToDisplay.phone}\""/>
<c:set var="defaultRue" value="value=\"${userToDisplay.adresse.rue}\""/>
<c:set var="defaultCpo" value="value=\"${userToDisplay.adresse.cpo}\""/>
<c:set var="defaultVille" value="value=\"${userToDisplay.adresse.ville}\""/>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Gestion de Profil"/>
</jsp:include>
<div class="row">
    <div class="col">
        <%--Message d'erreur de connexion--%>
        <p class="row text-decoration-underline text-danger">
            ${empty messageErreur ? "" : messageErreur}
        </p>
        <div class="row">
            <c:if test="${'creation'.equals(affichage)}">
                <form method="post" action="${pageContext.request.contextPath}/comptecreation" class="col">
            </c:if>
            <c:if test="${'modification'.equals(affichage)}">
                <form method="post" action="${pageContext.request.contextPath}/comptemodif" class="col">
            </c:if>
                <%--Pseudo / Nom--%>
                <div class="row">
                    <div class="col input-group mb-3">
                        <span class="col-2 input-group-text" id="idPseudo">Pseudo : <sup class="text-danger">*</sup></span>
                        <input type="text" class="col form-control" placeholder="Pseudo" name="pseudo" required
                            ${!empty userToDisplay.pseudo ? defaultPseudo : null}>

                    </div>
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idNom">Nom : <sup class="text-danger">*</sup></span>
                        <input type="text" class="form-control" placeholder="Nom" name="nom" required
                            ${!empty userToDisplay.nom ? defaultNom : null}>
                    </div>
                </div>

                <%--Prénom / Email--%>
                <div class="row">
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idPrenom">Prénom : <sup class="text-danger">*</sup></span>
                        <input type="text" class="form-control" placeholder="Prénom" name="prenom" required
                            ${!empty userToDisplay.prenom ? defaultPrenom : null}>
                    </div>
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idEmail">Email : <sup class="text-danger">*</sup></span>
                        <input type="email" class="form-control" placeholder="E-mail" name="email" required
                            ${!empty userToDisplay.email ? defaultEmail : null}>
                    </div>
                </div>

                <%--Téléphone / Rue--%>
                <div class="row">
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idTel">Tél : </span>
                        <input type="text" class="form-control" placeholder="Téléphone" name="tel"
                            ${!empty userToDisplay.phone ? defaultPhone : null}>
                    </div>
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idRue">Rue : </span>
                        <input type="text" class="form-control" placeholder="Rue" name="rue"
                            ${!empty userToDisplay.adresse.rue ? defaultRue : null}>
                    </div>
                </div>

                <%--Code Postal / Ville--%>
                <div class="row">
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idCpo">Code Postal : </span>
                        <input type="text" class="form-control" placeholder="Code Postal" name="cpo"
                            ${!empty userToDisplay.adresse.cpo ? defaultCpo : null}>
                    </div>
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idVille">Ville : </span>
                        <input type="text" class="form-control" placeholder="Ville" name="ville"
                            ${!empty userToDisplay.adresse.ville ? defaultVille : null}>
                    </div>
                </div>

                <%--Mot de passe Actuel--%>
                <c:if test="${'modification'.equals(affichage)}">
                    <div class="row">
                        <div class="col input-group mb-3">
                            <span class="input-group-text" id="idPassword">Mot de passe : <sup class="text-danger">*</sup></span>
                            <input type="password" class="form-control" placeholder="Mot de passe" name="password" required>
                        </div>
                    </div>
                </c:if>

                <%--Nouveau mot de passe / Confirmation--%>
                <div class="row">
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idNewPassword">Nouveau mot de passe : </span>
                        <input type="password" class="form-control" placeholder="Nouveau mot de passe" name="newPassword" />
                    </div>
                    <div class="col input-group mb-3">
                        <span class="input-group-text" id="idConfirmation">Confirmation : </span>
                        <input type="password" class="form-control" placeholder="Confirmation" name="confirmPassword"/>
                    </div>
                </div>

                <%--Créer / Annuler
                Afficher en cas de création de compte--%>
                <c:if test="${'creation'.equals(affichage)}">
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
                <c:if test="${'modification'.equals(affichage)}">
                    <div class="row">
                        <div class="col">
                            Credit : ${userToDisplay.credit}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <button type="submit" class="btn btn-secondary" name="action" value="maj">Enregistrer</button>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-secondary" name="action" value="supprimer">Supprimer</button>
                        </div>
                        <div class="col">
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/accueilS">Annuler</a>
                        </div>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>