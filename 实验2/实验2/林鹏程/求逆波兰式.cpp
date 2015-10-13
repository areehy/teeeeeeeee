#include<iostream>
#include<string.h>
#include <stdlib.h>
using namespace std;
#define maxsize 100
char getdata[maxsize];//输入时，接收数据的数组
char outdata[maxsize]; //输出时，接收数据的数组
char indata[maxsize]; //临时变量，负责接收数字串
double acculate;
char y[]={'+','-','*','/'};//运算符表
int p[maxsize];
/*---------------------------字符栈-----------------------------------------*/
struct stack
{
	char data[maxsize];
	int top;
};
void initstack(stack &s)  //初始化操作
{
  s.top=-1;
}
void initialP()
{
p['#']=0;  //对优先级进行声明
p['+']=p['-']=1;
p['*']=p['/']=2;
}
int push(stack &s,char c)  //入栈操作
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
int pop(stack &s,char c[])  //出栈操作
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
 bool isempty(stack s) //判断栈是否为空
{
	if(s.top==-1)
	  return true;
	else
	  return false;

}
/*---------------------------数字栈-----------------------------------------*/
 struct digit  //数组结构体
 {
	 int top;
	 double data[maxsize];
 };
 void digit_initial(digit &s) //初始化
 {
	 s.top=-1;
 }
 bool digit_empty(digit s)//判断是否为空
 {
	 if(s.top==-1)
		 return 1;
	 else
		 return 0;
 }
 int digit_push(digit &s,double x)//数字结构体入栈操作
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
bool isyun(char c)  //判断是否为运算符
{
	int i;
	for(i=0;i<4;i++)
	    if(y[i]==c)
	      return true;
	return false;
}
void operate1()//输出逆波兰式
{
	stack s;
    initstack(s);
    s.top++;
	s.data[s.top]='#';   //初始化栈顶为#号
	initialP();// 初始化运算符优先级
	cout<<"逆波兰式的生成及计算程序，编制人：林鹏程，105032013070，计本2班"<<"\n";
	cout<<"输入一以#结束的中缀表达式(包括+—*/（）数字#)："<<"\n";
	cin>>getdata;
	char getc[2];  //接收出栈元素
	int len=0;//字符串长度
	int i;
	int j=0;
	for(i=0;i<maxsize;i++)
	{  if(getdata[i]>='0'&&getdata[i]<='9'||getdata[i]=='.')  //输出数字串
	   {
		   indata[j]=getdata[i];
		   j++;
		   if(getdata[i+1]<'0'||getdata[i+1]>'9')
		    {
				indata[j]='\0';
				len=strlen(outdata); //得到outdata的长度
				if(outdata[len-1]>='0'&&outdata[len-1]<='9')//判断前面是否有数字，有的话，以&分开
					strcat(outdata,"&");
				strcat(outdata,indata);
		      j=0;
		    }
      }
      else if(isyun(getdata[i]))//是运算符
      {
		  if(p[getdata[i]]>p[s.data[s.top]]||s.data[s.top]=='(') //当前运算符优先级大于栈顶元素
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
	  else if(getdata[i]=='(')//处理"("
	  {
	  	push(s,getdata[i]);
	  }
	  else if(getdata[i]==')') //遇到")"的处理
     {
         while(s.data[s.top]!='(')
         {   pop(s,getc);
		     strcat(outdata,getc);
          //   printf("%c",getc);
         }
         s.top--;//向前移一位；之后自然覆盖"("
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
void operate2()//逆波兰式值的计算
{
	digit  s;
	digit_initial(s);
	int i;
	int j=0;
	int len;
	char tdata[maxsize];
	double temp1,temp2,temp3;//从字符到数字转换的临时接收变量
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
		 	;//什么都不做
		else if(isyun(outdata[i]))//如果是运算符
		{
		    digit_pop(s,temp2);
			digit_pop(s,temp1);
		if(outdata[i]=='*')//如果是双目运算符乘号
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
	cout<<"逆波兰式:";
	cout<<outdata<<"\n";
	cout<<"--------------------------------------------------------------------"<<"\n";
	cout<<"计算的值为：";
	cout<<acculate<<"\n";
 }

int main()
{
	operate1();
	operate2();
	output();
	return 0;
}
