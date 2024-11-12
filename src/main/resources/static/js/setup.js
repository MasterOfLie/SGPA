$("#inputCpfCnpj").keydown(function (event) {
    if (event.key === 'Tab') {
        return
    }
    try {
        $("#inputCpfCnpj").unmask();
    } catch (e) { }

    var tamanho = $("#inputCpfCnpj").val().length;

    if (tamanho < 11) {
        $("#inputCpfCnpj").mask("999.999.999-99");
    } else {
        $("#inputCpfCnpj").mask("99.999.999/9999-99");
    }
    var elem = this;
    setTimeout(function () {
        elem.selectionStart = elem.selectionEnd = 10000;
    }, 0);
    var currentValue = $(this).val();
    $(this).val('');
    $(this).val(currentValue);
});

function setup(event) {
    const Nome = document.getElementById('inputNome');
    const Email = document.getElementById('inputEmail');
    const Cidade = document.getElementById('inputCidade');
    const Estado = document.getElementById('inputEstado');
    const Setor = document.getElementById('inputSetor');
    const CpfCnpj = document.getElementById('inputCpfCnpj');
    const Senha = document.getElementById('inputSenha');
    const ConfirmarSenha = document.getElementById('inputConfirmarSenha');
    const NomeAplicacao = document.getElementById('inputNomeAplicacao');
    const AzureString = document.getElementById('inputAzureString');
    const Container = document.getElementById('inputContainer');

    let formValid = true;
    let password = true;

    if (Nome.value === '') {
        Nome.classList.add('is-invalid');
        formValid = false;
    } else {
        Nome.classList.remove('is-invalid');
    }
    if (Email.value === '') {
        Email.classList.add('is-invalid');
        formValid = false;
    } else {
        Email.classList.remove('is-invalid');
    }
    if (Cidade.value === '') {
        Cidade.classList.add('is-invalid');
        formValid = false;
    } else {
        Cidade.classList.remove('is-invalid');
    }
    if (Estado.value === '') {
        Estado.classList.add('is-invalid');
        formValid = false;
    } else {
        Estado.classList.remove('is-invalid');
    }
    if (Setor.value === '') {
        Setor.classList.add('is-invalid');
        formValid = false;
    } else {
        Setor.classList.remove('is-invalid');
    }
    if (CpfCnpj.value === '') {
        CpfCnpj.classList.add('is-invalid');
        formValid = false;
    } else {
        CpfCnpj.classList.remove('is-invalid');
    }
    if (Senha.value === '') {
        Senha.classList.add('is-invalid');
        formValid = false;
    } else {
        Senha.classList.remove('is-invalid');
    }
    if (ConfirmarSenha.value === '' || ConfirmarSenha.value !== Senha.value) {
        ConfirmarSenha.classList.add('is-invalid');
        password = false
        formValid = false;
    } else {
        ConfirmarSenha.classList.remove('is-invalid');
    }
    if (NomeAplicacao.value === '') {
        NomeAplicacao.classList.add('is-invalid');
        formValid = false;
    } else {
        NomeAplicacao.classList.remove('is-invalid');
    }
    if (AzureString.value === '') {
        AzureString.classList.add('is-invalid');
        formValid = false;
    } else {
        AzureString.classList.remove('is-invalid');
    }
    if (Container.value === '') {
        Container.classList.add('is-invalid');
        formValid = false;
    } else {
        Container.classList.remove('is-invalid');
    }

    if(!password){
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'As senhas informadas não coincidem. Verifique e tente novamente.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
        return
    }

    if (!formValid) {
        Swal.fire({
            toast: true,
            position: 'bottom-end',
            icon: 'error',
            title: 'Por favor, preencha todos os campos obrigatórios.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true
        });
        return;
    }

    const conteudo = {
        nome: Nome.value,
        email: Email.value,
        cidade: Cidade.value,
        estado: Estado.value,
        setor: Setor.value,
        cpfCnpj: CpfCnpj.value,
        senha: Senha.value,
        nomeAplicacao: NomeAplicacao.value,
        azureString: AzureString.value,
        container: Container.value
    };
    Swal.fire({
        title: 'Carregando...',
        html: 'Por favor, aguarde.',
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });
    fetch('/api/setup/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(conteudo)
    })
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
                window.location.href = "/auth";
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