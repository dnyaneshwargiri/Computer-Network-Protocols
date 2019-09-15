#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<strings.h>
//#include<error.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<sys/wait.h>
#include<unistd.h>
#include<netinet/in.h>
#include<netdb.h>
#include<ctype.h>


void error(char *msg)
{
perror(msg);
exit(1);
}

int main(int argc ,char *argv[])
{
if(argc<3)
error("usage ip port");

int sockfd,n;
struct hostent *server;
struct sockaddr_in cli_addr;
sockfd=socket(AF_INET,SOCK_STREAM,0);

if(sockfd<0)
error("socket error");

server=gethostbyname(argv[1]);
if(server==NULL)
error("NO such host");

bzero((char *)&cli_addr,sizeof(cli_addr));

cli_addr.sin_family=AF_INET;
cli_addr.sin_port=htons(atoi(argv[2]));

bcopy((char*)server->h_addr,(char*)&cli_addr.sin_addr.s_addr,server->h_length);


n=connect(sockfd,(struct sockaddr *)&cli_addr,sizeof(cli_addr));
if(n<0)
error("Connect error ");

char buffer[256];
while(1)
{
bzero(buffer,256);
printf("Client : Enter ur msg -");
fgets(buffer,256,stdin);
write(sockfd,buffer,strlen(buffer));

bzero(buffer,256);
read(sockfd,buffer,256);
printf("Client : server says -%s",buffer);
}
close(sockfd);
return 0;

}

