<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<H1>TitleH1</H1>

<div class="mtheorem">
<div class="mtitle">Theorem</div>
<div class="mbody">
\[ a+b+c \]
</div>
</div>
<br clear="all" />

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<form action="">
	a<input type="text" width="20" maxlength="20" value="${a}" name="a" id="aId"> <BR>
	<button type="submit" value="css" name="waction">submit</button>
</form>
</div>
</div>
<br clear="all" />

<div class="mexample">
<div class="mtitle">Example Solution</div>
<div class="mbody">
</div>
\[ a+b+c \]
</div>
<br clear="all" />

<div class="mnote">
<div class="mtitle">Note</div>
<div class="mbody">
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
</div>
</div>
<br clear="all" />

<div class="mremark">
<div class="mtitle">Remark</div>
<div class="mbody">
Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
</div>
</div>
<br clear="all" />

