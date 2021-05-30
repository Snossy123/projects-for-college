#include "TextEditor.h" 
#include <iostream>
#include <fstream> 
#include <string>
#include <vector>

using namespace std;
ofstream outFile;
ifstream inFile;
string line;
//outFile << "First thing to write in a file! " << endl;

TextEditor::TextEditor()
{
 
    inFile.open("Mini Text Editor.txt", ios::binary);
    while (getline(inFile, line)) {

        
        if (line.length() > 2 && line.find(' ') != -1)
        {
            line = line.substr(line.find(' ') + 1);
            v.push_back(line);
        }
    }
    inFile.close();
 
    items = v.size();
}
 void TextEditor::AddLine(string text)
{
    v.push_back(text);
    items++;
}

void TextEditor::InsertLine(int pos, string text)  //Add line at a specific position
{
    v.insert(v.begin() + pos - 1, text);
    items++;
}

string TextEditor::GetLineText(int pos)
{
    return (v[pos - 1]);
}

void TextEditor::DeleteLine(int pos)
{
    v.erase(v.begin() + pos - 1);
    items--;
}

void TextEditor::UpdateLine(int pos, string text)
{
    v[pos - 1] = text;
}

int* TextEditor::FindAll(string text)  //return the number of the lines containing specific word
{
    int* arr = new int[items];
    for (int i = 0; i < items; i++) {
        arr[i] = -1;
    }
    int j = 0;
    for (int i = 0; i < items; i++) {

        if (v[i].find(text) != std::string::npos) {
            arr[j] = i + 1;
            j++;
        }
    }

    return arr;
}

void TextEditor::FindAndReplace(string oldText, string newText)
{
    for (int i = 0; i < items; i++) {

        int start = v[i].find(oldText);
        if (start != -1) {
            v[i].replace(v[i].begin() + start, v[i].begin() + start + oldText.length(), newText);
            // replace all text that equal the old text with new text

        }
    }
}
void TextEditor::Show() //write elements to the file
{
    outFile.open("Mini Text Editor.txt", ios::binary);
    for (int i = 0; i < v.size(); i++) {
        outFile << i + 1 << " " << v[i] <<endl;
    } 
    outFile.close();
}
void TextEditor::display() {
    for (int i = 0; i < v.size(); i++) {
        cout << " line = " << i + 1 << "- " << v[i] << endl;
    }
    cout << endl;
}
 
TextEditor::~TextEditor()
{
    outFile.close();
}
