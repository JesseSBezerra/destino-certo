package br.com.destino_certo.util.teste;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioCodigoParada {

	private Session s;
	private Transaction tx;

	public void cadastrar(CodigosParadas codigosParadas) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.save(codigosParadas);
			tx.commit();
		} catch (Exception e) {
		} finally {
			s.close();
		}

	}

	
	public void editar(CodigosParadas codigosParadas) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.update(codigosParadas);
			tx.commit();
		} catch (Exception e) {
		} finally {
			s.close();
		}
	}

	
	public void remover(CodigosParadas codigosParadas)
			 {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.delete(codigosParadas);
			tx.commit();
		} catch (Exception e) {
			;
		} finally {
			s.close();
		}
	}

	
	public CodigosParadas procurar(Long numero)
			 {
		// TODO Auto-generated method stub
		iniciarTransacao();
		CodigosParadas codigosParadas = null;
		try {
			codigosParadas = (CodigosParadas) s.get(CodigosParadas.class, numero);
			if (codigosParadas == null) {
				;
			}
		} catch (Exception e) {
			;
		} finally {
			s.close();
		}
		return codigosParadas;
	}

	@SuppressWarnings("unchecked")
	
	public List<CodigosParadas> listar() {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(CodigosParadas.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}

	private void iniciarTransacao() {
		s = HibernateUtil.getSessionFactory().openSession();
		tx = s.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	
	public List<CodigosParadas> listar(String nomeCampo, boolean valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(CodigosParadas.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	
	public List<CodigosParadas> listar(String nomeCampo, Long valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(CodigosParadas.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}

	
	public CodigosParadas procurar(String nomeCampo, Long valorCampo,
			String nomeCampo0, Integer valorCampo0)
			 {
		iniciarTransacao();
		Criteria c = s.createCriteria(CodigosParadas.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		c.add(Restrictions.eq(nomeCampo0, valorCampo0));
		// TODO Auto-generated method stub
		CodigosParadas codigosParadas = (CodigosParadas) c.uniqueResult();
		return codigosParadas;
	}
}
