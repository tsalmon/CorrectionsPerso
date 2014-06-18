#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv){
	int n = 6;
	for (int i = 0; i < n; ++i){
		if(!fork()){
			break;
		}
	}
}