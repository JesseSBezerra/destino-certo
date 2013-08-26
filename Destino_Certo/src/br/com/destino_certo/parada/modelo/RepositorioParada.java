package br.com.destino_certo.parada.modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioParada implements IRepositorioParada {

	private Session s;
	private Transaction tx;

	@Override
	public void cadastrar(Parada parada)
			throws ParadaJaCadastradaExcepetion {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.save(parada);
			tx.commit();
		} catch (Exception e) {
			throw new ParadaJaCadastradaExcepetion();
		} finally {
			s.close();
		}

	}

	@Override
	public void editar(Parada parada)
			throws ParadaNaoEncontradaException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.update(parada);
			tx.commit();
		} catch (Exception e) {
			throw new ParadaNaoEncontradaException();
		} finally {
			s.close();
		}
	}

	@Override
	public void remover(Parada parada)
			throws ParadaNaoEncontradaException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.delete(parada);
			tx.commit();
		} catch (Exception e) {
			throw new ParadaNaoEncontradaException();
		} finally {
			s.close();
		}
	}

	@Override
	public Parada procurar(Long numero)
			throws ParadaNaoEncontradaException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Parada parada = null;
		try {
			parada = (Parada) s.get(Parada.class, numero);
			if (parada == null) {
				throw new ParadaNaoEncontradaException();
			}
		} catch (Exception e) {
			throw new ParadaNaoEncontradaException();
		} finally {
			s.close();
		}
		return parada;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parada> listar() {
		// TODO Auto-generated method stub
		iniciarTransacao();
		return s.createCriteria(Parada.class).list();
	}

	private void iniciarTransacao() {
		s = HibernateUtil.getSessionFactory().openSession();
		tx = s.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parada> listar(String nomeCampo, boolean valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(Parada.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}
}
