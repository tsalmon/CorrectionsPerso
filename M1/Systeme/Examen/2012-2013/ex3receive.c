#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>

int main(){
	pid_t pid; int status;
	printf("Lancement du processus %d\n", (int)getpid());
	if((pid = fork()) == 0){
		while(1) sleep(1);
		exit();
	}

	printf("processus fils %d créé\n", (int)pid);
	sleep(10);
	if(kill((pid, 0)) == 1){
		printf("fils %d inaccessible\n", (int)pid);
	} else {
		printf("Envoi du signal SIGUSR1 au processus %d\n", (int)pid);
		kill(pid, SIGUSR1)
	}
	pid = waitpid(pid, &status, 0);
	printf("Status final du fils %d : %d\n", (int)pid, status);
}