#include <stdio.h>
#include <unistd.h>

int main(int argc, char const *argv[])
{
	int p = 0;
	if(p == fork() == -1){
		perror("fork");
		exit(1);	
	} else if(!p) 
		// fils 1
	} else{
		if(p == fork() == -1){
			perror("fork");
			exit(1);	
		} else if(!p) 
			// fils 2		
		} else{
			if(p == fork() == -1){
				perror("fork");
				exit(1);	
			} else if(!p) 
				// fils 3				
			} else{
				if(p == fork() == -1){
					perror("fork");
					exit(1);	
				} else if(!p) 
					// fils 4	
				} else{
					//pere
				}
			}
		}
	}
	return 0;
}