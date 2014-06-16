#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
 
#define INITIAL_STOCK   20
#define NB_CLIENTS      2
 
 
/* Structure stockant les informations des threads clients et du magasin. */
typedef struct
{
   char buffer[INITIAL_STOCK];
 
   pthread_t thread_stock;
   pthread_t thread_clients [NB_CLIENTS];
 
   pthread_mutex_t mutex_stock;
   pthread_cond_t cond_stock;
   pthread_cond_t cond_clients;
}
stock_t;
 
static stock_t stock = {
   .mutex_stock = PTHREAD_MUTEX_INITIALIZER,
   .cond_stock = PTHREAD_COND_INITIALIZER,
   .cond_clients = PTHREAD_COND_INITIALIZER,
};
 
 
/* Fonction pour le thread du magasin. */
static void * fn_clients (void * p_data){
	int k = 0;
	while (1){
		pthread_mutex_lock (& stock.mutex_stock);
		pthread_cond_wait (& stock.cond_stock, & stock.mutex_stock);

		for(k = 0; k < INITIAL_STOCK && stock.buffer[k] != '\0'; k++){
		}
		printf("lecture d'un char = %c(%d)\n", stock.buffer[k-1], k);
		stock.buffer[k-1] = '\0';
		 

		pthread_cond_signal (& stock.cond_clients);
		pthread_mutex_unlock (& stock.mutex_stock);
	} 
	return NULL;
}
 
static void * fn_store (void * p_data){
	int nb = (int) p_data;
	int i = 0, k = 0;
	char lecteur;

	while(1){
	  	while((lecteur = getchar()) != '\n'){
	    	pthread_mutex_lock (& stock.mutex_stock); 

	    	for(k = 0; k < INITIAL_STOCK && stock.buffer[k] != '\0'; k++){
	    	}
    		stock.buffer[k] = lecteur;
	    	printf("%d = %c\n", k, stock.buffer[k]);
	    	pthread_cond_broadcast (&stock.cond_stock);

	    	for(k = 1; k < NB_CLIENTS; k++){
	    		pthread_cond_wait (&stock.cond_clients, & stock.mutex_stock);
	 		}

	    	pthread_mutex_unlock (&stock.mutex_stock);
		}
 	}
   return NULL;
}
  
int main (void){
   int i = 0;
   int ret = 0;

	for(i = 0; i < INITIAL_STOCK; i++){
		stock.buffer[i] = '\0';
	}   
 
   /* Creation des threads. */
   ret = pthread_create (
      &stock.thread_stock, NULL,
      fn_store, NULL
   );
 
   if (! ret){
      for (i = 0; i < NB_CLIENTS; i++){
         ret = pthread_create (
            & stock.thread_clients [i], NULL,
            fn_clients, &i);
 
        if (ret){
        	perror("pthread_create");
        }
      }
   }
   else{
    	perror("pthread_create");
   }
 
 
   /* Attente de la fin des threads. */
   i = 0;
   for (i = 0; i < NB_CLIENTS; i++){
      pthread_join (stock.thread_clients [i], NULL);
   }
   pthread_join (stock.thread_stock, NULL);
   return EXIT_SUCCESS;
}