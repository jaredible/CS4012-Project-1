package net.jaredible.reporter.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.jaredible.reporter.entity.Question;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Question> getQuestions() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Question> cq = cb.createQuery(Question.class);
		Root<Question> root = cq.from(Question.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public Question getQuestion(int id) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, id);
		return question;
	}

	@Override
	public void saveQuestion(Question question) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(question);
	}

	@Override
	public void deleteQuestion(int id) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.byId(Question.class).load(id);
		session.delete(question);
	}

}

// org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
// https://stackoverflow.com/questions/26203446/spring-hibernate-could-not-obtain-transaction-synchronized-session-for-current
