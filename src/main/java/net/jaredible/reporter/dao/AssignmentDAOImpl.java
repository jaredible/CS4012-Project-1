package net.jaredible.reporter.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.jaredible.reporter.model.Assignment;

@Repository
public class AssignmentDAOImpl implements AssignmentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Assignment> getAssignments() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Assignment> cq = cb.createQuery(Assignment.class);
		Root<Assignment> root = cq.from(Assignment.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Assignment getAssignment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Assignment assignment = session.get(Assignment.class, id);
		return assignment;
	}

	@Override
	@Transactional
	public void saveAssignment(Assignment assignment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(assignment);
	}

	@Override
	@Transactional
	public void deleteAssignment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Assignment assignment = session.byId(Assignment.class).load(id);
		session.delete(assignment);
	}

}
