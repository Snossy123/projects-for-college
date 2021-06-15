package clinic;

import javax.swing.*;

abstract public class Person extends JFrame
{ 
    // private attributes
     private String person_name , person_address , person_mobile ;

   //  getter

    public Person()
    {
    }

     public String getPerson_name()
    {
        return person_name;
    }

    public String getPerson_address() 
    {
        return person_address;
    }

    public String getPerson_mobile() 
    {
        return person_mobile;
    }
    
    // setter

   //Person_name
    public void setPerson_name(String person_name) 
    {
         for(int i=0;i<person_name.length();++i)
        {
            if(person_name.charAt(i)=='@' ||person_name.charAt(i)=='#' || person_name.charAt(i)=='$'
                   || person_name.charAt(i)=='%'||person_name.charAt(i)=='&' || person_name.charAt(i)=='*')
            {
             System.out.println("invalid input for person_name , it can't contine any special character ");
             break;
            }
             else
            {
           this.person_name = person_name;
            }
        }
        
    }
    //Person_address
    public void setPerson_address(String person_address) 
    {
         for(int i=0;i<person_address.length();++i)
        {
            if(person_address.charAt(i)=='@' ||person_address.charAt(i)=='#' || person_address.charAt(i)=='$'
                   || person_address.charAt(i)=='%'||person_address.charAt(i)=='&' || person_address.charAt(i)=='*')
            {
             System.out.println("invalid input for person_address , it can't contine any special character ");
             break;
            }
             else
            {
            this.person_address = person_address;
            }
        }
        
    }
    //Person_mobile
    public void setPerson_mobile(String person_mobile) 
    {
        for(int i=0;i<person_mobile.length();++i)
        {
            if(person_mobile.charAt(i) < 0 || person_mobile.charAt(i) == '@' ||  person_mobile.charAt(i) == '$' || person_mobile.charAt(i) == '#'
                    || person_mobile.charAt(i) == '%'|| person_mobile.charAt(i) == '&'|| person_mobile.charAt(i) == '*')
                {
                    System.out.println("invalid input for person_mobile , it must be positive numbers ");
                    break;
                }
            else
                {
                    this.person_mobile = person_mobile;
                }
        }
          
    }
        // paramterized constructor
   public Person( String person_name, String person_address, String person_mobile)
    { 
          //person_name
            
             for(int i=0;i<person_name.length();++i)
            {
                if(person_name.charAt(i)=='@' ||person_name.charAt(i)=='#' || person_name.charAt(i)=='$'
                       || person_name.charAt(i)=='%'||person_name.charAt(i)=='&' || person_name.charAt(i)=='*')
                    {
                     System.out.println("invalid input for person_name , it can't contine any special character ");
                     break;
                    }
                 else
                    {
                     this.person_name = person_name;
                    }
            }
            //person_address
              for(int i=0;i<person_address.length();++i)
           {
            if(person_address.charAt(i)=='@' ||person_address.charAt(i)=='#' || person_address.charAt(i)=='$'
                   || person_address.charAt(i)=='%'||person_address.charAt(i)=='&' || person_address.charAt(i)=='*')
                {
                 System.out.println("invalid input for person_address , it can't contine any special character ");
                 break;
                }
             else
                {
                this.person_address = person_address;
                }
            }
      
              //person_mobile
              for(int i=0;i<person_mobile.length();++i)
            {
                if(person_mobile.charAt(i) < 0 || person_mobile.charAt(i) == '@' ||  person_mobile.charAt(i) == '$' || person_mobile.charAt(i) == '#'
                        || person_mobile.charAt(i) == '%'|| person_mobile.charAt(i) == '&'|| person_mobile.charAt(i) == '*')
                    {
                        System.out.println("invalid input for person_mobile , it must be positive numbers ");
                        break;
                    }
                else
                    {
                        this.person_mobile = person_mobile;
                    }
           }
          
    }


}