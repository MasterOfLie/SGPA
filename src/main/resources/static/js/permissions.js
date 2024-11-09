// document.getElementById("PROFILE_CREATE").addEventListener('click', function () {
//     const checked = document.getElementById("PROFILE_CREATE").checked;
//     const idperfil = document.getElementById("id").value
//     if (checked) {
//         addPermissions("PROFILE_CREATE", idperfil)
//     } else {
//         removePermissions("PROFILE_CREATE", idperfil)
//     }
// });
// document.getElementById("PROFILE_EDIT").addEventListener('click', function () {
//     const checked = document.getElementById("PROFILE_EDIT").checked;
//     const idperfil = document.getElementById("id").value
//     if (checked) {
//         addPermissions("PROFILE_EDIT", idperfil)
//     } else {
//         removePermissions("PROFILE_EDIT", idperfil)
//     }
// });
// document.getElementById("PROFILE_ASSIGN").addEventListener('click', function () {
//     const checked = document.getElementById("PROFILE_ASSIGN").checked;
//     const idperfil = document.getElementById("id").value
//     if (checked) {
//         addPermissions("PROFILE_ASSIGN", idperfil)
//     } else {
//         removePermissions("PROFILE_ASSIGN", idperfil)
//     }
// });
// document.getElementById("PROFILE_TOGGLE").addEventListener('click', function () {
//     const checked = document.getElementById("PROFILE_TOGGLE").checked;
//     const idperfil = document.getElementById("id").value
//     if (checked) {
//         addPermissions("PROFILE_TOGGLE", idperfil)
//     } else {
//         removePermissions("PROFILE_TOGGLE", idperfil)
//     }
// });
// document.getElementById("PROFILE_VIEW").addEventListener('click', function () {
//     const checked = document.getElementById("PROFILE_VIEW").checked;
//     const idperfil = document.getElementById("id").value
//     if (checked) {
//         addPermissions("PROFILE_VIEW", idperfil)
//     } else {
//         removePermissions("PROFILE_VIEW", idperfil)
//     }
// });

const permissions = [
    "PROFILE_CREATE",
    "PROFILE_EDIT",
    "PROFILE_ASSIGN",
    "PROFILE_TOGGLE",
    "PROFILE_VIEW",
    "USER_CREATE",
    "USER_EDIT",
    "USER_TOGGLE",
    "USER_VIEW",
    "SECTOR_CREATE",
    "SECTOR_EDIT",
    "SECTOR_DELETE",
    "SECTOR_TOGGLE",
    "SECTOR_VIEW",
    "SERVICE_CREATE",
    "SERVICE_EDIT",
    "SERVICE_DELETE",
    "SERVICE_TOGGLE",
    "SERVICE_VIEW",
    "DEPARTMENT_CREATE",
    "DEPARTMENT_EDIT",
    "DEPARTMENT_DELETE",
    "DEPARTMENT_TOGGLE",
    "DEPARTMENT_ASSIGN",
    "DEPARTMENT_VIEW",
    "PROCESS_CREATE",
    "PROCESS_EDIT",
    "PROCESS_STATUS_CHANGE",
    "REQUESTER_EDIT",
    "REQUESTER_PASSWORD_CHANGE",
    "APPLICATION_EDIT"
];

permissions.forEach(function (permissionId) {
    const element = document.getElementById(permissionId);

    if (element) {
        element.addEventListener('click', function () {
            const idperfil = document.getElementById("id").value;
            const checked = this.checked;
            if (checked) {
                addPermissions(permissionId, idperfil);
            } else {
                removePermissions(permissionId, idperfil);
            }
        });
    } else {
        console.warn(`Elemento com o ID "${permissionId}" não encontrado.`);
    }
});

function addPermissions(permission, perfil) {
    const conteudo = {
        permission: permission,
        id: perfil
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
    fetch("/api/perfil/add", cabecalho)
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

function removePermissions(permission, perfil) {
    const conteudo = {
        permission: permission,
        id: perfil
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
    fetch("/api/perfil/remove", cabecalho)
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

function duplicado(event) { //TODO BUG? CORRIGIR FUTURAMENTE
    function enviarDuasRequisicoesSimultaneas() {
        const conteudo1 = {
            permission: "PROFILE_CREATE",
            id: "467441ea-7dca-4dbd-a170-14d6a08a58da"
        };

        const conteudo2 = {
            permission: "PROFILE_CREATE",
            id: "467441ea-7dca-4dbd-a170-14d6a08a58da"
        };

        const cabecalho = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo1)
        };

        const cabecalho2 = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(conteudo2)
        };

        // Enviar duas requisições ao mesmo tempo
        Promise.all([
            fetch("/api/perfil/add", cabecalho),
            fetch("/api/perfil/add", cabecalho2)
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

}