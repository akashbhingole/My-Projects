IGApp.controller('InvoiceController', InvoiceController);

function InvoiceController($scope, $location, InvoiceService, PDFGenerator) {
  console.log("InvoiceController loaded successfully.");
  $scope.invoiceObj = {};

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

  $scope.saveInvoice = function(invoiceForm) {
    $scope.submitted = true;
    if(invoiceForm.$valid) {
      InvoiceService.saveInvoice($scope.invoiceObj);
      $scope.redirectTo('invoice');
    }/* else {
      alert("Please enter all the customer details.");
    }*/
  };

  $scope.showInvoice = function() {
    $scope.invoice = angular.copy(InvoiceService.getCurrentInvoice());
    $scope.payementObj = InvoiceService.getSubTotalAndTaxAmountAndTotal();
  };

  $scope.generatePDF = function(){
    PDFGenerator.generatePDF($scope.invoice);
  };
}
