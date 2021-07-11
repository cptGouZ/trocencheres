
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Connexion"/>
    <jsp:param name="messageErreur" value="${messageErreur}"/>
    <jsp:param name="messageConfirm" value="${messageConfirm}"/>
</jsp:include>
            <%--BLOC CONNEXION--%>
            <div class="row justify-content-center my-5 ">
                <%--Form Connexion--%>
                <div class="col-4 border border-secondary border-3 rounded-3">
                    <form method="post" action="${pageContext.request.contextPath}/connexion">
                        <input type="hidden" name="destination" value="${destination}${!empty uid ? "?uid="+=uid : ""}">
                        <input type="hidden" name="uid" value="${uid}">
                        <%--LOGIN--%>
                        <div class="input-group mb-3 mt-3">
                            <span class="input-group-text" >Identifiant : <sup class="text-danger">* </sup></span>
                            <input type="text" class="form-control" placeholder="pseudo / email" name="login" required>
                        </div>
                        <%--MDP--%>
                        <div class="input-group mb-3">
                            <span class="input-group-text" >Mot de passe : <sup class="text-danger">* </sup></span>
                            <input type="password" class="form-control" placeholder="mot de passe" name="mdp" required >
                        </div>
                        <div class="row">
                            <%--RESTE CONNECTE--%>
                            <div class="col">
                                <input type="checkbox" id="resteConnecte" name="resteConnecte" checked>
                                <label for="resteConnecte">Se souvenir de moi</label>
                            </div>
                            <%--LIEN REINIT MDP--%>
                            <div class="col">
                                <a href="${pageContext.request.contextPath}/reinitMdp" >Mot de passe oublié</a>
                            </div>
                        </div>

                        <%--Message d'erreur de connexion--%>
                        ${empty messageErreurLog ? "" : "<p class=\"text-decoration-underline text-danger\">"+=messageErreurLog+="</p>"}

                        <div class="row my-1">
                            <%--BOUTON CONNEXION--%><br>
                            <button class="col mx-2 btn btn-secondary"  name="connexion">Connexion</button>

                            <%--BOUTON CREER COMPTE/PROFIL--%>
                            <a href="${pageContext.request.contextPath}/creationcompte" class="col mx-2  btn btn-secondary">Créer un compte</a>
                        </div>
                        <div class="row my-1 justify-content-center">
                            <%--BOUTON ANNULER--%>
                            <a href="${pageContext.request.contextPath}/accueil" class="col-6 btn btn-secondary">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>

<jsp:include page="fragments/footer.jsp"/>

