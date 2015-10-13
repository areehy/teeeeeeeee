#include<iostream>
#include<string>
using namespace std;
typedef struct{
	int pagenumber;
	int mark;
	int blocknumber;
	int modify;
	string location;
}_pagetable;
typedef struct{
	string operate;
	int page_number1;
	string page1_interaddress;
	int page_number2;
	string page2_interaddress;
}_order;
int k=1;
int fifo_or_optimal=1;
int old=0;
int list[3]={0,1,2};
int find=0;
int chose[3]={0,1,2};
void coutpagetable(_pagetable pagetable[6]){
	cout<<"页号"<<"  "<<"标志"<<"  "<<"主存块号";
	cout<<"  "<<"修改标志"<<"  "<<"磁盘位置"<<endl;
	for(int i=0;i<6;i++){
		cout<<pagetable[i].pagenumber;
		cout<<"      "<<pagetable[i].mark;
		cout<<"      "<<pagetable[i].blocknumber;
		cout<<"         "<<pagetable[i].modify;
		cout<<"       "<<pagetable[i].location;
		cout<<endl;
	}
}
void modifypagetable(int i,int j,_pagetable pagetable[6]){
	pagetable[i].mark=1;
	pagetable[i].modify=k;
	pagetable[i].blocknumber=pagetable[j].blocknumber;
	pagetable[j].location=pagetable[i].location;
	pagetable[i].location="NULL";
	pagetable[j].blocknumber=-1;
	pagetable[j].modify=k;
	pagetable[j].mark=0;
	coutpagetable(pagetable);
}//这里是当页面调入调出时执行的操作
void FIFO(int i,_pagetable pagetable[6]){
	modifypagetable(i,list[old],pagetable);
	k++;
	list[old]=i;
	if(old!=2)
		old++;
	else
		old=0;

}
void compare_chose(int i){
	for(int j=0;j<3;j++){
		if(i==chose[j])
			find=j;
	}
}
void reflesh_find(int i,_order order[5],int type){
	if(type==1)
		compare_chose(order[i].page_number2);
	for(i=i+1;i<5;i++){
		compare_chose(order[i].page_number1);
		compare_chose(order[i].page_number2);
	}
}

int getblocknumber(int pagenumber,_pagetable pagetable[6]){
	int i=pagenumber;
	if(pagetable[i].mark==1)
		return pagetable[i].blocknumber;
	else{
		if(fifo_or_optimal==1)
			FIFO(i,pagetable);
		else
			cout<<"wrong input!=.="<<endl;
		return pagetable[i].blocknumber;
	}
}

void perform(int i,_order order[5],_pagetable pagetable[6]){
	reflesh_find(i,order,1);
	int i1=getblocknumber(order[i].page_number1,pagetable);
	reflesh_find(i,order,2);
	int i2=getblocknumber(order[i].page_number2,pagetable);
	cout<<"物理表达式"<<endl;
	cout<<"("<<i1<<order[i].page1_interaddress<<")";
	cout<<order[i].operate;
	cout<<"("<<i2<<order[i].page2_interaddress<<")"<<endl;
}
void main(){
	_pagetable pagetable[6]={
		{0,1,3,0,"NULL"},
		{1,1,4,0,"NULL"},
		{2,1,5,0,"NULL"},
		{3,0,-1,0,"030"},
		{4,0,-1,0,"025"},
		{5,0,-1,0,"026"}
	};
	_order order[5]={
		{"+",0,"030",2,"003"},
		{"-",1,"050",2,"005"},
		{"*",2,"001",5,"004"},
		{"/",3,"007",1,"031"},
		{"读",4,"034",3,"025"}
	};


	for(int i=0;i<5;i++){
		perform(i,order,pagetable);
	}
	int az;
	cin>>az;
}