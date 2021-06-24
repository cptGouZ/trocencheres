
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="fragments/header.jsp"%>


            <%--BLOC CONNEXION--%>
            <br>
            <div class="row">
                <br>
                <br>
                <%--Colonne Vide--%>
                <div class="col">
                </div>

                <%--Form Connexion--%>
                <div class="col">
                    <form method="post" action="connexion">
                        <%--LOGIN--%>
                        <div class="input-group mb-3">
                            <span class="input-group-text" >Identifiant : </span>
                            <input type="text" class="form-control" placeholder="pseudo / email" name="login" required>
                        </div>
                        <%--MDP--%>
                        <div class="input-group mb-3">
                            <span class="input-group-text" >Mot de pass</span>
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
                            <%--BOUTON ANNULER--%>
                            <a href="${pageContext.request.contextPath}/accueilS" class="btn btn-secondary"> Annuler </a>
                            <br>
                            <br>

                            <%--BOUTON CREER COMPTE/PROFIL--%>
                            <a href="${pageContext.request.contextPath}/gestioncompte?userId=0" class="btn btn-secondary"> Créer un compte </a>
                            <br>

                            <%--LIEN REINIT MDP--%>
                            <li><a href="${pageContext.request.contextPath}/reinitMdp" >Mot de passe oublié</a></li>
                            <br>
                        </div>
                </div>
                <%--Colonne Vide--%>
                <div class="col">
                </div>
            </div>

<%@ include file="fragments/footer.jsp"%>

