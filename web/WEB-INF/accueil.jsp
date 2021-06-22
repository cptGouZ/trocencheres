<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>

<div class="row">
    <div class="col">
        <form action="http://localhost:8080/trocencheres_war_exploded/accueilS" method="post">
            <label id="col" for="contient"></label><textarea name="contient" id="contient" cols="20" rows="2">Le nom de l'article contient</textarea>
            <label for="categorie">Categorie : </label>
            <select name="categorie" id="categorie">
                <option value="toutes">Toutes</option>
                <option value="informatique">Informatique</option>
                <option value="ameublement">Ameublement</option>
                <option value="vetement">Vetement</option>
                <option value="sport&loisirs">Sports&Loisirs</option>
            </select>
            <button class="btn" type="submit">Rechercher</button>
        </form>
    </div>
</div>

<div class="affic">
    <div class="col">
        <p>
            <%
                out.print("<div>" + request.getAttribute("categorie") + "</div>");
            %>
        </p>
    </div>
</div>



</body>
</html>
