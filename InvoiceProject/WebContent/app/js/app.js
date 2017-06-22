var IGApp = angular.module("InvoiceGeneratorApp", ['ngRoute', 'ngMessages']);

IGApp.config(function($routeProvider){
  $routeProvider
  	.when('/', {
      templateUrl: "app/views/login.html",
      controller: "LoginController"
    })
    .when('/dashboard', {
        templateUrl: "app/views/dashboard.html",
        controller: "DashboardController"
    })
    .when('/add-edit-invoice', {
      templateUrl: 'app/views/add-edit-invoice.html',
      controller: 'InvoiceController'
    })
    .when('/invoice', {
      templateUrl: 'app/views/invoice.html',
      controller: 'InvoiceController'
    })
});
