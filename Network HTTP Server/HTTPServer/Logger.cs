using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace HTTPServer
{
    class Logger
    {

        public static void LogException(Exception ex)
        {
            // TODO: Create log file named log.txt to log exception details in it
            string f_Path = @"D:\New folder (18)\fcis 3rd year\C.Network\net\Template[2021-2022]\HTTPServer\bin\Debug\log.txt";
            //Writting in file
            using (StreamWriter writer = new StreamWriter(f_Path, true))
            {
                // for each exception write its details associated with datetime 

                //Datetime:
                writer.WriteLine("Date : " + DateTime.Now.ToString());

                while (ex != null)
                {

                    writer.WriteLine(ex.GetType().FullName);
                    //message:
                    writer.WriteLine("Message : " + ex.Message);
                    ex = ex.InnerException;
                }
            }
        }
    }
}
