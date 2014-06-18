#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

#define BUFFER_SIZE 12

int main(int argc, char const *argv[])
{
	char c[BUFFER_SIZE];
	int i = 0;
	
	dup2(2, 1);	
	
	while((i = read(0, c, BUFFER_SIZE)) > 0){
		if(!fork()){
			c[i-1] = '\0'; // supprime le \n
			execlp(c, c, (char *)0);
		} else {
			wait(&i);

		}
	}
	return 0;	
}