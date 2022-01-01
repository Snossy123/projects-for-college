using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.IO;

namespace HTTPServer
{
    class Server
    {
        Socket serverSocket;
        public Server(int portNumber, string redirectionMatrixPath)
        {
            //TODO: call this.LoadRedirectionRules passing redirectionMatrixPath to it
            LoadRedirectionRules(redirectionMatrixPath);
            //TODO: initialize this.serverSocket
            serverSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            IPEndPoint Host = new IPEndPoint(IPAddress.Any, portNumber);
            serverSocket.Bind(Host);
            Console.WriteLine("Listening........");
        }

        public void StartServer()
        {
            // TODO: Listen to connections, with large backlog.
            serverSocket.Listen(10000);
            // TODO: Accept connections in while loop and start a thread for each connection on function "Handle Connection"
            
            while (true)
            {
                
                //TODO: accept connections and start thread for each accepted connection.
                Socket Client_Socket = serverSocket.Accept();
                Console.WriteLine("New Client Accepted : {0}", Client_Socket.RemoteEndPoint);
                Thread NewThread = new Thread(new ParameterizedThreadStart(HandleConnection));
                NewThread.Start(Client_Socket);
               

            }   
        }

        public void HandleConnection(object obj)
        {
            // TODO: Create client socket 
            Socket CS = (Socket)obj;
            // set client socket ReceiveTimeout = 0 to indicate an infinite time-out period
            CS.ReceiveTimeout = 0;
            // TODO: receive requests in while true until remote client closes the socket.
            while (true)
            {
                try
                {
                    // TODO: Receive request
                    byte[] received_message = new byte[1024];
                    int ReceivedLen = CS.Receive(received_message);
                    // TODO: break the while loop if receivedLen==0
                    if(ReceivedLen == 0)
                    {
                        Console.WriteLine("Client : {0} ended the connection ", CS.RemoteEndPoint);
                        break;
                    }
                    // TODO: Create a Request object using received request string
                    Request data = new Request(Encoding.ASCII.GetString(received_message));
                    // TODO: Call HandleRequest Method that returns the response
                    Response response = HandleRequest(data);
                    // TODO: Send Response back to client
                    CS.Send(Encoding.ASCII.GetBytes(response.ResponseString));
                   

                }
                catch (Exception ex)
                {
                    // TODO: log exception using Logger class
                    Logger.LogException(ex);
                }
                

            }
            // TODO: close client socket
            CS.Close();
        }
         
        Response HandleRequest(Request request)
        {
            try
            {
                //TODO: check for bad request 
                if (!request.ParseRequest())
                {
                    string default_page = LoadDefaultPage(Configuration.BadRequestDefaultPageName);
                    Response resp = new Response(StatusCode.BadRequest, default_page, "400 Bad Request","");
                    return resp;
                }
                //TODO: map the relativeURI in request to get the physical path of the resource
                String content = LoadDefaultPage(request.relativeURI);
                //TODO: check for redirect
                String get_redpath = GetRedirectionPagePathIFExist(request.relativeURI);
                if (get_redpath != "")
                {
                    string get_load_redpath = LoadDefaultPage('/'+ get_redpath);
                    Response resp = new Response(StatusCode.Redirect, "text/html", get_load_redpath, GetRedirectionPagePathIFExist(request.relativeURI));
                    return resp;
                }
                //TODO: check file exists
                if (content == "")
                {
                    string not_found = LoadDefaultPage(Configuration.NotFoundDefaultPageName);
                    Response resp = new Response(StatusCode.NotFound, "text/html", not_found, "");
                    return resp;
                }
                //TODO: read the physical file
                // Create OK response
                Response respon = new Response(StatusCode.OK , "text/html", content, "");
                return respon;
            }
            catch (Exception ex)
            {
                // TODO: log exception using Logger class
                // TODO: in case of exception,
                Logger.LogException(ex);
                string int_error_default_page = LoadDefaultPage(Configuration.InternalErrorDefaultPageName);
                Response resp_ = new Response(StatusCode.InternalServerError, "text/html", int_error_default_page, "");
                return resp_;
            }
        }

        private string GetRedirectionPagePathIFExist(string relativePath)
        {
            // using Configuration.RedirectionRules return the redirected page path if exists else returns empty
            string[] rel_path = relativePath.Split('/');
            foreach (var t in Configuration.RedirectionRules)
            {
                ///blah/foo
                if (t.Key == rel_path[1])
                {
                    return t.Value;
                }
            }
            return string.Empty;
        }

        private string LoadDefaultPage(string defaultPageName)
        {
            // TODO: check if filepath not exist log exception using Logger class and return empty string
            string f_Path = Configuration.RootPath + defaultPageName ;
           
            if (!File.Exists(f_Path))
            {
                Exception ex = new Exception(Configuration.NotFoundDefaultPageName);
                Logger.LogException(ex);
                return string.Empty;
            }
            // else read file and return its content
            else
            {
                string file_ = File.ReadAllText(f_Path);
                return file_;
            }
        }

        private void LoadRedirectionRules(string filePath)
        {
            try
            {
                // TODO: using the filepath paramter read the redirection rules from file 
                string fil_e = File.ReadAllText(filePath);
                //aboutus.html,aboutus2.html
                string[] redpath = fil_e.Split(','); //aboutus.html
                                                     //aboutus2.html
                // then fill Configuration.RedirectionRules dictionary 
                Configuration.RedirectionRules.Add(redpath[0], redpath[1]);
            }
            catch (Exception ex)
            {
                // TODO: log exception using Logger class
                Logger.LogException(ex);
                Environment.Exit(1);
            }
        }
    }
}
