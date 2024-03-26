package Packageapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Intervention")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "Agent_ID", referencedColumnName = "ID")
    private Agent Agent_ID;

    @ManyToOne
    @JoinColumn(name = "Parc", referencedColumnName = "ID")
    private Parc Parc_ID;



}
