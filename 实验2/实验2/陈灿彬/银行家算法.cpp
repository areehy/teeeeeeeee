#include<iostream>
using namespace std;
#define N 5  //五个进程
#define M 3  //三类资源
bool Finish[N];//进程备注
int Allocation[N][M]; //---->二维数组
int Request[N][M];//请求向量
int Max[N][M];  //最大需求量---->二维数组
int ava[M];  //剩余量---> 一维数组
int nee[N][M];//---->  二维数组
int work[M];
int workth[N];//===--安全序列 
int p[N][M]; //动态的work 
void setfirst(bool x);
bool security();
void setback();
void outprint();
void initial()  //对每个资源进行赋初始值，finish[i]都为false 
{
	for(int i=0;i<N;i++)
	{
		Finish[i]=false;
	}
	
}
void need(int w[N][M],int a[N][M])  //计算一开始各需求量
{
	int i;
	int j;
	for(i=0;i<N;i++)
		for(j=0;j<M;j++)
			nee[i][j]=w[i][j]-a[i][j];
}
bool bankist(int num,int type1,int type2,int type3)// x,y,z,分别是三类资源的需求量
{
	if(type1<=nee[num][0]&&type2<=nee[num][1]&&type3<=nee[num][2])
	{	if(type1<=ava[0]&&type2<=ava[1]&&type3<=ava[2])
     	{
		     setfirst(1);
          if(!security())//执行安全性算法
		  {
			setback();
			return false;
	      }
	    }
		 else
	     	 return false; 
	}
	else
		return false;
	return true;
}
void setfirst(bool x)  //先试探着把资源分配给进程 
{
	int i;
	int j;
	if(x)
		for(i=0;i<N;i++)
			for(j=0;j<M;j++)
			{
				ava[j]=ava[j]-Request[i][j];
				Allocation[i][j]=Allocation[i][j]+Request[i][j];
				nee[i][j]=nee[i][j]-Request[i][j];
			}

}
void setback()  //如果不存在安全性序列，则重置之前的操作 
{
	int i;
	int j;
    for(i=0;i<N;i++)
		for(j=0;j<M;j++)
		{
			ava[j]=ava[j]+Request[i][j];
			Allocation[i][j]=Allocation[i][j]-Request[i][j];
			nee[i][j]=nee[i][j]+Request[i][j];
		}

}
bool security()
{
	int i;
	int j;
	int u;
	int r;
	int k;
	int n=0;//安全序列数组的记录 
	r=false;
	for(j=0;j<M;j++)
		work[j]=ava[j];   
	initial();//--->初始化fin数组为false
    for(i=0;i<N;)
	{
		u=true;
		if(!Finish[i])
		{
			for(j=0;j<M;j++)
				if(nee[i][j]>work[j]) //安全性算法条件一判断 
					u=false;
			if(u)            //如果条件一满足，进入条件二 
			{ 
				for(j=0;j<M;j++)
				{
					p[i][j]=work[j]; 
					work[j]=work[j]+Allocation[i][j];
				}
				Finish[i]=true;
				workth[n]=i;
				n++;
				r=true;
			}
		}
		if(i==N-1)
		{	
			i=0;
		   if(!r)
		    	break;
		   r=false;
		}
		else
		    i++;	
	}
	for(i=0;i<N;i++)     //判断是否存在安全性序列 
		if(!Finish[i])
			return false;
	return true;
}
void outprint()  //输出语句 
{
	int i;
	int j;
	printf("进程名\t work \t Need \t Allocation \t work+Allocation \tFinish\n");
	printf("\t A B C \t A B C \t A B C \t\t A B C\n");
	for(i=0;i<N;i++)          //输出格式控制 
	{	  
		printf("P%d\t ",workth[i]);
		for(j=0;j<M;j++)
			printf("%d ",p[workth[i]][j]);
		printf("\t ");
		for(j=0;j<M;j++)
			printf("%d ",nee[workth[i]][j]);
		printf("\t ");
		for(j=0;j<M;j++)
			printf("%d ",Allocation[workth[i]][j]);
		printf("\t\t ");
		for(j=0;j<M;j++)
			printf("%d ",p[workth[i]][j]+Allocation[workth[i]][j]);
		if(i<N-1)
		printf("\t\t\t");
		else
		printf("\t\t");
		if(Finish[workth[i]])
			printf("true\n");
		else
			printf("false\n");
	}
}
int main()
{
	int i;
	int j;
	int num,type[M];
	int r;
	printf("输入测试数据：(包括现有资源数，进程信息，以及请求信息)\n");
	for(i=0;i<M;i++)
		scanf("%d",&ava[i]);
	for(i=0;i<N;i++)
	{
		for(j=0;j<M;j++)
			scanf("%d",&Max[i][j]);
		for(j=0;j<M;j++)
			scanf("%d",&Allocation[i][j]);
	}
	scanf("%d",&num);
	for(i=0;i<M;i++)
		scanf("%d",&type[i]);
	for(i=0;i<M;i++)
		Request[num][i]=type[i];
	need(Max,Allocation);//计算需求
	r=bankist(num,type[0],type[1],type[2]);
	if(r)
		outprint();
   else
 	  printf("系统进入不安全状态,此时系统不分配资源\n");
	return 0;
}