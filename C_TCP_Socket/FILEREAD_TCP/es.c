#include <stdio.h>
#include <stdlib.h>
#include <error.h>
#include <string.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/wait.h>
#define MYPORT 6005 /*well know port */
#define BACKLOG 10 /* how many pending connection queue will hold */
#define MAXLINE 10000
void  str_echo(int new_fd)
{
       /* int  n,i;
        char line[MAXLINE],line1[MAXLINE];
      	read(new_fd,line,MAXLINE);
        printf("client line was:  %s\n",line);
       	printf("Enter msg :");
	gets(line1);
        write(new_fd,line1,sizeof(line1));*/

     FILE *fptr;
     int  n,i,a,b;
      char line[MAXLINE],line1[MAXLINE],ch;

      read(new_fd,line,MAXLINE);
      printf("File name is :  %s\n",line);

      fptr=fopen(line, "r");

if (fptr == NULL)
    {
        printf("Cannot open file \n");
        exit(0);
    }

   ch = fgetc(fptr);
   i=0;

    while (ch != EOF)

    {
        line1[i]=ch;
        printf ("%c", ch);

        ch = fgetc(fptr);
        i++;

    }

    

send(new_fd,line1,strlen(line1),0);

fclose(fptr);



	 }
main()
{
int sockfd , new_fd ; /* listen on sock_fd ,new connection on new_fd */
struct sockaddr_in my_addr; /* my address */
struct sockaddr_in their_addr; 
int clilen;
int childpid;
if ((sockfd = socket (AF_INET, SOCK_STREAM ,0))==-1)
{
perror("soket");
exit(1);
}
my_addr.sin_family= AF_INET;
my_addr.sin_port= htons(MYPORT);
my_addr.sin_addr.s_addr= htonl(INADDR_ANY);
bzero(&(my_addr.sin_zero),8);
if (bind(sockfd, (struct sockaddr *)&my_addr,sizeof(struct sockaddr)) == -1){
perror("bind");
exit(1);
}
if(listen(sockfd,BACKLOG) == -1) {
perror("listen");
exit(1);
}
for( ; ; )
{
clilen =  sizeof(their_addr);
if ((new_fd =accept(sockfd, (struct sockaddr*)&their_addr,&clilen)) == -1)
{
perror("accept");
continue;
}
	
      str_echo(new_fd);
      close(new_fd);	

	
               

   }
}
  

