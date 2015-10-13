#include<iostream>
using namespace std;
#define N 5  //�������
#define M 3  //������Դ
bool Finish[N];//���̱�ע
int Allocation[N][M]; //---->��ά����
int Request[N][M];//��������
int Max[N][M];  //���������---->��ά����
int ava[M];  //ʣ����---> һά����
int nee[N][M];//---->  ��ά����
int work[M];
int workth[N];//===--��ȫ���� 
int p[N][M]; //��̬��work 
void setfirst(bool x);
bool security();
void setback();
void outprint();
void initial()  //��ÿ����Դ���и���ʼֵ��finish[i]��Ϊfalse 
{
	for(int i=0;i<N;i++)
	{
		Finish[i]=false;
	}
	
}
void need(int w[N][M],int a[N][M])  //����һ��ʼ��������
{
	int i;
	int j;
	for(i=0;i<N;i++)
		for(j=0;j<M;j++)
			nee[i][j]=w[i][j]-a[i][j];
}
bool bankist(int num,int type1,int type2,int type3)// x,y,z,�ֱ���������Դ��������
{
	if(type1<=nee[num][0]&&type2<=nee[num][1]&&type3<=nee[num][2])
	{	if(type1<=ava[0]&&type2<=ava[1]&&type3<=ava[2])
     	{
		     setfirst(1);
          if(!security())//ִ�а�ȫ���㷨
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
void setfirst(bool x)  //����̽�Ű���Դ��������� 
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
void setback()  //��������ڰ�ȫ�����У�������֮ǰ�Ĳ��� 
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
	int n=0;//��ȫ��������ļ�¼ 
	r=false;
	for(j=0;j<M;j++)
		work[j]=ava[j];   
	initial();//--->��ʼ��fin����Ϊfalse
    for(i=0;i<N;)
	{
		u=true;
		if(!Finish[i])
		{
			for(j=0;j<M;j++)
				if(nee[i][j]>work[j]) //��ȫ���㷨����һ�ж� 
					u=false;
			if(u)            //�������һ���㣬���������� 
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
	for(i=0;i<N;i++)     //�ж��Ƿ���ڰ�ȫ������ 
		if(!Finish[i])
			return false;
	return true;
}
void outprint()  //������ 
{
	int i;
	int j;
	printf("������\t work \t Need \t Allocation \t work+Allocation \tFinish\n");
	printf("\t A B C \t A B C \t A B C \t\t A B C\n");
	for(i=0;i<N;i++)          //�����ʽ���� 
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
	printf("����������ݣ�(����������Դ����������Ϣ���Լ�������Ϣ)\n");
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
	need(Max,Allocation);//��������
	r=bankist(num,type[0],type[1],type[2]);
	if(r)
		outprint();
   else
 	  printf("ϵͳ���벻��ȫ״̬,��ʱϵͳ��������Դ\n");
	return 0;
}