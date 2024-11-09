function addPerfil(event){
    const conteudo ={
        idUsuario: document.getElementById("id").value,
        perfil: event.value
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
    Swal.fire({
        title: 'Carregando...',
        html: 'Por favor, aguarde.',
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });
    fetch("/api/usuario/add", cabecalho)
        .then(response => {
            Swal.close();
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
                })
            }
        })

}
function removePerfil(event){
    const conteudo ={
        idUsuario: document.getElementById("id").value
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
    Swal.fire({
        title: 'Carregando...',
        html: 'Por favor, aguarde.',
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });
    fetch("/api/usuario/remove", cabecalho)
        .then(response => {
            Swal.close();
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
                })
            }
        })

}
function addDepartamento(event){
    const conteudo ={
        usuarioID: document.getElementById("id").value,
        departamentoID: event.value
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
    Swal.fire({
        title: 'Carregando...',
        html: 'Por favor, aguarde.',
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });
    fetch("/api/departamentos/add", cabecalho)
        .then(response => {
            Swal.close();
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
                })
            }
        })

}