#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void print_array(int *array, unsigned int rows, unsigned int columns);
int colimacon(int **array, unsigned int rows, unsigned int columns);


int colimacon(int **array, unsigned int rows, unsigned int columns){
	int k = 0;
	int i = 0;
	int j = 0;

	(* array) = malloc(rows * columns * sizeof(int *));

	if(!array){
		perror("malloc");
		return 0;
	}

	for (i = 0; i < rows * columns; ++i){
		(*array)[i] = 1;
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
	printf("%d\t", array[0]);
	for (int i = 1; i < rows * columns; ++i){
		if((i % columns) == 0) {
			printf("\n");
		}
		printf("%d\t", array[i]);
	}
	printf("\n");
}

void f(int **a){
	*a = malloc(sizeof(int));
	*(*a) = 10;
}

int main(){	
	//int row = 8;
	//int col = 8;
	//int *t;
	//colimacon(&t, row, col);
	//print_array(t, row, col);
	int *a;
	f(&a);
	printf("a=%d\n", *a);
}