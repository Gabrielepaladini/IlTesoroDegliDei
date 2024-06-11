/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.type;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public enum CommandType {

    NORTH, SOUTH, EAST, WEST, //usato dall'utente per indicare la direzione in cui spostarsi
    USE, //adottato dall'utente per far uso di un elemento
    PRESS, //usato dall'utente per premere un elemento
    OPEN, //usato dall'utente per aprire un elemento
    LOOK, //adottato dall'utente per guardare l'ambiente circorstante
    EXAMINE, //adottato dall'utente per esaminare un elemento
    PICK_UP, //usato dall'utente per raccogliere un elemento    
    INSERT, //usato dall'utente per inserire un elemento 
    READ; //usato per leggero il contenuto di un elemento    
}
