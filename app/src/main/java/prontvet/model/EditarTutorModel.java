package prontvet.model;

import prontvet.Util;
import prontvet.table.TutorEntity;

public class EditarTutorModel {
    public TutorEntity tutorEntity;

    public boolean validate() {
        if (tutorEntity == null) {
            Util.showError("Erro ao editar tutor!");
            return false;
        }
        if (tutorEntity.getNome() == null || tutorEntity.getNome().isEmpty()) {
            Util.showError("O nome precisa ser preenchido!");
            return false;
        }
        if (tutorEntity.getTelefone() == null || tutorEntity.getTelefone().isEmpty()) {
            Util.showError("O telefone precisa ser preenchido!");
            return false;
        }
        if (tutorEntity.getEndereco() == null || tutorEntity.getEndereco().isEmpty()) {
            Util.showError("O endereço precisa ser preenchido!");
            return false;
        }
        return true;
    }
}
