package Packageapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Entity(name = "Intervention")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;


    @JoinColumn(name = "Agent_ID", referencedColumnName = "ID")
    private Long Agent_ID;


    @JoinColumn(name = "parc_id", referencedColumnName = "ID")
    private Long Parc_ID;



}
