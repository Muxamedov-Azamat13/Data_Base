package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {

            // Создания данных
            Session session = sessionFactory.getCurrentSession();

            Person person1 = new Person("ALice", 25);
            Person person2 = new Person("Bob", 30);
            Person person3 = new Person("John", 30);

            session.beginTransaction();

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

            // Чтения данных
            Person person = session.get(Person.class,1);
            System.out.println(person);


            // Обновления данных
            if (person != null){
                person.setName("Megan");
                session.merge(person);
            }

            // Удаления данных
            Person personDel = session.get(Person.class,6);
            if (personDel != null){
                session.remove(personDel);
            }

            session.getTransaction().commit();

            System.out.println("Данные сохранены в базу данных!");
        }


    }
}