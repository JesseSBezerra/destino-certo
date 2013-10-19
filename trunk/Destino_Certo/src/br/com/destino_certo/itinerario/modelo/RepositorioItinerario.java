package br.com.destino_certo.itinerario.modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioItinerario implements IRepositorioItinerario {

	private Session s;
	private Transaction tx;

	@Override
	public void cadastrar(Itinerario itinerario)
			throws ItinerarioJaCadastradoExcepetion {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.save(itinerario);
			tx.commit();
		} catch (Exception e) {
			throw new ItinerarioJaCadastradoExcepetion();
		} finally {
			s.close();
		}

	}

	@Override
	public void editar(Itinerario itinerario)
			throws ItinerarioNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.update(itinerario);
			tx.commit();
		} catch (Exception e) {
			throw new ItinerarioNaoEncontradoException();
		} finally {
			s.close();
		}
	}

	@Override
	public void remover(Itinerario itinerario)
			throws ItinerarioNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.delete(itinerario);
			tx.commit();
		} catch (Exception e) {
			throw new ItinerarioNaoEncontradoException();
		} finally {
			s.close();
		}
	}

	@Override
	public Itinerario procurar(Long numero)
			throws ItinerarioNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Itinerario itinerario = null;
		try {
			itinerario = (Itinerario) s.get(Itinerario.class, numero);
			if (itinerario == null) {
				throw new ItinerarioNaoEncontradoException();
			}
		} catch (Exception e) {
			throw new ItinerarioNaoEncontradoException();
		} finally {
			s.close();
		}
		return itinerario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Itinerario> listar() {
		// TODO Auto-generated method stub
		iniciarTransacao();
		return s.createCriteria(Itinerario.class).list();
	}

	private void iniciarTransacao() {
		s = HibernateUtil.getSessionFactory().openSession();
		tx = s.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Itinerario> listar(String nomeCampo, boolean valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(Itinerario.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}
	
	@Override
	public Itinerario procurar(String nomeCampo, Long valorCampo,String nomeCampo0,boolean valorCampo0,String nomeCampo1,String valorCampo1)
			throws ItinerarioNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Itinerario itinerario = null;
		Criteria c = s.createCriteria(Itinerario.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		c.add(Restrictions.eq(nomeCampo0, valorCampo0));
		c.add(Restrictions.eq(nomeCampo1, valorCampo1));
		try {
			itinerario = (Itinerario) c.uniqueResult();
			if (itinerario == null) {
				throw new ItinerarioNaoEncontradoException();
			}
		} catch (Exception e) {
			throw new ItinerarioNaoEncontradoException();
		} finally {
			s.close();
		}
		return itinerario;
	}
	
}
