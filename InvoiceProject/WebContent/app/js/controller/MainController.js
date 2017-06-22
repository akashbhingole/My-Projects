IGApp.controller('MainController',MainController);

function MainController($scope,$http, $location) {
	
	$scope.userName = window.localStorage.getItem("loggedInUserName");
	
	$scope.redirectTo = function(url) {
		$location.url("/"+url);
	};
	
	$scope.logout = function() {
		var loggedInFrom = window.localStorage.getItem("loggedInFrom");
		if(loggedInFrom!=null) {
			if(loggedInFrom == 'gmail') {
				$scope.doLogoutFromGmail();
			}
			else {
				$scope.doLogout();
			}
		}
	};
	
	$scope.doLogout = function() {
    	var url = "http://localhost:8080/InvoiceProject/login/logout";
    	var data = {
    	};
	    $http({
			   method  : 'POST',
			   url     :url,
			   data    : $.param(data),  // pass in data as strings
			   headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
	    	 }).success(function(data) {
				 if(data!=null && data.flag!=null && data.flag=='success') {
					 document.location.href = "http://localhost:8080/InvoiceProject/";
				 }
				 else {
					 alert("System Under Maintenance");
				 }
	    	 });
	};
	
	$scope.doLogoutFromGmail = function() {
	    document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/InvoiceProject/";
	};
	
}