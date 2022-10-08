package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   public List<User> listUsers() {
      TypedQuery<User> query= sessionFactory.getCurrentSession().createQuery("FROM User", User.class);
      return query.getResultList();
   }

   @Override
   public User getUserOwnCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("FROM User WHERE userCar.model= :modelName AND userCar.series= :serialName", User.class)
              .setParameter("modelName", model)
              .setParameter("serialName", series);
      return (User) query.getSingleResult();
   }

}
