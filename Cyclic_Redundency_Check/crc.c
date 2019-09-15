#include<stdio.h>
#include<string.h>
# define N strlen(g)
 
char t[28],cs[28],g[]="110011";
int a,e,c;
 
void xor(){
    for(c = 1;c < N; c++)
    cs[c] = (( cs[c] == g[c])?'0':'1');
}
 
void crc(){
    for(e=0;e<N;e++)
        cs[e]=t[e];
    do{
        if(cs[0]=='1')
            xor();
        for(c=0;c<N-1;c++)
            cs[c]=cs[c+1];
        cs[c]=t[e++];
    }while(e<=a+N-1);
}
 
int main()
{
    printf("\nEnter data : ");
    scanf("%s",t);
 
    printf("\nGeneratng polynomial : %s",g);
    a=strlen(t);
    for(e=a;e<a+N-1;e++)
        t[e]='0';
   
    printf("\nModified data is : %s",t);
   
    crc();
    printf("\nChecksum is : %s",cs);
    for(e=a;e<a+N-1;e++)
        t[e]=cs[e-a];
    printf("\nFinal codeword is : %s",t);
       
        return 0;
}


