var LoginApp = angular.module("LoginApp", ['ngRoute']);
LoginApp.config(function($routeProvider){
	$routeProvider
	.when('/', {
		templateUrl: "app/views/login.html",
		controller: "LoginController"
	});
});
