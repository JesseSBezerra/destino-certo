package br.com.destino_certo.util.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static{
		try{
		sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable t){
			System.out.println("Não foi possivel criar uma sessão \n"+t.getMessage());
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
