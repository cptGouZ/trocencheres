<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Mot de passe oublié"/>
    <jsp:param name="messageErreur" value="${messageErreur}"/>
    <jsp:param name="messageConfirm" value="${messageConfirm}"/>
</jsp:include>

<%--BLOC REINIT MDP--%>
<div class="container">
    <div class="row">
        <%--Colonne Vide--%>
        <div class="col">
        </div>
        <%--FORM Reinit MDP--%>
        <div class="col">
            <form method="" action="">
            <%--Titre--%>
            <h3>Reinitialisation Mot de passe</h3>
            <%--EMAIL du COMPTE--%>
            <label for="email" > Email associé à votre compte : </label>
            <input type="text" id="email" name="email" ><br>
            <%--BOUTON REINIT--%>
            <button  type="submit button" class="btn btn-secondary"  name="reinitMdp"> Reinitialiser </button>
            </form>

            <form method="get" action="connexion" >
            <%--BOUTON ANNULER--%>
            <button  type="submit button" class="btn btn-secondary"  > Annuler </button>
            </form>

        </div>
        <div class="col">
        </div>
    </div>
</div>
<%@ include file="fragments/footer.jsp"%>