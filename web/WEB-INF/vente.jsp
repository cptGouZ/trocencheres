
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="fragments/header.jsp"%>


<html>
<head>
    <title>Vente</title>
</head>
<body>
 <h3>Vente</h3>

 <%--BLOC VENTE--%>
 <br>
 <div class="row">
     <%--Colonne PHOTO--%>
     <div class="col">
         <p> Mettre lien vers Photo</p>
     </div>

     <%--Form VENTE--%>
     <div class="col">
         <form method="" action="vente">
             <%--ARTICLE--%>
             <div class="input-group mb-3">
                 <span class="input-group-text" >Article : </span>
                 <input type="text" class="form-control" placeholder="nom de l'article" name="article" required>
             </div>
             <%--DESCRIPTION--%>
                 <div class="input-group">
                     <span class="input-group-text" >Description : </span>
                     <textarea class="form-control" placeholder="décrire l'article" id="floatingTextarea2" style="height: 100px" required></textarea>
                 </div><br>
             <%--CATEGORIES--%>
             <div>
                 <label for="categorie">Catégorie :</label>
                 <select name="categorie" id="categorie">
                     <option value="">--Sélectionner une catégorie--</option>
                     <option value="informatique">Informatique</option>
                     <option value="ameublement">Ameublement</option>
                     <option value="vetement">Vetement</option>
                     <option value="sport&loisirs">Sport&Loisirs</option>
                 </select>
             </div>
             <br>

             <%--UPLOAD PHOTO--%>
             <div class="input-group mb-3">
                 <label class="input-group-text" for="inputGroupFile01">Télécharger Photo Article</label>
                 <input type="file" class="form-control" id="inputGroupFile01">
             </div><br>

             <%--MISE A PRIX--%>
             <div>
                 <label for="tentacles">Mise à prix :</label>
                 <input type="number" id="tentacles" name="tentacles" min="1" max="">
             </div><br>

             <%--DEBUT ENCHERE--%>
                 <div>
                     <label for="debutEnchere">Début de l'enchère :</label>
                     <input type="date" id="debutEnchere" name="debutEnchere"
                            value=""
                            min="2018-01-01" max="2050-12-31">
                 </div>

             <%--FIN ENCHERE--%>
                 <div>
                     <label for="finEnchere">Fin de l'enchère :</label>
                     <input type="date" id="finEnchere" name="finEnchere"
                        value=""
                        min="2018-01-01" max="2018-12-31">
                 </div>
                 <br>

             <%--BLOC RETRAIT--%>
                 <div class="border border-dark">Adresse de retrait
                <%--RUE--%>
                    <div class="input-group mb-3">
                        <span class="input-group-text" >Rue : </span>
                        <input type="text" class="form-control" placeholder="rue" name="rue" required>
                    </div>
                <%--CODE POSTAL--%>
                    <div class="input-group mb-3">
                        <span class="input-group-text" >Code Postal : </span>
                        <input type="text" class="form-control" placeholder="code postal" name="cpo" required>
                    </div>
                <%--VILLE--%>
                    <div class="input-group mb-3">
                        <span class="input-group-text" >Ville : </span>
                        <input type="text" class="form-control" placeholder="ville" name="article" required>
                    </div>
                 </div>
                 <br>
             <%--BOUTON ENREGISTRER--%>
             <button  type="submit button" class="btn btn-secondary"  name="enregistrer"> Enregistrer </button>
             <%--BOUTON ANNULER--%>
             <button  type="submit button" class="btn btn-secondary"  name="annuler"> Annuler </button>

         </form>
     </div>
     <%--Colonne Vide--%>
     <div class="col">
     </div>
 </div>





 <%@ include file="fragments/footer.jsp"%>
