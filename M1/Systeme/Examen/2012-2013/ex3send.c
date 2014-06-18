#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

void hand(int sig){
	switch(sig){
		case SIGUSR1: printf("SIGUSR1\n"); return;
		case SIGUSR2: printf("SIGUSR2\n"); return;
		default: printf("AUTRE\n");
	}
}

int main(){
	pid_t pid; int status;
	printf("Lancement du processus %d\n", (int)getpid());
	if((pid = fork()) == 0){
		struct sigaction action;
		action.sa_handler = hand;
		sigaction(SIGUSR1, &action, NULL);
		sigaction(SIGUSR2, &action, NULL);
		while(1) sleep(1);
		exit(1);
	}

	printf("processus fils %d créé\n", (int)pid);
	sleep(10);
	if(kill(pid, 0) == -1){
		printf("fils %d inaccessible\n", (int)pid);
	} else {
		printf("Envoi du signal SIGUSR1 au processus %d\n", (int)pid);
		kill(pid, SIGUSR1);
	}
	pid = waitpid(pid, &status, 0);
	printf("Status final du fils %d : %d\n", (int)pid, status);
}