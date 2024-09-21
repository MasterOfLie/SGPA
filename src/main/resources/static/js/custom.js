$(document).ready(function () {
    $('#listar-departamentos').DataTable({
        info: false,
        ordering: true,
        paging: true,
        searching: true,
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json'
        }
    });
});
let fileInputCount = 1; // Contador de inputs

function addFileInput() {
    // Incrementa o contador para garantir IDs únicos
    fileInputCount++;

    // Cria um novo grupo de input de arquivo
    const newFileInputGroup = document.createElement('div');
    newFileInputGroup.className = 'file-input-group';

    // Define um ID único para o novo input e o label
    const newFileInputId = 'arquivosup' + fileInputCount;
    const newFileLabelId = 'file-label' + fileInputCount;

    // Adiciona o HTML do novo input e label
    newFileInputGroup.innerHTML = `
        <label for="${newFileInputId}" id="${newFileLabelId}"><i class="bi bi-cloud-arrow-up"></i> Escolher Arquivo(s)</label>
        <input id="${newFileInputId}" type="file" name="file" class="form-control mt-2" hidden multiple />
    `;

    // Adiciona o novo input ao container
    document.getElementById('fileInputContainer').appendChild(newFileInputGroup);

    // Adiciona o evento de mudança ao novo input
    document.getElementById(newFileInputId).addEventListener('change', function() {
        const fileLabel = document.getElementById(newFileLabelId);
        const files = this.files;

        if (files.length > 0) {
            const fileNames = [];
            for (let i = 0; i < files.length; i++) {
                fileNames.push(files[i].name);
            }
            fileLabel.textContent = fileNames.join(', ');
        } else {
            fileLabel.innerHTML = '<i class="bi bi-cloud-arrow-up"></i> Escolher Arquivo(s)';
        }
    });
}

// Evento inicial para o primeiro input
document.getElementById('arquivosup1').addEventListener('change', function() {
    const fileLabel = document.getElementById('file-label1');
    const files = this.files;

    if (files.length > 0) {
        const fileNames = [];
        for (let i = 0; i < files.length; i++) {
            fileNames.push(files[i].name);
        }
        fileLabel.textContent = fileNames.join(', ');
    } else {
        fileLabel.innerHTML = '<i class="bi bi-cloud-arrow-up"></i> Escolher Arquivo(s)';
    }
});