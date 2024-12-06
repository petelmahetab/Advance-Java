package Dao;

import Model.Hospital;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class detailDao {


    private SessionFactory sessionFactory;
    public void HospitalDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    //Main Logic is here... Create an Config xml and through sessionFactory begin-transaction ADD-->Save
    public void addDetails(Hospital hospital) {
        // Initialize Hibernate Configuration and Session
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        Session session = config.buildSessionFactory().openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(hospital);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the error for debugging
            throw new RuntimeException("Failed to save hospital details", e);
        } finally {
            session.close();
        }

    }

       //For Updating records
    public void updateDetails(Hospital hospital) {
        // Initialize Hibernate Configuration and Session
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        Session session = config.buildSessionFactory().openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hospital);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Log the error for debugging
            throw new RuntimeException("Failed to Update hospital details", e);
        } finally {
            session.close();
        }

    }


   //For READ the Content
   public Hospital getDetailsById(int id) {
       // Initialize Hibernate Configuration and Session
       Configuration config = new Configuration();
       config.configure("hibernate.cfg.xml");
       SessionFactory sessionFactory = config.buildSessionFactory();
       Session session = sessionFactory.openSession();
       Hospital movie = session.get(Hospital.class, id);
       session.close();

       return movie;
   }

//For getting ALl details

    public List<Hospital> getAllDetailsById(Hospital hospital) {
        // Initialize Hibernate Configuration and Session
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        Session session = config.buildSessionFactory().openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
           session.createQuery("From Student").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            // Log the error for debugging
            throw new RuntimeException("Failed to Update hospital details", e);
        } finally {
            session.close();
        }
        return (List<Hospital>) hospital;
    }

//    Delete Details
    public void deleteDetails(int id) {
        // Initialize Hibernate Configuration and Session
        Configuration configuration=new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory=configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Hospital hospital = session.get(Hospital.class, id);
        if (hospital == null) {
            System.out.println("Hospital with ID " + id + " not found.");
        } else {
            session.delete(hospital);
        }
   transaction.commit();
   session.close();

    }
}
