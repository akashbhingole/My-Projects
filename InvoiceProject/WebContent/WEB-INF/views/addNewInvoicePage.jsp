<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Invoice Project</title>
	</head>
	<body>
		<h2>Add Invoice Details</h2>
		<form:form method="POST" action="http://localhost:8080/InvoiceProject/invoice/save">
	   		<table>
			    <tr>
			        <td><form:label path="id">Invoice ID:</form:label></td>
			        <td><form:input path="id" value="${invoice.id}" readonly="true"/></td>
			    </tr>
			    <tr>
			        <td><form:label path="customerName">Customer Name:</form:label></td>
			        <td><form:input path="customerName" value="${invoice.customerName}"/></td>
			    </tr>
			    <tr>
			        <td><form:label path="customerAddress">Customer Address:</form:label></td>
			        <td><form:input path="customerAddress" value="${invoice.customerAddress}"/></td>
			    </tr>
			    <tr>
			        <td><form:label path="itemName">Item Name:</form:label></td>
			        <td><form:input path="itemName" value="${invoice.itemName}"/></td>
			    </tr>
			    
			    <tr>
			        <td><form:label path="price">Price:</form:label></td>
                    <td><form:input path="price" value="${invoice.price}"/></td>
			    </tr>
			     <tr>
			        <td><form:label path="quantity">Quantity:</form:label></td>
                    <td><form:input path="quantity" value="${invoice.quantity}"/></td>
			    </tr>
			     <tr>
			        <td><form:label path="total">Total</form:label></td>
                    <td><form:input path="total" value="${invoice.total}"/></td>
			    </tr>
			    <tr>
			      <td colspan="2"><input type="submit" value="Submit"/></td>
		      </tr>
			</table> 
		</form:form>
		
		<c:if test="${!empty invoiceList}">
			<h2>Invoice List</h2>
			<table align="left" border="1">
				<tr>
					<th>Invoice ID</th>
					<th>Customer Name</th>
					<th>Customer Address</th>
					<th>Item Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total</th>
					<th>Actions on Row</th>
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
						<td align="center">
							<a href="http://localhost:8080/InvoiceProject/invoice/edit.html?id=${invoice.id}">Edit</a> |
							<a href="http://localhost:8080/InvoiceProject/invoice/delete.html?id=${invoice.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
	</body>
</html>