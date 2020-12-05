<%@page import="java.util.ArrayList"%>
<%@page import="org.bson.Document"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Course Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
		<div class="col-md-8" style="margin: auto">
		<h1><%request.getParameter("viewCourse");%></h1>
			<div class="form-area"
				style="background-color: #FAFAFA; padding: 10px 40px 60px; margin: 10px 0px 60px; border: 1px solid GREY;">
			<form role="form" action="Course" method="post">
				<table class="table table-striped table-hover" style="table-layout: fixed; width: 100%">
					<thead class="thead-dark">
						<tr align="center">
							<th>Grade Item</th>
							<th></th>
							<th></th>
							<th></th>
							
							
						</tr>
					</thead>
					<tbody>
						<%ArrayList<Document> std = (ArrayList<Document>)request.getAttribute("assignments"); 
        				for(Document s:std){%>
						<tr align="center">
							<td><%=s.get("name")%></td>
							<td></td>
							<td><input type="file" name="submission" id="submission"></td>
							<td><button
									type="submit" class="btn btn-primary" name="submit"
									value="<%=s.get("name")%>"><i class="fa fa-plus" title="submit"></i></button>
								</td>
						<%}%>
					</tbody>
				</table>
				</form>
			</div>
		</div>
</body>
</html>