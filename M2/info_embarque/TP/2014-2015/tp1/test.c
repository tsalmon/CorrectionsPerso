#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <time.h>
#include "colimacon.h"

double timeColimacon(unsigned int rows, unsigned int columns, int print){
	int *array;
	double start, stop;

	struct timeval  tv ;
	
	gettimeofday(&tv, NULL);	
	start = (tv.tv_usec);

	if(colimacon(&array, rows, columns) == 0){
		perror("colimacon");
    	return -1;
	}
		
	gettimeofday(&tv, NULL);	
	stop = (tv.tv_usec);

	if(print){
		print_array(array, rows, columns);
	}

	free(array);

	return stop - start;
}

/**
 *	main
 */
int main(){

	unsigned int rows,columns;
	double res;

	rows=1; columns=1;
	res = timeColimacon(rows,columns,1);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 
	
	rows=1; columns=10;
	res = timeColimacon(rows,columns,1);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 

	rows=10; columns=1;
	res = timeColimacon(rows,columns,1);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 

	rows=10; columns=10;
	res = timeColimacon(rows,columns,1);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 
	
	rows=7; columns=7;
	res = timeColimacon(rows,columns, 1);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 

	rows=9; columns=10;
	res = timeColimacon(rows,columns, 1);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 

	rows=10; columns=9;
	res = timeColimacon(rows,columns, 1);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 

	rows=100; columns=100;
	res = timeColimacon(rows,columns, 0);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res); 

	rows=1000; columns=1000;
	res = timeColimacon(rows,columns, 0);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res);

	rows=10000; columns=10000;
	res = timeColimacon(rows,columns, 0);
	printf("Temps en millisecondes d'exécution pour colimacon %d %d : %f sec\n",rows,columns,res);

	return 0;
}
