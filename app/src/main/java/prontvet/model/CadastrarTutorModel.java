package prontvet.model;

import prontvet.Util;
import prontvet.table.TutorEntity;

public class CadastrarTutorModel {
    public TutorEntity tutorEntity = new TutorEntity();

    public boolean validate() {
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
