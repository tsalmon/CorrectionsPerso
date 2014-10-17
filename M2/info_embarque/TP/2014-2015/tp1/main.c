
#include <stdio.h>
#include "colimacon.h"

int main(){	
	int row = 8;
	int col = 8;
	int *t;
	colimacon(&t, row, col);
	print_array(t, row, col);
}

