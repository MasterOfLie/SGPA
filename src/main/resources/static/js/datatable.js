$(document).ready(function () {
        $('#tabela').DataTable({
            info: false,
            ordering: true,
            paging: true,
            scrollX: true,
            scrollY: false,
            searching: true,
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json'
            },
        });
});
$(document).ready(function () {
    $('.perfils').DataTable({
        info: false,
        ordering: true,
        paging: false,
        scrollX: true,
        scrollY: false,
        searching: false,
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json'
        }
    });
});