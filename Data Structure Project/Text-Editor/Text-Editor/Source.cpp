#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "TextEditor.h"

using namespace std;

void displayItems(int* arr , TextEditor t) {
	
	for (int i = 0; i < t.items; i++) {
		if (arr[i] != -1)
			cout << arr[i] << endl;
	}
}

int main()
{
    TextEditor t;
    //t.display();
    /*t.AddLine("ahmed");
    t.AddLine("samy");
    t.AddLine("ALaa");
    t.AddLine("kreem");
    t.AddLine("Snossy");
    t.AddLine("Esraa");*/
    /*t.AddLine("eslam");
    t.AddLine("Ali");
    t.AddLine("waleed");
    t.AddLine("mohamed");
    t.AddLine("kahled");
    t.AddLine("nour");
    t.AddLine("omr");
    t.AddLine("pipo");
    t.AddLine("messi");
    t.AddLine("salah");
    t.AddLine("kahled");
    t.AddLine("nour");*/
    
    //t.FindAndReplace("love", "Soso");

	int index;
	string line;
	int choice;
	//TextEditor t;

	string oldstr;
	while (true)
	{
		cout << "#############################################################################################\n";
		cout <<"#	to add new line press------> 1					\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to update a line press------> 2					\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to insert  new line press ------> 3				\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to search for a line press------> 4				\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to display all lines numbers which contains a specific string prees------> 5\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to replace all a specific strings with another string press------> 6 	\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to delete a line press------> 7					\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to display all files lines press------> 8		\n";
		cout << "---------------------------------------------------------------------------------------------\n";
		cout <<"#	to close the editor press--> 9					\n";
		cout <<"##############################################################################################\n";

		cin >> choice;

		if (choice == 9)
			break;
		switch (choice)
		{
		case 1:
			cout << "enter the line do you want to add it\n";
			ws(cin);
			getline(cin, line);
			t.AddLine(line);
			break;

		case 2:
			cout << "enter the number of the line do you want to update\n";
			cin >> index;
			cout << "enter the content of the new line \n";
			ws(cin);
			getline(cin, line);
			t.UpdateLine(index, line);
			break;
		case 3:
			cout << "enter the index do you want to insert in a line\n";
			cin >> index;
			cout << "enter the content of the line\n";
			ws(cin);
			getline(cin, line);
			t.InsertLine(index, line);

			break;
		case 4:
			cout << "enter the index do you want to search for a line\n";
			cin >> index;
			cout<<"line "<<index<<" = "<<t.GetLineText(index)<<endl;
			break;
		case 5:
			cout << "enter the specific string \n";
			cin >> line;
			displayItems(t.FindAll(line), t);
			break;
		case 6:
			cout << "enter the old string you want to replace it from lines\n";
			cin >> oldstr;
			cout << "enter the new string \n";
			cin >> line;
			t.FindAndReplace(oldstr, line);

			break;
		case 7:
			cout << "enter index of line you want to delete \n";
			cin >> index;
			t.DeleteLine(index);
			break;
		case 8:
			t.display();
			break;
		 
		default:
			cout << "invalid choice \n";
			break;
		}
		 
	}

    
    t.Show();
    
    return 0;
}

