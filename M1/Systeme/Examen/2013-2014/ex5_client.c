#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>

#define SOCK_PATH "echo_socket"

struct log_msg{
  pid_t sender_pid;
  char sender_name[20];
  char message[1024]; 
} log_msg;

int main(int argc, char **argv){

  if(argc != 2){
    printf("need argument : user name\n");
    return 0;
  }  

  int s, len;
  struct log_msg logger, logger2;
  socklen_t t;
  struct sockaddr_un remote;
  char str[100];

  if ((s = socket(AF_UNIX, SOCK_STREAM, 0)) == -1) {
      perror("socket");
      exit(1);
  }

  remote.sun_family = AF_UNIX;
  strcpy(remote.sun_path, SOCK_PATH);
  len = strlen(remote.sun_path) + sizeof(remote.sun_family);
  if (connect(s, (struct sockaddr *)&remote, len) == -1) {
      perror("connect");
      exit(1);
  }

  sprintf(logger.sender_name, "%s", argv[1]);
  logger.sender_pid = getpid();

  while(printf("> "), fgets(str, 100, stdin), !feof(stdin)) {

      sprintf(logger.message, "%s", str);

      if (send(s, &logger, sizeof(struct log_msg), 0) == -1) {
          perror("send");
          exit(1);
      }

      if ((t=recv(s, &logger2, 100, 0)) > 0) {
          str[t] = '\0';
          printf("reçu> %s", logger2.message);
      } else {
          if (t < 0) perror("recv");
          else printf("Server closed connection\n");
          exit(1);
      }
  }

  close(s);

    return 0;
}