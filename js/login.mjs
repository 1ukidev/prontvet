// Login

import { loadDB } from "./db.mjs"

const handleLogin = () => {
    loadDB().then((data) => {
        if (data && data.usuarios) {
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;
            const admin = data.usuarios[0];

            if (email === admin.email && password === admin.senha) {
                localStorage.setItem("user", JSON.stringify(admin));
                changePage("");
                alertify.alert("", "Logado com sucesso!");
            } else {
                alertify.error("Usuário ou senha inválidos!");
            }
        }
    });
}

export const initLogin = () => {
    const btnEntrar = document.getElementById("btnEntrar");
    if (btnEntrar) {
        btnEntrar.addEventListener("click", handleLogin);
    }
}
