IGApp.service("InvoiceService", InvoiceService);

function InvoiceService(HttpCommunicationUtil){
  var service = this;
  var currentInvoice = {};
  var invoiceList = [];

  this.setCurrentInvoice = function(invoiceObj) {
    service.currentInvoice = angular.copy(invoiceObj);
  };

  this.getCurrentInvoice = function() {
    return service.currentInvoice;
  };

  this.getAllInvoice = function(successCB, errorCB) {
    HttpCommunicationUtil.doGet("json/invoiceList.json", function(data){
      service.invoiceList = data.object;
      successCB(data);
    }, function(data){
      errorCB(data);
    });
  };

  this.getInvoiceById = function(invoiceId) {
    var lenght = service.invoiceList.length;
    for(var i=0 ; i<lenght ; i++){
      if(this.invoiceList[i].id == invoiceId){
        return service.invoiceList[i];
      }
    }
  };

  this.saveInvoice = function(invoiceObj) {
    service.currentInvoice = angular.copy(invoiceObj);
  };

  this.getSubTotalAndTaxAmountAndTotal = function() {
    var obj = {}, subtotal = 0, taxAmount = 0, total = 0;
    for(var i=0 ; i<service.currentInvoice.itemList.length; i++) {
      subtotal += parseInt(service.currentInvoice.itemList[i].total);
    }

    taxAmount = (subtotal * 12)/100;
    total = subtotal + taxAmount;
    return {"subtotal": subtotal, "taxAmount": taxAmount, "total": total};
  };
}
