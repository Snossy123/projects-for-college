using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HTTPServer
{
    public enum RequestMethod
    {
        GET,
        POST,
        HEAD
    }

    public enum HTTPVersion
    {
        HTTP10, // HTTP/1.0
        HTTP11,// HTTP/1.1
        HTTP09
    }

    class Request
    {
        string[] requestLines;
        public string relativeURI;
        Dictionary<string, string> headerLines= new Dictionary<string, string>();

        public Dictionary<string, string> HeaderLines
        {
            get { return headerLines; }
        }
        string requestString;

        public Request(string requestString)
        {
            this.requestString = requestString;
        }
        /// <summary>
        /// Parses the request string and loads the request line, header lines and content, returns false if there
        /// is a parsing error
        /// </summary>
        /// <returns>True if parsing succeeds, false otherwise.</returns>
        public bool ParseRequest()

        {
            //TODO: parse the receivedRequest using the \r\n delimeter

            requestLines = requestString.Split(new string[] { "\r\n" }, StringSplitOptions.None);

            // check that there is atleast 3 lines: Request line, Host Header, Blank line (usually 4 lines with the last empty line for empty content)

            if (requestLines.Length < 3)
            {
                return false;
            }

            // Parse Request line
            
            if (!ParseRequestLine()) // divide coming request into status-line,header-lines,blankline-content
            {
                return false;
            }

            // Validate blank line exists
            
            if (!ValidateBlankLine())
            {
                return false;
            }

            // Load header lines into HeaderLines dictionary
           
            if (!LoadHeaderLines())
            {
                return false;
            }

            return true;
        }

        private bool ParseRequestLine()
        {
            //Method   URI   HTTP-Version\r\n
            string[] status_line = requestLines[0].Split(' ');

            // check for existing method 
            if ("GET" != status_line[0]&&"POST"!= status_line[0]&& "HEAD" != status_line[0])
            {
                return false;
            }

            //HTTP/1.0
            string[] httpArr = status_line[2].Split('/'); // HTTP  1.0
            string[] version = httpArr[1].Split('.'); // 1 0
            string httpVer = httpArr[0] + version[0] + version[1]; // HTTP10

            // check for existing version 
            if ("HTTP10" != httpVer && "HTTP11" != httpVer && "HTTP09" != httpVer)
            {
                return false;
            }

            relativeURI = status_line[1];

            //Indicates whether the string is well-formed by attempting to construct a URI with the string and ensures that the string does not require further escaping.
            if (!ValidateIsURI(relativeURI))
            {
                return false;
            }

            //else
            return true;
        }

        private bool ValidateIsURI(string uri)
        {
            return Uri.IsWellFormedUriString(uri, UriKind.RelativeOrAbsolute);
        }

        private bool LoadHeaderLines()
        {
            //Date: Wed, 30 Jan 2002 12:48:17 EST
            for (int m = 1; m < requestLines.Length - 2; m++)
            {
                string[] H_lines = requestLines[m].Split(new string[] { ": " }, StringSplitOptions.None);
                headerLines.Add(H_lines[0], H_lines[1]);
            }
            return true;
        }

        private bool ValidateBlankLine()
        {
            //requestLines[requestLines.Length - 2] ===>>requestLines[4 - 2]===>>requestLines[2] ==>>blankline!= "" ===>blankline notfound
            if (requestLines[requestLines.Length - 2] != "")
                return false;
            return true;
        }

    }
}
