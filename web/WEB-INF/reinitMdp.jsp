
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Réinitialisation MDP</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col">
        </div>
        <div class="col">
            <form method="post" action="reinitMdp.jsp">

            <h3>Réinitialisation Mot de passe</h3>
            <label for="email" > Email associé à votre compte : </label>
            <input type="text" id="email" name="email" required><br>
            <button  type="submit button" class="btn btn-secondary"  name="reinitMdp"> Réinitialiser </button>
            <button  type="submit button" class="btn btn-secondary"  name="annuler"> Annuler </button>

            </form>
        </div>
        <div class="col">
        </div>
    </div>
</div>

</body>
</html>
