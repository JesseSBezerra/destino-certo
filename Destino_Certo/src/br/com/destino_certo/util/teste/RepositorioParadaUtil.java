package br.com.destino_certo.util.teste;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.destino_certo.util.hibernate.HibernateUtil;

public class RepositorioParadaUtil {

	private Session s;
	private Transaction tx;

	public void cadastrar(ParadaUtil paradaUtil) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.save(paradaUtil);
			tx.commit();
		} catch (Exception e) {
		} finally {
			s.close();
		}

	}

	
	public void editar(ParadaUtil paradaUtil) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.update(paradaUtil);
			tx.commit();
		} catch (Exception e) {
		} finally {
			s.close();
		}
	}

	
	public void remover(ParadaUtil paradaUtil)
			 {
		// TODO Auto-generated method stub
		iniciarTransacao();
		try {
			s.delete(paradaUtil);
			tx.commit();
		} catch (Exception e) {
			;
		} finally {
			s.close();
		}
	}

	
	public ParadaUtil procurar(Long numero)
			 {
		// TODO Auto-generated method stub
		iniciarTransacao();
		ParadaUtil paradaUtil = null;
		try {
			paradaUtil = (ParadaUtil) s.get(ParadaUtil.class, numero);
			if (paradaUtil == null) {
				;
			}
		} catch (Exception e) {
			;
		} finally {
			s.close();
		}
		return paradaUtil;
	}

	@SuppressWarnings("unchecked")
	
	public List<ParadaUtil> listar() {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(ParadaUtil.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}

	private void iniciarTransacao() {
		s = HibernateUtil.getSessionFactory().openSession();
		tx = s.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	
	public List<ParadaUtil> listar(String nomeCampo, boolean valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(ParadaUtil.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	
	public List<ParadaUtil> listar(String nomeCampo, Long valorCampo) {
		// TODO Auto-generated method stub
		iniciarTransacao();
		Criteria c = s.createCriteria(ParadaUtil.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		return c.list();
	}

	
	public ParadaUtil procurar(String nomeCampo, Long valorCampo,
			String nomeCampo0, Integer valorCampo0)
			 {
		iniciarTransacao();
		Criteria c = s.createCriteria(ParadaUtil.class);
		c.add(Restrictions.eq(nomeCampo, valorCampo));
		c.add(Restrictions.eq(nomeCampo0, valorCampo0));
		// TODO Auto-generated method stub
		ParadaUtil paradaUtil = (ParadaUtil) c.uniqueResult();
		return paradaUtil;
	}
}
