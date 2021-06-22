
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Réinitialisation MDP</title>
</head>
<body>

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
            <h3>Réinitialisation Mot de passe</h3>
            <%--EMAIL du COMPTE--%>
            <label for="email" > Email associé à votre compte : </label>
            <input type="text" id="email" name="email" ><br>
            <%--BOUTON REINIT--%>
            <button  type="submit button" class="btn btn-secondary"  name="reinitMdp"> Réinitialiser </button>
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

</body>
</html>
