#include<iostream>
#include<string.h>
#include <stdlib.h>
using namespace std;
#define maxsize 100
char getdata[maxsize];//����ʱ���������ݵ�����
char outdata[maxsize]; //���ʱ���������ݵ�����
char indata[maxsize]; //��ʱ����������������ִ�
double acculate;
char y[]={'+','-','*','/'};//�������
int p[maxsize];
/*---------------------------�ַ�ջ-----------------------------------------*/
struct stack
{
	char data[maxsize];
	int top;
};
void initstack(stack &s)  //��ʼ������
{
  s.top=-1;
}
void initialP()
{
p['#']=0;  //�����ȼ���������
p['+']=p['-']=1;
p['*']=p['/']=2;
}
int push(stack &s,char c)  //��ջ����
{
	if(s.top==maxsize-1)
	  return 0;
	 else
	 {
	 s.top++;
	 s.data[s.top]=c;
	 return 1;
     }
}
int pop(stack &s,char c[])  //��ջ����
{
	if(s.top==-1)
	  return 0;
	else
	{
		c[0]=s.data[s.top];
		c[1]='\0';
		s.top--;
		return 1;

	}

 }
 bool isempty(stack s) //�ж�ջ�Ƿ�Ϊ��
{
	if(s.top==-1)
	  return true;
	else
	  return false;

}
/*---------------------------����ջ-----------------------------------------*/
 struct digit  //����ṹ��
 {
	 int top;
	 double data[maxsize];
 };
 void digit_initial(digit &s) //��ʼ��
 {
	 s.top=-1;
 }
 bool digit_empty(digit s)//�ж��Ƿ�Ϊ��
 {
	 if(s.top==-1)
		 return 1;
	 else
		 return 0;
 }
 int digit_push(digit &s,double x)//���ֽṹ����ջ����
 {
	 if(s.top==maxsize-1)
		 return 0;
	 else
	 {
		 s.top++;
		 s.data[s.top]=x;
		 return 1;
	 }
 }
 int digit_pop(digit &s,double &x)
 {
	 if(s.top==-1)
		 return 0;
	 else
	 {
		 x=s.data[s.top];
		 s.top--;
		 return 1;
	 }
 }
 /*--------------------------------------------------------------------------------*/
bool isyun(char c)  //�ж��Ƿ�Ϊ�����
{
	int i;
	for(i=0;i<4;i++)
	    if(y[i]==c)
	      return true;
	return false;
}
void operate1()//����沨��ʽ
{
	stack s;
    initstack(s);
    s.top++;
	s.data[s.top]='#';   //��ʼ��ջ��Ϊ#��
	initialP();// ��ʼ����������ȼ�
	cout<<"�沨��ʽ�����ɼ�������򣬱����ˣ������̣�105032013070���Ʊ�2��"<<"\n";
	cout<<"����һ��#��������׺���ʽ(����+��*/��������#)��"<<"\n";
	cin>>getdata;
	char getc[2];  //���ճ�ջԪ��
	int len=0;//�ַ�������
	int i;
	int j=0;
	for(i=0;i<maxsize;i++)
	{  if(getdata[i]>='0'&&getdata[i]<='9'||getdata[i]=='.')  //������ִ�
	   {
		   indata[j]=getdata[i];
		   j++;
		   if(getdata[i+1]<'0'||getdata[i+1]>'9')
		    {
				indata[j]='\0';
				len=strlen(outdata); //�õ�outdata�ĳ���
				if(outdata[len-1]>='0'&&outdata[len-1]<='9')//�ж�ǰ���Ƿ������֣��еĻ�����&�ֿ�
					strcat(outdata,"&");
				strcat(outdata,indata);
		      j=0;
		    }
      }
      else if(isyun(getdata[i]))//�������
      {
		  if(p[getdata[i]]>p[s.data[s.top]]||s.data[s.top]=='(') //��ǰ��������ȼ�����ջ��Ԫ��
      	   {  push(s,getdata[i]);
		  }
      	else
		{
			while(p[getdata[i]]<=p[s.data[s.top]]&&s.data[s.top]!='('&&s.data[s.top]!='#')
      	  {
			  pop(s,getc);
			  strcat(outdata,getc);
      	    //  cout<<getc;
	      }
		  push(s,getdata[i]);
		}
	  }
	  else if(getdata[i]=='(')//����"("
	  {
	  	push(s,getdata[i]);
	  }
	  else if(getdata[i]==')') //����")"�Ĵ���
     {
         while(s.data[s.top]!='(')
         {   pop(s,getc);
		     strcat(outdata,getc);
          //   printf("%c",getc);
         }
         s.top--;//��ǰ��һλ��֮����Ȼ����"("
     }
	 else if(getdata[i]=='#')
	 {
	 	while(s.top!=0)
	 	 {
	 	 pop(s,getc);
		 strcat(outdata,getc);
	 	// printf("%c",getc);
	    }
	  //  printf("\n");
	    break;
	 }
  }
}
void operate2()//�沨��ʽֵ�ļ���
{
	digit  s;
	digit_initial(s);
	int i;
	int j=0;
	int len;
	char tdata[maxsize];
	double temp1,temp2,temp3;//���ַ�������ת������ʱ���ձ���
	char c[maxsize];
	len=strlen(outdata);
	for(i=0;i<len;i++)
	{
		if(outdata[i]>='0'&&outdata[i]<='9')
		{
			  tdata[j]=outdata[i];
              j++;
			if(outdata[i+1]<'0'||outdata[i+1]>'9')
			{
				tdata[j]='\0';
				digit_push(s,atof(tdata));
				j=0;
			}

		}
		else if(outdata[i]=='&')
		 	;//ʲô������
		else if(isyun(outdata[i]))//����������
		{
		    digit_pop(s,temp2);
			digit_pop(s,temp1);
		if(outdata[i]=='*')//�����˫Ŀ������˺�
          {

				temp3=temp1*temp2;
				digit_push(s,temp3);
			}
			else if(outdata[i]=='/')
			{

				temp3=temp1/temp2;
				digit_push(s,temp3);
			}
			else if(outdata[i]=='-')
			{

				temp3=temp1-temp2;
				digit_push(s,temp3);

			}
			else if(outdata[i]=='+')
			{
				temp3=temp1+temp2;
				digit_push(s,temp3);
			}
		}
	}
	digit_pop(s,acculate);
}
void output()
{

	cout<<"--------------------------------------------------------------------"<<"\n";
	cout<<"�沨��ʽ:";
	cout<<outdata<<"\n";
	cout<<"--------------------------------------------------------------------"<<"\n";
	cout<<"�����ֵΪ��";
	cout<<acculate<<"\n";
 }

int main()
{
	operate1();
	operate2();
	output();
	return 0;
}
