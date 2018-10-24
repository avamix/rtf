<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/pages/layout/header.jsp" %>

<header class="header">
    <h1>Give me your feedback your clothes and your motorcycle</h1>
</header>

<table class="table">
    <thead>
    <tr>
        <th scope="col" class="col-xs-7">Name</th>
        <th/>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">
            <a href="project.html">Project 1</a>
        </th>
        <td scope="row">
            <div class="btn-group">
                <button class="button copy" onclick="window.location='project.html'"/>
                <button class="button delete"/>
            </div>
        </td>
    </tr>
    </tbody>
</table>
<form action="project.html">
    <button class="button add">Add new project</button>
</form>

<%@ include file="/WEB-INF/pages/layout/footer.jsp" %>
