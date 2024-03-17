
package MODELO;

public class pila_Arbol_expresion {
    
    private Nodo_T_pila ultimo_dato;
    
    public pila_Arbol_expresion(){
         ultimo_dato = null;
    }
    
    public void agregar(Nodo_T_arbol fragmento){
    Nodo_T_pila nuevo = new Nodo_T_pila(fragmento);
    nuevo.siguiente_dato= ultimo_dato;
    ultimo_dato=nuevo;
    }
    
    public boolean pila_vacia(){
    return ultimo_dato ==null;
    }
    
    public Nodo_T_arbol ultimo_dato(){
    return ultimo_dato.dato;
    }
    
    public void vaciar_pila(){
    ultimo_dato= null;
    }
    public void reiniciarpila(){
    ultimo_dato= null;
    }
     public Nodo_T_arbol quitar(){
    Nodo_T_arbol aux = null;
    if(!pila_vacia()){
        aux = ultimo_dato.dato;
        ultimo_dato = ultimo_dato.siguiente_dato;
    }
         return aux;
    }
}
