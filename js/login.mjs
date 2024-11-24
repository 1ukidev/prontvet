// Login

import { loadDB } from "./db.mjs"

export const loadLogin = () => {
    const btnEntrar = document.getElementById("btnEntrar");
    if (btnEntrar) {
        btnEntrar.addEventListener("click", () => {
            loadDB().then((data) => {
                if (data && data.usuarios) {
                    const email = document.getElementById("email").value;
                    const password = document.getElementById("password").value;
                    const admin = data.usuarios[0];
        
                    if (email === admin.email && password === admin.senha) {
                        changePage("home");
                    } else {
                        alert("Usuário ou senha inválidos!");
                    }
                }
            });
        });
    }
}
