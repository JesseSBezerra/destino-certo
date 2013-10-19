package br.com.destino_certo.usuario.bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import br.com.destino_certo.util.autenticar.FacesContextUtil;

@ManagedBean
@SessionScoped
public class SessaoMB {

	public void destruir_sessao_encaminhar(){
		FacesContextUtil.setSessionAttribute("usuario", null);
		FacesContextUtil.setNavegacao("login");
		System.out.println("teste");
	}

}
