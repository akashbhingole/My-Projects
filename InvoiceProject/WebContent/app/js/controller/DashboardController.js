IGApp.controller("DashboardController", DashboardController);

function DashboardController($scope,$http, $location, InvoiceService){
  
	
	$scope.redirectTo = function(url) {
		$location.url("/"+url);
	};

	function getInvoiceList() {
		InvoiceService.getInvoiceList(function(data){
			$scope.invoiceList = data;
		}, function(data){
			console.log("error"); 
			console.log(data);
		});
		
		console.log($scope.invoiceList);
	};

	$scope.getInvoiceDetailsById = function(invoiceId) {
		InvoiceService.getInvoiceDetailsById(invoiceId,function(data){
			InvoiceService.setCurrentInvoice(data);
			$location.url('invoice?id='+invoiceId);
		}, function(data){
			console.log("error"); 
			console.log(data);
		});
	};

	$scope.editInvoiceDetailsById = function(invoiceId) {
		if(invoiceId!=null) {
			$scope.redirectTo('add-edit-invoice?id='+invoiceId);
		}
	};
	
	$scope.deleteInvoiceDetailsById = function(invoiceId) {
		if(invoiceId!=null) {
			var conf = confirm('Are you sure you want to delte?');
			if(conf) {
				InvoiceService.deleteInvoiceDetailsById(invoiceId,function(data){
						getInvoiceList();
						alert('Invoice Details deleted');
					}, function(data){
						console.log("error"); 
						console.log(data);
				});
			}
		}
	};
	
  
	function init() {
		getInvoiceList();
	};

	init();
}
