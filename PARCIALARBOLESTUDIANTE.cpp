#include <iostream>
#include <malloc.h>
#include <cstdlib>

using namespace std;

//Estructura Estudiante - Arbol
struct estudiante{
    int codigo;
    char nombre[30];
    char apellido[30];
    int anio;
    int mes;
    int dia;
    estudiante *izq;
    estudiante *der;
};

estudiante *raiz, *aux, *auxC, *aux2C;

int posicionar(){
     if(auxC->codigo < aux2C->codigo){
             if(aux2C->izq!=NULL){
             aux2C = aux2C->izq;
             posicionar();
            }
           else
            aux2C->izq = auxC;
        }
      else if(auxC->codigo > aux2C->codigo){
          if(aux2C->der!=NULL){
              aux2C = aux2C->der;
             posicionar();
            }
          else{
             aux2C->der = auxC;
            }
        }
      return 0;
    }

//Funcion Estudiante - arbol
int RegistrarEstudianteArbol(){
	 int volver = 0;
	 cout<<"Bienvenido al area de registro"<<endl;
	 cout<<"\n";
	
	 do{
	 	auxC = (struct estudiante *) malloc (sizeof(struct estudiante));
	     cout<<"Ingresa el nombre del estudiante: "<<endl;
	     cin>>auxC->nombre;
	     cout<<"\n";
	    
	     cout<<"Ingresa el apellido del estudiante: "<<endl;
	 	 cin>>auxC->apellido;
		 cout<<"\n";
		
		 cout<<"Fecha de nacimiento del estudiante (ingresa solo numeros):"<<endl;
	     system("pause");
		 cout<<"\n";
		
		 cout<<"dia"<<endl;
	     cin>>auxC->dia;
	     cout<<"\n";
	     cout<<"mes"<<endl;
	     cin>>auxC->mes;
	     cout<<"\n";
	     cout<<"anio"<<endl;
	     cin>>auxC->anio;
	     cout<<"\n";
	    
	     cout<<"Ingresa codigo del estudiante "<<endl;
	     cin>>auxC->codigo;
	     cout<<"\n";
	    
	     auxC->izq = auxC->der = NULL;
	       
	     //Agrega como raiz si no hay uno.
	     if(raiz==NULL){
	         raiz = auxC;
	         auxC = NULL;
	         free(auxC);
	        }
	      else{ //si no hay raiz lo manda a posicionar en el lado izq o der.
	         aux2C = raiz;
	         posicionar();
	        }
	    
	     cout<<"\n";
         cout<<"Estudiante Registrado."<<endl;
         system("pause");
         cout<<"Desea registrar otro estudiante? (1.SI/2.NO)";
         cin>>volver;
         cout<<"\n";
        }while(volver!=2);
     return 0;
    }


int inOrden(estudiante *recursive){
	  if(recursive->izq!=NULL){
         inOrden(recursive->izq);
        }
      cout<<"Estudiante: "<<recursive->nombre<<" "<<recursive->apellido<<endl;
      cout<<"Fecha: "<<recursive->anio<<"/"<<recursive->mes<<"/"<<recursive->dia<<endl;
	  cout<<"Codigo: "<<recursive->codigo<<endl;
      cout<<"\n";
      cout<<"----------------------------------------------------------"<<endl;
	  cout<<"\n";
     if(recursive->der!=NULL){
          inOrden(recursive->der);
        }
     return 0;
    }

int preorden(estudiante *recursive){
      cout<<"Estudiante: "<<recursive->nombre<<" "<<recursive->apellido<<endl;
      cout<<"Fecha: "<<recursive->anio<<"/"<<recursive->mes<<"/"<<recursive->dia<<endl;
	  cout<<"Codigo: "<<recursive->codigo<<endl;
	  cout<<"\n";
      cout<<"----------------------------------------------------------"<<endl;
	  cout<<"\n";
      if(recursive->izq!=NULL){
         preorden(recursive->izq);
        }
      if(recursive->der!=NULL){
         preorden(recursive->der);
        }
     return 0;
    }
int postorden(estudiante *recursive){
	  if(recursive!=NULL){
         postorden(recursive->izq);  //enviar lado izquierdo
         postorden(recursive->der); 
         cout<<"Estudiante: "<<recursive->nombre<<" "<<recursive->apellido<<endl;
	     cout<<"Fecha: "<<recursive->anio<<"/"<<recursive->mes<<"/"<<recursive->dia<<endl;
		 cout<<"Codigo: "<<recursive->codigo<<endl;
		 cout<<"\n";
	   	 cout<<"----------------------------------------------------------"<<endl;
		 cout<<"\n";
        } 
     return 0;
}
//RECORRER EL ARBOL 

int recorrer(){
	  int elegir = 0;
    	aux = raiz;
       if(aux!=NULL){
          cout<<"Bienvenido al area de registro"<<endl;
		  cout<<"\n";
		  do{
			  cout<<"Elige el recorrido:"<<endl;
		      cout<<"1. InOrden"<<endl;
	          cout<<"2. PreOrden"<<endl;
	          cout<<"3. PostOrden"<<endl;
	          cout<<"4. salir"<<endl;
		      cin>>elegir;
	          switch (elegir)
	           {
	             case 1: 
					aux = raiz;
					inOrden(aux);
					system("pause"); 
					break;
	             case 2: 
					aux = raiz;
					preorden(aux);
					system("pause");
					break;
	             case 3: 
	            	aux = raiz;
					postorden(aux);
					system("pause");
	            	break;
	             case 4: cout<<"Adios"; break;
	             default:cout<<"Opcion Invalida."; break;
	            }
		    }while(elegir!=4);
        }else{
         cout<<"Vacio..."<<endl;
         cout<<"No se ha registrado estudiantes"<<endl;
         system("pause");
	    }
	 return 0;
    }


struct Estudiante{
    int codigo2;
    char nombre2[30];
    char apellido2[30];
    int anio2;
    int mes2;
    int dia2;
    Estudiante *izq2;
    Estudiante *der2;
};

Estudiante *raiz2, *aux2, *auxF, *aux2F;

int posicionar2(){
     if(auxF->anio2 && auxF->mes2 && auxF->dia2 < aux2F->anio2 && aux2F->mes2 && aux2F->dia2){
             if(aux2F->izq2!=NULL){
             aux2F = aux2F->izq2;
             posicionar2();
            }
           else
            aux2F->izq2 = auxF;
        }
      else if(auxF->anio2 && auxF->mes2 && auxF->dia2 >= aux2F->anio2 && aux2F->mes2 && aux2F->dia2){
          if(aux2F->der2!=NULL){
              aux2F = aux2F->der2;
             posicionar2();
            }
          else{
             aux2F->der2 = auxF;
            }
        }
        return 0;
    }

//Funcion Estudiante - arbol
int RegistrarFecha(){
	 int volver2 = 0;
	 cout<<"Bienvenido al area de registro"<<endl;
	 cout<<"\n";
	
	 do{
	 	 auxF = (struct Estudiante *) malloc (sizeof(struct Estudiante));
	 	
	 	 cout<<"Ingresa codigo del estudiante "<<endl;
	     cin>>auxF->codigo2;
	     cout<<"\n";
	     
	     cout<<"Ingresa el nombre del estudiante: "<<endl;
	     cin>>auxF->nombre2;
	     cout<<"\n";
	    
	     cout<<"Ingresa el apellido del estudiante: "<<endl;
	 	 cin>>auxF->apellido2;
		 cout<<"\n";
		
		 cout<<"Fecha de nacimiento del estudiante (ingresa solo numeros):"<<endl;
	     system("pause");
		 cout<<"\n";
		
		 cout<<"dia"<<endl;
	     cin>>auxF->dia2;
	     cout<<"\n";
	     cout<<"mes"<<endl;
	     cin>>auxF->mes2;
	     cout<<"\n";
	     cout<<"anio"<<endl;
	     cin>>auxF->anio2;
	     cout<<"\n";
	     
	     auxF->izq2 = auxF->der2 = NULL;
	       
	     //Agrega como raiz si no hay uno.
	     if(raiz2==NULL){
	         raiz2 = auxF;
	         auxF = NULL;
	         free(auxF);
	        }
	      else{ //si no hay raiz lo manda a posicionar en el lado izq o der.
	         aux2F = raiz2;
	         posicionar2();
	        }
	        cout<<"\n";
            cout<<"Estudiante Registrado."<<endl;
            system("pause");
            cout<<"Desea registrar otro estudiante? (1.SI/2.NO)";
            cin>>volver2;
            cout<<"\n";
        }while(volver2!=2);
       return 0;
    }


int inOrdenfecha(Estudiante *recursive){
	  if(recursive->izq2!=NULL){
         inOrdenfecha(recursive->izq2);
        }
      cout<<"Estudiante: "<<recursive->nombre2<<" "<<recursive->apellido2<<endl;
      cout<<"Fecha: "<<recursive->anio2<<"/"<<recursive->mes2<<"/"<<recursive->dia2<<endl;
	  cout<<"Codigo: "<<recursive->codigo2<<endl;
      cout<<"\n";
      cout<<"----------------------------------------------------------"<<endl;
	  cout<<"\n";
     if(recursive->der2!=NULL){
          inOrdenfecha(recursive->der2);
        }
        return 0;
    }

int preordenfecha(Estudiante *recursive){
      cout<<"Estudiante: "<<recursive->nombre2<<" "<<recursive->apellido2<<endl;
      cout<<"Fecha: "<<recursive->anio2<<"/"<<recursive->mes2<<"/"<<recursive->dia2<<endl;
	  cout<<"Codigo: "<<recursive->codigo2<<endl;
	  cout<<"\n";
      cout<<"----------------------------------------------------------"<<endl;
	  cout<<"\n";
      if(recursive->izq2!=NULL){
         preordenfecha(recursive->izq2);
        }
      if(recursive->der2!=NULL){
         preordenfecha(recursive->der2);
        }
        return 0;
    }

int postordenfecha(Estudiante *recursive){
	 if(recursive!=NULL){
         postordenfecha(recursive->izq2);  //enviar lado izquierdo
         postordenfecha(recursive->der2); 
         cout<<"Estudiante: "<<recursive->nombre2<<" "<<recursive->apellido2<<endl;
	     cout<<"Fecha: "<<recursive->anio2<<"/"<<recursive->mes2<<"/"<<recursive->dia2<<endl;
		 cout<<"Codigo: "<<recursive->codigo2<<endl;
		 cout<<"\n";
	   	 cout<<"----------------------------------------------------------"<<endl;
		 cout<<"\n";
        } 
        return 0;
    }
    
    


int recorrer2(){
	  int elegir2 = 0;
    	aux2 = raiz2;
       if(aux2!=NULL){
          cout<<"Bienvenido al area de registro"<<endl;
		  cout<<"\n";
		  do{
			  cout<<"Elige el recorrido:"<<endl;
		      cout<<"1. InOrdenfecha"<<endl;
	          cout<<"2. PreOrdenfecha"<<endl;
	          cout<<"3. PostOrdenfecha"<<endl;
	          cout<<"4. salir"<<endl;
		      cin>>elegir2;
	          switch (elegir2)
	           {
	             case 1: 
					aux2 = raiz2;
					inOrdenfecha(aux2);
					system("pause"); 
					break;
	             case 2: 
					aux2 = raiz2;
					preordenfecha(aux2);
					system("pause");
					break;
	             case 3: 
	            	aux2 = raiz2;
					postordenfecha(aux2);
					system("pause");
	            	break;
	             case 4: cout<<"Adios"; break;
	             default:cout<<"Opcion Invalida."; break;
	            }
		    }while(elegir2!=4);
        }else{
         cout<<"Vacio..."<<endl;
         cout<<"No se ha registrado estudiantes"<<endl;
         system("pause");
	    }
	    return 0;
    }


//Menu
int main(){
     int opc = 0;
      do{
         cout<<"\nMenu"<<endl;
         cout<<"1. Registrar Estudiante Codigo"<<endl;
         cout<<"2. Registrar Estudiante Fecha"<<endl;
         cout<<"3. Recorrer arbol (Mostrar datos)"<<endl;
         cout<<"4. Recorrer arbol fecha (Mostrar datos)"<<endl;
         cout<<"5. Salir"<<endl;
         cin>>opc;
         switch (opc)
             {
             case 1: RegistrarEstudianteArbol(); break;
             case 2: RegistrarFecha(); break;
             case 3: recorrer(); break;
             case 4: recorrer2(); break;
             case 5: cout<<"Adios"; break;
             default:cout<<"Opcion Invalida."; break;
            }
        }while(opc!=5);
    }


