<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<select class="form-select" aria-label="Default select example">
    <option value="0">Categories</option>
    <c:forEach var="categorie" items="${listeCategories}">
        <option value="${categorie.id}">${categorie.libelle}</option>
    </c:forEach>
</select>

