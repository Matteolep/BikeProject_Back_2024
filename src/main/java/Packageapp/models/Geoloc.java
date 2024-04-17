package Packageapp.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Geoloc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Geoloc",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"Longitude", "Latitude"})
        })
public class Geoloc {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;


    @Setter
    @Column(name = "Longitude")
    private Double Longitude;

    @Setter
    @Column(name = "Latitude")
    private Double Latitude;

    @Column(name = "nom")
    private String  Nom;
}
