
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="fragments/header.jsp"%>

<%--Message d'erreur de connexion--%>
<% String affichMessageErr = (String) request.getAttribute("messageErreurLog"); %>
<%= affichMessageErr %>

            <%--BLOC CONNEXION--%>
            <br>
            <div class="row">
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
                        <br>
                        <%--RESTE CONNEXTE--%>
                        <input type="checkbox" id="resteConnecte" name="resteConnecte"
                               checked>
                        <label for="resteConnecte">Se souvenir de moi</label>
                        <%--LIEN REINIT MDP--%>
                        <li><a href="${pageContext.request.contextPath}/reinitMdp" >Mot de passe oublié</a></li>
                        <%--BOUTON CONNEXION--%>
                        <button  type="submit button" class="btn btn-secondary"  name="connexion"> Connexion </button>


                    </form>

                    <form method="get" action="${pageContext.request.contextPath}/gestioncompte?userId=0">
                        <%--BOUTON CREATION COMPTE--%>
                        <button  type="submit button" class="btn btn-secondary"  name="creerCompte"> Créer un compte </button>
                    </form>
                </div>
                <%--Colonne Vide--%>
                <div class="col">
                </div>
            </div>

<%@ include file="fragments/footer.jsp"%>

