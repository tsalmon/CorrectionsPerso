#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/un.h>

#define SOCK_PATH "/var/log/mysyslog"
#define FILE_PATH "var/log/mysyslog.log"

struct log_msg{
  pid_t sender_pid;
  char sender_name[20];
  char message[1024]; 
} log_msg;

int main(void)
{

	int fd;
	struct log_msg logger;
    int s, s2, len;
    socklen_t t;
    struct sockaddr_un local, remote;
    char str[100], ecrire[2024];

    if ((s = socket(AF_UNIX, SOCK_STREAM, 0)) == -1) {
        perror("socket");
        exit(1);
    }

    local.sun_family = AF_UNIX;
    strcpy(local.sun_path, SOCK_PATH);
    unlink(local.sun_path);
    len = strlen(local.sun_path) + sizeof(local.sun_family);
    if (bind(s, (struct sockaddr *)&local, len) == -1) {
        perror("bind");
        exit(1);
    }

    if (listen(s, 5) == -1) {
        perror("listen");
        exit(1);
    }

    if((fd = open(FILE_PATH, O_CREAT | O_WRONLY | O_TRUNC, 0666)) == -1){
    	perror("open");
    	exit(1);
    }
	
	int result = flock(fd, LOCK_SH);
	if(result == -1){
		perror("flock");
		exit(1);
	}

    for(;;) {
        int done, n;
        printf("Waiting for a connection...\n");
        t = sizeof(remote);
        if ((s2 = accept(s, (struct sockaddr *)&remote, &t)) == -1) {
            perror("accept");
            exit(1);
        }

        printf("Connected.\n");

        done = 0;
        do {
            n = recv(s2, &logger, sizeof(struct log_msg), 0);
            if (n <= 0) {
                if (n < 0) perror("recv");
                done = 1;
            }
            sprintf(ecrire,"%s(%d) : %s\n", logger.sender_name, logger.sender_pid, logger.message);
     		write(fd, ecrire, strlen(ecrire));
            if (!done) 
                if (send(s2, &logger, n, 0) < 0) {
                    perror("send");
                    done = 1;
                }
        } while (!done);

        close(s2);
    }

    return 0;
}