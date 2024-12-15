<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />



<script type="text/javascript">
  function sudokuListChange(){
	  
	  var s = document.getElementById("sudokuList").value;
	  console.log(s);
	  
	  for(let i=0;i<9;i++){
		  for(let j=0;j<9;j++){
			  var c = s.charAt(i*9+j);
			  
			  if(c=='.'){
				  c=" "
			  }
			  
			  var id = "input"+i+""+j;
			  console.log(id+"="+c);

			  document.getElementById(id).value=c;
		  }
	  }
	  
  }

</script>


<style>
TABLE.sudokuTable {
	border-collapse: separate;
	border-spacing: 0;
	border-width: 1px 0 0 1px;
	width: 300px;
	height: 300px;
}

TD.s {
	border: 1px solid #000;
	border-bottom: none;
	border-right: none;
	text-align: center;
}

TD.s0 {
	background: #F8F8F8;
}

TD.s1 {
	background: #DDD;
}

TD.so {
  color: red;
  font-weight: bold;
}

tr.s:last-child td.s {
	border-bottom: 1px solid #000;
}

tr.s td.s:last-child {
	border-right: 1px solid #000;
}

INPUT.sudokuInput {
	background-color: transparent;
	border-color: transparent;
	width: 8px;
	text-align: center;
	font-weight: bold;
}

.container {
    width: 100%;
    padding: 10px;
}

.column {
	float: left;
    margin-right: 10px;
	border: 0px solid #000;
	min-width: 320px;
}

.solutions {
	font-size: small;
}

</style>


<div class="container">
	
	<div class="column">
	<h1>
	Sudoku
	</h1>
	<form action="sudoku">
	<table class="sudokuTable">

				<c:forEach var="i" begin="0" end="8" step="1" varStatus="istatus">

					<tr class="s">

						<c:forEach var="j" begin="0" end="8" step="1" varStatus="jstatus">

							<c:choose>
								<c:when
									test="${    ( (0<=i) && (i<3) && (0<=j) && (j<3) ) 
                    || ( (6<=i) && (i<9) && (0<=j) && (j<3) )
                    || ( (6<=i) && (i<9) && (6<=j) && (j<9) )
                    || ( (0<=i) && (i<3) && (6<=j) && (j<9) )
                    || ( (3<=i) && (i<6) && (3<=j) && (j<6) )
                     }">
									<c:set var="bclass" value="s0" />
								</c:when>
								<c:otherwise>
									<c:set var="bclass" value="s1" />
								</c:otherwise>
							</c:choose>

							<td id="cell${i}${j}" class="s ${bclass}">
							<input id="input${i}${j}" class="sudokuInput" name="c${i}${j}" value="${sudoku.cell(i,j)}"></td>
						</c:forEach>

					</tr>

				</c:forEach>



			</table>

			<input type="hidden" name="waction" value="execute">
			<BR> 
			<input type="submit" value="solve" name="sudokuAction">
			<input type="submit" value="clear" name="sudokuAction">
			<input type="submit" value="platinumBlonde" name="sudokuAction"> 
			<BR> 
			<input type="text" width="81" maxlength="81" value="" name="sudokuString"> 
			<input type="submit" value="upload" name="sudokuAction"> 
			<BR> 
			<input type="submit" value="easy" name="sudokuAction"> 
			<input type="submit" value="wiki" name="sudokuAction"> 
			<input type="submit" value="diabolical" name="sudokuAction"> 
				
			<BR>  
			<select id="sudokuList" name="sudokuListString" onchange="sudokuListChange()">
				<c:forEach var="sudokuPair" items="${sudokus}">
					<option value="${sudokuPair[1]}">${sudokuPair[0]}</option>
				</c:forEach>
			</select> 
			<input type="submit" value="reload" name="sudokuAction"> 	

		</form>

	</div>

<c:if test="${sudokuSolution!=null}">
	<div class="column">
			<h1>
			<c:if test="${sudokuSolution.solved()}">Solution</c:if>
			<c:if test="${!sudokuSolution.solved()}">Failed</c:if>
			</h1>
			
			<form action="sudoku">
			<table class="sudokuTable">
				<c:forEach var="i" begin="0" end="8">
					<tr class="s">
						<c:forEach var="j" begin="0" end="8">

							<c:choose>
								<c:when
									test="${    ( (0<=i) && (i<3) && (0<=j) && (j<3) ) 
                    || ( (6<=i) && (i<9) && (0<=j) && (j<3) )
                    || ( (6<=i) && (i<9) && (6<=j) && (j<9) )
                    || ( (0<=i) && (i<3) && (6<=j) && (j<9) )
                    || ( (3<=i) && (i<6) && (3<=j) && (j<6) )
                     }">
									<c:set var="bclass" value="s0" />
								</c:when>
								<c:otherwise>
									<c:set var="bclass" value="s1" />
								</c:otherwise>
							</c:choose>
							
							<c:choose>
							<c:when test="${sudokuSolution.board[i][j]==sudoku.board[i][j]}">
								<c:set var="oclass" value="so" />
							</c:when>
								<c:otherwise>
									<c:set var="oclass" value="" />
								</c:otherwise>
								</c:choose>

							<TD class="s ${bclass} ${oclass}">${sudokuSolution.board[i][j]}</TD>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
			</form>

			<div class="solutions">
				<c:forEach var="log" items="${sudokuLogs}">
					<BR> ${log} 
  				</c:forEach>
			</div>
	</div>

</c:if>
	
</div>
<br clear="all" />

<c:if test="${sudokuSolution!=null}">
	<div class="solutions">
			<h3>ProblemCode</h3>
			${sudoku.toLine()}
			<h3>SolutionCode</h3>
			${sudokuSolution.toLine()}
	</div>
</c:if>

