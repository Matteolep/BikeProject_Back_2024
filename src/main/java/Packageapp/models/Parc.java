package Packageapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "park")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "nbtot")
    private Integer nbtot;

    @Column(name = "nbdispo")
    private Integer nbdispo;

    @ManyToOne
    @JoinColumn(name = "geoloc_ID", referencedColumnName = "ID")
    private Geoloc geoloc_ID;

    @Column(name = "statut")
    private Boolean statut;




}
