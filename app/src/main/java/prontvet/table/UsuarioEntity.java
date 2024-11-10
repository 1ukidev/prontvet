package prontvet.table;

public class UsuarioEntity {
    private Integer id;
    private String email;
    private String senha;

    public UsuarioEntity() {}

    public UsuarioEntity(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public UsuarioEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsuarioEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioEntity setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    @Override
    public String toString() {
        return email;
    }
}
