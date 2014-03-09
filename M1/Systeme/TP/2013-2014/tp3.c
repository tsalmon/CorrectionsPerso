/*
  Note du correcteur : je n'ai pas fais la question 2.3
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> 
#include <sys/wait.h>
#include <sys/types.h>
#include <string.h>

void test_exec(char *cmd, char *args[]){
  if(execvp(cmd, args) == -1){  
    perror("execv");
    exit(1);
  }
}

void ex1(int argc, char *argv[]){
  char **exo_arg;
  int i = 2;
  pid_t p;

  if(argc != 2){
    puts("trop d'arguments");
    return ;
  }

  if((exo_arg = malloc(sizeof(char *) * (argc))) == NULL){
    perror("malloc");
    exit(1);
  }
  exo_arg[0] = argv[0];
  for(; i < argc; i++){
    exo_arg[i-1] = argv[i];
  }
  exo_arg[argc - 1] = NULL;
  
  for(i = 0; i < argc -1; i++){
    printf("exo_arg[%d] = %s\n", i, exo_arg[i]);
  }
  

  if((p = fork()) == -1){
    perror("fork");
    exit(1);
  } else if (p == 0){
    test_exec("ls", exo_arg);
  } else {
    if(wait(NULL) == -1){
      perror("wait");
      exit(1);
    }
  }
  free(exo_arg);
}

void ex2(int argc, char *argv[]){
  char **exo_arg;
  int i;
  pid_t p;
  char *cmd;
  if(argc < 3){
    puts("need argument <bin/<cmd>>");
    return ;
  }
  i = strlen(argv[2]) + 1;
  if((cmd = malloc(i + 1)) == NULL){
    perror("malloc");
    exit(1);
  }
  cmd[i] = '\0';
  snprintf(cmd, i, "%s", argv[2]);
  if((exo_arg = malloc(sizeof(char *) * (argc - 1))) == NULL){
    perror("malloc");
    exit(1);
  }
  for(i = 2; i < argc; i++){
    exo_arg[i-2] = argv[i];
  }
  exo_arg[i-2] = NULL;
  exo_arg[0] = cmd;
  if((p = fork()) == -1){
    perror("fork");
    exit(1);
  } else if (p == 0){
    test_exec(cmd, exo_arg);
  } else {
    if(wait(NULL) == -1){
      perror("wait");
      exit(1);
    }
  }
  free(cmd);
  free(exo_arg);
}

void ex3(int argc, char *argv[]){
  int status;
  char *arg[] = {NULL};
  if(argc < 4 || argc > 5){
    puts("need arguments <condition> <cmd> [<cmd>]");
    return ;
  }
  switch(fork()){
  case -1:
    perror("fork");
    exit(1);
  case 0:
    execlp(argv[2], "", NULL);
    exit(0);
  default:
    if(wait(&status) == -1){
      perror("wait");
      exit(1);
    }
    if(status == 256 && argc == 5){
      execlp(argv[4], "", NULL);
    } else if(status == 0){
      execlp(argv[3], "", NULL);
    }
  }
}

int main(int argc, char *argv[]){
  
  if(argc < 2){
    puts("need argument <1,2,3,... (exercice)>");
    return 0;
  }
  if(!strcmp(argv[1], "1")){
    ex1(argc, argv);
  } else if(!strcmp(argv[1], "2")){
    ex2(argc, argv);
  } else if(!strcmp(argv[1], "3")){
    ex3(argc, argv);
  } else {
    puts("exercice doesn't exist");
  }
  return 0;
}
