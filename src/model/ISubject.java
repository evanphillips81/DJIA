
package model;

/**
 *
 * @author Evan Phillips
 */
public interface ISubject {
    
    public void register(Object o);
    public void unregister(Object o);
    public void notifyObservers();
}
