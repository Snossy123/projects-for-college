using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Diagnostics;

namespace IsATree
{
    class Program
    {
        static void Main(string[] args)
        {
            FileStream file;
            int cases;
            StreamReader sr;
            string line;
            int wrongAnswers;
            TextReader origConsole = Console.In;
            Console.WriteLine("Is it a Tree:\n[1] Sample test cases\n[2] Complete testing\n");
            Console.Write("\nEnter your choice [1-2]: ");
            char choice = (char)Console.ReadLine()[0];
            switch (choice)
            {
                case '1':
                    #region SAMPLE CASES
                    file = new FileStream("inout.txt", FileMode.Open, FileAccess.Read);
                   
                    sr = new StreamReader(file);
                    line = sr.ReadLine();
                    cases = int.Parse(line);
                    wrongAnswers = 0;
                    for (int i = 0; i < cases; i++)
                    {
                        line = sr.ReadLine();
                        int n = int.Parse(line);
                        line = sr.ReadLine();
                        int e = int.Parse(line);
                        line = sr.ReadLine();
                        string[] vertices = line.Split(',');
                        var edges = new List<KeyValuePair<string, string>>();
                        for (int j = 0; j < e; j++)
                        {
                            line = sr.ReadLine();
                            string[] lineParts = line.Split(',');
                            edges.Add(new KeyValuePair<string, string>(lineParts[0], lineParts[1]));
                        }
                        Stopwatch sw = Stopwatch.StartNew();
                        bool ReceivedAnswer = CheckTree.IsTree(vertices, edges), expectedAnswer = bool.Parse(sr.ReadLine());
                        sw.Stop();
                        if (ReceivedAnswer != expectedAnswer)
                        {
                            Console.WriteLine("wrong answer at case " + (i + 1) + " expected answer = " + expectedAnswer + " , received answer= " + ReceivedAnswer);
                            wrongAnswers++;
                            return;
                        }
                        /*if (sw.ElapsedMilliseconds > 6000)
                        {
                            wrongAnswers++;
                            Console.WriteLine("Time limit exceed: case # " + (i + 1));
                            return;
                        }*/
                    }
                    sr.Close();
                    file.Close();
                    if (wrongAnswers == 0)
                        Console.WriteLine("Congratulation");

                    Console.WriteLine("Sample cases run successfully. you should run Complete Testing... ");
                    Console.Write("Do you want to run Complete Testing now (y/n)? ");
                    choice = (char)Console.Read();
                    if (choice == 'n' || choice == 'N')
                        break;
                    else if (choice == 'y' || choice == 'Y')
                        goto CompleteTest;
                    else
                    {
                        Console.WriteLine("Invalid Choice!");
                        break;
                    }
                    break;
                #endregion
                case '2':
                #region COMPLETE CASES
                CompleteTest:
                    Console.WriteLine("\nComplete Testing is running now...");

                    file = new FileStream("inout_complete.txt", FileMode.Open, FileAccess.Read);
                    sr = new StreamReader(file);
                    line = sr.ReadLine();
                    cases = int.Parse(line);
                    wrongAnswers = 0;
                    for (int i = 0; i < cases; i++)
                    {
                        line = sr.ReadLine();
                        int n = int.Parse(line);
                        line = sr.ReadLine();
                        int e = int.Parse(line);
                        line = sr.ReadLine();
                        string[] vertices = line.Split(',');
                        var edges = new List<KeyValuePair<string, string>>();
                        for (int j = 0; j < e; j++)
                        {
                            line = sr.ReadLine();
                            string[] lineParts = line.Split(',');
                            edges.Add(new KeyValuePair<string, string>(lineParts[0], lineParts[1]));
                        }
                        Stopwatch sw = Stopwatch.StartNew();
                        bool ReceivedAnswer = CheckTree.IsTree(vertices, edges), expectedAnswer = bool.Parse(sr.ReadLine());
                        sw.Stop();
                        if (ReceivedAnswer != expectedAnswer)
                        {
                            Console.WriteLine("wrong answer at case " + (i + 1) + " expected answer = " + expectedAnswer + " , received answer= " + ReceivedAnswer);
                            wrongAnswers++;
                            return;
                        }
                        if (sw.ElapsedMilliseconds > 31000)
                        {
                            wrongAnswers++;
                            Console.WriteLine("Time limit exceed: case # " + (i + 1) + "  "+ sw.ElapsedMilliseconds);
                            return;
                        }
                    }
                    sr.Close();
                    file.Close();
                    if (wrongAnswers == 0)
                        Console.WriteLine("Congratulations... your program runs successfully");
                    break;
                    #endregion
            }
        }
    }
}

