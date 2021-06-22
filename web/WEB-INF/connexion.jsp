
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="fragments/header.jsp"%>

            <div class="row">
                <div class="col">

                </div>

                <div class="col">
                    <form method="post" action="connexion.jsp">

                        <label for="login" > Identifiant : </label>
                        <input type="text" id="login" name="login" required><br>

                        <label for="mdp" > Mot de passe : </label>
                        <input type="text" id="mdp" name="mdp" required><br>

                        <input type="checkbox" id="resteConnecte" name="resteConnecte"
                               checked>
                        <label for="resteConnecte">Se souvenir de moi</label>
                        <li><a href="${pageContext.request.contextPath}/reinitMdp" >Mot de passe oublié</a></li>

                        <button  type="submit button" class="btn btn-secondary"  name="connexion"> Connexion </button>
                        <button  type="submit button" class="btn btn-secondary"  name="creerCompte"> Créer un compte </button>

                    </form>
                </div>

                <div class="col">

                </div>
            </div>

<%@ include file="fragments/footer.jsp"%>

