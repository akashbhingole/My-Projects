IGApp.service("PDFGenerator", PDFGenerator);

function PDFGenerator() {

	this.generatePDF = function(invoice) {
		/*var tableBody = [];
		tableBody.push([{ text: 'Item Id', style: 'tableHeader' }, { text: 'Item Name', style: 'tableHeader'}, { text: 'Price', style: 'tableHeader' }, { text: 'Quantity', style: 'tableHeader' }, { text: 'Total', style: 'tableHeader' }]);
*/
		/*var item;
		for(var i=0; i<invoice.itemList.length; i++){
			item = invoice.itemList[i];
			tableBody.push([item.id, item.name, item.price, item.quantity, item.total+'']);
		}*/

		//preparing document specifications
		var docDefinition = {
				content: [
				          {
				        	  text: 'Invoice\n\n',
				        	  style: 'header'
				          },
			              {
			            	  text: [
			            	         {text: 'Customer Id:', style: 'label'},
			            	         {text: invoice.id,style: 'val'}
			            	  ]
			              },
			              {
			            	  text: [
			            	         {text: 'Customer Name:', style: 'label'},
			            	         {text: invoice.customerName,style: 'val'}
			            	  ]
			              },
			              {
			            	  text: [
			            	         {text: 'Customer Address:', style: 'label'},
			            	         {text: invoice.customerAddress,style: 'val'}
			            	  ]
			              },
			              {
			            	  text: [
			            	         {text: 'Item Name:', style: 'label'},
			            	         {text: invoice.itemName,style: 'val'}
			            	  ]
			              },
			              {
			            	  text: [
			            	         {text: 'Price:', style: 'label'},
			            	         {text: invoice.price,style: 'val'}
			            	  ]
			              },
			              {
			            	  text: [
			            	         {text: 'Quantity:', style: 'label'},
			            	         {text: invoice.quantity,style: 'val'}
			            	  ]
			              },
			              {
			            	  text: [
			            	         {text: 'Total:', style: 'label'},
			            	         {text: invoice.total,style: 'val'}
			            	  ]
			              }
			              /*{
			            	  text: [
			            	         {text: 'Customer Address:', style: 'label'},
			            	         {text: invoice.address,style: 'val'}
			            	  ]
			              },
			              { 
			            	  text: 'Item Details:', fontSize: 14, bold: true, margin: [0, 20, 0, 8] 
			              }*/
			              /*{
		            		  style: 'tableExample',
		            		  table: {
		            			  headerRows: 1,
		            			  body: tableBody
		            		  },
		            		  layout: 'lightHorizontalLines'
			              }*/
			    ],
			    styles: {
			    	header: {
			    		fontSize: 18,
			    		bold: true
			    	},
			    	label: {
			    		fontSize: 12,
			    		bold: true
			    	},
			    	val: {
			    		fontSize: 12
			    	}
			    	/*tableExample: {
			    		margin: [0, 5, 0, 15]
			    	},
			    	tableHeader: {
			    		bold: true,
			    		fontSize: 13,
			    		color: 'black'
			    	}*/
			    }
		};

		// download the PDF
		pdfMake.createPdf(docDefinition).download();
	};
}
