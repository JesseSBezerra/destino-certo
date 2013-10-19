package br.com.destino_certo.usuario.modelo;

import java.util.List;

public interface IRepositorioUsuario {
public void cadastrar(Usuario usuario) throws UsuarioJaCadastradoException;
public void editar(Usuario usuario) throws UsuarioNaoEncontradoExcepition;
public void remover(Usuario usuario) throws UsuarioNaoEncontradoExcepition;
public Usuario procurar(String login) throws UsuarioNaoEncontradoExcepition;
public List<Usuario> listarUsuarios();
}
