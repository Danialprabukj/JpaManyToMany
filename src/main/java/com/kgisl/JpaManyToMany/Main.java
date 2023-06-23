package com.kgisl.JpaManyToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
     public static void main(String[] args) {
        // Create a student
        Student student1 = new Student();
        student1.setName("John Doe");

        // Create a course
        Course course1 = new Course();
        course1.setName("Mathematics");

        // Add the course to the student
        student1.getCourses().add(course1);

        // Add the student to the course
        course1.getStudents().add(student1);

        // Save the student and course to the database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student1);
        em.persist(course1);
        em.getTransaction().commit();

        // Retrieve the student and course from the database
        em.getTransaction().begin();
        Student retrievedStudent = em.find(Student.class, student1.getId());
        Course retrievedCourse = em.find(Course.class, course1.getId());
        em.getTransaction().commit();

        // Print the retrieved student and course details
        System.out.println("Retrieved Student: " + retrievedStudent.getName());
        System.out.println("Retrieved Course: " + retrievedCourse.getName());
    }
}
