// Cuida do roteamento da aplicação

import { loadHome } from "./home.mjs";
import { loadLogin } from "./login.mjs";

const root = document.getElementById("root");

const pageCache = new Map();

const getPage = async (page, onLoadCallback) => {
    if (pageCache.has(page)) {
        root.innerHTML = pageCache.get(page);
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
        console.error("Erro ao carregar a página:", error);
        root.innerHTML = `<p>Erro ao carregar a página: ${error.message}</p>`;
    }
};

export const route = () => {
    switch (location.hash) {
        case "#login":
            getPage("login", loadLogin);
            break;
        case "":
            getPage("home", loadHome);
            break;
    }
};
