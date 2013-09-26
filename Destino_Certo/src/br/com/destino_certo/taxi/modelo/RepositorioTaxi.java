package br.com.destino_certo.taxi.modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioTaxi implements IRepositorioTaxi {

	private Session s;
	private Transaction tx;

	@Override
	public void cadastrar(Taxi taxi)
			throws TaxiJaCadastradoExcepetion {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.save(taxi);
			tx.commit();
		} catch (Exception e) {
			throw new TaxiJaCadastradoExcepetion();
		} finally {
			s.close();
		}

	}

	@Override
	public void editar(Taxi taxi)
			throws TaxiNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.update(taxi);
			tx.commit();
		} catch (Exception e) {
			throw new TaxiNaoEncontradoException();
		} finally {
			s.close();
		}
	}

	@Override
	public void remover(Taxi taxi)
			throws TaxiNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.delete(taxi);
			tx.commit();
		} catch (Exception e) {
			throw new TaxiNaoEncontradoException();
		} finally {
			s.close();
		}
	}

	@Override
	public Taxi procurar(Long numero)
			throws TaxiNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Taxi taxi = null;
		try {
			taxi = (Taxi) s.get(Taxi.class, numero);
			if (taxi == null) {
				throw new TaxiNaoEncontradoException();
			}
		} catch (Exception e) {
			throw new TaxiNaoEncontradoException();
		} finally {
			s.close();
		}
		return taxi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxi> listar() {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(Taxi.class);
		c.addOrder(Order.asc("itinerario.numero"));
		return c.list();
	}

	private void iniciarTransacao() {
		s = HibernateUtil.getSessionFactory().openSession();
		tx = s.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxi> listar(String nomeCampo, boolean valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(Taxi.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxi> listar(String nomeCampo, Long valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(Taxi.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}

	@Override
	public Taxi procurar(String nomeCampo, Long valorCampo,
			String nomeCampo0, Integer valorCampo0)
			throws TaxiNaoEncontradoException {
		iniciarTransacao();
		Criteria c = s.createCriteria(Taxi.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		c.add(Restrictions.eq(nomeCampo0, valorCampo0));
		// TODO Auto-generated method stub
		Taxi taxi = (Taxi) c.uniqueResult();
		return taxi;
	}
}
