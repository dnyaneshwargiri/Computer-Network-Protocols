#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<arpa/inet.h>
#include<sys/socket.h>

#define BUFLEN 503
#define PORT 8885

void die(char *s)
{
	perror(s);
	exit(1);
}

int main(void)
{
	struct sockaddr_in si_me, si_other;
	
	int s, i,j, slen = sizeof(si_other) , recv_len;
	char buf[BUFLEN],data[BUFLEN];
        FILE *fout;
	
	if ((s=socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1)
	{
		die("socket");
	}
	
	memset((char *) &si_me, 0, sizeof(si_me));
	
	si_me.sin_family = AF_INET;
	si_me.sin_port = htons(PORT);
	si_me.sin_addr.s_addr = htonl(INADDR_ANY);
	
	if( bind(s , (struct sockaddr*)&si_me, sizeof(si_me) ) == -1)
	{
		die("bind");
	}
	
        recvfrom(s, buf, 20, 0, (struct sockaddr *) &si_other, &slen);
        fout=fopen(buf,"r");
        fread(&data,sizeof(data),1,fout);
        printf("%s",data);

        sendto(s,data,503,0,(struct sockaddr *) &si_other,slen);
          close(s);
	//shutdown(s, SHUT_WR);
	return 0;
}
