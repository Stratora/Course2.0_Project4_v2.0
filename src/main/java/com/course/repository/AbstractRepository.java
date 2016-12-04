package com.course.repository;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by fg on 7/24/2016.
 * Contains common methods for work with database
 */

abstract class AbstractRepository<T> {

    private final Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    AbstractRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Return sorted list
     * @param sortColumn - The property to order on
     * @return sorted list by sortColumn value
     */
    List<T> findAll(String sortColumn) {
        Criteria criteria = getCriteria();
        if (sortColumn != null) {
            criteria.addOrder(Order.asc(sortColumn));
        }
        return criteria.list();
    }

    /**
     * Save object to database
     * @param object which need to save
     */
    void add(final T object) {
        sessionFactory.getCurrentSession().save(object);
    }

    /**
     * Update object in database
     * @param object which need to update
     */
    void update(final T object){
        sessionFactory.getCurrentSession().update(object);
    }

    /**
     * Find the object in the database by its property
     * @param property name
     * @param value of property
     * @return found object
     */
    T findByProperty(final String property, final Object value) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq(property, value));
        return (T) criteria.uniqueResult();
    }

    /**
     * Find the list of objects which satisfied property
     * @param property name
     * @param value of property
     * @return the list of objects
     */
    List<T> findListByProperty(final String property, final Object value) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq(property, value));
        return criteria.list();
    }

    /**
     * Return criteria. Result transformer - DISTINCT_ROOT_ENTITY
     * @return criteria
     */
    Criteria getCriteria() {
        return sessionFactory.getCurrentSession().createCriteria(clazz)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
    }
}