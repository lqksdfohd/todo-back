package org.ahmed.todolist.entite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "to_id_generator")
    @TableGenerator(name = "to_id_generator", table = "ID_TABLE", pkColumnName = "ID", pkColumnValue = "TODO_ID"
            ,valueColumnName = "NEXT_ID", initialValue = 100)
    private Integer id;

    @Column(name = "TITRE")
    private String titre;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "FAITE")
    private boolean faite;
}
