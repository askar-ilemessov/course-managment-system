<%@page import="java.util.ArrayList"%>
<%@page import="org.bson.Document"%>
<%@page import="com.mongodb.client.MongoCursor"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student View</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
	<div class="row">
	<ul class="nav nav-tabs col-11" id="myTab" role="tablist">

		<li class="nav-item"><a class="nav-link active" id="courses-tab"
			data-toggle="tab" href="#courses1" role="tab" aria-controls="student"
			aria-selected="true">Courses</a></li>

		<li class="nav-item"><a class="nav-link" id="courses-tab"
			data-toggle="tab" href="#courses2" role="tab" aria-controls="profile"
			aria-selected="false">Registered Courses</a></li>
	</ul>
	<h1><%request.getSession(true).getAttribute("userid");%></h1>
	<a href="/cms/logout">Log Out</a>
	</div>

	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="courses1" role="tabpanel"
			aria-labelledby="courses-tab">
			<div class="container">
				<div class="table-title">
					<div class="row">
						<div class="col-6 pt-2">
							<h2>
								Register for <b>Courses</b>
							</h2>
						</div>
						<div class="col-6"></div>
					</div>
				</div>
				<form  action="RegisterCourse" method="post">
					<table class="table table-striped table-hover">
						<thead class="thead-dark">
							<tr align="center">

								<th>Course Code</th>
								<th>Course Name</th>
								<th>Professor Assigned</th>
								<th>Section</th>
								<th>Term</th>
								<th>Capacity</th>
								<th></th>
							</tr>
						</thead>

						<tbody>
							<%
								ArrayList<Document> std = (ArrayList<Document>) request.getAttribute("courses");
							for (Document s : std) {
							%>
							<tr align="center">

								<td><%=s.get("course_code")%></td>
								<td><%=s.get("course_name")%></td>
								<td><%=s.get("prof_name")%></td>
								<td><%=s.get("section")%></td>
								<td><%=s.get("term")%></td>
								<td><%=s.get("capacity")%></td>

								<td> </td>
								
							</tr>
							<%}%>
						</tbody>

					</table>
					<input type="text" id="courseCode" name="course_code" placeholder="Corse Code" class="form-control" required> 
					<input type="submit" name="Register" class= "btn btn-info" value= "submit" > 
				</form>
			</div>
		</div>
		<div class="tab-pane fade" id="courses2" role="tabpanel"
			aria-labelledby="courses-tab">
			<div class="container">
				<div class="table-title">
					<div class="row">
						<div class="col-6 pt-2">
							<h2>
								Registered <b>Courses</b>
							</h2>
						</div>

					</div>
					<form action="Course" method="get">
					<table class="table table-striped table-hover">
						<thead class="thead-dark">
							<tr align="center">
								
								<th>Course Name</th>
								<th>Course Term</th>
								<th>Actions</th>


						</tr>
					</thead>
					<tbody>
						<%
							List<Document> regCourses = (List<Document>) request.getAttribute("courses2");
								//ArrayList<Document> std2 = (ArrayList<Document>) request.getAttribute("courses2");
							
							for (Document course : regCourses) {
							
							%>
							<tr align="center">
								
								<td><%=course.getString("name")%></td>
								<td><%=course.getString("term")%></td>
								<td><button
									type="submit" class="btn btn-primary" name="viewCourse"
									value="<%=course.get("name")%>"><i class="fa fa-eye" data-toggle="tooltip" title="viewCourse"></i></button>
								</td>

							</tr>
							<%}%>
						</tbody>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>