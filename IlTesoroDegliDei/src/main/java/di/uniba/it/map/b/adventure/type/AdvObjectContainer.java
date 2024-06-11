/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class AdvObjectContainer extends AdvObject {

    //lista che contiene item di tipo 'AdvObject' che rappresentano gli item contenitori nel gioco
    private List<AdvObject> cont_list = new ArrayList();

    //costruttore con parametro id
    public AdvObjectContainer(int id) {
        super(id);
    }

    //costruttore con parametri id e nome
    public AdvObjectContainer(int id, String name) {
        super(id, name);
    }

    //costruttore con parametri id, nome e descrizione
    public AdvObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    //costruttore con parametri id, nome, descrizione e alias
    public AdvObjectContainer(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

    //metodo che restituisce il valore della variabile 'cont_list'
    public List<AdvObject> getList() {
        return cont_list;
    }

    //metodo che permette di modificare il valore della variabile 'list'
    public void setList(List<AdvObject> cont_list) {
        this.cont_list = cont_list;
    }

    //metodo che permette di inserire in 'cont_list' un item di tipo 'AdvObject'
    public void addObject(AdvObject o) {
        cont_list.add(o);
    }

    //metodo che permette di rimuovere un item di tipo 'AdvObject' da 'cont_list'
    public void removeObject(AdvObject o) {
        cont_list.remove(o);
    }

}
