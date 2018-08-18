package com.lcclovehww.hibernate;

import org.hibernate.classic.Session;

import com.lcclovehww.jdbc.Role;

public class HibernateExample {
	public static void main(String[] args) {
		Session session=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Role role =(Role)session.get(Role.class,1L);
			System.err.println(role.getRole_name());
			System.err.println(role.getId());
			System.err.println(role.getRemark());
		}finally{
			if(session!=null)
				session.close();
		}
	}
}
