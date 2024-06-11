/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
package di.uniba.it.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Command {

    private final CommandType type; //nome associato al comando
    private final String name; //nome associato al comando
    private Set<String> alias; //insieme di tutti i possibili sinonimi del comando

    //costruttore con parametri tipo e nome
    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    //costruttore con parametri tipo, nome e variante
    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    //metodo che restituisce il valore della variabile 'name'
    public String getName() {
        return name;
    }

    //metodo che restituisce il valore della variabile 'alias'
    public Set<String> getAlias() {
        return alias;
    }

    //metodo che permette di modificare il valore della variabile 'alias'
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    //metodo che permette di modificare il valore della variabile 'alias'
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    //metodo che restituisce il valore della variabile 'type'
    public CommandType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.alias);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Command other = (Command) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return Objects.equals(this.alias, other.alias);
    }

}
