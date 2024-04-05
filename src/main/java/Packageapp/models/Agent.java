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

    @Column(name = "Nom")
    private String Name;
    private Boolean IsBusy;


}
