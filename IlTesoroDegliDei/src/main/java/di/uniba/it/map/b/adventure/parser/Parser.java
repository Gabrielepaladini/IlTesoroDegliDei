/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.parser;

import di.uniba.it.map.b.adventure.type.AdvObject;
import di.uniba.it.map.b.adventure.type.Command;
import di.uniba.it.map.b.adventures.Utils;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class Parser {

    //insieme di elementi di tipo string che rappresentano le parole da ignorare durante l'analisi del testo
    private final Set<String> stopwords;

    //costruttore con parametro 'stopwords'
    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    //metodo che verifica se il token passato come parametro compare nella lista dei comandi e ne restituisce l'indice
    private int checkForCommand(String token, List<Command> commands) {
        if (token == null || commands == null || commands.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equalsIgnoreCase(token) || commands.get(i).getAlias() != null && commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    //metodo che verifica se il token passato come parametro compare nella lista degli oggetti
    //se nella lista degli oggetti sono presenti 'container' ricerca il token anche nella lista degli oggetti contenuti nei container
    private int checkForObject(String token, List<AdvObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getName().equals(token) || objects.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {

        List<String> tokens = Utils.parseString(command, stopwords);

        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);
            if (ic > -1) {
                if (tokens.size() > 2) {
                    int io1 = checkForObject(tokens.get(1), objects);
                    int io2 = checkForObject(tokens.get(2), objects);
                    int ioinv1 = -1; //indice oggetto1 inventario
                    int ioinv2 = -1; //indice oggetto2 inventario

                    // Controllo se i primi due oggetti sono nell'inventario
                    if (io1 < 0) {
                        io1 = checkForObject(tokens.get(1), inventory);
                        if (io1 < 0 && tokens.size() > 3) {
                            io1 = checkForObject(tokens.get(2), inventory);
                        }
                        ioinv1 = io1;  //l'indice dell'oggetto nell'inventario viene assegnato a ioinv
                        io1 = -1; //indice dell'oggetto nell'elenco degli oggetti viene impostato su -1
                        //assicura che venga scelto correttamente l'oggetto dall'inventario anziché dall'elenco degli oggetti.
                    }
                    if (io2 < 0) {
                        io2 = checkForObject(tokens.get(2), inventory);
                        if (io2 < 0 && tokens.size() > 3) {
                            io2 = checkForObject(tokens.get(3), inventory);
                        }
                        ioinv2 = io2;
                        io2 = -1;
                    }

                    if (io1 > -1 && io2 > -1 && ioinv1 < 0 && ioinv2 < 0) {
                        // Restituisce il comando/oggetto1/oggetto2 corrispondenti
                        return new ParserOutput(commands.get(ic), objects.get(io1), objects.get(io2));
                    } else if (io1 > -1 && ioinv2 > -1 && ioinv1 < 0) {
                        // Restituisce il comando/oggetto1/oggetto2 nell'inventario corrispondenti
                        return new ParserOutput(commands.get(ic), objects.get(io1), inventory.get(ioinv2));
                    } else if (ioinv1 > -1 && io2 > -1 && ioinv2 < 0) {
                        // Restituisce il comando/oggetto1 nell'inventario/oggetto2 corrispondenti
                        return new ParserOutput(commands.get(ic), inventory.get(ioinv1), objects.get(io2));
                    } else if (ioinv1 > -1 && ioinv2 > -1) {
                        // Restituisce il comando/oggetto1 nell'inventario/oggetto2 nell'inventario corrispondenti
                        return new ParserOutput(commands.get(ic), inventory.get(ioinv1), inventory.get(ioinv2));
                    } else {
                        // Restituisce solo il comando/oggetto1 null/oggetto2 null
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else if (tokens.size() > 1) {
                    int io = checkForObject(tokens.get(1), objects);
                    int ioinv = -1;

                    // Controllo se l'oggetto è nell'inventario
                    if (io < 0) {
                        io = checkForObject(tokens.get(1), inventory);
                        if (io < 0 && tokens.size() > 2) {
                            io = checkForObject(tokens.get(2), inventory);
                        }
                        ioinv = io;
                        io = -1;
                    }

                    if (io > -1 && ioinv < 0) {
                        // Restituisce il comando/oggetto corrispondenti/oggetto nell'inventario è inesistente (null)
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    } else if (ioinv > -1) {
                        // Restituisce il comando/oggetto nell'inventario corrispondente/l'oggetto è inesistente (null)
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        // Restituisce solo il comando/oggetto null/oggetto nell'inventario null
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    // Restituisce solo il comando/oggetto null/oggetto nell'inventario null
                    return new ParserOutput(commands.get(ic), null, null);
                }
            } else {
                // Comando non trovato
                return new ParserOutput(null, null, null);
            }
        } else {
            // Input dell'utente vuoto
            return null;
        }
    }
}
