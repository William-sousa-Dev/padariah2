document.addEventListener("DOMContentLoaded", () => {

    // ---------- CADASTRO ----------
    const form = document.getElementById("formUsuario");
    if (form) {
        form.addEventListener("submit", (event) => {
            event.preventDefault();
            const usuario = {
                id: Date.now(),
                nome: document.getElementById("nome").value,
                cpf: document.getElementById("cpf").value,
                dataNasc: document.getElementById("dataNasc").value,
                email: document.getElementById("email").value,
                telefone: document.getElementById("telefone").value
            };
            localStorage.setItem("usuarioAtual", JSON.stringify(usuario));
            window.location.href = "home.html";
        });
    }

    // ---------- PRODUTOS E CARRINHO ----------
    const lista = document.getElementById("listaProdutos");
    const carrinho = document.getElementById("carrinho");
    const valorTotalSpan = document.getElementById("valorTotal");
    let valorTotal = 0;

    if (lista) {
        const produtos = [
            { id: 1, nome: "Pão Francês", preco: 0.80, img: "imagens/pao_frances.png" },
            { id: 2, nome: "Bolo de Cenoura", preco: 12.00, img: "imagens/bolo_cenoura.png" },
            { id: 3, nome: "Café Expresso", preco: 5.00, img: "imagens/cafe_expresso.png" },
            { id: 4, nome: "Croissant", preco: 7.50, img: "imagens/croissant.png" },
            { id: 5, nome: "Sonho de Creme", preco: 6.00, img: "imagens/sonho_creme.png" }
        ];

        produtos.forEach(produto => {
            const card = document.createElement("div");
            card.classList.add("produto-card");
            card.innerHTML = `
        <img src="${produto.img}" alt="${produto.nome}">
        <h3>${produto.nome}</h3>
        <p>R$ ${produto.preco.toFixed(2)}</p>
      `;
            const btn = document.createElement("button");
            btn.textContent = "Adicionar 🛒";
            btn.onclick = () => {
                const itemCarrinho = document.createElement("li");
                itemCarrinho.textContent = `${produto.nome} - R$ ${produto.preco.toFixed(2)}`;
                carrinho.appendChild(itemCarrinho);
                valorTotal += produto.preco;
                valorTotalSpan.textContent = valorTotal.toFixed(2);
            };
            card.appendChild(btn);
            lista.appendChild(card);
        });

        document.getElementById("finalizarCompra").onclick = () => {
            if (valorTotal === 0) { alert("Seu carrinho está vazio!"); return; }
            const usuario = JSON.parse(localStorage.getItem("usuarioAtual")) || { nome: "Desconhecido", id: "???" };
            const compra = {
                usuario_id: usuario.id,
                data_compra: new Date().toLocaleString("pt-BR"),
                valor_total: valorTotal.toFixed(2)
            };
            alert(`Compra realizada!\n\nUsuário: ${usuario.nome}\nData: ${compra.data_compra}\nTotal: R$ ${compra.valor_total}`);
            carrinho.innerHTML = "";
            valorTotal = 0;
            valorTotalSpan.textContent = "0.00";
        };
    }

});
