LoginApp.controller("LoginController", LoginController);

function LoginController($scope,$http,$location,googleService){
	
	googleService.init();

	$scope.checkLoginForGmail = function() {
		googleService.login().then(function(data){
			window.localStorage.setItem("loggedInUserName", data);
			window.localStorage.setItem("loggedInFrom", "gmail");
			document.location.href = "http://localhost:8080/InvoiceProject/invoiceApp.html";
		});
	};
	
	$scope.loginObj = {};
	
	$scope.checkLogin = function(loginForm) {
		if(loginForm.$valid) {
	    	var url = "http://localhost:8080/InvoiceProject/login/verify";
	    	var data = {
	    			userName:$scope.loginObj.userName,	
	    			password:$scope.loginObj.password
	    	};
		    $http({
   				   method  : 'POST',
   				   url     :url,
   				   data    : $.param(data),  // pass in data as strings
   				   headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
		    	 }).success(function(data) {
					 if(data!=null && data.flag!=null && data.flag=='success') {
						 window.localStorage.setItem("loggedInUserName", data.userName);
						 window.localStorage.setItem("loggedInFrom", "db");
						 document.location.href = "http://localhost:8080/InvoiceProject/invoiceApp.html";
					 }
					 else {
						 alert("Invalid Credentials");
					 }
		    	 });
		}
	};
}
