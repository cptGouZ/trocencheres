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
    <jsp:param name="messageErreur" value="${messageErreur}"/>
    <jsp:param name="messageConfirm" value="${messageConfirm}"/>
</jsp:include>

<c:if test="${'modification'.equals(affichagejsp)}">
<form method="post" action="${pageContext.request.contextPath}/gestioncompte${!empty luid ? "?uid="+=luid: ""}">
</c:if>
<c:if test="${'creation'.equals(affichagejsp)}">
<form method="post" action="${pageContext.request.contextPath}/creationcompte">
</c:if>
    <div class="row justify-content-center">
        <div class="col-11 col-md-8 lg-5">
            <div class="row my-5 gy-4 justify-content-evenly">
                <%--Pseudo--%>
                <div class="col-12 col-lg-6 ">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idPseudo">Pseudo : <sup class="text-danger">*</sup></label>
                        <input type="text" class="col form-control" placeholder="Pseudo" name="pseudo" id="idPseudo" required
                            ${!empty userToDisplay.pseudo ? defaultPseudo : null}/>
                    </div>
                </div>
                <%--Nom--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idNom">Nom : <sup class="text-danger">*</sup></label>
                        <input type="text" class="col form-control" placeholder="Nom" name="nom" id="idNom" required
                            ${!empty userToDisplay.nom ? defaultNom : null}/>
                    </div>
                </div>
                <%--Prénom--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idPrenom">Prénom : <sup class="text-danger">*</sup></label>
                        <input type="text" class="col form-control" placeholder="Prénom" name="prenom" id="idPrenom" required
                            ${!empty userToDisplay.prenom ? defaultPrenom : null}>
                    </div>
                </div>
                <%--Email--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idEmail">Email : <sup class="text-danger">*</sup></label>
                        <input type="email" class="col form-control" placeholder="E-mail" name="email" id="idEmail" required
                            ${!empty userToDisplay.email ? defaultEmail : null}>
                    </div>
                </div>
                <%--Téléphone--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idTel">Tél : </label>
                        <input type="text" class="col form-control" placeholder="Téléphone" id="idTel" name="tel"
                            ${!empty userToDisplay.phone ? defaultPhone : null}/>
                    </div>
                </div>
                <%--Rue--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idRue">Rue : </label>
                        <input type="text" class="col form-control" placeholder="Rue" name="rue" id="idRue"
                            ${!empty userToDisplay.adresse.rue ? defaultRue : null}>
                    </div>
                </div>

                <%--Code Postal--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idCpo">Code Postal : </label>
                        <input type="text" class="col form-control" placeholder="Code Postal" name="cpo" id="idCpo"
                            ${!empty userToDisplay.adresse.cpo ? defaultCpo : null}>
                    </div>
                </div>

                <%--Ville--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-3 input-group-text" for="idVille">Ville : </label>
                        <input type="text" class="col form-control" placeholder="Ville" name="ville" id="idVille"
                            ${!empty userToDisplay.adresse.ville ? defaultVille : null}>
                    </div>
                </div>

                <%--Nouveau mot de passe--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-4 input-group-text" for="idNewPassword">Nouveau mot de passe : </label>
                        <input type="password" class="form-control" placeholder="Nouveau mot de passe" name="newPw" id="idNewPassword"/>
                    </div>
                </div>
                <%--Confirmation--%>
                <div class="col-12 col-lg-6">
                    <div class="row input-group mx-0">
                        <label class="col-4 input-group-text" for="idConfirmation">Confirmation : </label>
                        <input type="password" class="form-control" placeholder="Confirmation" name="newPwConf" id="idConfirmation"/>
                    </div>
                </div>

                <%--Création--%>
                <c:if test="${'creation'.equals(affichagejsp)}">
                    <%--Créer / Annuler--%>
                    <button type="submit" class="col-5 col-lg-3 my-2 btn btn-secondary" name="action" value="creer">Créer</button>
                    <a class="col-5 col-lg-3 my-2 btn btn-secondary" href="${pageContext.request.contextPath}/accueil">Annuler</a>
                </c:if>

                <%--Modification--%>
                <c:if test="${'modification'.equals(affichagejsp)}">
                    <%--Mot de passe Actuel--%>
                    <div class="col-12 col-lg-6">
                        <div class="row input-group mx-0">
                            <label class="col-3 input-group-text" for="idPassword">Mot de passe : <sup class="text-danger">*</sup></label>
                            <input type="password" class="col form-control" placeholder="Mot de passe" name="currentPw" id="idPassword" required>
                        </div>
                    </div>
                    <%--Enregistrer / Supprimer--%>
                    <div class="row justify-content-evenly my-3">
                        <button type="submit" class="col mx-5 btn btn-secondary" name="action" value="maj">Enregistrer</button>
                        <a class="col mx-5 btn btn-secondary" href="${pageContext.request.contextPath}/suppressioncompte?uid=${userToDisplay.id}">Supprimer</a>
                        <a class="col mx-5 btn btn-secondary" href="${pageContext.request.contextPath}/accueil">Annuler</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</form>

<jsp:include page="fragments/footer.jsp"/>