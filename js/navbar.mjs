// Navbar

const cache = new Map();

const getNavbar = async () => {
    if (cache.has('navbar')) {
        return cache.get('navbar');
    }

    try {
        const response = await fetch('components/navbar.html');
        if (!response.ok) {
            throw new Error(`Erro ao carregar o componente: ${response.statusText}`);
        }

        const html = await response.text();
        cache.set('navbar', html);
        return html;
    } catch (error) {
        console.error(`Erro ao carregar o componente: ${error.message}`);
        return `<p>Erro ao carregar o componente: ${error.message}</p>`;
    }
}

export const showNavbar = () => {
    const navbar = document.querySelector('.navbar');
    if (!navbar) return;
    getNavbar().then((html) => {
        navbar.innerHTML = html;
        if (localStorage.getItem('user')) {
            const linkLogin = document.getElementById('linkLogin');
            if (linkLogin) {
                linkLogin.innerHTML = 'Logado';
            }
        }
    });
}
