<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<H1>NeuralNetwork</H1>
<div class="button-container">
<a href="${ctx}/a/neuralNetworks/mnist" class="button">Mnist</a>
<a href="${ctx}/a/neuralNetworks/geometry?waction=guess" class="button">Geometry</a>
</div>