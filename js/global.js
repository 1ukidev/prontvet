// Métodos globais

const changePage = (page) => location.hash = page;

const login = () => {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (email === "admin" && password === "admin") {
        changePage("home");
    } else {
        alert("Usuário ou senha inválidos.");
    }
}
