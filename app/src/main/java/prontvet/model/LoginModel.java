package prontvet.model;

import prontvet.Util;
import prontvet.table.UsuarioEntity;

public class LoginModel {
    public UsuarioEntity usuarioEntity = new UsuarioEntity();

    public boolean validate() {
        if (usuarioEntity.getEmail() == null || usuarioEntity.getEmail().isEmpty()) {
            Util.showError("O e-mail precisa ser preenchido!");
            return false;
        }
        if (usuarioEntity.getSenha() == null || usuarioEntity.getSenha().isEmpty()) {
            Util.showError("A senha precisa ser preenchida!");
            return false;
        }
        return true;
    }
}
