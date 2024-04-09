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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "Longitude")
    private Integer Longitude;

    @Column(name = "Latitude")
    private Integer Latitude;

    @Column(name = "Ville")
    private String  Ville;
}
