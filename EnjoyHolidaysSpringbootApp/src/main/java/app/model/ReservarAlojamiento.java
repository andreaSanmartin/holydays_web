package app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Reserva_Alojamiento")
public class ReservarAlojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "res_id")
    private Long id;

    @Column(name = "res_fecha_inicio")
    private Date fechaInicio;

    @Column(name = "res_fecha_fin")
    private Date fechaFinal;

    @Column(name = "res_num_dias")
    private int numDias;

    @Column(name = "res_total")
    private double total;

    //union con las otras tablas
    @JsonBackReference
    @JoinColumn(name = "alj_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Alojamiento alojamiento;

    @JsonBackReference
    @JoinColumn(name = "usu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    
}
