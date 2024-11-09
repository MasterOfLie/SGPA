document.getElementById("cep").addEventListener('focusout', function (){
    const cep = document.getElementById("cep").value.replace(/\D/g, '')
    fetch('https://viacep.com.br/ws/'+ cep + '/json/')
        .then(response => {
            if (response.ok) {
                return response.json()
            } else if(!response.ok) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Erro inesperado no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            }
        })
        .then(dados => {
            if (dados && dados.erro) {
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'warning',
                    title: 'CEP não encontrado!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            } else if (dados) {
                document.getElementById("estado").value = dados.uf;
                document.getElementById("cidade").value = dados.localidade;
                document.getElementById("estado").value = dados.estado;
            }
        })
})
$(document).ready(function(){
    $('#telefone').mask('(00) 00000-0000');
});
$(document).ready(function(){
    $('#cep').mask('00000-000');
});
$("#cpfCnpj").keydown(function(event){
    if(event.key === 'Tab'){
        return
    }
    try {
        $("#cpfCnpj").unmask();
    } catch (e) {}

    var tamanho = $("#cpfCnpj").val().length;

    if(tamanho < 11){
        $("#cpfCnpj").mask("999.999.999-99");
    } else {
        $("#cpfCnpj").mask("99.999.999/9999-99");
    }
    var elem = this;
    setTimeout(function(){
        elem.selectionStart = elem.selectionEnd = 10000;
    }, 0);
    var currentValue = $(this).val();
    $(this).val('');
    $(this).val(currentValue);
});

function alterarSenha(event){
    const newPassword = document.getElementById("senha").value
    const usuarioID = document.getElementById("id").value

    const conteudo = {
        newPassword: newPassword,
        idUsuario: usuarioID
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
        fetch("/api/solicitante/senha", cabecalho)
            .then(response => {
                if (!response.ok){
                    Swal.fire({
                        toast: true,
                        position: 'bottom-end',
                        icon: 'error',
                        title: 'Erro inesperado no servidor!',
                        showConfirmButton: false,
                        timer: 3000,
                        timerProgressBar: true
                    });
                }else {
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
                }else {
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
function alterarDados(event){

    const conteudo = {
        id: document.getElementById("id").value,
        name: document.getElementById("nome").value,
        cpfCnpj: document.getElementById("cpfCnpj").value,
        telefone: document.getElementById("telefone").value,
        email: document.getElementById("email").value,
        cep: document.getElementById("cep").value,
        cidade: document.getElementById("cidade").value,
        bairro: document.getElementById("bairro").value,
        logradouro: document.getElementById("logradouro").value,
        estado: document.getElementById("estado").value
    }
    console.log(conteudo)
    const cabecalho =
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo)
        }
    fetch("/api/solicitante/dados", cabecalho)
        .then(response => {
            if (!response.ok){
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Erro inesperado no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            }else {
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
            }else {
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

function cadastrarSolicitante(event){
    const conteudo = {
        name: document.getElementById("nome").value,
        cpfCnpj: document.getElementById("cpfCnpj").value,
        telefone: document.getElementById("telefone").value,
        email: document.getElementById("email").value,
        cep: document.getElementById("cep").value,
        cidade: document.getElementById("cidade").value,
        bairro: document.getElementById("bairro").value,
        logradouro: document.getElementById("logradouro").value,
        estado: document.getElementById("estado").value,
        password: document.getElementById("password").value
    }
    console.log(conteudo)
    const cabecalho =
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(conteudo)
        }
    fetch("/api/auth/register", cabecalho)
        .then(response => {
            if (!response.ok){
                Swal.fire({
                    toast: true,
                    position: 'bottom-end',
                    icon: 'error',
                    title: 'Erro inesperado no servidor!',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
                });
            }else {
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
            }else {
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