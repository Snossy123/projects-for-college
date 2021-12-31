using System.Collections.Generic;
using System.Text.RegularExpressions;



public enum Token_Class
{
    Begin, Call, Declare, End, Do, Else, EndIf, EndUntil, EndWhile, If, Integer,
    Parameters, Procedure, Program, Read, Real, Set, Then, Until, While, Write,
    Dot, Semicolon, Comma, LParanthesis, RParanthesis, EqualOp, LessThanOp,
    GreaterThanOp, NotEqualOp, PlusOp, MinusOp, MultiplyOp, DivideOp,
    Idenifier, Constant, String, Number, Comment, AndOp, OrOp, AssignOp,
    DataTypeInt, DataTypeChar, DataTypeFloat, DataTypeDouble, DataTypeString,
    LeftParentheses, RightParentheses, LeftBraces, RightBraces, Repeat, Return, Endl, Elseif, main
}
namespace JASON_Compiler
{


    public class Token
    {
        public string lex;
        public Token_Class token_type;
    }

    public class Scanner
    {
        public List<Token> Tokens = new List<Token>();
        Dictionary<string, Token_Class> ReservedWords = new Dictionary<string, Token_Class>();
        Dictionary<string, Token_Class> Operators = new Dictionary<string, Token_Class>();
        Dictionary<string, Token_Class> DataTypes = new Dictionary<string, Token_Class>();

        public Scanner()
        {
            ReservedWords.Add("if", Token_Class.If);
            ReservedWords.Add("BEGIN", Token_Class.Begin);
            ReservedWords.Add("CALL", Token_Class.Call);
            ReservedWords.Add("DECLARE", Token_Class.Declare);
            ReservedWords.Add("end", Token_Class.End);
            ReservedWords.Add("DO", Token_Class.Do);
            ReservedWords.Add("else", Token_Class.Else);
            ReservedWords.Add("ENDIF", Token_Class.EndIf);
            ReservedWords.Add("ENDUNTIL", Token_Class.EndUntil);
            ReservedWords.Add("ENDWHILE", Token_Class.EndWhile);
            ReservedWords.Add("INTEGER", Token_Class.Integer);
            ReservedWords.Add("PARAMETERS", Token_Class.Parameters);
            ReservedWords.Add("PROCEDURE", Token_Class.Procedure);
            ReservedWords.Add("PROGRAM", Token_Class.Program);
            ReservedWords.Add("read", Token_Class.Read);
            ReservedWords.Add("REAL", Token_Class.Real);
            ReservedWords.Add("SET", Token_Class.Set);
            ReservedWords.Add("then", Token_Class.Then);
            ReservedWords.Add("until", Token_Class.Until);
            ReservedWords.Add("WHILE", Token_Class.While);
            ReservedWords.Add("write", Token_Class.Write);
            ReservedWords.Add("repeat", Token_Class.Repeat);
            ReservedWords.Add("return", Token_Class.Return);
            ReservedWords.Add("endl", Token_Class.Endl);
            ReservedWords.Add("elseif", Token_Class.Elseif);
            ReservedWords.Add("main", Token_Class.main);

            Operators.Add(".", Token_Class.Dot);
            Operators.Add(";", Token_Class.Semicolon);
            Operators.Add(",", Token_Class.Comma);
            Operators.Add("(", Token_Class.LParanthesis);
            Operators.Add(")", Token_Class.RParanthesis);
            Operators.Add("{", Token_Class.LeftBraces);
            Operators.Add("}", Token_Class.RightBraces);
            Operators.Add("=", Token_Class.EqualOp);
            Operators.Add("<", Token_Class.LessThanOp);
            Operators.Add(">", Token_Class.GreaterThanOp);
            Operators.Add("!=", Token_Class.NotEqualOp);
            Operators.Add("+", Token_Class.PlusOp);
            Operators.Add("-", Token_Class.MinusOp);
            Operators.Add("*", Token_Class.MultiplyOp);
            Operators.Add("/", Token_Class.DivideOp);
            Operators.Add(":=", Token_Class.AssignOp);

            Operators.Add("||", Token_Class.OrOp);
            Operators.Add("&&", Token_Class.AndOp);

            DataTypes.Add("char", Token_Class.DataTypeChar);
            DataTypes.Add("int", Token_Class.DataTypeInt);
            DataTypes.Add("double", Token_Class.DataTypeDouble);
            DataTypes.Add("string", Token_Class.DataTypeString);
            DataTypes.Add("float", Token_Class.DataTypeFloat);

        }

        public bool Number(string lex)
        {
            var re = new Regex(@"^[0-9]+(\.\d+)?$", RegexOptions.Compiled);

            // MatchCollection m = re.Matches(lex);
            if (re.IsMatch(lex))
            {
                return true;
            }
            return false;
        }

        public bool Str(string lex)
        {
            var re = new Regex("^\".*\"$", RegexOptions.Compiled);
            //MatchCollection m = re.Matches(lex);
            if (re.IsMatch(lex))
            {
                return true;
            }
            return false;
        }


        public bool Comment(string lex)
        {
            //string num = ^(/ \*) (Number|[A-Z]|[a-z])* (\* /)$;
            var re = new Regex(@"^/\*(.|\n)*\*/$", RegexOptions.Compiled);
            //MatchCollection m = re.Matches(lex);
            if (re.IsMatch(lex))
            {
                return true;
            }
            return false;
        }

        public bool arthOP(string lex)
        {
            var re = new Regex(@"^[+|\-|*|/]$", RegexOptions.Compiled);


            // MatchCollection m = re.Matches(lex);
            if (re.IsMatch(lex))
            {
                return true;
            }
            return false;
        }

        public bool conOP(string lex)
        {
            var re = new Regex(@"^[<|>|=|(<>)]$", RegexOptions.Compiled);


            // MatchCollection m = re.Matches(lex);
            if (re.IsMatch(lex))
            {
                return true;
            }
            return false;
        }

        // public bool boolOP(string lex)
        //{
        //    var re =new Regex(@"[\&\& | \|\|]", RegexOptions.Compiled);


        //    // MatchCollection m = re.Matches(lex);
        //    if (re.IsMatch(lex))
        //    {
        //        return true;
        //    }
        //    return false;
        //}  



        public void StartScanning(string SourceCode)
        {
            for (int i = 0; i < SourceCode.Length; i++)
            {
                //"1392"
                int j = i;
                char CurrentChar = SourceCode[i]; //1
                string CurrentLexeme = CurrentChar.ToString(); //lex = 1


                if (CurrentChar == ' ' || CurrentChar == '\r' || CurrentChar == '\n')
                    continue;
                // ;  () {}
                if (CurrentChar == ',' || CurrentChar == ';' || CurrentChar == '(' || CurrentChar == ')' || CurrentChar == '{' || CurrentChar == '}')
                {
                    FindTokenClass(CurrentLexeme);
                }
                // assign operator
                else if ((j < SourceCode.Length - 1) && ((CurrentChar == ':' && SourceCode[i + 1] == '=')))
                {
                    CurrentLexeme += '=';
                    i++;
                    FindTokenClass(CurrentLexeme);
                }
                // comment
                else if (CurrentChar == '/' && SourceCode[j + 1] == '*')
                {

                    bool flag = false;

                    // String x = " sagkdguk  jksajhd876283 69834*#*titt " ;
                    while (j < SourceCode.Length - 1)
                    {
                        /*croos */
                        if (flag)
                        {
                            CurrentLexeme += CurrentChar;
                        }
                        flag = true;
                        j++;
                        if (j < SourceCode.Length - 1)
                        {
                            CurrentChar = SourceCode[j];
                        } /*  */
                        if (CurrentChar == '*' && SourceCode[j + 1] == '/')
                        {
                            CurrentLexeme += CurrentChar;
                            CurrentLexeme += SourceCode[j + 1];
                            j += 2;
                            break;
                        }
                        if ((CurrentChar != '*' || SourceCode[j + 1] != '/') && j == SourceCode.Length - 2)
                        {

                            CurrentLexeme += CurrentChar;
                            CurrentLexeme += SourceCode[j + 1];
                            j += 2;
                            break;

                        }
                    }
                    i = j - 1;
                    FindTokenClass(CurrentLexeme); // "kjhk"
                }
                //identifier
                else if (char.IsLetter(CurrentChar))
                {
                    bool flag = false;
                    while (char.IsLetterOrDigit(CurrentChar) && j < SourceCode.Length)
                    {

                        if (flag)
                        {
                            CurrentLexeme += CurrentChar;
                        }
                        flag = true;
                        j++;
                        if (j < SourceCode.Length)
                        {
                            CurrentChar = SourceCode[j];
                        }

                    }

                    i = j - 1;
                    FindTokenClass(CurrentLexeme); // "kjhk"
                }


                // الحمد للهNUMBER
                else if ((CurrentChar >= '0' && CurrentChar <= '9') || CurrentChar == '.')
                {
                    int doteCount = 0;
                    bool flag = false;

                    while ((char.IsDigit(CurrentChar) || (CurrentChar == '.' && doteCount == 0)) && j < SourceCode.Length)
                    {
                        if (CurrentChar == '.')
                        {
                            doteCount++;
                        }
                        if (flag)
                        {
                            CurrentLexeme += CurrentChar;
                        }
                        flag = true;
                        j++;
                        if (j < SourceCode.Length)
                        {
                            CurrentChar = SourceCode[j];
                        }
                    }
                    i = j - 1;
                    FindTokenClass(CurrentLexeme);
                }
                // ReservedWords
                else if (char.IsLetterOrDigit(CurrentChar))
                {
                    bool flag = false;

                    // String x = " sagkdguk  jksajhd876283 69834*#*titt " ;
                    while (char.IsLetterOrDigit(CurrentChar) && j < SourceCode.Length)
                    {

                        if (flag)
                        {
                            CurrentLexeme += CurrentChar;
                        }
                        flag = true;
                        j++;
                        if (j < SourceCode.Length)
                        {
                            CurrentChar = SourceCode[j];
                        }

                    }
                    i = j - 1;
                    FindTokenClass(CurrentLexeme); // "kjhk"
                }

                // الحمد لله    //string
                else if (CurrentChar == '\"')
                {

                    bool flag = false;

                    // String x = " sagkdguk  jksajhd876283 69834*#*titt " ;
                    while (j < SourceCode.Length)
                    {

                        if (flag)
                        {
                            CurrentLexeme += CurrentChar;
                        }
                        flag = true;
                        j++;
                        if (j < SourceCode.Length)
                        {
                            CurrentChar = SourceCode[j];
                        }
                        if (CurrentChar == '\"')
                        {
                            CurrentLexeme += CurrentChar;
                            j++;
                            break;
                        }
                    }
                    i = j - 1;
                    FindTokenClass(CurrentLexeme); // "kjhk"
                }
                // الحمد لله  /*

                //Arithmatic_Operator
                else if (CurrentChar == '+' || CurrentChar == '-' || CurrentChar == '*' || CurrentChar == '/')
                {
                    FindTokenClass(CurrentLexeme);
                }
                //Condition_Operator
                else if (CurrentChar == '<' || CurrentChar == '>' || CurrentChar == '=' || ((j < SourceCode.Length - 1) && (CurrentChar == '<' && SourceCode[j + 1] == '>')))
                {
                    if ((j < SourceCode.Length - 1) && (CurrentChar == '<' && SourceCode[j + 1] == '>'))
                    {
                        CurrentLexeme += '>';
                        i++;
                    }
                    FindTokenClass(CurrentLexeme);
                }

                // Boolean_Operator
                else if ((j < SourceCode.Length - 1) && ((CurrentChar == '&' && SourceCode[j + 1] == '&') || (CurrentChar == '|' && SourceCode[j + 1] == '|')))
                {
                    if (CurrentChar == '|')
                        CurrentLexeme += '|';
                    else if (CurrentChar == '&')
                        CurrentLexeme += '&';
                    i++;
                    FindTokenClass(CurrentLexeme);

                }
                // error list
                else
                {
                    FindTokenClass(CurrentLexeme);
                }

            }

            JASON_Compiler.TokenStream = Tokens;
        }
        void FindTokenClass(string Lex)
        {
            Token_Class TC;
            Token Tok = new Token();
            Tok.lex = Lex;
            // comment
            if (Comment(Lex))
            {
                Tok.token_type = Token_Class.Comment;
                Tokens.Add(Tok);
            }
            //  Operators
            else if (Operators.ContainsKey(Lex))
            {
                Tok.token_type = Operators[Lex];
                Tokens.Add(Tok);
            }
            // DataTypes
            else if (DataTypes.ContainsKey(Lex))
            {
                Tok.token_type = DataTypes[Lex];
                Tokens.Add(Tok);
            }
            //Is it a reserved word?
            else if (ReservedWords.ContainsKey(Lex))
            {

                Tok.token_type = ReservedWords[Lex];
                Tokens.Add(Tok);
            }

            //Is it an identifier?
            else if (isIdentifier(Lex))
            {

                Tok.token_type = Token_Class.Idenifier;
                Tokens.Add(Tok);
            }


            //Is it a Constant?
            else if (Number(Lex))
            {
                Tok.token_type = Token_Class.Number;
                Tokens.Add(Tok);
            }


            //String
            else if (Str(Lex))
            {
                Tok.token_type = Token_Class.String;
                Tokens.Add(Tok);
            }

            //Is it an condation operator

            else if (conOP(Lex))
            {
                if (Lex == "<>")
                    Tok.token_type = Token_Class.NotEqualOp;
                else if (Lex == ">")
                    Tok.token_type = Token_Class.GreaterThanOp;
                else if (Lex == "=")
                    Tok.token_type = Token_Class.EqualOp;
                else if (Lex == "<")
                    Tok.token_type = Token_Class.LessThanOp;

                Tokens.Add(Tok);
            }

            //Is it an operator?
            else if (arthOP(Lex))
            {
                if (Lex == "+")
                    Tok.token_type = Token_Class.PlusOp;
                if (Lex == "-")
                    Tok.token_type = Token_Class.MinusOp;
                if (Lex == "*")
                    Tok.token_type = Token_Class.MultiplyOp;
                if (Lex == "/")
                    Tok.token_type = Token_Class.DivideOp;

                Tokens.Add(Tok);
            }

            //Is it an undefined?
            else
            {

                // ANDD ANYTHING ELSE ON ERROR LIST
                Errors.Error_List.Add(Lex);
            }
        }



        bool isIdentifier(string lex)
        {
            //string num = ^(/ \*) (Number|[A-Z]|[a-z])* (\* /)$;
            var re = new Regex(@"^([A-Za-z])([A-Za-z0-9])*$", RegexOptions.Compiled);
            //MatchCollection m = re.Matches(lex);
            if (re.IsMatch(lex))
            {
                return true;
            }
            return false;
        }

    }
}
