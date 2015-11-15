/*把百分制成绩转换为ABC等级制*/
#include"stdio.h"
int main()
{
	int n,m;
	printf("请输入分数：\n");
	scanf("%d",&m);

	switch(m/10)
	{
		case 10:
		case 9:printf("A\n");
			break;
		case 8:printf("B\n");
			break;
		case 7:printf("C\n");
			break;
		case 6:printf("及格\n");
            break;
		case 5:
		case 4:
		case 3:
		case 2:
		case 1:
		case 0:printf("不及格！\n");
	}
    return 0;
}
