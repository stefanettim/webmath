<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="wver" value="20231004" />
<fmt:formatDate value="${requestScope.startTime}" pattern="yyyyMMdd" var="wdate"/>


<!doctype html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>${applicationScope.mProperties.pageTitle}</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="${ctx}/css/style.css?wver=${wver}">
        <link rel="stylesheet" href="${ctx}/css/w.css?wver=${wver}">
        
        <!--  https://www.mathjax.org/#demo -->
        <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
		<script id="MathJax-script" async=true src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
                
    </head>
    
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
 
 
		<div class="wrapAll clearfix">
			<div class="mainsection">
			
			    <div class="header">
 				    <div class="logo" onclick="location.href='<c:out value="${ctx}"/>'" style="cursor: pointer;">
						<img src='${ctx}/img/logo.png' alt="logo">
					</div>
				    <div class="headerTitle"></div>
					<div class="headerPaths">
						<a href="${ctx}">home</a>
						<c:set var="linkpath" value="${ctx}/a"></c:set>
						<c:forEach items="${wpaths}" var="wp" varStatus="vs">
						    <c:set var="linkpath" value="${linkpath}/${wp}"></c:set>
						    <c:set var="link" value="${linkpath}"></c:set>
						    <c:if test="${!vs.last}">
						    	<c:set var="link" value="${link}/index"></c:set>
						    	&#x2022;<a href="${link}">${wp}</a>
						    </c:if>
						</c:forEach>
					</div>
	
					<div class="headerLinks">
						<a href="${ctx}/a/fakeError">fakeError</a>
						&#x2022;<a href="${ctx}/a/css">css</a>
						|<a href="${ctx}/a/about">about</a>
					</div>
				</div>
				
				
	<div class="article">
		<c:if test="${not empty message}"><div class="message">${message}</div></c:if>	

		<!-- page: <%request.getAttribute("page");%> -->			
		<% String tPath = "/WEB-INF/jsp/"+request.getAttribute("page")+".jsp";%>
		<jsp:include  page="<%=tPath%>" />
	</div>	

		<div class="pagefooter">
		
			<div class="footerlinks">
			    <table class="footer">
			    <tr><td>start</td><td><fmt:formatDate value="${requestScope.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
			    <tr><td>end</td><td><fmt:formatDate value="${requestScope.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
			    <tr><td>elapsed</td><td>${requestScope.elapsedTime}</td></tr>
			    <c:if test="${not empty sessionInfo}">
			    <tr><td>session</td><td><%=""+request.getAttribute("sessionInfo")%></td></tr>
			    </c:if>
			    </table>
			</div>
			
			<div class="footerlinks">
			    <table class="footer">
			    <tr><td>full</td><td>${requestScope.wfull}</td></tr>
			    <tr><td>path</td><td>${requestScope.wpath}</td></tr>
			    </table>
			</div>

			<div class="footerlinks">
			    <table class="footer">
			    <tr><td>manager</td><td>${requestScope.wmanager}</td></tr>
			    <tr><td>init</td><td>${requestScope.winit}</td></tr>
			    <tr><td>action</td><td>${requestScope.waction}</td></tr>
			    </table>
			</div>

		</div>
			
			</div>		
		</div>
		
    </body>
</html>
