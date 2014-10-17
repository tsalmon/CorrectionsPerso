CC=gcc
CFLAGS=-W -Wall -ansi -pedantic
LDFLAGS= -g
EXEC=colimacon.exe
SRC:=$(wildcard *.c)
SRC:=$(filter-out test.c, $(SRC))
OBJ=$(SRC:.c=.o) 

all:$(EXEC)

$(EXEC): $(OBJ)
	$(CC) $(LDFLAGS) -o $@ $^ $(CFLAGS)

%.o: %.cpp
	$(CC) $(LDFLAGS) -o $@ -c $< $(CFLAGS) 

.PHONY: clean mrproper

clean:
	rm -f *.o

mrproper: clean
	rm -f colimacon.exe	