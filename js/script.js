const changePage = (page) => location.hash = page;

const getPage = (page) => {
    fetch(`pages/${page}.html`)
        .then((response) => { return response.text() })
        .then((html) => { document.body.innerHTML = html })
        .catch((error) => { console.error(error) })
}

const route = () => {
    switch (location.hash) {
        default:
            getPage("login");
    }
}

window.addEventListener("hashchange", () => {
    route();
})

route();
