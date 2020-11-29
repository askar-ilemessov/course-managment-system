<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Management</title>
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
		<li class="nav-item"><a class="nav-link active" id="accounts-tab"
			data-toggle="tab" href="#accounts" role="tab" aria-controls="home"
			aria-selected="true">Accounts</a></li>

		<li class="nav-item"><a class="nav-link" id="courses-tab"
			data-toggle="tab" href="#courses" role="tab" aria-controls="profile"
			aria-selected="false">Courses</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="accounts" role="tabpanel"
			aria-labelledby="accounts-tab">
			<div class="container">
				<div class="table-title">
					<div class="row">
						<div class="col-6 pt-2">
							<h2>
								Manage <b>Accounts</b>
							</h2>
						</div>
						<div class="col-6">
							<div class="py-2" style="float: right;">
								<button class="btn btn-primary" name="viewApplications"
									data-toggle="modal">
									<i class="fa fa-user"></i><span> View Applications</span>
								</button>
							</div>

						</div>
					</div>
				</div>
				<% System.out.println(request.getAttribute("accounts"));%>
				<table class="table table-striped table-hover">
					<thead class="thead-dark">
						<tr align="center">
							<th></th>
							<th>Name</th>
							<th>Email</th>
							<th>Account Type</th>
							<th>Phone</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr align="center">
							<td><span class="custom-checkbox"> <input
									type="checkbox" id="checkbox5" name="options[]" value="1">
									<label for="checkbox5"></label>
							</span></td>
							<td>student mc student</td>
							<td>email@email</td>
							<td>Student</td>
							<td>(480) 631-2097</td>
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
	<!-- Delete Modal HTML -->
	<div id="deleteModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-header">
						<h4 class="modal-title">Delete Account</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p>Are you sure you want to delete this account?</p>
						<p class="text-warning">
							<small>This action cannot be undone.</small>
						</p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-danger" name="deleteConfirm" value="Delete">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Create Course Modal -->
	<div id="createCourseModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="container">
					<div style="margin: auto">
						<div class="form-area"
							style="background-color: #FAFAFA; padding: 10px 40px 60px; margin: 10px 0px 60px; border: 1px solid GREY;">
							<form role="form" action="CreateCourse" method="post">
								<br style="clear: both">
								<h3 style="margin-left: 25px; text-align: center;">Create
									Course</h3>
								<div class="line">
									<div class="form-group">
										<input type="text" class="form-control" id="course_name"
											name="course_name" placeholder="Name of the course" required>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="course_code"
											name="course_code" placeholder="Course code" required>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="section"
											name="section" placeholder="Course section" required>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="prof_name"
											name="prof_name" placeholder="Professor Name" required>
									</div>
								</div>
								<div class="form-group">
									<select class="form-control" id="term" name="term" required>
										<option disabled selected hidden=true>Term</option>
										<option>Fall</option>
										<option>Winter</option>
										<option>Summer</option>
									</select>
								</div>
								<input type="button" class="btn btn-default" data-dismiss="modal"
						value="Cancel">
						<input type="submit" id="submit" name="submit" value="Create"
						class="btn btn-primary float-right">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>