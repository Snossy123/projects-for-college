#ifndef TEXTEDITOR_H
#define TEXTEDITOR_H
#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

class TextEditor
{
public:

    vector <string> v;
    int items;

    void AddLine(string text);
    void InsertLine(int pos, string text);

    string GetLineText(int pos);
    void DeleteLine(int pos);

    void UpdateLine(int pos, string text);
    int* FindAll(string text);

    void FindAndReplace(string oldText, string newText);
    void display();
    
    
    void Show();
     
    //void PutInFile();

    TextEditor();
    ~TextEditor();


};

#endif // TEXTEDITOR_H
