package br.com.destino_certo.util.autenticar;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import br.com.destino_certo.usuario.modelo.Usuario;


public class FaseListener implements PhaseListener{

	private static final long serialVersionUID = 1L;
		
	
	@Override
	public void beforePhase(PhaseEvent fase) {
		
		if(fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			Usuario  usuario = (Usuario) FacesContextUtil.getSessionAttribute("usuario");
			if(usuario == null){
				FacesContextUtil.setNavegacao("login");
			}
		}
		
//		if(fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)){
//			Session session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//			FacesContextUtil.setRequestSession(session);
//		}	
	}
	
	@Override
	public void afterPhase(PhaseEvent fase) {
//		if(fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
//			Session session = FacesContextUtil.getRequestSession();
//			try{
//				session.getTransaction().commit();
//			}catch (Exception e) {
//				System.out.println("Erro no commit da transação: "+e.getMessage());
//				System.out.println("Erro no commit da transação: "+e.getCause());
//				if(session.getTransaction().isActive()){
//					session.getTransaction().rollback();
//				}	
//			}finally{
//				session.close();
//			}
//		}	
	}

	@Override
	public PhaseId getPhaseId() {
		//O FaseListener será invocado em todas as fases
		return PhaseId.ANY_PHASE;
	}

}
