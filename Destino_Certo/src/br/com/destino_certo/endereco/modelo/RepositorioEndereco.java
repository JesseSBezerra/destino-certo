package br.com.destino_certo.endereco.modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioEndereco implements IRepositorioEndereco{

	private Session s;
	private Transaction tx;
	
	public RepositorioEndereco() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void cadastrar(Endereco endereco) {
		// TODO Auto-generated method stub
		init();
		try{
			s.save(endereco);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			s.close();
		}
	}

	@Override
	public void editar(Endereco endereco) {
		// TODO Auto-generated method stub
		try{
			s.update(endereco);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			s.close();
		}

		
	}

	@Override
	public void remover(Endereco endereco) {
		// TODO Auto-generated method stub
		try{
			s.delete(endereco);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			s.close();
		}

		
	}

	@Override
	public Endereco procurar(Integer id) {
		// TODO Auto-generated method stub
		init();
		Endereco endereco = null;
		try{
			endereco = (Endereco) s.load(Endereco.class, id);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			s.close();
		}
		return endereco;
	}

	@Override
	public Endereco procurar(String logradouro) {
		// TODO Auto-generated method stub
		init();
		Endereco endereco = null;
		try{
			Criteria c = s.createCriteria(Endereco.class);
			c.add(Restrictions.eq("logradouro", logradouro));
			endereco = (Endereco) c.uniqueResult();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			s.close();
		}
		return endereco;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listar() {
		init();
		// TODO Auto-generated method stub
		List<Endereco> lista = null;
		try{
			Criteria c = s.createCriteria(Endereco.class);
			lista = c.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			s.close();
		}
		return lista;
	}
	
	public void init(){
		s = HibernateUtil.getSessionFactory().openSession();
		tx = s.beginTransaction();
	}

}
