using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurrencyExchange
{
    // *****************************************
    // DON"T CHANGE CLASS NAME OR FUNCTION NAME
    // *****************************************
    public static class CurrencyExchange
    {
        public static int search(int N, bool[,] knows, int r, int c)
        {
            if (r == N) { return c; }
            if(knows[r, c]) { return search(N,knows,r+1,c); }
            else { return search(N, knows, r, c + 1); }
        
        }
        //Your Code is Here:
        //==================
        /// <summary>
        /// find the index of the USD Dollar $ with the smallest number of questions.
        /// </summary>
        /// <param name="N">Number of customers (N)</param>
        /// <param name="M">Number of currencies (M)</param>
        /// <param name="knows">N*M Matrix indicating whether customer i knows currency j or not</param>
        /// <returns>index of US Dollar</returns>
        public static int CheckUSD(int N, int M, bool[,] knows)
        {
            //knows[P,C]=true if person P knows currency C and knows[P,C]=false if person P doesn't know Currency C.
            int customer = 0,currency = 0; // currency
            while (customer < N) //Customers
            {
                if (knows[customer, currency]){ customer++;}//Rows++
                else{ currency++;}//columns++
            }
            return currency;
         } 
    }
}
