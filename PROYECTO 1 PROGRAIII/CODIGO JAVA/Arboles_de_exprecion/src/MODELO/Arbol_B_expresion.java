
package MODELO;


public class Arbol_B_expresion {
    Nodo_T_arbol raiz;
    public Arbol_B_expresion(){//constructor por defecto
    raiz = null;
    }
    public Arbol_B_expresion(String expresionArit){
    raiz = creacion_arb_Bex(expresionArit);
    }
    public void vaicararborlex(){
    raiz= null;
    }
    
    public void CrearNodo(Object dato){
    raiz = new  Nodo_T_arbol(dato);
    }
    public Nodo_T_arbol Subarbol(Nodo_T_arbol dato2,Nodo_T_arbol dato1,Nodo_T_arbol raizd1yd2 ){
    raizd1yd2.izq=dato1;
    raizd1yd2.der=dato2;
    return raizd1yd2;
    }
    public boolean arbExB_vacio(){
    return raiz ==null;
    }
    // recorridos de arbolres ----------------------------------------------------------------------------------------------------------------------------------------------------------------
    private String preorden(Nodo_T_arbol subarbolpre,String cadena){
    String cadena_de_exprecion;
    cadena_de_exprecion= "";
    if(subarbolpre !=null){
    cadena_de_exprecion=cadena + subarbolpre.dato.toString()+"\n"+preorden(subarbolpre.izq,cadena)+preorden(subarbolpre.der,cadena);
    }
    return cadena_de_exprecion;
    }
    
    private String inorden(Nodo_T_arbol subarbolpre,String cadena){
    String cadena_de_exprecion;
    cadena_de_exprecion= "";
    if(subarbolpre !=null){
    cadena_de_exprecion = cadena + inorden(subarbolpre.izq,cadena)+subarbolpre.dato.toString()+"\n"+inorden(subarbolpre.der,cadena);
    }
    return cadena_de_exprecion;
    }
    
    private String posorden(Nodo_T_arbol subarbolpre,String cadena){
    String cadena_de_exprecion;
    cadena_de_exprecion= "";
    if(subarbolpre !=null){
    cadena_de_exprecion = cadena + posorden(subarbolpre.izq,cadena)+posorden(subarbolpre.der,cadena)+subarbolpre.dato.toString()+"\n";
    }
    return cadena_de_exprecion;
    }
    private int prioridad_de_operacion(char operador){
        int prioridad= 100;
        switch(operador){
            case '^':               
                prioridad = 30;
                break;
            case '/':
            case '*':
                prioridad = 20;
                break;
            case '+':
            case '-':                
                prioridad = 10;
                break;  
            default:
                prioridad=0;
        }           
    return operador;
    }
    
    private boolean siesoperador(char c){
    boolean resultado;
    switch(c){
     case '(':
     case ')':
     case '^': 
     case '*':
     case '/':
     case '+':  
     case '-':    
         resultado = true;
         break;
     default:
         resultado = false;
    }
    return resultado;
    }
    public String toString(int opcion){
        String cadena_de_exprecion="";
    switch(opcion){
        case 0:
            cadena_de_exprecion= preorden(raiz,cadena_de_exprecion);
            break;
        case 1:
            cadena_de_exprecion= inorden(raiz,cadena_de_exprecion);
            break;
        case 2:
            cadena_de_exprecion= posorden(raiz,cadena_de_exprecion);
            break;            
     case 3:
            cadena_de_exprecion= mostrar_arbol(raiz,0);
            break; 
    }
    return cadena_de_exprecion;
    }
    
    
   
    private Nodo_T_arbol creacion_arb_Bex(String cadena){
    pila_Arbol_expresion pilaopredor;
    pila_Arbol_expresion pilaexpresion;
    Nodo_T_arbol validador;
    Nodo_T_arbol op1;
    Nodo_T_arbol op2;
    Nodo_T_arbol op;
    
    pilaopredor = new pila_Arbol_expresion();
    pilaexpresion = new pila_Arbol_expresion();       
    char fragmento_evaluado;
    for(int i=0;i< cadena.length();i++){
    fragmento_evaluado = cadena.charAt(i);
    validador = new  Nodo_T_arbol(fragmento_evaluado);
    if(!siesoperador(fragmento_evaluado)){
    pilaexpresion.agregar(validador);
    }else{
    switch(fragmento_evaluado){
        case '(':
            pilaopredor.agregar(validador);
            break;
        case ')':
            while(!pilaopredor.pila_vacia()&& !pilaopredor.ultimo_dato().dato.equals('(')){
            op2 = pilaexpresion.quitar();
            op1 = pilaexpresion.quitar();
            op = pilaopredor.quitar();
            op = Subarbol(op2,op1,op);
            pilaexpresion.agregar(op);
            }
            break;
        default:
            while(!pilaopredor.pila_vacia()&& prioridad_de_operacion(fragmento_evaluado)<=prioridad_de_operacion(pilaopredor.ultimo_dato().dato.toString().charAt(0))){                                        
             op2 = pilaexpresion.quitar();
            op1 = pilaexpresion.quitar();
            op = pilaopredor.quitar();
            op = Subarbol(op2,op1,op);
            pilaexpresion.agregar(op);
            }
            pilaopredor.agregar(validador);
    }
    }
    
    }
    while(!pilaopredor.pila_vacia()){
            op2 = pilaexpresion.quitar();
            op1 = pilaexpresion.quitar();
            op = pilaopredor.quitar();
            op = Subarbol(op2,op1,op);
            pilaexpresion.agregar(op);
    }
    op = pilaexpresion.quitar();
    
    return op;
    }
     //https://www.youtube.com/watch?v=RDdOnaIZ_70 minuto 23:09
    
    public double evaluaroperacion (){
    return evalua(raiz);
    }
    public double evalua (Nodo_T_arbol subarbol){
    
        double acumulador=0;
        if(!siesoperador(subarbol.dato.toString().charAt(0))){
        return Double.parseDouble(subarbol.dato.toString());
        }else{
        switch(subarbol.dato.toString().charAt(0)){
            case '^':
                acumulador =acumulador + Math.pow (evalua(subarbol.izq),evalua(subarbol.der));
                break;
            case '*':
                acumulador =acumulador + evalua(subarbol.izq)* evalua(subarbol.der);
                break;
            case '/':
                acumulador =acumulador + evalua(subarbol.izq)/ evalua(subarbol.der);
                break;     
            case '+':
                acumulador =acumulador + evalua(subarbol.izq)+ evalua(subarbol.der);
                break; 
            case '-':
                acumulador =acumulador + evalua(subarbol.izq)- evalua(subarbol.der);
                break;     
        }
        }
    return acumulador;    
        
        
    }public static String grafica="";
    public String mostrar_arbol(Nodo_T_arbol arbol,int contador){
       
        
    if(arbol==null){
    //return grafica;
    }else{
    mostrar_arbol(arbol.der,contador+1);
    for(int i=0;i<contador;i++){
        grafica= grafica+"   \n";
    }
    grafica= "  °  "+grafica + arbol.dato.toString(); 
    mostrar_arbol(arbol.izq,contador+1);
    
    }
        return grafica;
    }
    
    
}
