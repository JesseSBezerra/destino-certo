package br.com.destino_certo.onibus.modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioOnibus implements IRepositorioOnibus {

	private Session s;
	private Transaction tx;

	@Override
	public void cadastrar(Onibus onibus) throws OnibusJaCadastradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.save(onibus);
			tx.commit();
		} catch (Exception e) {
			throw new OnibusJaCadastradoException();
		} finally {
			s.close();
		}

	}

	@Override
	public void editar(Onibus onibus) throws OnibusNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.update(onibus);
			tx.commit();
		} catch (Exception e) {
			throw new OnibusNaoEncontradoException();
		} finally {
			s.close();
		}
	}

	@Override
	public void remover(Onibus onibus) throws OnibusNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.delete(onibus);
			tx.commit();
		} catch (Exception e) {
			throw new OnibusNaoEncontradoException();
		} finally {
			s.close();
		}
	}

	@Override
	public Onibus procurar(Long numero) throws OnibusNaoEncontradoException {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Onibus onibus = null;
		try {
			onibus = (Onibus) s.get(Onibus.class, numero);	
			if(onibus==null){
				throw new OnibusNaoEncontradoException();
			}
		} catch (Exception e) {
			throw new OnibusNaoEncontradoException();
		} finally {
			s.close();
		}
		return onibus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Onibus> listar() {
		// TODO Auto-generated method stub
		iniciarTransacao();
		return s.createCriteria(Onibus.class).list();
	}

	private void iniciarTransacao() {
		s = HibernateUtil.getSessionFactory().openSession();
		tx = s.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Onibus> listar(String nomeCampo, boolean valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(Onibus.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}
}
