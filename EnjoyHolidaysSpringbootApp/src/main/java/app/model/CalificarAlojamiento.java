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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "calificaralojamiento")
public class CalificarAlojamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cal_id")
    private Long id;

    @Column(name = "cal_puntuacion")
    private double puntuacion;

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
