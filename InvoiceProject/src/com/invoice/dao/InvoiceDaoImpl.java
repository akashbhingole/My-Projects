package com.invoice.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invoice.model.Invoice;
import com.invoice.model.LoginDetails;


@Repository("invoiceDao")
public class InvoiceDaoImpl implements InvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean isValidUser(String userName, String password) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select l from LoginDetails l where l.username=:userName and l.password=:password");
		query.setString("userName", userName);
		query.setString("password", password);
		LoginDetails loginDetails = (LoginDetails) query.uniqueResult();
		if(loginDetails!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> getInvoiceList() throws Exception {
		return (List<Invoice>) sessionFactory.getCurrentSession().createCriteria(Invoice.class).list();
	}
	
	@Override
	public Invoice getInvoiceDetails(int id) throws Exception {
		return (Invoice) sessionFactory.getCurrentSession().get(Invoice.class, id);
	}
	
	@Override
	public Invoice addNewInvoice(Invoice invoice) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(invoice);
		return invoice;
	}

	@Override
	public void deleteInvoiceDetails(Invoice invoice) throws Exception {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Invoice WHERE id = "+invoice.getId()).executeUpdate();
	}

}
