// Home

import { loadDB } from "./db.mjs";

export const loadHome = () => {
    const produtos = document.getElementById("produtos");
    if (produtos) {
        loadDB().then((data) => {
            if (data && data.produtos) {
                const produtosHTML = data.produtos.map((produto) => `
                    <div class="col-4">
                        <img src="${produto.img}">
                        <h4>${produto.descricao}</h4>
                        <p>${produto.preco.toFixed(2)}</p>
                    </div>
                `).join("");
                
                produtos.innerHTML = produtosHTML;
            } else {
                produtos.innerHTML = "<p>Nenhum produto encontrado.</p>";
            }
        });
    }
}
