#include <stdio.h>
#include <stdlib.h>

#define container_of(ptr, type, member) ({ const typeof( ((type *)0)->member ) *__mptr = (ptr); (type *)( (char *)__mptr - offsetof(type, member) );})
#define list_for_each_entry(cur, head, member);

typedef struct list_head{
	int i;
	struct list_head* prev;
	struct list_head* next;
} list_head;

int k = 0;

void INIT_LIST_HEAD (struct list_head *head);
void list_add (struct list_head *node, struct list_head *head);
void list_del (struct list_head *node);

void INIT_LIST_HEAD (struct list_head *head){
	head->i = k++;
	head->prev = head;
	head->next = head;
}

void list_add (struct list_head *node, struct list_head *head){
	node->next = head->next;
	node->prev = head;
	head->next->prev = node;
	head->next = node;
}

void list_del (struct list_head *node){
	node->prev->next = node->next;
	node->next->prev = node->prev;
}

int main(){
	struct list_head liste1;
	struct list_head liste2;
	struct list_head liste3;
	struct list_head liste4;
	struct list_head liste5;

	INIT_LIST_HEAD(&liste1);
	INIT_LIST_HEAD(&liste2);
	INIT_LIST_HEAD(&liste3);
	INIT_LIST_HEAD(&liste4);
	INIT_LIST_HEAD(&liste5);

	list_add(&liste2, &liste1);
	list_add(&liste3, &liste1);
	list_add(&liste4, &liste1);
	list_add(&liste5, &liste1);

	list_del(&liste3);

	printf("%d %d %d %d %d\n", liste1.i, liste1.next->i, liste1.next->next->i, liste1.next->next->next->i, liste1.next->next->next->next->i);
	printf("%d %d %d %d %d\n", liste1.i, liste5.i, liste4.i, liste3.i, liste2.i);
}