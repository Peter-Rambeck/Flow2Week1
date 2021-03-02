/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat3.jpademo.entities;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author peter
 */
public class TheTester {
    
     public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("Peter", 1901);
        Person p2 = new Person("Hansemand", 1902);
        
        Address a1 = new Address("Rabalerstræde", 2000, "Amar");
        Address a2 = new Address("Hørved", 2200, "Gedestrup");
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);
        
        p1.addFee(f1);
        p1.addFee(f3);
        p2.addFee(f2);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Butterfly");
        SwimStyle s3 = new SwimStyle("Breast stroke");
        
        p1.addSwimStyle(s2);
        p1.addSwimStyle(s3);
        p2.addSwimStyle(s1);
        
        em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
            p1.removeSwimStyle(s2);
        em.getTransaction().commit();
        
        
        System.out.println("p1: " +p1.getP_id() + ", " + p1.getName());
        System.out.println("p2: " + p2.getP_id() + ", " + p2.getName());
        
        System.out.println("TestStreet: " + p1.getAddress().getStreet());
        
        System.out.println("2vejs: " + a1.getPerson().getName());
        System.out.println("Hvem har betalt f2 " + f2.getPerson().getName());
        System.out.println("Hvad er blevet betalt: ");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        for(Fee f: fees) {
            System.out.println(f.getPerson().getName() +": " + f.getAmount() + "Kr " + ": " + f.getPayDate() + ": " + f.getPerson().getAddress().getCity());
        }
        
        
    }
    
}
