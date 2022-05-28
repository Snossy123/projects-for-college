using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace IsATree
{
    public class CheckTree
    {
        //===============================================================================
        //Your Code is Here:
        public static bool IsTree(string[] vertices, List<KeyValuePair<string, string>> edges)
        {
            //Declarations 
            Dictionary<string, List<string>> adl = new Dictionary<string, List<string>>();
            Dictionary<string, bool> V = new Dictionary<string, bool>();
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;

            while (count1 < edges.Count)
            {
                if (!(adl.ContainsKey(edges[count1].Key))){ adl.Add(edges[count1].Key, new List<string>()); }
                adl[edges[count1].Key].Add(edges[count1].Value);
                count1++;
            }
           
            while (count2 < vertices.Length)
            {
                if (!(V.ContainsKey(vertices[count2]))) { V.Add(vertices[count2], false); }
                V[vertices[count2]] = (false);
                count2++;
            }
            for (count3=0; count3 < vertices.Length; count3++) {
                if (D_F_S( vertices[count3], V, adl)) { return false; }
            }
            return true;
         }

        //===============================================================================
        private static bool D_F_S(string root, Dictionary<string, bool> V ,Dictionary<string, List<string>> adl)
        {
            if (V[root])
            {
                return true;
            }
            V[root] = true;

            if (adl.ContainsKey(root))
            {
                foreach (var i in adl[root])
                {
                    if (D_F_S( i, V,adl)) { return true; }
                }
            }
            V[root] = false;

            return V[root];
        }
        //===============================================================================
    }
}
