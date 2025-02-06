#include <iostream>
using namespace std;

int max5(int a);

int main()
{
	int kg;
	cin >> kg;

	if (kg == 4 || kg < 3|| max5(kg) == -1)
	{
		cout << "-1";
		exit(0);
	}
	

	cout << max5(kg)+(kg-(max5(kg)*5))/3;
	
	
	

	return 0;
}

int max5(int a)
{
	if (a % 5 == 0)
	{
		return a / 5;
	}
	if (a < 0)
	{
		return -1;
	}

	return max5(a - 3);
}
