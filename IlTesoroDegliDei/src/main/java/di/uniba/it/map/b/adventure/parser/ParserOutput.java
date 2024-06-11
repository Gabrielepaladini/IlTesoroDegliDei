/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.parser;

import di.uniba.it.map.b.adventure.type.AdvObject;
import di.uniba.it.map.b.adventure.type.Command;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class ParserOutput {

    //variabile che rappresenta un comando
    private Command command;
    //variabile che rappresenta un oggetto
    private AdvObject object;
    //variabile che rappresenta un oggetto contenuto nell'inventario
    private AdvObject invObject1;
    //variabile che rappresenta un oggetto contenuto nell'inventario
    private AdvObject invObject2;

    //costruttore con parametri command e object
    //richiamo quando viene coinvolto un oggetto presente nell'ambiente
    public ParserOutput(Command command, AdvObject object) {
        this.command = command;
        this.object = object;
    }

    //costruttore con parametri command, object e invObject
    //richiamato quando vengono coinvolti un oggetto dell'ambiente ed uno dell'inventario
    public ParserOutput(Command command, AdvObject object, AdvObject invObject) {
        this.command = command;
        this.object = object;
        this.invObject1 = invObject;
    }

    //costruttore con parametri command, invObject1, invObject2
    //richiamato quando vengono coinvolti due oggetti nell'inventario
    /*public ParserOutput(AdvObject invObject1, AdvObject invObject2, Command command) {
        this.command = command;
        this.invObject1 = invObject1;
        this.invObject2 = invObject2;
    }*/

    //metodo che restituisce il valore della variabile 'command'
    public Command getCommand() {
        return command;
    }

    //metodo che permette di modificare il valore della variabile 'command'
    public void setCommand(Command command) {
        this.command = command;
    }

    //metodo che restituisce il valore della variabile 'object'
    public AdvObject getObject() {
        return object;
    }

    //metodo che permette di modificare il valore della variabile 'object'
    public void setObject(AdvObject object) {
        this.object = object;
    }

    //metodo che restituisce il valore della variabile 'invObject1'
    /*public AdvObject getInvObject1() {
        return invObject1;
    }

    //metodo che permette di modificare il valore della variabile 'invObject1'
    public void setInvObject1(AdvObject invObject) {
        this.invObject1 = invObject;
    }

    //metodo che restituisce il valore della variabile 'invObject2'
    public AdvObject getInvObject2() {
        return invObject2;
    }

    //metodo che permette di modificare il valore della variabile 'invObject2'
    public void setInvObject2(AdvObject invObject) {
        this.invObject2 = invObject;
    }*/

}
