// Cuida do roteamento da aplicação

import { initCarrinho } from "./carrinho.mjs";
import { initHome } from "./home.mjs";
import { initLogin } from "./login.mjs";
import { initProdutos } from "./produtos.mjs";

const root = document.getElementById("root");

const pageCache = new Map();

const getPage = async (page, onLoadCallback) => {
    loading.style.display = "block";
    root.innerHTML = "";

    if (pageCache.has(page)) {
        root.innerHTML = pageCache.get(page);
        loading.style.display = "none";
        if (onLoadCallback) onLoadCallback();
        return;
    }

    try {
        const response = await fetch(`pages/${page}.html`);
        if (!response.ok) {
            throw new Error(`Erro ao carregar a página: ${response.statusText}`);
        }

        const html = await response.text();
        pageCache.set(page, html);
        root.innerHTML = html;
        if (onLoadCallback) onLoadCallback();
    } catch (error) {
        console.error(`Erro ao carregar a página: ${error.message}`);
        root.innerHTML = `<p>Erro ao carregar a página: ${error.message}</p>`;
    } finally {
        loading.style.display = "none";
    }
};

export const route = () => {
    switch (location.hash) {
        case "#login":
            getPage("login", initLogin);
            break;
        case "#produtos":
            getPage("produtos", initProdutos);
            break;
        case "#carrinho":
            getPage("carrinho", initCarrinho);
            break;
        case "":
            getPage("home", initHome);
            break;
    }
};
