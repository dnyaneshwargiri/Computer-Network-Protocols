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
if(argc<2)
error("usage  port");
FILE *fptr;
socklen_t clilen;
char buffer[1024*12];
char buf[7];
char data[1024*12];

int sockfd,n,size;
struct sockaddr_in serv_addr,cli_addr;

sockfd=socket(AF_INET,SOCK_DGRAM,IPPROTO_UDP);
if (sockfd<0)
	{
		error("socket");
	}


memset((char *)&serv_addr,0,sizeof(serv_addr));



serv_addr.sin_family=AF_INET;
serv_addr.sin_port=htons(atoi(argv[1]));
serv_addr.sin_addr.s_addr=INADDR_ANY;


n=bind(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr));


if (n<0)
	{
		error("bind");
	}

clilen=sizeof(cli_addr);
//bzero(buffer,1024*6);
recvfrom(sockfd,buffer,20,0,(struct sockaddr *)&cli_addr,&clilen);
printf("File Name is : \n%s",buffer);

for(int i=0;i<7;i++)
buf[i]=buffer[i];

fptr=fopen(buf,"r");
if(fptr==NULL)
error("Unable to open file");
fseek(fptr,0,SEEK_END);
size=ftell(fptr);

sendto(sockfd,&size,sizeof(int),0,(struct sockaddr *)&cli_addr,clilen);

fseek(fptr,0,SEEK_SET);

fread(&data,size,1,fptr);

sendto(sockfd,data,size,0,(struct sockaddr *)&cli_addr,clilen);

/*fread(&data,sizeof(data),1,fptr);

//printf("File contents : %s",data);

sendto(sockfd,data,1024*6,0,(struct sockaddr *)&cli_addr,clilen);

sendto(sockfd,data,1024*6,0,(struct sockaddr *)&cli_addr,clilen);*/








return 0;
}
