document.getElementById("sidebar-toggle").addEventListener('click', function () {
    const sidebar = document.getElementById('side-menu');
    const content = document.getElementById('conteudo');
    if (window.innerWidth <= 648) {
        sidebar.classList.toggle('side-mobile');
        content.classList.toggle('content-mobile');
    } else {
        if (sidebar.classList.contains('side-hidden')) {
            sidebar.classList.remove('side-hidden');
            content.classList.remove('content-hidden')
            setTimeout(function() {
                $('#tabela').DataTable().columns.adjust().draw();
            }, 300);
        } else {
            sidebar.classList.add('side-hidden');
            content.classList.add('content-hidden')
            setTimeout(function() {
                $('#tabela').DataTable().columns.adjust().draw();
            }, 300);
        }
    }
})
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
    dropdown[i].addEventListener("click", function () {
        // for (let j = 0; j < dropdown.length; j++) {
        //     const dropdownContent = dropdown[j].nextElementSibling;
        //     if (dropdownContent.style.display === "flex" && dropdown[j] !== this) { // fechar dropdowns abertos
        //         dropdownContent.style.display = "none";
        //         dropdown[j].classList.remove("active");
        //     }
        // }
        this.classList.toggle("active");
        var dropdownContent = this.nextElementSibling;
        if (dropdownContent.style.display === "flex") {
            dropdownContent.style.display = "none";
        } else {
            dropdownContent.style.display = "flex";
        }
    });
}