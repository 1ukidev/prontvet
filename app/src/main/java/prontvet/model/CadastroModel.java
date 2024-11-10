package prontvet.model;

import prontvet.Util;
import prontvet.table.UsuarioEntity;

public class CadastroModel {
    public UsuarioEntity usuarioEntity = new UsuarioEntity();
    public String repitaSenha;

    public boolean validate() {
        if (usuarioEntity.getEmail() == null || usuarioEntity.getEmail().isEmpty()) {
            Util.showError("O e-mail precisa ser preenchido!");
            return false;
        }
        if (usuarioEntity.getSenha() == null || usuarioEntity.getSenha().isEmpty()) {
            Util.showError("A senha precisa ser preenchida!");
            return false;
        }
        if (repitaSenha == null || repitaSenha.isEmpty()) {
            Util.showError("A senha precisa ser repetida!");
            return false;
        }
        if (!usuarioEntity.getSenha().equals(repitaSenha)) {
            Util.showError("As senhas não conferem!");
            return false;
        }
        return true;
    }
}
