<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<label for="categorie2">Categorie : </label>
<select name="categorie" id="categorie2">
    <option value="toutes">toutes</option>
    <c:forEach items="${libellesCategories}" var="libelleCategorie">
        <option value="${libelleCategorie}">${libelleCategorie}</option>
    </c:forEach>
</select>

