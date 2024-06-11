package di.uniba.it.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
/**
 * Questa classe descrive la rappresentazione dell'inventario.
 */
public class Inventory {

    private List<AdvObject> list = new ArrayList<>();

    /**
     * Questo metodo restituisce il valore della variabile 'list'.
     *
     * @return list
     */
    public List<AdvObject> getList() {
        return list;
    }

    /**
     * Questo metodo serve per modificare il valore della variabile 'list'.
     *
     * @param list La List di String da inserire nella variabile 'list'
     */
    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    /**
     * Questo metodo permette di aggiungere un elemento di classe AdvObject alla
     * List 'list'.
     *
     * @param o L'oggetto di classe AdvObject da aggiungere alla List 'list'
     */
    public void add(AdvObject o) {
        list.add(o);
    }

    /**
     * Questo metodo permette di rimuovere un elemento di classe AdvObject alla
     * List 'list'.
     *
     * @param o L'oggetto di classe AdvObject da rimuovere alla List 'list'
     */
    public void remove(AdvObject o) {
        list.remove(o);
    }
}
