function novoProcesso(event) {
    const descricao = document.getElementById("descricao").value
    const departamentoID = document.getElementById("departamentoID").value
    const solicitanteID = document.getElementById("solicitanteID").value
    const servicoID = document.getElementById("servicoID").value

    if (departamentoID === "disabled") {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Digite um Nome ao Servico!',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
    } else {
        if (solicitanteID === "disabled" || servicoID === "servicoID") {
            Swal.fire({
                toast: true,
                position: 'bottom-end',
                icon: 'error',
                title: 'Você deve selecionar o Departamento, Solicitante e Servico para continuar!',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true
            });
        } else {
            const conteudo = {
                descricao: descricao,
                departamentoID: departamentoID,
                solicitanteID: solicitanteID,
                servicoID: servicoID
            }
            const cabecalho =
                {
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    method: "POST",
                    body: JSON.stringify(conteudo)
                }
            fetch("/api/processos/criar", cabecalho)
                .then(response => {
                    if (!response.ok) {
                        Swal.fire({
                            toast: true,
                            position: 'bottom-end',
                            icon: 'error',
                            title: 'Erro inesperado no servidor!',
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true
                        });
                    } else {
                        return response.json()
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
                            window.location.href = "/processos/editar/" + dados.id
                        }, 500)
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
                })
        }
    }
}

function movimentarProcesso(event) {
    const processoID = document.getElementById("id").value
    const destino = document.getElementById("departamentoMovimento").value
    const descricao = document.getElementById("descricaoMovimentacao").value

    if (destino === 'disabled') {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Selecione o departamento de destino!',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
        return
    }

    const conteudo = {
        processoID: processoID,
        destino: destino,
        descricao: descricao,
    }
    const cabecalho =
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo)
        }
    fetch("/api/processos/movimentar", cabecalho)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Error interno no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                return response.json()
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
        })

}

function duplicadoProcesso(event) { //TODO BUG? CORRIGIR FUTURAMENTE
    function enviarDuasRequisicoesSimultaneas() {
        const descricao = document.getElementById("descricao").value
        const departamentoID = document.getElementById("departamentoID").value
        const solicitanteID = document.getElementById("solicitanteID").value
        const servicoID = document.getElementById("servicoID").value
        const conteudo1 = {
            descricao: "testando duplicado serviço 1",
            departamentoID: departamentoID,
            solicitanteID: solicitanteID,
            servicoID: servicoID
        };

        const conteudo2 = {
            descricao: "testando duplicado serviço 2",
            departamentoID: departamentoID,
            solicitanteID: solicitanteID,
            servicoID: servicoID
        };

        const cabecalho = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(conteudo1)
        };

        const cabecalho2 = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(conteudo2)
        };

        // Enviar duas requisições ao mesmo tempo
        Promise.all([
            fetch("/api/processos/criar", cabecalho),
            fetch("/api/processos/criar", cabecalho2)
        ])
            .then(responses => Promise.all(responses.map(res => res.json())))
            .then(results => {
                console.log("Resultado da primeira requisição:", results[0]);
                console.log("Resultado da segunda requisição:", results[1]);
            })
            .catch(error => {
                console.error("Erro em uma das requisições:", error);
            });
    }

    enviarDuasRequisicoesSimultaneas();

} //TODO REMOVER DUPLICAÇAO
function statusProcesso(event, valor) {
    const status = valor
    const id = document.getElementById("id").value

    const conteudo = {
        processoID: id,
        status: status,
    }
    const cabecalho =
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo)
        }
    fetch("/api/processos/status", cabecalho)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Error interno no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                return response.json()
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
        })
}
function downloadArquivo(event){
    console.log(event.value)
    const conteudo = {
        id: event.value
    }
    const cabecalho =
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo)
        }
    fetch("/api/files/download", cabecalho)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Error interno no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                return response.json()
            }
        })
        .then(dados => {
            if (dados.OK) {
                setTimeout(() => {
                    window.open(dados.OK, '_blank')
                }, 500)
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
        })
}
function deleteFile(button){
    const conteudo = {
        id: button.value
    }
    const cabecalho =
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo)
        }
    fetch("/api/files/delete", cabecalho)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Error interno no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                return response.json()
            }
        })
        .then(dados => {
            if (dados.OK) {
                const row = button.closest('tr');
                row.remove();
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
        })
}
function acessoExterno(event){
    const conteudo = {
        id: event.value
    }
    const cabecalho =
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo)
        }
    fetch("/api/files/externo", cabecalho)
        .then(response => {
            if (!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Error interno no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else {
                return response.json()
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
        })
}




