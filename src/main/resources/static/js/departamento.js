function novoDepartamento(event) {
    const nome = document.getElementById("nome").value
    const descricao = document.getElementById("descricao").value

    if (nome === "") {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Digite um Nome ao Departamento!',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
    } else {
        const conteudo = {
            nome: nome,
            descricao: descricao
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
        fetch("/api/departamentos/criar", cabecalho)
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
                        window.location.href = "/departamentos/editar/" + dados.id
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
function statusDepartamento(event){
    const conteudo = {
        departamentoID: document.getElementById("id").value,
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
    fetch("/api/departamentos/status", cabecalho)
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
function excluirDepartamento(event){
    Swal.fire({
        title: "Excluir Perfil",
        text: "Deseja excluir este Departamento? Lembre-se de que nenhum usuário poderá ter este departamento atribuído durante a exclusão. Esta operação é irreversível.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sim",
        cancelButtonText: "Cancelar"
    }).then((result) => {
        if (result.isConfirmed) {
            const conteudo = {
                departamentoID: document.getElementById("id").value,
            }
            const cabecalho =
                {
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    method: "DELETE",
                    body: JSON.stringify(conteudo)
                }
            fetch("/api/departamentos/delete", cabecalho)
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
                            title: "Excluído!",
                            text: dados.OK,
                            icon: "success",
                            confirmButtonText: "OK"
                        }).then(result => {
                            if (result.isConfirmed) {
                                setTimeout(() => {
                                    window.location.href = "/departamentos"
                                }, 300)
                            }
                        });
                    }else{
                        Swal.fire({
                            title: "Error!",
                            text: dados.Error,
                            icon: "error",
                        })
                    }
                })
        }
    });
} // TODO FAZER TRATAMENTO DE (POSSUI PROCESSO NO DEPARTAMENTO (MOSTRANDO SLQ NO PORTAL))
function editarDepartamento(event){
    const conteudo = {
        id: document.getElementById("id").value,
        nome: document.getElementById("nome").value,
        descricao: document.getElementById("descricao").value,
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
    fetch("/api/departamentos/editar", cabecalho)
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