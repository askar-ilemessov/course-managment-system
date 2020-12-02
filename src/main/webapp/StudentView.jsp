<%@page import="java.util.ArrayList"%>
<%@page import="org.bson.Document"%>
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
	
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item"><a class="nav-link active"
				id="courses-tab" data-toggle="tab" href="#courses" role="tab"
				aria-controls="student" aria-selected="true">Courses</a></li>

			<li class="nav-item"><a class="nav-link" id="courses-tab"
				data-toggle="tab" href="#courses" role="tab" aria-controls="profile"
				aria-selected="false">Courses</a></li>
		</ul>
		
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="courses" role="tabpanel"
				aria-labelledby="courses-tab">
				<div class="container">
					<div class="table-title">
						<div class="row">
							<div class="col-6 pt-2">
								<h2>
									Register for  <b>Courses</b>
								</h2>
							</div>
							<div class="col-6">
								<div class="py-2" style="float: right;">
									<button class="btn btn-primary" name="Register"
										data-toggle="modal">
										<i class="fa fa-user"></i><span> Register</span>
									</button>
								</div>

							</div>
						</div>
					</div>
					<form role="form" action="student" method="post">
					<%!String deleteDoc = "null";%>
					<table class="table table-striped table-hover">
						<thead class="thead-dark">
							<tr align="center">
								<th></th>
								<th>Course Code</th>
								<th>Course Name</th>
								<th>Professor Assigned</th>
								<th>Section</th>
								<th>Term</th>
								<th>Capacity</th>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<Document> std = (ArrayList<Document>) request.getAttribute("courses");
							for (Document s : std) {
							%>
							<tr align="center">
								<td><span class="custom-checkbox"> <input
										type="checkbox" id="checkbox5" name="options[]" value="1">
										<label for="checkbox5"></label>
								</span></td>
								<td><%=s.get("course_code")%></td>
								<td><%=s.get("course_name")%></td>
								<td><%=s.get("prof_name")%></td>
								<td><%=s.get("section")%></td>
								<td><%=s.get("term")%></td>
								<td><%=s.get("capacity")%></td>
							</tr>
							<%}%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="courses" role="tabpanel"
				aria-labelledby="courses-tab">
				<div class="container">
					<div class="table-title">
						<div class="row">
							<div class="col-6 pt-2">
								<h2>
									Manage <b>Courses</b>
								</h2>
							</div>
							<div class="col-6">
								<div class="py-2" style="float: right;">
									<button class="btn btn-primary" name="viewApplications"
										data-toggle="modal" data-target="#createCourseModal">
										<i class="fa fa-plus"></i><span> Create Course</span>
									</button>
								</div>

							</div>
						</div>
					</div>
					<table class="table table-striped table-hover">
						<thead class="thead-dark">
							<tr align="center">
								<th>Course Code</th>
								<th>Course Name</th>
								<th>Professor Assigned</th>
								<th>Scheduled Time</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr align="center">
								<td>4004</td>
								<td>Software Quality Assurance</td>
								<td>Jean-Pierre Corriveau</td>
								<td>Fri 2:30-5:30</td>
								<td><button type="button" class="btn btn-danger"
										name="deleteButton" data-toggle="modal"
										data-target="#deleteModal">
										<i class="fa fa-trash" data-toggle="tooltip" title="Delete"></i>
									</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</form>
</body>
</html>