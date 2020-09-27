package app.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @Column(name = "usu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usu_nombre")
    private String nombre;

    @Column(name = "usu_correo",  unique = true)
    private String correo;

    @Column(name = "usu_genero")
    private String genero;

    @Column(name = "usu_fecha_nac")
    private Date fechaNacimiento;

    @Column(name = "usu_telefono")
    private String telefono;

    @Column(name = "usu_estado")
    private boolean estado;

}
