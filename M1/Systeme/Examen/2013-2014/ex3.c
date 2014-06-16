#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <limits.h>

void ex3(const int cond, const char *file){
	int fd, i;
	char *projection;
	struct stat finfo;

	if ((fd = open (file, O_RDWR)) < 0){
		perror("open");
		exit(EXIT_FAILURE);
	}

	if (fstat (fd,&finfo) < 0){
		perror("fstat");
		if(close(fd)){
			perror("close");
		}
		exit(EXIT_FAILURE);
	}

	if ((projection = mmap(0, finfo.st_size, PROT_READ | 
											PROT_WRITE, MAP_SHARED, fd, 0))
		== MAP_FAILED){
		perror("mmap");
		if(close(fd)){
			perror("close");
		}
		exit(EXIT_FAILURE);
	}

	for(i = 0; i < finfo.st_size; i++){
		projection[i] = projection[i] + 13 + 26 * (cond * -1) % 256;
	}

	if(munmap((void *)projection, finfo.st_size)){
    	perror("munmap");
	    if(close(fd)){
    		perror("close");
	    }
   		exit(EXIT_FAILURE);
    }
    if(close(fd)){
    	perror("close");
    	exit(EXIT_FAILURE);
    }
}

int main(int argc, char const *argv[]){
	if(argc == 2 && !(argv[1][0] == '-')){
		ex3(0, argv[1]);
	} else if(argc == 3 && argv[1][0] == '-' && argv[1][1] == 'u' && argv[1][2] == '\0'){
		ex3(1, argv[2]);
	} else {
		printf("rot13 [-u] <filename>\n");
	}
	return 0;
}