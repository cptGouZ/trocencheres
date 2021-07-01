
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
                <div class="col-4 border border-info border-3 rounded-3">
                    <form method="post" action="${pageContext.request.contextPath}/connexion">
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

                        <%--Message d'erreur de connexion--%>
                        <p class="text-decoration-underline text-danger">${empty messageErreurLog ? "" : messageErreurLog}</p>


                        <%--BOUTON CONNEXION--%>
                        <button  type="submit button" class="btn btn-secondary"  name="connexion"> Connexion </button>
                        <br>

                        <%--RESTE CONNECTE--%>
                        <input type="checkbox" id="resteConnecte" name="resteConnecte"
                               checked>
                        <label for="resteConnecte">Se souvenir de moi</label>
                        <br>
                    </form>
                    <div>
                        <div class="input-group mb-2 me-2">
                            <%--BOUTON ANNULER--%>
                            <a href="${pageContext.request.contextPath}/accueil" class="btn btn-secondary me-4 mb-2 rounded"> Annuler </a>

                            <%--BOUTON CREER COMPTE/PROFIL--%>
                            <a href="${pageContext.request.contextPath}/creationcompte" class="btn btn-secondary mb-2 rounded"> Créer un compte </a>
                            <br>
                        </div>

                        <%--LIEN REINIT MDP--%>
                        <li><a href="${pageContext.request.contextPath}/reinitMdp" >Mot de passe oublié</a></li>
                        <br>
                    </div>
                </div>
            </div>

<jsp:include page="fragments/footer.jsp"/>

