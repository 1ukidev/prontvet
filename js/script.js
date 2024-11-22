const root = document.getElementById("root");

const pageCache = new Map();

const getPage = async (page) => {
    if (pageCache.has(page)) {
        root.innerHTML = pageCache.get(page);
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
    } catch (error) {
        console.error("Erro ao carregar a página:", error);
        root.innerHTML = `<p>Erro ao carregar a página: ${error.message}</p>`;
    }
};

const route = () => {
    switch (location.hash) {
        case "#home":
            getPage("home");
            break;
        case "":
            getPage("login");
            break;
    }
};

window.addEventListener("hashchange", route);

route();

const changePage = (page) => location.hash = page;
