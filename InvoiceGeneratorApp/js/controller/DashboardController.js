IGApp.controller("DashboardController", DashboardController);

function DashboardController($scope, $location, InvoiceService){
  $scope.redirectTo = function(url) {
    $location.url("/"+url);
  };

  function fetchInvoiceList() {
      console.log("Inside fetchInvoiceList...");
      InvoiceService.getAllInvoice(function(data){
        $scope.invoiceList = data.object;
      }, function(data){
        console.log("error"); console.log(data);
      });
  };

  $scope.showInvoice = function(invoiceId) {
    console.log("Inside showInvoice :: " + invoiceId);
    console.log(InvoiceService.getInvoiceById(invoiceId));
    InvoiceService.setCurrentInvoice(InvoiceService.getInvoiceById(invoiceId));
    $location.url('invoice');
  };

  function init() {
    fetchInvoiceList();
  };

  init();
}
