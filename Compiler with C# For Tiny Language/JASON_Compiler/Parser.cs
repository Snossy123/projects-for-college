using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JASON_Compiler
{

    public class Node
    {
        public List<Node> Children = new List<Node>();

        public string Name;
        public Node(string N)
        {
            this.Name = N;
        }
    }
    public class Parser
    {
        int InputPointer = 0;
        List<Token> TokenStream;
        public Node root;

        public Node StartParsing(List<Token> TokenStream)
        {
            this.InputPointer = 0;
            this.TokenStream = TokenStream;
            root = new Node("Program");
            root.Children.Add(Program());
            return root;
        }
        bool checkTerm(int InputPtr)
        {
            if((InputPtr < TokenStream.Count &&
                (TokenStream[InputPtr].token_type == Token_Class.Number ||
                 TokenStream[InputPtr].token_type == Token_Class.Idenifier)
                )||(InputPtr < TokenStream.Count-1 &&
                    (TokenStream[InputPtr].token_type == Token_Class.Idenifier &&
                    TokenStream[InputPtr + 1].token_type == Token_Class.LParanthesis)
                    )
                ) { return true; }
            return false;
        }
        bool checkEquation(int inputPtr)
        {
            if (checkFunCall()||checkTerm(inputPtr) || (inputPtr < TokenStream.Count &&
                        TokenStream[inputPtr].token_type == Token_Class.LParanthesis))
            {
                return true;
            }
            return false;
        }
        bool checkAssign()
        {
            if(InputPointer < TokenStream.Count - 1 &&
                (TokenStream[InputPointer].token_type == Token_Class.Idenifier &&
                TokenStream[InputPointer + 1].token_type == Token_Class.AssignOp
                ))
            { return true; }
            return false;
        }
        bool checkCond()
        {
            if (
                (InputPointer < TokenStream.Count - 2 && (
                    TokenStream[InputPointer].token_type == Token_Class.Idenifier &&
                    Optypes.Contains(TokenStream[InputPointer + 1].token_type) &&
                    TokenStream[InputPointer + 2].token_type == Token_Class.Number ||
                    TokenStream[InputPointer + 2].token_type == Token_Class.Idenifier)

                ) || (InputPointer < TokenStream.Count - 3 && (
                    TokenStream[InputPointer].token_type == Token_Class.Idenifier &&
                    Optypes.Contains(TokenStream[InputPointer + 1].token_type) &&
                    TokenStream[InputPointer + 2].token_type == Token_Class.Idenifier &&
                    TokenStream[InputPointer + 3].token_type == Token_Class.LParanthesis)
                )
             )
            { return true; }
            return false;
        }

        bool checkFunCall()
        {
            if(InputPointer < TokenStream.Count-1 && 
                (TokenStream[InputPointer].token_type == Token_Class.Idenifier &&
                TokenStream[InputPointer + 1].token_type == Token_Class.LParanthesis)
              )
            {
                return true;
            }
            return false;
        }

        Node elseIFstat()
        {
            Node x = new Node("statements");
            if (InputPointer < TokenStream.Count)
            {

                x.Children.Add(match(Token_Class.Elseif));
                x.Children.Add(Condstatement());
                x.Children.Add(match(Token_Class.Then));
                x.Children.Add(statements());
                if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Elseif)
                {

                    x.Children.Add(elseIFstat());
                }
                else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Else)
                {

                    x.Children.Add(elsestat());
                }
                else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.End)
                {

                    x.Children.Add(match(Token_Class.End));
                }


            }
            return x;
        }
        Node elsestat()
        {
            Node x = new Node("statements");
            if (InputPointer < TokenStream.Count)
            {

                x.Children.Add(match(Token_Class.Else));
                if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type != Token_Class.End) {
                    x.Children.Add(statements());
                }
                x.Children.Add(match(Token_Class.End));
            }
            return x;
        }
        Node IfStatement()
        {
            Node x = new Node("statements");
            if (InputPointer < TokenStream.Count)
            {

                x.Children.Add(match(Token_Class.If));
                x.Children.Add(Condstatement());
                x.Children.Add(match(Token_Class.Then));
                x.Children.Add(statements());
                if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Elseif)
                {

                    x.Children.Add(elseIFstat());
                }
                else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Else)
                {

                    x.Children.Add(elsestat());
                }
                else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.End)
                {

                    x.Children.Add(match(Token_Class.End));
                }

            }
            return x;
        }
        Node Term()
        {
            Node x = new Node("Term");
            if (TokenStream[InputPointer].token_type == Token_Class.Number)
            {
                x.Children.Add(match(Token_Class.Number));
            }

            else if (TokenStream[InputPointer].token_type == Token_Class.Idenifier && TokenStream[InputPointer + 1].token_type == Token_Class.LParanthesis)
            {
                x.Children.Add(function_call());
            }

            else if (TokenStream[InputPointer].token_type == Token_Class.Idenifier)
            {
                x.Children.Add(match(Token_Class.Idenifier));
            }
            return x;
        }

        Token_Class[] datatypes = { Token_Class.DataTypeInt, Token_Class.DataTypeDouble, Token_Class.DataTypeFloat, Token_Class.DataTypeChar, Token_Class.DataTypeString };
        Token_Class[] Optypes = { Token_Class.GreaterThanOp, Token_Class.LessThanOp, Token_Class.EqualOp, Token_Class.NotEqualOp };
        Token_Class[] ArthOptypes = { Token_Class.PlusOp, Token_Class.MinusOp, Token_Class.MultiplyOp, Token_Class.DivideOp };

        Node Decl_Statement3()
        {
            Node x = new Node("Decl_Statement3");
            if (TokenStream[InputPointer].token_type == Token_Class.Comma)
            {
                x.Children.Add(match(Token_Class.Comma));
                if (TokenStream[InputPointer].token_type == Token_Class.Idenifier && TokenStream[InputPointer + 1].token_type == Token_Class.AssignOp)
                {
                    bool Semi_Colon = false;
                    x.Children.Add(Ass_Statement(Semi_Colon));
                }
                else
                {
                    x.Children.Add(match(Token_Class.Idenifier));
                }
                x.Children.Add(Decl_Statement3());
                return x;
            }
            else
            {
                return null;
            }
        }
        Node Decl_Statement2()
        {
            Node x = new Node("Decl_Statement2");
            if (TokenStream[InputPointer].token_type == Token_Class.Idenifier && TokenStream[InputPointer + 1].token_type == Token_Class.AssignOp)
            {
                x.Children.Add(Ass_Statement(false));
            }
            else
            {
                x.Children.Add(match(Token_Class.Idenifier));
            }
            x.Children.Add(Decl_Statement3());
            return x;
        }
        Node Decl_Statement()
        {
            Node x = new Node("Decleration_Statement");
            x.Children.Add(DataType());
            x.Children.Add(Decl_Statement2());
            x.Children.Add(match(Token_Class.Semicolon));

            return x;
        }
        Node Equation_1()
        {
            Node x = new Node("Equation_1");
            if(InputPointer < TokenStream.Count && 
                ArthOptypes.Contains(TokenStream[InputPointer].token_type)
                )
            {
                x.Children.Add(ArthOp()); 
                x.Children.Add(Equation());
            }
            else if(InputPointer < TokenStream.Count() &&
                 TokenStream[InputPointer].token_type!= Token_Class.Semicolon
                )
            { 
                x.Children.Add(Equation());

            }

            return x;
             
        } 
      //function call | term | ()
        Node Equation()
        {
            Node x = new Node("Equation");
            if (checkFunCall())
            {
                x.Children.Add(function_call());
                x.Children.Add(Equation_1());
            }
            else if (checkTerm(InputPointer))
            {
                x.Children.Add(Term());
                x.Children.Add(Equation_1());
            }

            else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.LParanthesis)
            {
                x.Children.Add(match(Token_Class.LParanthesis));
         
                x.Children.Add(Equation());
                x.Children.Add(match(Token_Class.RParanthesis));
                x.Children.Add(Equation_1());

            }
            return x;
        }
        Node ArthOp()
        {
            Node x = new Node("ArthOp");
            if (TokenStream[InputPointer].token_type == Token_Class.PlusOp)
            {
                x.Children.Add(match(Token_Class.PlusOp));
              
            }

            else if (TokenStream[InputPointer].token_type == Token_Class.MinusOp)
            {
                x.Children.Add(match(Token_Class.MinusOp));
               
            }

            else if (TokenStream[InputPointer].token_type == Token_Class.MultiplyOp)
            {
                x.Children.Add(match(Token_Class.MultiplyOp));
               
            }
            else if (TokenStream[InputPointer].token_type == Token_Class.DivideOp)
            {
                x.Children.Add(match(Token_Class.DivideOp));
               
            }
            else { return null; }
            return x;
        }


        Node Write_statement()
        {
            Node x = new Node("Write_statement");
            if (TokenStream[InputPointer].token_type == Token_Class.Write)
            {
                x.Children.Add(match(Token_Class.Write));
                x.Children.Add(Write_statement_1());
                x.Children.Add(match(Token_Class.Semicolon));

            }
            return x;

        }

        Node Write_statement_1()
        {
            Node x = new Node("Write_statement_1");
            if (TokenStream[InputPointer].token_type == Token_Class.Endl)
            {
                x.Children.Add(match(Token_Class.Endl));

            }
            else
            {
                x.Children.Add(Expression());

            }
            return x;

        }

        Node Read_statement()
        {
            Node x = new Node("Read_statement");
            if (TokenStream[InputPointer].token_type == Token_Class.Read)
            {
                x.Children.Add(match(Token_Class.Read));
                x.Children.Add(match(Token_Class.Idenifier));
                x.Children.Add(match(Token_Class.Semicolon));

            }
            return x;

        }

        Node Return_statement()
        {
            Node x = new Node("Return_statement");
            if (TokenStream[InputPointer].token_type == Token_Class.Return)
	        {
                x.Children.Add(match(Token_Class.Return));
                x.Children.Add(Expression());
                x.Children.Add(match(Token_Class.Semicolon));

            }
            return x;

        }

        Node Parameter()
        {
            Node x = new Node("Parameter");
            if (InputPointer < TokenStream.Count)
            {
                x.Children.Add(DataType());
                x.Children.Add(match(Token_Class.Idenifier));
            }
            return x;
        }
        Node Comaparam()
        {
            Node x = new Node("Parameter");
            if (InputPointer < TokenStream.Count-2 && TokenStream[InputPointer].token_type == Token_Class.Comma &&
                datatypes.Contains(TokenStream[InputPointer + 1].token_type) &&
                  TokenStream[InputPointer + 2].token_type == Token_Class.Idenifier  )
            {
                x.Children.Add(match(Token_Class.Comma));
                x.Children.Add(Parameter());
                x.Children.Add(Comaparam());

            } 

            return x;
        }
        Node Parameters()
        {
            Node x = new Node("Parameter");
            if (InputPointer < TokenStream.Count)
            {
                if (InputPointer < TokenStream.Count -1 &&  datatypes.Contains(TokenStream[InputPointer].token_type) &&
                    TokenStream[InputPointer+1].token_type == Token_Class.Idenifier)
                {
                    x.Children.Add(Parameter());
                    x.Children.Add(Comaparam());
                }
                else
                {
                    return null;
                }
            }
            return x;
        }
        Node ConditionOP()
        {
            Node x = new Node("ConditionOP");
            if (TokenStream[InputPointer].token_type == Token_Class.GreaterThanOp)
                x.Children.Add(match(Token_Class.GreaterThanOp));
            if (TokenStream[InputPointer].token_type == Token_Class.LessThanOp)
                x.Children.Add(match(Token_Class.LessThanOp));
            if (TokenStream[InputPointer].token_type == Token_Class.EqualOp)
                x.Children.Add(match(Token_Class.EqualOp));
            if (TokenStream[InputPointer].token_type == Token_Class.NotEqualOp)
                x.Children.Add(match(Token_Class.NotEqualOp));
            return x;
        }

        Node DataType()
        {
            Node x = new Node("DataType");
            if (TokenStream[InputPointer].token_type == Token_Class.DataTypeChar)
                x.Children.Add(match(Token_Class.DataTypeChar));
            else if (TokenStream[InputPointer].token_type == Token_Class.DataTypeDouble)
                x.Children.Add(match(Token_Class.DataTypeDouble));
            else if (TokenStream[InputPointer].token_type == Token_Class.DataTypeFloat)
                x.Children.Add(match(Token_Class.DataTypeFloat));
            else if (TokenStream[InputPointer].token_type == Token_Class.DataTypeInt)
                x.Children.Add(match(Token_Class.DataTypeInt));
            else if (TokenStream[InputPointer].token_type == Token_Class.DataTypeString)
                x.Children.Add(match(Token_Class.DataTypeString));
            
            return x;
        }

        Node Condition()
        {
            Node x = new Node("Condition");
            if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Idenifier) 
            {
                x.Children.Add(match(Token_Class.Idenifier));
                x.Children.Add(ConditionOP());
                x.Children.Add(Term());
            }
           return x;
        }
        Node Condstatement_()
        {

            Node x = new Node("Condition");

            if (InputPointer < TokenStream.Count && (TokenStream[InputPointer].token_type == Token_Class.AndOp || TokenStream[InputPointer].token_type == Token_Class.OrOp))
            {
                x.Children.Add(match(TokenStream[InputPointer].token_type));
                x.Children.Add(Condition());
                x.Children.Add(Condstatement_());

            }
            else
                return null;

            return x;
        }
        Node Condstatement()
        {
            Node x = new Node("Condstatement");
            x.Children.Add(Condition());
            x.Children.Add(Condstatement_());
            return x;

        }
        Node Expression()
        {
            Node x = new Node("Expression");

            if (checkEquation(InputPointer))
            {
                x.Children.Add(Equation());
            }
            else if (checkTerm(InputPointer))
            {
                x.Children.Add(Term());
            }

            else if (TokenStream[InputPointer].token_type == Token_Class.String)
            {
                x.Children.Add(match(Token_Class.String));
            }
            return x;
        }
        Node Ass_Statement(bool simeColonSate)
        {
            Node x = new Node("Assignment_Statement");
            if (TokenStream[InputPointer].token_type == Token_Class.Idenifier && TokenStream[InputPointer + 1].token_type == Token_Class.AssignOp)
            {
                x.Children.Add(match(Token_Class.Idenifier));
                x.Children.Add(match(Token_Class.AssignOp));
                x.Children.Add(Expression());
                if (simeColonSate)
                {
                    x.Children.Add(match(Token_Class.Semicolon));
                }
            }
            return x;
        } 
        Node statement(ref bool noState)
        {
            Node x = new Node("statement");
            // if statemant
            if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.If)
            {

                x.Children.Add(IfStatement());

            }
            // else if statemant
            else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Elseif)
            {

                x.Children.Add(elseIFstat());

            }
            // else statemant
            else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Else)
            {

                x.Children.Add(elsestat());

            }
            // Repeat statemant
            else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Repeat)
            {

                x.Children.Add(match(Token_Class.Repeat));
                x.Children.Add(statements());
                x.Children.Add(match(Token_Class.Until));
                x.Children.Add(Condstatement());

            }
            // Assignment_Statement
            else if (checkAssign())
            {
                x.Children.Add(Ass_Statement(true));
            }
            // Comment 
            else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Comment)
            {
                x.Children.Add(match(Token_Class.Comment));
            }
            // Function_Declaration
            else if (InputPointer < TokenStream.Count - 2 && 
                (datatypes.Contains(TokenStream[InputPointer].token_type) &&
                  TokenStream[InputPointer+1].token_type == Token_Class.Idenifier &&
                  TokenStream[InputPointer + 2].token_type == Token_Class.LeftParentheses))
            {
                x.Children.Add(DataType());
                x.Children.Add(match(Token_Class.Idenifier));
                x.Children.Add(match(Token_Class.LeftParentheses));
                x.Children.Add(Parameters());
                x.Children.Add(match(Token_Class.RightParentheses));

            } 
            // declaration statement 
            
            else if (InputPointer < TokenStream.Count -2 && 
                (datatypes.Contains(TokenStream[InputPointer].token_type) &&
                TokenStream[InputPointer + 1].token_type == Token_Class.Idenifier &&
                TokenStream[InputPointer + 2].token_type != Token_Class.LParanthesis
                ))
            {
                x.Children.Add(Decl_Statement());
            }
            // equation term or // start (
            else if (checkEquation(InputPointer))
            {
                x.Children.Add(Equation());
            }
            // Expression string | term | equation
            else if (InputPointer < TokenStream.Count &&
                (TokenStream[InputPointer].token_type == Token_Class.String || checkTerm(InputPointer)||checkEquation(InputPointer)))
            {
                x.Children.Add(Expression());
            }
            
            // term statement
            else if (checkTerm(InputPointer))
            {
                x.Children.Add(Term());
            }
            // Write_Statement write + //  Expression
            else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Write)
            {
                x.Children.Add(Write_statement());
            }
            // read statement
            else if (InputPointer < TokenStream.Count && TokenStream[InputPointer].token_type == Token_Class.Read)
            {
                x.Children.Add(Read_statement());
            }
            // Return_Statement
            else if (InputPointer < TokenStream.Count &&TokenStream[InputPointer].token_type == Token_Class.Return )
            {
                x.Children.Add(Return_statement());
            }
            // Condition statement 
            else if (checkCond())
            {
                x.Children.Add(Condstatement());
            }
            else { noState = false; }
            return x;
        }
        Node statements()
        {
            Node x = new Node("statements");
            if (InputPointer < TokenStream.Count)
            {

                bool stateNext = true;
                x.Children.Add(statement(ref stateNext));
                if(!stateNext) { return x; }
                x.Children.Add(statements());

            }
            return x;
        }
        Node funBody()
        {
            Node x = new Node("functionBody");
            if (InputPointer < TokenStream.Count)
            {

                x.Children.Add(match(Token_Class.LeftBraces));
                x.Children.Add(statements());
                x.Children.Add(match(Token_Class.RightBraces));

            }
            return x;
        }
        Node mainFunction()
        {
            Node x = new Node("mainfunction");
            if (InputPointer < TokenStream.Count)
            {
                
                if (datatypes.Contains(TokenStream[InputPointer].token_type))
                {
                     
                  x.Children.Add(DataType());
                          
                }
                
                x.Children.Add(match(Token_Class.main));
                x.Children.Add(match(Token_Class.LParanthesis));
                x.Children.Add(match(Token_Class.RParanthesis));

                x.Children.Add(funBody());
            }
            return x;
        }
        
        Node functions()
        {
            Node x = new Node("funAboveMain");
            if (InputPointer < TokenStream.Count -1 && datatypes.Contains(TokenStream[InputPointer].token_type) &&
                TokenStream[InputPointer+1].token_type != Token_Class.main
                )
            {

                if (InputPointer < TokenStream.Count - 2 &&
                (datatypes.Contains(TokenStream[InputPointer].token_type) &&
                  TokenStream[InputPointer + 1].token_type == Token_Class.Idenifier &&
                  TokenStream[InputPointer + 2].token_type == Token_Class.LParanthesis))
                {
                    x.Children.Add(DataType());
                    x.Children.Add(match(Token_Class.Idenifier));
                    x.Children.Add(match(Token_Class.LParanthesis));
                    x.Children.Add(Parameters());
                    x.Children.Add(match(Token_Class.RParanthesis));

                }

                x.Children.Add(funBody());

                x.Children.Add(functions());

            } 
            return x;
        }
        Node M_function()
        {
            Node x = new Node("M_function");
            if (InputPointer < TokenStream.Count)
            { 
                x.Children.Add(functions());
                if (InputPointer < TokenStream.Count &&
                  TokenStream[InputPointer].token_type == Token_Class.Comment)
                {
                    x.Children.Add(match(Token_Class.Comment));
                }
                x.Children.Add(mainFunction());
            }
            return x;
        }
        Node Program()
        {
            Node program = new Node("Program");

            if (InputPointer < TokenStream.Count && 
                  TokenStream[InputPointer].token_type == Token_Class.Comment)
            {
                program.Children.Add(match(Token_Class.Comment));
            }
            program.Children.Add(M_function());



            MessageBox.Show("Success");
            return program;
        }

        Node Header()
        {
            Node header = new Node("Header");

            return header;
        }
        Node DeclSec()
        {
            Node declsec = new Node("DeclSec");
            // write your code here to check atleast the declare sturcure 
            // without adding procedures
            return declsec;
        }
        Node Block()
        {
            Node block = new Node("block");
            // write your code here to match statements
            return block;
        }
        Node Read()
        {
            Node x = new Node("Read");
            if (InputPointer < TokenStream.Count - 2)
            {

                x.Children.Add(match(Token_Class.Read));
                x.Children.Add(match(Token_Class.Idenifier));
                x.Children.Add(match(Token_Class.Semicolon));
            }
            return x;
        }

        Node func_call_tmp2()
        {
            Node x = new Node("func_call_tmp2");
            if (InputPointer < TokenStream.Count - 1 &&
                TokenStream[InputPointer].token_type == Token_Class.Comma &&
                checkTerm(InputPointer+1)
                )
            {

                x.Children.Add(match(Token_Class.Comma));
                x.Children.Add(Term());
                x.Children.Add(func_call_tmp2());
            }
            else
                return null;

            return x;
        }
        Node func_call_tmp1()
        {
            Node x = new Node("func_call_tmp1");
            if (checkTerm(InputPointer))
            {

                x.Children.Add(Term());
                x.Children.Add(func_call_tmp2());
            }
            else
                return null;

            return x;
        }
        Node function_call()
        {
            Node x = new Node("Function Call");
            if (TokenStream[InputPointer].token_type == Token_Class.Idenifier && TokenStream[InputPointer + 1].token_type == Token_Class.LParanthesis &&
                InputPointer < TokenStream.Count)
            {
                x.Children.Add(match(Token_Class.Idenifier));
                x.Children.Add(match(Token_Class.LParanthesis));
                x.Children.Add(func_call_tmp1());
                x.Children.Add(match(Token_Class.RParanthesis));

            }

            return x;
        }

        public Node match(Token_Class ExpectedToken)
        {

            if (InputPointer < TokenStream.Count)
            {
                if (ExpectedToken == TokenStream[InputPointer].token_type)
                {
                    InputPointer++;
                    Node newNode = new Node(ExpectedToken.ToString());

                    return newNode;

                }

                else
                {
                    Errors.Error_List.Add("Parsing Error: Expected "
                        + ExpectedToken.ToString() + " and " +
                        TokenStream[InputPointer].token_type.ToString() +
                        "  found\r\n");
                    InputPointer++;
                    return null;
                }
            }
            else
            {
                Errors.Error_List.Add("Parsing Error: Expected "
                        + ExpectedToken.ToString() + "\r\n");
                InputPointer++;
                return null;
            }
        }

        public static TreeNode PrintParseTree(Node root)
        {
            TreeNode tree = new TreeNode("Parse Tree");
            TreeNode treeRoot = PrintTree(root);
            if (treeRoot != null)
                tree.Nodes.Add(treeRoot);
            return tree;
        }
        static TreeNode PrintTree(Node root)
        {
            if (root == null || root.Name == null)
                return null;
            TreeNode tree = new TreeNode(root.Name);
            if (root.Children.Count == 0)
                return tree;
            foreach (Node child in root.Children)
            {
                if (child == null)
                    continue;
                tree.Nodes.Add(PrintTree(child));
            }
            return tree;
        }
    }
}
