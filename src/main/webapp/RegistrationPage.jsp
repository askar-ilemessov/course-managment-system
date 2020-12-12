<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Registration</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
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
	<div class="container">
		<div class="col-md-8" style="margin: auto">
			<div class="form-area"
				style="background-color: #FAFAFA; padding: 10px 40px 60px; margin: 10px 0px 60px; border: 1px solid GREY;">
				<form role="form" action="register" method="post">
					<br style="clear: both">
					<h3 style="margin-bottom: 25px; text-align: center;">Account
						Registration</h3>
					<div class="row">
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="First Name" required>
						</div>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="lastName"
								name="lastName" placeholder="Last Name" required>
						</div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="email" name="email"
							placeholder="Email" required>
					</div>
					<div class="form-group">
						<select class="form-control" id="accountType" name="accountType" required>
							<option disabled selected hidden=true>Account Type</option>
							<option>Student</option>
							<option>Professor</option>
						</select>
					</div>

					<button type="submit" id="submit" name="submit"
						class="btn btn-primary float-right">Apply</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>