package prontvet.database;

import prontvet.entities.UsuarioEntity;

public class UsuarioService {
    /**
     * Salva um usuário no banco de dados.
     * 
     * @param usuario Usuário a ser salvo.
     * @return Usuário salvo.
     */
    public static UsuarioEntity save(UsuarioEntity usuario) {
        return UsuarioRepository.getInstance().save(usuario);
    }
}
