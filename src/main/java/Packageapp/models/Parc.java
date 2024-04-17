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


    @JoinColumn(name = "geoloc_ID", referencedColumnName = "ID")
    private Long geoloc_ID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Integer getNbtot() {
        return nbtot;
    }

    public void setNbtot(Integer nbtot) {
        this.nbtot = nbtot;
    }

    public Integer getNbdispo() {
        return nbdispo;
    }

    public void setNbdispo(Integer nbdispo) {
        this.nbdispo = nbdispo;
    }

    public Long getGeoloc_ID() {
        return geoloc_ID;
    }

    public void setGeoloc_ID(Long geoloc_ID) {
        this.geoloc_ID = geoloc_ID;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    @Column(name = "statut")
    private Boolean statut;




}
