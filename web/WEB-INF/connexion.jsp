
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Se Connecter</title>
</head>

<body>
<header>

</header>
        <div class="container">
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
                        <li><a href="">Mot de passe oublié</a></li>

                        <button  type="submit button" class="btn btn-secondary"  name="connexion"> Connexion </button>
                        <button  type="submit button" class="btn btn-secondary"  name="creerCompte"> Créer un compte </button>

                    </form>
                </div>

                <div class="col">

                </div>
            </div>
        </div>



</body>
</html>
