var IGApp = angular.module("InvoiceGeneratorApp", ['ngRoute', 'ngMessages']);

IGApp.config(function($routeProvider){
  $routeProvider
  .when('/', {
      templateUrl: "views/login.html",
      controller: "LoginController"
    })
    .when('/dashboard', {
        templateUrl: "views/dashboard.html",
        controller: "DashboardController"
    })
    .when('/add-edit-invoice', {
      templateUrl: 'views/add-edit-invoice.html',
      controller: 'InvoiceController'
    })
    .when('/invoice', {
      templateUrl: 'views/invoice.html',
      controller: 'InvoiceController'
    })
});
