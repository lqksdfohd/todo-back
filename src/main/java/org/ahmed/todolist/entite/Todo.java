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
    private Integer faite;

    @Transient
    private Accompli accompli;

    public void setAccompli(Accompli accompli){
        if(accompli == Accompli.TRUE){
            this.accompli = Accompli.TRUE;
            this.faite = 1;
        }else{
            this.accompli = Accompli.FALSE;
            this.faite = 0;
        }
    }
    @PostLoad
    public void miseEnPlaceApresChargement(){
        if(faite == 1){
            accompli = Accompli.TRUE;
        }else{
            accompli = Accompli.FALSE;
        }
    }
}
