/*
Problem Statement: To implement client-server calculator.
*/

/*
	Client Side
	Please pass ip address and port no as command line arguments in the same sequence.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 
#include<ctype.h>

void error(const char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
    int sockfd, portno, n;
    struct sockaddr_in serv_addr;
    struct hostent *server;

    char buffer[1024];
    if (argc < 3)
    {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }
    portno = atoi(argv[2]);
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) 
        error("ERROR opening socket");
    server = gethostbyname(argv[1]);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char *)server->h_addr, 
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    serv_addr.sin_port = htons(portno);
    if (connect(sockfd,(struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0) 
        error("ERROR connecting");
  
  
    int num1 ;
    int num2 ;
    int ans;
    int choice;
    
    
    
  S:bzero(buffer,256);						
    n = read(sockfd,buffer,255);				//Read Server String
    if (n < 0) 
         error("ERROR reading from socket");
    printf("Server - %s\n",buffer);
    scanf("%d" , &num1);						//Enter No 1
    write(sockfd, &num1, sizeof(int));     			//Send No 1 to Server
	
	
	
    bzero(buffer,256);						
    n = read(sockfd,buffer,255);				//Read Server String
    if (n < 0) 
         error("ERROR reading from socket");
    printf("Server - %s\n",buffer);
    scanf("%d" , &num2);						//Enter No 2
    write(sockfd, &num2, sizeof(int));     			//Send No 2 to Server
	
	
    	
    bzero(buffer,256);						
    n = read(sockfd,buffer,255);				//Read Server String
    if (n < 0) 
         error("ERROR reading from socket");
    printf("Server - %s",buffer);
    scanf("%d" , &choice);						//Enter choice
    write(sockfd, &choice, sizeof(int));     			//Send choice to Server

	if (choice == 5)
	goto Q;

    read(sockfd , &ans , sizeof(int));				//Read Answer from Server
    printf("Server - The answer is : %d\n" , ans);		//Get Answer from server
	
    if(choice != 5)
    	goto S;
    	
Q:  printf("You chose to exit, Thank You so much.");
     	
       
   
    
    close(sockfd);
    return 0;
}


/*
Server side terminal:
jj@ubuntu:~/Desktop$ gcc Server.c -o Server
jj@ubuntu:~/Desktop$ ./Server 4562
Client - Number 1 is : 50
Client - Number 2 is : 110
Client - Choice is : 1
Client - Number 1 is : 50
Client - Number 2 is : 60
Client - Choice is : 2
Client - Number 1 is : 4
Client - Number 2 is : 8
Client - Choice is : 3
Client - Number 1 is : 66
Client - Number 2 is : 11
Client - Choice is : 4
Client - Number 1 is : 5
Client - Number 2 is : 7
Client - Choice is : 5
jj@ubuntu:~/Desktop$ 


Client side Terminal:

jj@ubuntu:~/Desktop$ gcc client.c -o client
jj@ubuntu:~/Desktop$ ./client 192.168.244.139 4562
Server - Enter Number 1
50
Server - Enter Number 2
110
Server - Enter your choice : 
1.Addition
2.Subtraction
3.Multiplication
4.Division
5.Exit
1
Server - The answer is : 160
Server - Enter Number 1
50
Server - Enter Number 2
60
Server - Enter your choice : 
1.Addition
2.Subtraction
3.Multiplication
4.Division
5.Exit
2
Server - The answer is : -10
Server - Enter Number 1
4
Server - Enter Number 2
8
Server - Enter your choice : 
1.Addition
2.Subtraction
3.Multiplication
4.Division
5.Exit
3
Server - The answer is : 32
Server - Enter Number 1
66
Server - Enter Number 2
11
Server - Enter your choice : 
1.Addition
2.Subtraction
3.Multiplication
4.Division
5.Exit
4
Server - The answer is : 6
Server - Enter Number 1
5
Server - Enter Number 2
7
Server - Enter your choice : 
1.Addition
2.Subtraction
3.Multiplication
4.Division
5.Exit
5
You chose to exit, Thank You so much.jj@ubuntu:~/Desktop$

*/


