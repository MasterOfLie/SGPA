document.getElementById("collapsed").addEventListener('click', function() {
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('conteudo');
    if(window.innerWidth < 670){
        if (sidebar.style.display === "none" || sidebar.style.display === "") {
            sidebar.style.display = "flex";
            content.style.width = "calc(100vw - 260px)";
        } else {
            sidebar.style.display = "none";
            content.style.width = "100%";
        }
    }else{
        if (sidebar.classList.contains('collapsed')) {
            sidebar.classList.remove('collapsed');
            sidebar.classList.add('expanded');
            content.classList.remove('conteudo-expanded');
            setTimeout(function() {
                $('#tabela').DataTable().columns.adjust().draw();
            }, 300);
        } else {
            sidebar.classList.remove('expanded');
            sidebar.classList.add('collapsed');
            content.classList.add('conteudo-expanded');
            setTimeout(function() {
                $('#tabela').DataTable().columns.adjust().draw();
            }, 300);
        }
    }
})
