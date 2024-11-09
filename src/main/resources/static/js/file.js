// ----------------------------- UPLOAD DO ARQUIVO ----------------------------------------
const uploadContainer = document.getElementById('upload-container');
const fileInput = document.getElementById('file-input');
const uploadLabel = document.getElementById('upload-label');
const progressBar = document.getElementById('progress-bar');
const progress = document.getElementById('progress');
const idProcesso = document.getElementById("id").value;
function uploadFile(file, idProcesso) {
    const formData = new FormData();
    formData.append("file", file);

    const xhr = new XMLHttpRequest();

    xhr.open("POST", "/api/files/upload", true);

    xhr.setRequestHeader("idProcesso", idProcesso);

    // Atualiza a barra de progresso
    xhr.upload.addEventListener("progress", (event) => {
        if (event.lengthComputable) {
            const percent = Math.round((event.loaded * 100) / event.total);
            progress.style.width = percent + "%";
        }
    });

    xhr.onload = () => {
        if (xhr.status === 200) {
            try {
                const jsonResponse = JSON.parse(xhr.responseText);
                progress.style.width = 0;
                console.log(jsonResponse)
                arquivosAdd(jsonResponse.Nome, jsonResponse.Data , jsonResponse.Usuario, jsonResponse.id);
            } catch (error) {
                console.error("Erro ao analisar a resposta JSON:", error);
                alert("Erro ao processar a resposta do servidor!");
            }
        } else {
            alert("Erro no upload!");
        }
    };

    xhr.send(formData);
}

// Eventos para arraste e solte
uploadContainer.addEventListener("dragover", (event) => {
    event.preventDefault();
    uploadContainer.classList.add("drag-over");
});

uploadContainer.addEventListener("dragleave", () => {
    uploadContainer.classList.remove("drag-over");
});

uploadContainer.addEventListener("drop", (event) => {
    event.preventDefault();
    uploadContainer.classList.remove("drag-over");

    const file = event.dataTransfer.files[0];
    if (file) {
        progressBar.style.display = "block";
        uploadFile(file, idProcesso);
    }
});

// Prevenir propagação do clique no label
uploadLabel.addEventListener("click", (event) => event.stopPropagation());

// Evento para clique no contêiner principal
uploadContainer.addEventListener("click", () => fileInput.click());

// Evento para selecionar arquivo
fileInput.addEventListener("change", (event) => {
    const file = event.target.files[0];
    if (file) {
        progressBar.style.display = "block";
        uploadFile(file, idProcesso);
    }
});

function arquivosAdd(valor1, valor2, valor3, valor4) {
    var table = document.querySelector("#arquivos-table tbody");
    var row = table.insertRow(0);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);

    cell1.innerHTML = valor1;
    cell2.innerHTML = valor2;
    cell3.innerHTML = valor3;
    cell4.classList.add("d-flex", "justify-content-center"); // Adiciona classes corretamente
    cell4.innerHTML = `
        <div class="form-check form-switch">
            <input class="form-check-input" 
                   title="Arquivo disponível no portal de solicitações" 
                   type="checkbox" 
                   onclick="acessoExterno(this)" 
                   value="${valor4}">
        </div>
    `;
    cell5.innerHTML = `
        <div class="d-flex justify-content-center">
            <button type='button' class='btn btn-sm' onclick='downloadArquivo(this)' value='${valor4}'>
                <i class='bi bi-download'></i>
            </button>
            <button type='button' class='btn btn-sm text-danger' onclick='deleteFile(this)' value='${valor4}'>
                <i class='bi bi-trash3'></i>
            </button>
        </div>
    `;
}
