package cloud.masteroflie.sgpa.models;

import cloud.masteroflie.sgpa.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;
    private String cpf;

    private RoleEnum role = RoleEnum.USUARIO;
    private boolean enabled = true;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Processo> processos = new HashSet<>();

    @OneToMany(mappedBy = "requerente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Processo> requrente = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_departamento",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id")
    )
    private Set<Departamento> departamentos = new HashSet<>();

    public Usuario() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.role == RoleEnum.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_STAFF"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
        } else if (this.role == RoleEnum.STAFF) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STAFF"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));

        }else
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}