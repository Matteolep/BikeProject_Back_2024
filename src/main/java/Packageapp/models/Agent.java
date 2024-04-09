package Packageapp.models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Agent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "Disponibilité")
    private Boolean IsBusy;

    @Column(name = "Nom")
    private String Nom;

    @Column(name = "prenom")
    private String prenom;



}
