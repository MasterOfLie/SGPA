$("#cpf_cnpj").keydown(function(event){
    if(event.key === 'Tab'){
        return
    }
    try {
        $("#cpf_cnpj").unmask();
    } catch (e) {}

    var tamanho = $("#cpf_cnpj").val().length;

    if(tamanho < 11){
        $("#cpf_cnpj").mask("999.999.999-99");
    } else {
        $("#cpf_cnpj").mask("99.999.999/9999-99");
    }
    var elem = this;
    setTimeout(function(){
        elem.selectionStart = elem.selectionEnd = 10000;
    }, 0);
    var currentValue = $(this).val();
    $(this).val('');
    $(this).val(currentValue);
});
document.getElementById("register").addEventListener('submit', function (event){
    event.preventDefault()

    const conteudo = {
        name: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        cpfCnpj: document.getElementById("cpf_cnpj").value,
        password: document.getElementById("senha").value,
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
    Swal.fire({
        title: 'Carregando...',
        html: 'Por favor, aguarde.',
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });
    fetch("/api/auth/register", cabecalho)
        .then(response => {
            Swal.close();
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
                document.getElementById("nome").value = '';
                document.getElementById("email").value = '';
                document.getElementById("cpf_cnpj").value = '';
                document.getElementById("senha").value = '';
            }else{
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
})