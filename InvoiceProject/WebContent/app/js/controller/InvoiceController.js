IGApp.controller('InvoiceController', InvoiceController);

function InvoiceController($scope,$http, $location, InvoiceService, PDFGenerator) {
	
	$scope.successMsg = "";
	
	$scope.invoiceObj = {
		id:0,
		customerName:'',
		customerAddress:'',
		itemName:'',
		price:'',
		quantity:'',
		total:''
	};

	$scope.redirectTo = function(url) {
		$location.url("/"+url);
	};

	$scope.addNewItem = function() {
	    if(undefined == $scope.invoiceObj.allItems) {
	    	$scope.invoiceObj.itemList = [];
	    }
	    $scope.invoiceObj.itemList.push($scope.newItem);
	    $scope.newItem = {};
	};

	/*$scope.showInvoice = function() {
	    $scope.invoice = angular.copy(InvoiceService.getCurrentInvoice());
	    $scope.payementObj = InvoiceService.getSubTotalAndTaxAmountAndTotal();
	};*/

	$scope.generatePDF = function(){
		PDFGenerator.generatePDF($scope.invoice);
	};
	
	
	$scope.getInvoiceDetailsById = function() {
		var invoiceId = $location.search().id;
		if(invoiceId!=null) {
			InvoiceService.getInvoiceDetailsById(invoiceId,function(data){
				 $scope.invoice = data;
				 $scope.invoiceObj = data;
			}, function(data){
				console.log("error"); 
				console.log(data);
			});
		}
	};
	
	$scope.saveInvoiceDetails = function(invoiceForm) {
		$scope.submitted = true;
	    if(invoiceForm.$valid) {
	    	var url = "http://localhost:8080/InvoiceProject/invoice/save";
	    	var data = {
	    			id:$scope.invoiceObj.id,	
	    			customerName:$scope.invoiceObj.customerName,
	    			customerAddress:$scope.invoiceObj.customerAddress,
	    			itemName:$scope.invoiceObj.itemName,
	    			price:$scope.invoiceObj.price,
	    			quantity:$scope.invoiceObj.quantity,
	    			total:$scope.invoiceObj.total
	    	};
		    	
		    $http({
   				   method  : 'POST',
   				   url     :url,
   				   data    : $.param(data),  // pass in data as strings
   				   headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
		    	 }).success(function(data) {
		    		 $scope.invoiceObj = data;
					 if(data!=null && data.id!=null) {
						 $scope.redirectTo('invoice?id='+data.id);
					 }
		    	 });
	    	
	    	//Service call not working
	    	/*InvoiceService.saveInvoiceDetails($scope.invoiceObj,function(data){
				 $scope.invoice = data;
			}, function(data){
				console.log("error"); 
				console.log(data);
			});*/
	    }
	};
	
	$scope.calculateTotal = function() {
		var price = 0;
		if($scope.invoiceObj.price!=null && $scope.invoiceObj.price!='') {
			price = $scope.invoiceObj.price;
		}
		
		var quantity = 0;
		if($scope.invoiceObj.quantity!=null && $scope.invoiceObj.quantity!='') {
			quantity = $scope.invoiceObj.quantity;
		}
	
		$scope.invoiceObj.total = price * quantity;
	};
	
	function init() {
		$scope.getInvoiceDetailsById();
	}
	
	init();
}
