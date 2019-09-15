#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<sys/wait.h>
#include<netinet/in.h>

void error(char *s)
{
	perror(s);
	exit(1);
}

int main(int argc ,char * argv[])

{
 
if(argc<3)
error("usage ip port");
socklen_t clilen;
FILE *fptr;
char buffer[1024*12];
struct sockaddr_in servaddr_in, cli_addr;
int sockfd,n,size;

sockfd=socket(AF_INET,SOCK_DGRAM,IPPROTO_UDP);

if (sockfd<0)
	{
		error("socket");
	}



memset((char *)&cli_addr,0,sizeof(cli_addr));


cli_addr.sin_family=AF_INET;
cli_addr.sin_port=htons(atoi(argv[2]));


n=inet_aton(argv[1], &cli_addr.sin_addr);


if (n==0)
	{
		error("no such host");
	}


clilen=sizeof(cli_addr);
printf("Enter fie name");
fgets(buffer,20,stdin);
sendto(sockfd,buffer,20,0,(struct sockaddr *)&cli_addr,clilen);

recvfrom(sockfd,&size,sizeof(int),0,(struct sockaddr *)&cli_addr,&clilen);

printf("File size is : %d",size);


recvfrom(sockfd,buffer,size,0,(struct sockaddr *)&cli_addr,&clilen);

fptr=fopen("rp.png","w");

fwrite(buffer,size,1,fptr);

//recvfrom(sockfd,buffer,1024*6,0,(struct sockaddr *)&cli_addr,&clilen);

//printf("File contents : %s",buffer);


close(sockfd);

return 0;}
