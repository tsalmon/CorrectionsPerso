#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <limits.h>

void safeclose(int pipe){
    if(close(pipe) == -1){
        perror("pipe");
        exit(1);
    }
}

void safedup2(int pipe, int st){
    if(dup2(pipe, st) == -1){
        perror("dup2");
        exit(1);
    }
}

void safepipe(int *p){
    if(pipe(p) == -1){
        perror("pipe");
        exit(1);
    }
}

void signal_handler(int signal_id){
    if(signal_id == SIGSEGV){
        char *c = "Lecture à l'adresse nulle interdite.\n";
        write(STDERR_FILENO, c, strlen(c));
        exit(EXIT_FAILURE);
    } else {
        char *c = "Ctrl^C intercepté\n";
        write(STDERR_FILENO, c, strlen(c));
    }
}

int main(){
    int pipeA[2];
    int pipeB[2];


    struct sigaction sa;

    memset(&sa, 0, sizeof(sa));
    sa.sa_handler = signal_handler;
    sa.sa_flags = 0;
    sigemptyset(&(sa.sa_mask));
    if(sigaction(SIGSEGV, &sa, NULL) != 0){
        perror("sigaction");
        exit(EXIT_FAILURE);
    }
    if(sigaction(SIGINT, &sa, NULL) != 0){
        perror("sigaction");
        exit(EXIT_FAILURE);
    }

    safepipe(pipeA);
    safepipe(pipeB);

    int a = fork();
    if (a == 0) {
        int b = fork();
        if (b == 0) {    
            safeclose(pipeA[1]);
            safeclose(pipeA[0]);

            safedup2(pipeB[1], 1); 

            safeclose(pipeB[1]);
            safeclose(pipeB[0]);

            execlp("grep", "grep", "user" , "/etc/passwd", (char *)0);
        } else {
            safedup2(pipeB[0], 0);
            safedup2(pipeA[1], 1); // chaining std_out to pipes_out

            safeclose(pipeA[1]);
            safeclose(pipeA[0]);
            safeclose(pipeB[1]);
            safeclose(pipeB[0]);

            execlp("cut", "cut", "-f", "5" , "-d", ":", (char *) 0);
        }
    } else {
        safeclose(pipeB[1]);
        safeclose(pipeB[0]);

        safedup2(pipeA[0], 0);

        safeclose(pipeA[0]);
        safeclose(pipeA[1]);

        execlp("tr", "tr", "a-z", "A-Z", NULL);
    }
    return 0;
}