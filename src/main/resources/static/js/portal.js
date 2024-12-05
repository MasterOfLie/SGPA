function portalProcesso(event) {
    const descricao = document.getElementById("descricao").value
    const servicoID = document.getElementById("servicoID").value
    console.log(servicoID)
    if(descricao === ''){
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Informe um descriçao!',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
        return
    }
    const conteudo = {
        descricao: descricao,
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
    fetch("/api/portal/processo", cabecalho)
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
                    window.location.href = "/portal/processo/" + dados.id
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