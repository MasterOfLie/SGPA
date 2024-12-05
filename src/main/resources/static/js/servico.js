function novoServico(event) {
    const nome = document.getElementById("nome").value;
    const descricao = document.getElementById("descricao").value;
    const setorID = document.getElementById("setorID").value;
    const aberturaOnline = document.getElementById("aberturaOnline").value;
    const departamento = document.getElementById("departamentoPadrao").value;

    if (aberturaOnline === "disabled") {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Por favor, selecione uma opção em Abertura Online.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
        return;
    }

    if (nome === "") {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Por favor, insira um nome para o serviço.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
    } else {
        if (!document.getElementById("departamentoPadrao").hasAttribute('disabled')) {
            if (departamento === '') {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Por favor, selecione um departamento padrão.',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
                return;
            }
        }
        if (setorID === "disabled") {
            Swal.fire({
                toast: true,
                position: 'bottom-end',
                icon: 'error',
                title: 'Por favor, selecione o setor responsável pelo serviço.',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true
            });
        } else {
            const conteudo = {
                nome: nome,
                descricao: descricao,
                setorID: setorID,
                aberturaOnline: aberturaOnline,
                departamentoID: departamento
            };
            const cabecalho = {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: "POST",
                body: JSON.stringify(conteudo)
            };
            fetch("/api/servicos/criar", cabecalho)
                .then(response => {
                    if (!response.ok) {
                        Swal.fire({
                            toast: true,
                            position: 'bottom-end',
                            icon: 'error',
                            title: 'Ocorreu um erro inesperado no servidor.',
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true
                        });
                    } else {
                        return response.json();
                    }
                })
                .then(dados => {
                    if (dados.OK) {
                        Swal.fire({
                            toast: true,
                            position: 'bottom-end',
                            icon: 'success',
                            title: dados.OK,
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true
                        });
                        setTimeout(() => {
                            window.location.href = "/servicos/editar/" + dados.id;
                        }, 500);
                    } else {
                        Swal.fire({
                            toast: true,
                            position: 'bottom-end',
                            icon: 'error',
                            title: dados.Error,
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true
                        });
                    }
                });
        }
    }
}
document.getElementById("aberturaOnline").addEventListener('change', function () {
    const departamentoPadrao = document.getElementById("departamentoPadrao");
    if (document.getElementById("aberturaOnline").value === 'true') {
        departamentoPadrao.removeAttribute('disabled');
        departamentoPadrao.setAttribute('required', '');
    } else {
        departamentoPadrao.setAttribute('disabled', '');
        departamentoPadrao.removeAttribute('required');
    }
});
function deleteServico(event){
    const conteudo = {
        id: document.getElementById("id").value,
    };
    const cabecalho = {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "DELETE",
        body: JSON.stringify(conteudo)
    };
    fetch("/api/servicos/delete", cabecalho)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Ocorreu um erro inesperado no servidor.',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                return response.json();
            }
        })
        .then(dados => {
            if (dados.OK) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'success',
                    title: dados.OK,
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
                setTimeout(() => {
                    window.location.href = "/servicos"
                }, 500);
            } else {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: dados.Error,
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            }
        });
}
function statusServico(event){
    const conteudo = {
        id: document.getElementById("id").value,
    };
    const cabecalho = {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "PUT",
        body: JSON.stringify(conteudo)
    };
    fetch("/api/servicos/status", cabecalho)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Ocorreu um erro inesperado no servidor.',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                return response.json();
            }
        })
        .then(dados => {
            if (dados.OK) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'success',
                    title: dados.OK,
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: dados.Error,
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            }
        });
}

function editarServico(event) {
    const id = document.getElementById("id").value
    const nome = document.getElementById("nome").value;
    const descricao = document.getElementById("descricao").value;
    const setorID = document.getElementById("setorID").value;
    const aberturaOnline = document.getElementById("aberturaOnline").value;
    const departamento = document.getElementById("departamentoPadrao").value;

    if (aberturaOnline === "disabled") {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Por favor, selecione uma opção em Abertura Online.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
        return;
    }

    if (nome === "") {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Por favor, insira um nome para o serviço.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
    } else {
        if (!document.getElementById("departamentoPadrao").hasAttribute('disabled')) {
            if (departamento === '') {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Por favor, selecione um departamento padrão.',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
                return;
            }
        }
        if (setorID === "disabled") {
            Swal.fire({
                toast: true,
                position: 'bottom-end',
                icon: 'error',
                title: 'Por favor, selecione o setor responsável pelo serviço.',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true
            });
        } else {
            const conteudo = {
                id: id,
                nome: nome,
                descricao: descricao,
                setorID: setorID,
                aberturaOnline: aberturaOnline,
                departamentoID: departamento
            };
            const cabecalho = {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: "PUT",
                body: JSON.stringify(conteudo)
            };
            fetch("/api/servicos/editar", cabecalho)
                .then(response => {
                    if (!response.ok) {
                        Swal.fire({
                            toast: true,
                            position: 'bottom-end',
                            icon: 'error',
                            title: 'Ocorreu um erro inesperado no servidor.',
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true
                        });
                    } else {
                        return response.json();
                    }
                })
                .then(dados => {
                    if (dados.OK) {
                        Swal.fire({
                            toast: true,
                            position: 'bottom-end',
                            icon: 'success',
                            title: dados.OK,
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true
                        });
                    } else {
                        Swal.fire({
                            toast: true,
                            position: 'bottom-end',
                            icon: 'error',
                            title: dados.Error,
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true
                        });
                    }
                });
        }
    }
}