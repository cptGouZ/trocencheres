
<!--Données pous JSTL et Charset-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Nouvelle vente"/>
</jsp:include>

 <%--BLOC VENTE--%>
 <div class="row">
     <%--Colonne PHOTO--%>
     <div class="col">
         <p> Mettre lien vers Photo</p>
     </div>

     <%--Form VENTE--%>
     <div class="col"><h3>Nouvelle Vente</h3>
         <%--Message d'erreur de connexion--%>
         <p class="text-decoration-underline text-danger">${empty messageErreurArticle ? "" : messageErreurArticle}</p>
         <form method="post" action="vente">
             <%--ARTICLE--%>
             <div class="input-group mb-3">
                 <span class="input-group-text" >Article :  <p class="text-danger">* </p>
                 <input type="text" class="form-control" placeholder="nom de l'article" name="article" value="${articleSaisie}" required></span>
             </div>
             <%--DESCRIPTION--%>
                 <div class="input-group">
                     <span class="input-group-text" >Description :  <p class="text-danger">* </p>
                     <textarea class="form-control" placeholder="décrire l'article" id="floatingTextarea2" style="height: 100px" name="description" required>${descriptionSaisie}</textarea></span>
                 </div><br>
             <%--CATEGORIES--%>
             <div>
                 <span class="input-group-text" for="categorie">Catégorie :  <p class="text-danger">* </p>
                 <select name="categorie" id="categorie"  required>
                     <option value="">--Sélectionner une catégorie--</option>
                     <option name="categorie" value="1" ${categorieSaisie==1 ? "selected" : ""} >Sport</option>
                     <option name="categorie" value="2" ${categorieSaisie==2 ? "selected" : ""}>Vêtement</option>
                     <option name="categorie" value="3" ${categorieSaisie==3 ? "selected" : ""}>Ameublement</option>
                     <option name="categorie" value="4" ${categorieSaisie==4 ? "selected" : ""}>Alimentation</option>
                     <option name="categorie" value="5" ${categorieSaisie==5 ? "selected" : ""}>Divers</option>
                 </select>
                 </span>
             </div>
             <br>

             <%--UPLOAD PHOTO--%>
             <div class="input-group mb-3">
                 <label class="input-group-text" for="inputGroupFile01">Télécharger Photo Article</label>
                 <input type="file" class="form-control" id="inputGroupFile01">
             </div><br>

             <%--MISE A PRIX--%>
             <div>
                 <span class="input-group-text" for="tentacles">Mise à prix :  <p class="text-danger">* </p>
                 <input type="number" id="tentacles" name="prixDepart" min="1" max="" value="${prixSaisie}" required></span>
             </div><br>

             <%--DEBUT ENCHERE--%>
                 <div>
                     <span class="input-group-text" for="debutEnchere">Début de l'enchère : <p class="text-danger">* </p>
                     <input type="date" id="debutEnchere" name="debutEnchere"
                            value="${empty dateDebutSaisie ? dateDuJour : dateDebutSaisie}"
                            min="${dateDuJour}" max="2050-12-31" required></span>
                 </div>

             <%--FIN ENCHERE--%>
                 <div>
                     <span class="input-group-text" for="debutEnchere">Fin de l'enchère : <p class="text-danger">* </p>
                     <input type="date" id="finEnchere" name="finEnchere"
                        value="${empty dateFinSaisie ? "" : dateFinSaisie}"
                        min="${dateDuJour}" max="2050-12-31"></span>
                 </div>
                 <br>

             <%--BLOC RETRAIT--%>
                 <div class="border border-dark">Adresse de retrait :
                <%--RUE--%>
                    <div class="input-group mb-3">
                        <span class="input-group-text" >Rue : </span>
                        <input type="text" class="form-control" placeholder="rue" name="rue" value="${empty rueSaisie ? userConnected.getAdresse().getRue() : rueSaisie}" required>
                    </div>
                <%--CODE POSTAL--%>
                    <div class="input-group mb-3">
                        <span class="input-group-text" >Code Postal : </span>
                        <input type="text" class="form-control" placeholder="code postal" name="cpo" value="${empty cpoSaisie ? userConnected.getAdresse().getCpo() : cpoSaisie}" required>
                    </div>
                <%--VILLE--%>
                    <div class="input-group mb-3">
                        <span class="input-group-text" >Ville : </span>
                        <input type="text" class="form-control" placeholder="ville" name="ville" value="${empty villeSaisie ? userConnected.getAdresse().getVille() : villeSaisie}" required>
                    </div>
                 </div>
                 <br>
             <%--BOUTON ENREGISTRER--%>
             <button  type="submit button" class="btn btn-secondary"  name="enregistrer"> Enregistrer </button>

         </form>
         <%--BOUTON ANNULER--%>
         <a href="${pageContext.request.contextPath}/accueilS" class="btn btn-secondary"> Annuler </a>


     </div>
     <%--Colonne Vide--%>
     <div class="col">
     </div>
 </div>

 <%@ include file="fragments/footer.jsp"%>
