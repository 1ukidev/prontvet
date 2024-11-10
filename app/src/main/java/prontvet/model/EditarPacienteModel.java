package prontvet.model;

import prontvet.Util;
import prontvet.table.PacienteEntity;

public class EditarPacienteModel {
    public PacienteEntity pacienteEntity;

    public boolean validate() {
        if (pacienteEntity.getTutor() == null) {
            Util.showError("O tutor precisa ser selecionado!");
            return false;
        }
        if (pacienteEntity.getNome() == null || pacienteEntity.getNome().isEmpty()) {
            Util.showError("O nome precisa ser preenchido!");
            return false;
        }
        if (pacienteEntity.getRaca() == null || pacienteEntity.getRaca().isEmpty()) {
            Util.showError("A raça precisa ser preenchida!");
            return false;
        }
        if (pacienteEntity.getSexo() == null) {
            Util.showError("O sexo precisa ser selecionado!");
            return false;
        }
        if (pacienteEntity.getIdade() <= 0) {
            Util.showError("A idade precisa ser preenchida!");
            return false;
        }
        if (pacienteEntity.getPeso() <= 0d) {
            Util.showError("O peso precisa ser preenchido!");
            return false;
        }
        if (pacienteEntity.getDescricao() == null || pacienteEntity.getDescricao().isEmpty()) {
            Util.showError("A descrição precisa ser preenchida!");
            return false;
        }
        return true;
    }
}
