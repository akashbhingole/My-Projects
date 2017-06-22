IGApp.service("InvoiceService", InvoiceService);

function InvoiceService(HttpCommunicationUtil){
	var service = this;

	this.setCurrentInvoice = function(invoiceObj) {
		service.currentInvoice = angular.copy(invoiceObj);
	};

	this.getCurrentInvoice = function() {
		return service.currentInvoice;
	};

	this.getInvoiceList = function(successCB, errorCB) {
		HttpCommunicationUtil.doGet("invoice/invoices", function(data){
			service.invoiceList = data;
			successCB(data);
		}, function(data){
			errorCB(data);
		});
	};

	this.getInvoiceDetailsById = function(invoiceId, successCB, errorCB) {
		HttpCommunicationUtil.doGet("invoice/details/"+invoiceId, function(data){
			successCB(data);
		}, function(data){
			errorCB(data);
		});
	};

	this.saveInvoiceDetails = function(invoiceObj, successCB, errorCB) {
		HttpCommunicationUtil.doPost("invoice/save", invoiceObj, function(data){
			service.currentInvoice = angular.copy(data);
			successCB(data);
		}, function(data){
			errorCB(data);
		});
	};
	
	this.deleteInvoiceDetailsById = function(invoiceId, successCB, errorCB) {
		HttpCommunicationUtil.doDelete("invoice/delete/"+invoiceId, function(data){
			service.currentInvoice = angular.copy(data);
			successCB(data);
		}, function(data){
			errorCB(data);
		});
	};

	/*this.getSubTotalAndTaxAmountAndTotal = function() {
		var obj = {}, subtotal = 0, taxAmount = 0, total = 0;
		for(var i=0 ; i<service.currentInvoice.itemList.length; i++) {
			subtotal += parseInt(service.currentInvoice.itemList[i].total);
		}

		taxAmount = (subtotal * 12)/100;
		total = subtotal + taxAmount;
		return {"subtotal": subtotal, "taxAmount": taxAmount, "total": total};
	};*/
}
