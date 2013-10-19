package br.com.destino_certo.usuario.modelo;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioUsuario implements IRepositorioUsuario {

	private Session s;
	private Transaction tx;

	
	
	@Override
	public void cadastrar(Usuario usuario) throws UsuarioJaCadastradoException {
		// TODO Auto-generated method stub
		try{
			s = HibernateUtil.getSessionFactory().openSession();
			tx = s.beginTransaction();
			s.save(usuario);
			tx.commit();
		}catch(Exception e){
			new UsuarioJaCadastradoException(usuario);
		}finally{
		}
    
	}

	@Override
	public void editar(Usuario usuario) throws UsuarioNaoEncontradoExcepition{
		// TODO Auto-generated method stub
		try {
			s = HibernateUtil.getSessionFactory().openSession();
			tx = s.beginTransaction();
			s.update(usuario);
			tx.commit();
		 } catch (Exception e) {
			// TODO: handle exception
			throw new UsuarioNaoEncontradoExcepition();
		}finally{
			s.close();
		}

	}

	@Override
	public void remover(Usuario usuario) throws UsuarioNaoEncontradoExcepition {
		// TODO Auto-generated method stub
		try {
			s = HibernateUtil.getSessionFactory().openSession();
			tx = s.beginTransaction();
			s.delete(usuario);
			tx.commit();
		 } catch (Exception e) {
			// TODO: handle exception
			throw new UsuarioNaoEncontradoExcepition();
		}finally{
			s.close();
		}

	}

	@Override
	public Usuario procurar(String login){
		// TODO Auto-generated method stub
		Usuario usuario = null;
		try {
			s = HibernateUtil.getSessionFactory().openSession();
			tx = s.beginTransaction();
			usuario = (Usuario) s.get(Usuario.class, login);
			if(usuario == null){
				throw new UsuarioNaoEncontradoExcepition();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			s.close();
		}
		return usuario;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> lista = null;
		try{
		s = HibernateUtil.getSessionFactory().openSession();
		Criteria c = s.createCriteria(Usuario.class);
		lista = c.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			s.close();
		}
		return lista;
	}

}
