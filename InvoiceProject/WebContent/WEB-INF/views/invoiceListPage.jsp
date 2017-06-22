<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Invoices</title>
</head>
<body>
	<h1>Invoice List</h1>
	<h3><a href="http://localhost:8080/InvoiceProject/invoice/addnewpage">Add New Invoice</a></h3>
	
	<c:if test="${!empty invoiceList}">
		<table align="left" border="1">
			<tr>
				<th>Invoice ID</th>
				<th>Customer Name</th>
				<th>Customer Address</th>
				<th>Item Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Total</th>
			</tr>
	
			<c:forEach items="${invoiceList}" var="invoice">
				<tr>
					<td><c:out value="${invoice.id}"/></td>
					<td><c:out value="${invoice.customerName}"/></td>
					<td><c:out value="${invoice.customerAddress}"/></td>
					<td><c:out value="${invoice.itemName}"/></td>
					<td><c:out value="${invoice.price}"/></td>
					<td><c:out value="${invoice.quantity}"/></td>
					<td><c:out value="${invoice.total}"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>