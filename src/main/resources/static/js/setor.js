function novoSetor(event) {
    const nome = document.getElementById("nome").value
    const descricao = document.getElementById("descricao").value

    if (nome === "") {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Digite um Nome ao Setor!',
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
        fetch("/api/setores/criar", cabecalho)
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
                        window.location.href = "/setores/editar/" + dados.id
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

function statusSetor(event) {

    const conteudo = {
        id: document.getElementById("id").value,
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
    fetch("/api/setores/status", cabecalho)
        .then(response => {
            if(!response.ok){
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Erro inesperado no servido!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            }else{
                return response.json()
            }
        })
        .then(dados => {
            if (dados.OK){
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

function editarSetor(event) {
    const nome = document.getElementById("nome").value
    if (nome === ''){
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Informe um nome ao setor!',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
        return
    }
    const conteudo = {
        id: document.getElementById("id").value,
        nome: nome,
        descricao: document.getElementById("descricao").value
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
    fetch("/api/setores/editar", cabecalho)
        .then(response => {
            if(!response.ok){
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Erro inesperado no servido!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            }else{
                return response.json()
            }
        })
        .then(dados => {
            if (dados.OK){
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
//TODO FALTA OPÇAO DE EXCLUIR