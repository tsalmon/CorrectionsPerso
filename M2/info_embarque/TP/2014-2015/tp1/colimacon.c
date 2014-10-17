#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "colimacon.h"

int colimacon(int **array, unsigned int rows, unsigned int columns){
	unsigned int k = 0;
	unsigned int i = 0;

	if(!(rows * columns)){
		perror("empty");
		return 0;
	}

	(* array) = malloc(rows * columns * sizeof(int *));

	if(!array){
		perror("malloc");
		return 0;
	}

	for (i = 0; i < rows * columns; ++i){
		(*array)[i] = 1;
	}

	if(rows == 1 || columns == 1){
		unsigned int a = (rows == 1) ? columns : rows;
		for (i = 0; i < a; ++i){
			(*array)[i] = i+1;
		}	
		return 1;	
	}

	i = 0;
	while(k < rows * columns){
		int a = i ;
		for (; i < a + columns && (*array)[i] == 1; ++i){
			(* array)[i] = ++k;
		}

		for(i = i - 1 + columns; (*array)[i] == 1 && (i <= rows * columns); i+=columns){
			(*array)[i] = ++k;
		}

		for(i = i - 1 - columns; (*array)[i] == 1; i--){
			(*array)[i] = ++k;
		}
		for(i = i - columns + 1; i != 0 && (*array)[i] == 1; i-=columns){
			(*array)[i] = ++k;
		}
		i+=columns;
		i++;
	}
	
	return 1;
}

void print_array(int *array, unsigned int rows, unsigned int columns){
	unsigned int i = 1;
	printf("%d\t", array[0]);
	for (; i < rows * columns; ++i){
		if((i % columns) == 0) {
			printf("\n");
		}
		printf("%d\t", array[i]);
	}
	printf("\n");
}

