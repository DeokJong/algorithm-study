#include <stdio.h>
#include <stdlib.h>

int main(){
    int coe,num,maxNum=0;
    double totalNum=0;
    scanf("%d",&coe);
    double* scoreArr=(double*)malloc(sizeof(double)*coe);
    
    for(int i =0;i<coe;i++){
        scanf("%d",&num);
        scoreArr[i]=num;
        if(num>maxNum) maxNum=num;
    }
    
    for(int i =0;i<coe;i++){
        scoreArr[i]=(scoreArr[i]/maxNum)*100;
        totalNum+=scoreArr[i];
    }
    printf("%lf",totalNum/coe);
    
    return 0;
}