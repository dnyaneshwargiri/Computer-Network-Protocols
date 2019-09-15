#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<strings.h>
//#include<error.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<sys/wait.h>
#include<netinet/in.h>
#include<unistd.h>
#include<ctype.h>
#define BACKLOG 5
void error(char *msg)
{
perror(msg);
exit(1);
}

int main(int argc ,char *argv[])
{
if(argc<2)
error("usage  port");

pid_t child_pid;
socklen_t clilen;
int sockfd,newsockfd,n;

struct sockaddr_in serv_addr,cli_addr;
sockfd=socket(AF_INET,SOCK_STREAM,0);
if(sockfd<0)
error("socket error");
bzero((char *)&serv_addr,sizeof(serv_addr));

serv_addr.sin_family=AF_INET;
serv_addr.sin_port=htons(atoi(argv[1]));
serv_addr.sin_addr.s_addr=INADDR_ANY;

n=bind(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr));
if(n<0)
error("Binding error");

n=listen(sockfd,BACKLOG);

if(n<0)
error("Listening error ");

while(1)
{


clilen=sizeof(cli_addr);

newsockfd=accept(sockfd,(struct sockaddr *)&cli_addr,&clilen);
if(newsockfd<0)
error("Accepting error ");

   if((child_pid=fork())==0)
   {close(sockfd);


char buffer[256];
while(1)
{
bzero(buffer,256);

read(newsockfd,buffer,256);
printf("Server : Client says -%s",buffer);

if(strncmp(buffer,"bye",3)==0)
{error("Server : bye"); break;}

printf("Server : Enter ur msg -");
fgets(buffer,256,stdin);
write(newsockfd,buffer,strlen(buffer));



}

   }
}






close(newsockfd);
close(sockfd);

/*INADDR_ANY INADDR_ANY INADDR_ANY INADDR_ANY INADDR_ANY INADDR_ANY*/
return 0;
}
