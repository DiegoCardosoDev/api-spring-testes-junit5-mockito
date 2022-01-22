package com.diegocardoso.apirestjunit.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//estrategia de geração de id
    private Integer id;
    private String name;

    @Column(unique = true)//email é unico
    private String email;
    private String password;


}
